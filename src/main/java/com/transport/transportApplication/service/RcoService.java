package com.transport.transportApplication.service;

import com.transport.transportApplication.model.Rco;
import com.transport.transportApplication.model.RecogidaDTO;
import com.transport.transportApplication.model.TableResponse;
import com.transport.transportApplication.repository.DateFilteredTableRepository;
import com.transport.transportApplication.repository.RhoTablesRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@ConditionalOnProperty(prefix = "bidea.datasource", name = "url")
public class RcoService {

    private static final DateTimeFormatter DATE_FORMAT = new DateTimeFormatterBuilder()
            .appendPattern("ddMMuuuu")
            .toFormatter(Locale.ROOT)
            .withResolverStyle(ResolverStyle.STRICT);

    private static final String TABLE_NAME = "RCO";
    private static final String TABLE_NAME_HISTO = "RCH";
    private static final String ORDER_COLUMN = "KEYRCO";

    private final DateFilteredTableRepository dateFilteredTableRepository;
    private final RhoTablesRepository rhoTablesRepository;


    public RcoService(DateFilteredTableRepository dateFilteredTableRepository,  RhoTablesRepository rhoTablesRepository) {
        this.dateFilteredTableRepository = dateFilteredTableRepository;
        this.rhoTablesRepository = rhoTablesRepository;
    }

    public RecogidaDTO getRecogidaCompleta(String recogida) {
        final String filterColumn="keyrco";
        if (recogida == null || recogida.isBlank()) {
            throw new IllegalArgumentException("debe indicar una recogida");
        }

        //Consulto tanto en RCO como en su histórico RCH
        List<Map<String, Object>> rcoRows = rhoTablesRepository.findAllAsMap("RCO", filterColumn,recogida);
        rcoRows.addAll(rhoTablesRepository.findAllAsMap("RCH", filterColumn,recogida));

        //Elimino los duplicados si los hubiera
        Map<Object, Map<String, Object>> uniqueRows = new LinkedHashMap<>();
        for (Map<String, Object> row : rcoRows) {
            uniqueRows.putIfAbsent(row.get(ORDER_COLUMN), row);
        }
        rcoRows = new ArrayList<>(uniqueRows.values());

        Map<String, Object> rho = rcoRows.isEmpty() ? null : rcoRows.get(0);

        RecogidaDTO.Detalle detalle = new RecogidaDTO.Detalle(
                rhoTablesRepository.findAllAsMap("RCL", filterColumn, recogida),
                rhoTablesRepository.findAllAsMap("RCO_INCIDENCIAS", filterColumn,recogida),
                rhoTablesRepository.findAllAsMap("RCO_REF", filterColumn,recogida));

        return new RecogidaDTO(rho, detalle);
    }

    public TableResponse<Map<String, Object>> getCountRco(String fromDate, String toDate){
        LocalDate start = parseDate(fromDate, "fromDate");
        LocalDate end = toDate == null || toDate.isBlank() ? start : parseDate(toDate, "toDate");

        if (end.isBefore(start)) {throw new IllegalArgumentException("Fechas incorrectas");}

        List<String> tablas = List.of(TABLE_NAME, TABLE_NAME_HISTO);
        long totalRecords = dateFilteredTableRepository.countByDateRange(tablas, ORDER_COLUMN, start, end);

        return new TableResponse<>(
                new TableResponse.Summary(
                        TABLE_NAME + " / " + TABLE_NAME_HISTO,
                        totalRecords==0 ? "ERROR" : "SUCCESS",
                        totalRecords==0 ? "No se han encontrado datos" : "Datos encontrados",
                        null,
                        null,
                        totalRecords,
                        true
                        ),null);
    }

    public TableResponse<Rco> getRco(String fromDate, String toDate, int numReg, int page) {
        LocalDate start = parseDate(fromDate, "fromDate");
        LocalDate end = toDate == null || toDate.isBlank() ? start : parseDate(toDate, "toDate");
        if (end.isBefore(start)) {
            throw new IllegalArgumentException("toDate debe ser igual o posterior a fromDate");
        }
        if (numReg < 1) {
            throw new IllegalArgumentException("numReg debe ser mayor que cero");
        }
        if (page < 1) {
            throw new IllegalArgumentException("offset debe ser un numero de pagina mayor o igual que 1");
        }
        try {
            Math.multiplyExact(page, numReg);
        } catch (ArithmeticException exception) {
            throw new IllegalArgumentException("La pagina solicitada excede el limite permitido", exception);
        }

        List<Rco> orders = dateFilteredTableRepository.findByDateRange(
                TABLE_NAME, ORDER_COLUMN, Rco.class, start, end, page, numReg);

        orders.addAll(dateFilteredTableRepository.findByDateRange(
                TABLE_NAME_HISTO, ORDER_COLUMN, Rco.class, start, end, page, numReg));


        List<Rco> ordersClean = orders.stream()
                .collect(Collectors.toMap(
                        Rco::getKeyrco,
                        Function.identity(),
                        (existente, nuevo) -> nuevo
                ))
                .values()
                .stream()
                .toList();

        List<String> tablas = List.of(TABLE_NAME,TABLE_NAME_HISTO);
        long totalRecords= dateFilteredTableRepository.countByDateRange(tablas, ORDER_COLUMN, start, end);

        String description = ordersClean.isEmpty()
                ? "No se encontraron recogidas para el rango de fechas en la página indicada"
                : "Consulta realizada correctamente";

        return new TableResponse<>(
                new TableResponse.Summary(
                        TABLE_NAME,
                        ordersClean.isEmpty() ? "ERROR" : "SUCCESS",
                        description,
                        page,
                        (long) ordersClean.size(),
                        totalRecords,
                        numReg != ordersClean.size()
                ),
                ordersClean);
    }

    private LocalDate parseDate(String value, String parameterName) {
        if (value == null || !value.matches("\\d{8}")) {
            throw new IllegalArgumentException(parameterName + " debe tener formato DDMMYYYY y ser una fecha valida");
        }
        try {
            return LocalDate.parse(value, DATE_FORMAT);
        } catch (DateTimeParseException exception) {
            throw new IllegalArgumentException(parameterName + " debe tener formato DDMMYYYY y ser una fecha valida");
        }
    }
}
