package com.transport.transportApplication.service;

import com.transport.transportApplication.model.TableResponse;
import com.transport.transportApplication.repository.AllTableRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

@Service
@ConditionalOnProperty(prefix = "bidea.datasource", name = "url")
public class AllTableService {

    private static final Set<String> DEDICATED_TABLES = Set.of("RHO", "RCO");

    private final AllTableRepository allTableRepository;

    public AllTableService(AllTableRepository allTableRepository) {
        this.allTableRepository = allTableRepository;
    }

    public TableResponse<Map<String, Object>> getTable(
            String tableName,
            int pageSize,
            int page) {
        String normalizedTableName = normalizeTableName(tableName);
        if (DEDICATED_TABLES.contains(normalizedTableName)) {
            throw new IllegalArgumentException(
                    "La tabla " + normalizedTableName + " tiene un servicio especifico");
        }
        if (!allTableRepository.supportsTable(normalizedTableName)) {
            throw new IllegalArgumentException("La tabla solicitada no esta incluida en los manifiestos");
        }
        if (page < 1) {
            throw new IllegalArgumentException("offset debe ser un numero de pagina mayor o igual que 1");
        }
        try {
            Math.multiplyExact(page, pageSize);
        } catch (ArithmeticException exception) {
            throw new IllegalArgumentException("La pagina solicitada excede el limite permitido", exception);
        }

        List<Map<String, Object>> elements =
                allTableRepository.findAllAsMap(normalizedTableName, page, pageSize);
        long totalRecords = allTableRepository.countAll(normalizedTableName);
        String description = elements.isEmpty()
                ? "No se encontraron registros en la pagina indicada"
                : "Consulta realizada correctamente";

        return new TableResponse<>(
                new TableResponse.Summary(
                        normalizedTableName,
                        elements.isEmpty() ? "ERROR" : "SUCCESS",
                        description,
                        page,
                        (long) elements.size(),
                        totalRecords,
                        pageSize != elements.size()
                        ),
                elements);
    }

    private String normalizeTableName(String tableName) {
        if (tableName == null || !tableName.matches("[A-Za-z][A-Za-z0-9_$#]*")) {
            throw new IllegalArgumentException("El nombre de tabla solicitado no es valido");
        }
        return tableName.toUpperCase(Locale.ROOT);
    }
}
