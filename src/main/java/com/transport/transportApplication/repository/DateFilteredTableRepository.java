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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

@Repository
@ConditionalOnProperty(prefix = "bidea.datasource", name = "url")
public class DateFilteredTableRepository {

    private static final Pattern ORACLE_IDENTIFIER = Pattern.compile("[A-Za-z][A-Za-z0-9_$#]*");
    private static final DateTimeFormatter ORACLE_DATE_FORMAT = DateTimeFormatter.ofPattern("ddMMyyyy");

    private final JdbcTemplate jdbcTemplate;
    private final String schema;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ConcurrentHashMap<String, String> columnsByTable = new ConcurrentHashMap<>();

    public DateFilteredTableRepository(
            JdbcTemplate jdbcTemplate,
            @Value("${bidea.datasource.schema:}") String schema) {
        this.jdbcTemplate = jdbcTemplate;
        this.schema = schema;
        if (schema != null && !schema.isBlank() && !ORACLE_IDENTIFIER.matcher(schema).matches()) {
            throw new IllegalArgumentException("El esquema Oracle de BIDEA no es valido");
        }
    }

    public <T> List<T> findByDateRange(
            String tableName,
            String orderColumn,
            Class<T> rowType,
            LocalDate fromDate,
            LocalDate toDate,
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
                    SELECT date_rows.*, ROWNUM row_num
                    FROM (
                        SELECT %s
                        FROM %s
                        WHERE FECHA >= TO_DATE(?, 'DDMMYYYY')
                          AND FECHA < TO_DATE(?, 'DDMMYYYY') + 1
                        ORDER BY FECHA, %s
                    ) date_rows
                    WHERE ROWNUM <= ?
                )
                WHERE row_num > ?
                """.formatted(columnsFor(tableName), table, orderBy);

        return jdbcTemplate.query(
                sql,
                BeanPropertyRowMapper.newInstance(rowType),
                fromDate.format(ORACLE_DATE_FORMAT),
                toDate.format(ORACLE_DATE_FORMAT),
                lastRow,
                firstRow);
    }

    public long countByDateRange(String tableName, LocalDate fromDate, LocalDate toDate) {
        String sql ="";
        sql = """
                SELECT COUNT(*)
                FROM %s
                WHERE FECHA >= TO_DATE(?, 'DDMMYYYY')
                  AND FECHA < TO_DATE(?, 'DDMMYYYY') + 1
                """.formatted(qualifiedTableName(tableName));

        Long count = jdbcTemplate.queryForObject(
                sql,
                Long.class,
                fromDate.format(ORACLE_DATE_FORMAT),
                toDate.format(ORACLE_DATE_FORMAT));
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
