package com.transport.transportApplication.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

@Repository
@ConditionalOnProperty(prefix = "bidea.datasource", name = "url")
public class AllTableRepository {

    private static final Pattern ORACLE_IDENTIFIER = Pattern.compile("[A-Za-z][A-Za-z0-9_$#]*");

    private final JdbcTemplate jdbcTemplate;
    private final String schema;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ConcurrentHashMap<String, String> columnsByTable = new ConcurrentHashMap<>();

    public AllTableRepository(
            JdbcTemplate jdbcTemplate,
            @Value("${bidea.datasource.schema:}") String schema) {
        this.jdbcTemplate = jdbcTemplate;
        this.schema = schema;
        if (schema != null && !schema.isBlank() && !ORACLE_IDENTIFIER.matcher(schema).matches()) {
            throw new IllegalArgumentException("El esquema Oracle de BIDEA no es valido");
        }
    }

    public <T> List<T> findAll(
            String tableName,
            String orderColumn,
            Class<T> rowType,
            int page,
            int pageSize) {
        String table = qualifiedTableName(tableName);
        String orderBy = validatedIdentifier(orderColumn);
        int lastRow = Math.multiplyExact(page, pageSize);
        int firstRow = Math.multiplyExact(page - 1, pageSize);
        String sql ="";
        sql = """
                SELECT *
                FROM (
                    SELECT all_rows.*, ROWNUM row_num
                    FROM (
                        SELECT %s
                        FROM %s
                        ORDER BY %s
                    ) all_rows
                    WHERE ROWNUM <= ?
                )
                WHERE row_num > ?
                """.formatted(columnsFor(tableName), table, orderBy);

        return jdbcTemplate.query(
                sql,
                BeanPropertyRowMapper.newInstance(rowType),
                lastRow,
                firstRow);
    }

    public List<java.util.Map<String, Object>> findAllAsMap(
            String tableName,
            int page,
            int pageSize) {
        String table = qualifiedTableName(tableName);
        String columns = columnsFor(tableName);
        int lastRow = Math.multiplyExact(page, pageSize);
        int firstRow = Math.multiplyExact(page - 1, pageSize);

        List<Object> params = new ArrayList<>();

        String sql ="";

        if (pageSize>0){
            sql = """
                SELECT *
                FROM (
                    SELECT all_rows.*, ROWNUM row_num
                    FROM (
                        SELECT %s
                        FROM %s
                        ORDER BY %s
                    ) all_rows
                    WHERE ROWNUM <= ?
                )
                WHERE row_num > ?
                """.formatted(columns, table, columns);

            params.add(lastRow);
            params.add(firstRow);
        }
        else
        {
            sql = """
                SELECT *
                FROM (
                    SELECT all_rows.*, ROWNUM row_num
                    FROM (
                        SELECT %s
                        FROM %s
                        ORDER BY %s
                    ) all_rows
                )
                """.formatted(columns, table, columns);
        }


        return jdbcTemplate.query(
                sql,
                new ColumnMapRowMapper(),
                params.toArray());
    }

    public long countAll(String tableName) {
        String sql ="";
        sql = """
                SELECT COUNT(*)
                FROM %s
                """.formatted(qualifiedTableName(tableName));

        Long count = jdbcTemplate.queryForObject(
                sql,
                Long.class);
        return count == null ? 0 : count;
    }

    private String columnsFor(String tableName) {
        String validatedTableName = validatedIdentifier(tableName);
        return columnsByTable.computeIfAbsent(validatedTableName, this::loadColumns);
    }

    public boolean supportsTable(String tableName) {
        String validatedTableName = validatedIdentifier(tableName);
        return new ClassPathResource("data-fields/" + validatedTableName + ".json").exists();
    }

    private String loadColumns(String tableName) {
        ClassPathResource manifest = new ClassPathResource("data-fields/" + tableName + ".json");
        if (!manifest.exists()) {
            throw new IllegalArgumentException("No existe el manifiesto de campos para la tabla " + tableName);
        }
        try (var input = manifest.getInputStream()) {
            List<String> columns = objectMapper.readValue(input, new TypeReference<>() {});
            if (columns.isEmpty() || columns.stream().anyMatch(column -> !ORACLE_IDENTIFIER.matcher(column).matches())) {
                throw new IllegalStateException(
                        "El manifiesto de columnas de " + tableName + " contiene identificadores no validos");
            }
            return String.join(", ", columns);
        } catch (IOException exception) {
            throw new IllegalStateException("No se pudo leer el manifiesto de campos de " + tableName, exception);
        }
    }

    private String qualifiedTableName(String tableName) {
        String validatedTableName = validatedIdentifier(tableName);
        return schema == null || schema.isBlank()
                ? validatedTableName
                : schema + "." + validatedTableName;
    }

    private String validatedIdentifier(String identifier) {
        if (identifier == null || !ORACLE_IDENTIFIER.matcher(identifier).matches()) {
            throw new IllegalArgumentException("El identificador de tabla o columna Oracle no es valido");
        }
        return identifier;
    }
}
