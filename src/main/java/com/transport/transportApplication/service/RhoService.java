package com.transport.transportApplication.service;

import com.transport.transportApplication.model.Rho;
import com.transport.transportApplication.model.TableResponse;
import com.transport.transportApplication.repository.DateFilteredTableRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Writer;
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
public class RhoService {

    private static final DateTimeFormatter DATE_FORMAT = new DateTimeFormatterBuilder()
            .appendPattern("ddMMuuuu")
            .toFormatter(Locale.ROOT)
            .withResolverStyle(ResolverStyle.STRICT);

    private static final String TABLE_NAME = "RHO";
    private static final String ORDER_COLUMN = "EXPEDICION";

    private final DateFilteredTableRepository dateFilteredTableRepository;
    private final RhoTablesService rhoTablesService;
    private static final String FROM_DATE = "fromDate";
    private static final String TO_DATE = "fromDate";

    public RhoService(
            DateFilteredTableRepository dateFilteredTableRepository,
            RhoTablesService rhoTablesService) {
        this.dateFilteredTableRepository = dateFilteredTableRepository;
        this.rhoTablesService = rhoTablesService;
    }

    public TableResponse<Rho> getRho(String fromDate, String toDate, int numReg, int page) {
        LocalDate start = parseDate(fromDate, FROM_DATE);
        LocalDate end = toDate == null || toDate.isBlank() ? start : parseDate(toDate, TO_DATE);
        validatePagination(start, end, numReg, page);

        List<Rho> expeditions = findPage(start, end, numReg, page);
        long totalRecords = dateFilteredTableRepository.countByDateRange(TABLE_NAME, start, end);
        String description = expeditions.isEmpty()
                ? "No se encontraron expediciones para el rango de fechas en la página indicada"
                : "Consulta realizada correctamente";

        return new TableResponse<>(
                new TableResponse.Summary(
                        TABLE_NAME,
                        expeditions.isEmpty() ? "ERROR" : "SUCCESS",
                        description,
                        page,
                        (long) expeditions.size(),
                        totalRecords,
                        expeditions.size() != numReg
                        ),
                expeditions);
    }

    public TableResponse<Map<String, Object>> getCountRho(String fromDate, String toDate){
        LocalDate start = parseDate(fromDate, FROM_DATE);
        LocalDate end = toDate == null || toDate.isBlank() ? start : parseDate(toDate, TO_DATE);

        if (end.isBefore(start)) {throw new IllegalArgumentException("Fechas incorrectas");}

        List<String> tablas = List.of(TABLE_NAME);
        long totalRecords = dateFilteredTableRepository.countByDateRange(tablas, ORDER_COLUMN, start, end);

        return new TableResponse<>(
                new TableResponse.Summary(
                        TABLE_NAME,
                        totalRecords==0 ? "ERROR" : "SUCCESS",
                        totalRecords==0 ? "No se han encontrado datos" : "Datos encontrados",
                        null,
                        null,
                        totalRecords,
                        true
                ),null);
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

    public void writeExpedicionCompletaSqlFechas(
            String fromDate,
            String toDate,
            int numReg,
            int offset,
            Writer writer) throws IOException {
        LocalDate start = parseDate(fromDate, FROM_DATE);
        LocalDate end = toDate == null || toDate.isBlank() ? start : parseDate(toDate, TO_DATE);
        validatePagination(start, end, numReg, offset);

        List<Rho> expeditions = findPage(start, end, numReg, offset);
        if (expeditions.isEmpty()) {
            writer.write("NO EXISTEN EXPEDICIONES");
            return;
        }

        rhoTablesService.writeExpedicionesCompletaSql(expeditions, writer);
    }

    private void validatePagination(LocalDate start, LocalDate end, int numReg, int page) {
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
    }

    private List<Rho> findPage(LocalDate start, LocalDate end, int numReg, int page) {
        return dateFilteredTableRepository.findByDateRange(
                TABLE_NAME, ORDER_COLUMN, Rho.class, start, end, page, numReg);
    }
}
