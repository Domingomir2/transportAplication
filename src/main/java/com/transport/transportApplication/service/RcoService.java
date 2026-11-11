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
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
@ConditionalOnProperty(prefix = "bidea.datasource", name = "url")
public class RcoService {

    private static final DateTimeFormatter DATE_FORMAT = new DateTimeFormatterBuilder()
            .appendPattern("ddMMuuuu")
            .toFormatter(Locale.ROOT)
            .withResolverStyle(ResolverStyle.STRICT);

    private static final String TABLE_NAME = "RCO";
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

        List<Map<String, Object>> rcoRows = rhoTablesRepository.findAllAsMap("RCO", filterColumn,recogida);
        rcoRows.addAll(rhoTablesRepository.findAllAsMap("RCH", filterColumn,recogida));

        Map<String, Object> rho = rcoRows.isEmpty() ? null : rcoRows.get(0);

        RecogidaDTO.Detalle detalle = new RecogidaDTO.Detalle(
                rhoTablesRepository.findAllAsMap("RCL", filterColumn, recogida),
                rhoTablesRepository.findAllAsMap("RCO_INCIDENCIAS", filterColumn,recogida),
                rhoTablesRepository.findAllAsMap("RCO_REF", filterColumn,recogida));

        return new RecogidaDTO(rho, detalle);
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
        long totalRecords = dateFilteredTableRepository.countByDateRange(TABLE_NAME, start, end);
        String description = orders.isEmpty()
                ? "No se encontraron recogidas para el rango de fechas en la página indicada"
                : "Consulta realizada correctamente";

        return new TableResponse<>(
                new TableResponse.Summary(
                        TABLE_NAME,
                        orders.isEmpty() ? "ERROR" : "SUCCESS",
                        description,
                        page,
                        orders.size(),
                        totalRecords),
                orders);
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
