package com.transport.transportApplication.service;

import com.transport.transportApplication.model.ExpedicionDTO;
import com.transport.transportApplication.model.TableResponse;
import com.transport.transportApplication.repository.RhoTablesRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

@Service
@ConditionalOnProperty(prefix = "bidea.datasource", name = "url")
public class RhoTablesService {

    //Aquí indicamos las tablas que se tienen que consultar de otra manera, en este caso, son las maestras
    //TODO - COMPLETAR LAS TABLAS MAESTRAS QUE FALTAN
    private static final Set<String> DEDICATED_TABLES = Set.of("ACT", "DEL", "EMB", "RCL","TST","TSV");

    private final RhoTablesRepository rhoTablesRepository;

    public RhoTablesService(RhoTablesRepository rhoTablesRepository) {
        this.rhoTablesRepository = rhoTablesRepository;
    }

    public ExpedicionDTO getExpedicionCompleta(String expedicion) {
        if (expedicion == null || expedicion.isBlank()) {
            throw new IllegalArgumentException("debe indicar una expedicion");
        }

        List<Map<String, Object>> rhoRows = rhoTablesRepository.findAllAsMap("RHO", expedicion);
        Map<String, Object> rho = rhoRows.isEmpty() ? null : rhoRows.get(0);

        ExpedicionDTO.Detalle detalle = new ExpedicionDTO.Detalle(
                rhoTablesRepository.findAllAsMap("ADR", "ADR_EXPEDICION", expedicion),
                rhoTablesRepository.findAllAsMap("HES", expedicion),
                rhoTablesRepository.findAllAsMap("HUELLA_CARBONO_DET", expedicion),
                rhoTablesRepository.findAllAsMap("IE_EDP", expedicion),
                rhoTablesRepository.findAllAsMap("REF", expedicion),
                rhoTablesRepository.findAllAsMap("REF_ADU_INFO", expedicion),
                rhoTablesRepository.findAllAsMap("REF_ADU_INFO_IMP", expedicion),
                rhoTablesRepository.findAllAsMap("REF_CLI", expedicion),
                rhoTablesRepository.findAllAsMap("RHO_COSTES_NAC", expedicion),
                rhoTablesRepository.findAllAsMap("RHO_IMPORTADOR", expedicion),
                rhoTablesRepository.findAllAsMap("RHO_INT", expedicion),
                rhoTablesRepository.findAllAsMap("RHO_PROC_ADU", expedicion),
                rhoTablesRepository.findAllAsMap("SA", expedicion),
                rhoTablesRepository.findAllAsMap("SA_INT", expedicion),
                rhoTablesRepository.findAllAsMap("XPCIGIC", expedicion));

        return new ExpedicionDTO(rho, detalle);
    }

    public TableResponse<Map<String, Object>> getTable(
            String tableName,
            String expedicion) {
        String normalizedTableName = normalizeTableName(tableName);
        if (DEDICATED_TABLES.contains(normalizedTableName)) {
            throw new IllegalArgumentException(
                    "La tabla " + normalizedTableName + " tiene un servicio especifico");
        }
        if (!rhoTablesRepository.supportsTable(normalizedTableName)) {
            throw new IllegalArgumentException("La tabla solicitada no esta incluida en los manifiestos");
        }
        if (expedicion.isEmpty() ) {
            throw new IllegalArgumentException("debe indicar una expedicion");
        }

        List<Map<String, Object>> elements =
                rhoTablesRepository.findAllAsMap(normalizedTableName, expedicion);
        long totalRecords = rhoTablesRepository.countAll(normalizedTableName,expedicion);
        String description = elements.isEmpty()
                ? "No se encontraron registros en la pagina indicada"
                : "Consulta realizada correctamente";

        return new TableResponse<>(
                new TableResponse.Summary(
                        normalizedTableName,
                        elements.isEmpty() ? "ERROR" : "SUCCESS",
                        description,
                        1,
                        elements.size(),
                        totalRecords),
                elements);
    }

    public TableResponse<Map<String, Object>> getTableByRef(
            String tableName,
            String refDom) {
        String normalizedTableName = normalizeTableName(tableName);
        if (DEDICATED_TABLES.contains(normalizedTableName)) {
            throw new IllegalArgumentException(
                    "La tabla " + normalizedTableName + " tiene un servicio especifico");
        }
        if (!rhoTablesRepository.supportsTable(normalizedTableName)) {
            throw new IllegalArgumentException("La tabla solicitada no esta incluida en los manifiestos");
        }
        if (refDom.isEmpty() ) {
            throw new IllegalArgumentException("debe indicar una referencia Domino");
        }

        List<Map<String, Object>> elements =
                rhoTablesRepository.findAllByRefAsMap(normalizedTableName, refDom);
        long totalRecords = rhoTablesRepository.countAllByRef(normalizedTableName,refDom);
        String description = elements.isEmpty()
                ? "No se encontraron registros en la pagina indicada"
                : "Consulta realizada correctamente";

        return new TableResponse<>(
                new TableResponse.Summary(
                        normalizedTableName,
                        elements.isEmpty() ? "ERROR" : "SUCCESS",
                        description,
                        1,
                        elements.size(),
                        totalRecords),
                elements);
    }

    private String normalizeTableName(String tableName) {
        if (tableName == null || !tableName.matches("[A-Za-z][A-Za-z0-9_$#]*")) {
            throw new IllegalArgumentException("El nombre de tabla solicitado no es valido");
        }
        return tableName.toUpperCase(Locale.ROOT);
    }
}
