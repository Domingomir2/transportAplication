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
        String sql = """
                SELECT *
                FROM (
                    SELECT all_rows.*
                    FROM (
                        SELECT 
                """ + columnsFor(tableName) + """
                        FROM 
                """ + table + """
                        WHERE EXPEDICION = ?
                    ) all_rows
                )
                """;

        return jdbcTemplate.query(
                sql,
                BeanPropertyRowMapper.newInstance(rowType),
                expedicion);
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
        String sql = """
                SELECT *
                FROM (
                    SELECT all_rows.*
                    FROM (
                        SELECT 
                """ + columns + """
                        FROM 
                """ + table + """
                        WHERE 
                """ + validatedFilterColumn + """
                        = ?
                    ) all_rows
                )
                """;

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
        String sql = """
                SELECT COUNT(*)
                FROM 
                """ + qualifiedTableName(tableName) + """
                WHERE EXPEDICION = ?
                """;

        Long count = jdbcTemplate.queryForObject(
                sql,
                Long.class,
                expedicion);
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
        String sql = """
                select expedicion
                from REF
                where referencia=?
                and tipo='DOM'
                order by fecha_e desc
                fetch first row only
                """;
        return jdbcTemplate.queryForObject(sql, String.class, ref);
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

    public List<java.util.Map<String, Object>> findAllAsMapByExpeditions(
            String tableName,
            String filterColumn,
            List<String> expeditions) {
        if (expeditions == null || expeditions.isEmpty()) {
            return Collections.emptyList();
        }

        String table = qualifiedTableName(tableName);
        String columns = columnsFor(tableName);
        String validatedFilterColumn = validatedIdentifier(filterColumn);
        String placeholders = String.join(", ", Collections.nCopies(expeditions.size(), "?"));
        String sql = """
                SELECT *
                FROM (
                    SELECT all_rows.*
                    FROM (
                        SELECT 
                """ + columns + """
                        FROM 
                """ + table + """
                        WHERE 
                """ + validatedFilterColumn + """
                        IN (
                """ + placeholders + """
                        )
                    ) all_rows
                )
                """;

        return jdbcTemplate.query(sql, new ColumnMapRowMapper(), expeditions.toArray());
    }

    public java.util.Map<String, String> findRealExpeditions(List<String> references) {
        if (references == null || references.isEmpty()) {
            return Collections.emptyMap();
        }

        String placeholders = String.join(", ", Collections.nCopies(references.size(), "?"));
        // NOSONAR - The table name is fixed to REF and the schema has already been validated.
        String sql = """
                SELECT referencia, expedicion
                FROM (
                    SELECT referencia,
                           expedicion,
                           ROW_NUMBER() OVER (
                               PARTITION BY referencia
                               ORDER BY fecha_e DESC
                           ) rn
                    FROM 
                """ + qualifiedTableName("REF") + """
                    WHERE tipo = 'DOM'
                      AND referencia IN (
                """ + placeholders + """
                      )
                )
                WHERE rn = 1
                """;

        java.util.Map<String, String> result = new java.util.HashMap<>();
        jdbcTemplate.query(sql, rs -> { // NOSONAR - Identifiers are validated and expedition values are bound.
            result.put(rs.getString("referencia"), rs.getString("expedicion"));
        }, references.toArray());
        return result;
    }
}
