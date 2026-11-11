package com.transport.transportApplication.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

@Repository
@ConditionalOnProperty(prefix = "bidea.datasource", name = "url")
public class RhoFilteredTableRepository {

    private static final Pattern ORACLE_IDENTIFIER = Pattern.compile("[A-Za-z][A-Za-z0-9_$#]*");

    private final JdbcTemplate jdbcTemplate;
    private final String schema;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ConcurrentHashMap<String, String> columnsByTable = new ConcurrentHashMap<>();

    public RhoFilteredTableRepository(
            JdbcTemplate jdbcTemplate,
            @Value("${bidea.datasource.schema:}") String schema) {
        this.jdbcTemplate = jdbcTemplate;
        this.schema = schema;
        if (schema != null && !schema.isBlank() && !ORACLE_IDENTIFIER.matcher(schema).matches()) {
            throw new IllegalArgumentException("El esquema Oracle de BIDEA no es valido");
        }
    }

    public <T> List<T> findByExpedicion(
            String tableName,
            String expedicion,
            String orderColumn,
            Class<T> rowType,
            int page,
            int pageSize) {
        String table = qualifiedTableName(tableName);
        String expedicionFilter = validatedIdentifier(expedicion);
        String orderBy = validatedIdentifier(orderColumn);
        int lastRow = Math.multiplyExact(page, pageSize);
        int firstRow = Math.multiplyExact(page - 1, pageSize);
        String sql ="";
        sql = """
                SELECT *
                FROM (
                    SELECT expedicion_rows.*, ROWNUM row_num
                    FROM (
                        SELECT %s
                        FROM %s
                        WHERE EXPEDICION=%s
                        ORDER BY FECHA, %s
                    ) expedicion_rows
                    WHERE ROWNUM <= ?
                )
                WHERE row_num > ?
                """.formatted(columnsFor(tableName), table, expedicionFilter,orderBy);

        return jdbcTemplate.query(
                sql,
                BeanPropertyRowMapper.newInstance(rowType),
                lastRow,
                firstRow);
    }

    public long countByExpedicion(String tableName, String expedicion) {
        String sql ="";
        sql = """
                SELECT COUNT(*)
                FROM %s
                WHERE EXPEDICION=%s
                """.formatted(qualifiedTableName(tableName),expedicion);

        Long count = jdbcTemplate.queryForObject(
                sql,
                Long.class);
        return count == null ? 0 : count;
    }

    private String columnsFor(String tableName) {
        String validatedTableName = validatedIdentifier(tableName);
        return columnsByTable.computeIfAbsent(validatedTableName, this::loadColumns);
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
