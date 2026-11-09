package com.transport.transportApplication.service;

import com.transport.transportApplication.model.Rco;
import com.transport.transportApplication.model.RcoResponse;
import com.transport.transportApplication.repository.DateFilteredTableRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.List;
import java.util.Locale;

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

    public RcoService(DateFilteredTableRepository dateFilteredTableRepository) {
        this.dateFilteredTableRepository = dateFilteredTableRepository;
    }

    public RcoResponse getRco(String fromDate, String toDate, int numReg, int page) {
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

        return new RcoResponse(
                    new RcoResponse.Summary(orders.isEmpty() ? "ERROR": "SUCCESS",
                            description, page, totalRecords),
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
