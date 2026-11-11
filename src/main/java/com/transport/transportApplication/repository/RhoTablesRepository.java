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
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

@Repository
@ConditionalOnProperty(prefix = "bidea.datasource", name = "url")
public class RhoTablesRepository {

    private static final Pattern ORACLE_IDENTIFIER = Pattern.compile("[A-Za-z][A-Za-z0-9_$#]*");

    private final JdbcTemplate jdbcTemplate;
    private final String schema;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ConcurrentHashMap<String, String> columnsByTable = new ConcurrentHashMap<>();

    public RhoTablesRepository(
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
            Class<T> rowType,
            String expedicion) {
        String table = qualifiedTableName(tableName);
        String sql ="";
        sql = """
                SELECT *
                FROM (
                    SELECT all_rows.*
                    FROM (
                        SELECT %s
                        FROM %s
                        WHERE EXPEDICION =%s
                    ) all_rows
                )
                """.formatted(columnsFor(tableName), table, expedicion);

        return jdbcTemplate.query(
                sql,
                BeanPropertyRowMapper.newInstance(rowType));
    }

    public List<java.util.Map<String, Object>> findAllAsMap(
            String tableName,
            String expedicion) {
        return findAllAsMap(tableName, "EXPEDICION", expedicion);
    }

    public List<java.util.Map<String, Object>> findAllAsMap(
            String tableName,
            String filterColumn,
            String expedicion) {
        String table = qualifiedTableName(tableName);
        String columns = columnsFor(tableName);
        String validatedFilterColumn = validatedIdentifier(filterColumn);
        String sql ="";
        sql = """
                SELECT *
                FROM (
                    SELECT all_rows.*
                    FROM (
                        SELECT %s
                        FROM %s
                        WHERE %s = ?
                    ) all_rows
                )
                """.formatted(columns, table, validatedFilterColumn);

        //System.out.println(sql);

        return jdbcTemplate.query(
                sql,
                new ColumnMapRowMapper(),
                expedicion);
    }

    public List<java.util.Map<String, Object>> findAllCompletaAsMap(
            String tableName,
            String expedicion) {
        return findAllAsMap(tableName, expedicion);
    }

    public long countAll(String tableName, String expedicion) {
        String sql ="";
        sql = """
                SELECT COUNT(*)
                FROM %s
                WHERE EXPEDICION =%s
                """.formatted(qualifiedTableName(tableName),expedicion);

        Long count = jdbcTemplate.queryForObject(
                sql,
                Long.class);
        return count == null ? 0 : count;
    }

    public List<java.util.Map<String, Object>> findAllByRefAsMap(
            String tableName,
            String ref) {
        String expedicion = getRefDom(ref);

        if  (expedicion != null && !expedicion.isBlank()) {
            return findAllAsMap(tableName, expedicion);
        }
        else{
            return  Collections.emptyList();
        }
    }

    public long countAllByRef(String tableName, String ref) {
        String expedicion = getRefDom(ref);

        if  (expedicion != null && !expedicion.isBlank()) {
            return  0;
        }
        else{
            return countAll(tableName, expedicion);
        }
    }

    public String getRefDom(String ref) {
        String sql ="";
        sql = """
                select expedicion
                from REF
                where referencia='%s'
                and tipo='DOM'
                order by fecha_e desc
                fetch first row only
                """.formatted(ref);
        return jdbcTemplate.queryForObject(sql, String.class);
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
