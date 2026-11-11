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

    //Aquí indicamos las tablas que se pueden consultar desde aquí, no son maestras
    private static final Set<String> DEDICATED_TABLES =
            Set.of("ADR","HES","HUELLA_CARBONO_DET","IE_EDP","RCH","RCL","RCO","RCO_INCIDENCIAS","RCO_REF",
                    "REF","REF_ADU_INFO","REF_ADU_IMP","REF_CLI","RHO","RHO_COSTES_NAC","RHO_INT","RHO_PROC_ADU",
                    "SA","SA_INT","XPCIGIC");

    private final RhoTablesRepository rhoTablesRepository;

    public RhoTablesService(RhoTablesRepository rhoTablesRepository) {
        this.rhoTablesRepository = rhoTablesRepository;
    }

    public ExpedicionDTO getExpedicionCompleta(String expedicion) {
        if (expedicion == null || expedicion.isBlank()) {
            throw new IllegalArgumentException("debe indicar una expedicion o referencia Domino");
        }

        String expedicionReal;
        try {
            expedicionReal=rhoTablesRepository.getRefDom(expedicion);
            if (expedicionReal==null) {expedicionReal=expedicion;}

        } catch (Exception e) {expedicionReal=expedicion;}



        List<Map<String, Object>> rhoRows = rhoTablesRepository.findAllAsMap("RHO", expedicionReal);
        Map<String, Object> rho = rhoRows.isEmpty() ? null : rhoRows.get(0);

        ExpedicionDTO.Detalle detalle = new ExpedicionDTO.Detalle(
                rhoTablesRepository.findAllAsMap("ADR", "ADR_EXPEDICION", expedicionReal),
                rhoTablesRepository.findAllAsMap("HES", expedicionReal),
                rhoTablesRepository.findAllAsMap("HUELLA_CARBONO_DET", expedicionReal),
                rhoTablesRepository.findAllAsMap("IE_EDP", expedicionReal),
                rhoTablesRepository.findAllAsMap("REF", expedicionReal),
                rhoTablesRepository.findAllAsMap("REF_ADU_INFO", expedicionReal),
                rhoTablesRepository.findAllAsMap("REF_ADU_INFO_IMP", expedicionReal),
                rhoTablesRepository.findAllAsMap("REF_CLI", expedicionReal),
                rhoTablesRepository.findAllAsMap("RHO_COSTES_NAC", expedicionReal),
                rhoTablesRepository.findAllAsMap("RHO_IMPORTADOR", expedicionReal),
                rhoTablesRepository.findAllAsMap("RHO_INT", expedicionReal),
                rhoTablesRepository.findAllAsMap("RHO_PROC_ADU", expedicionReal),
                rhoTablesRepository.findAllAsMap("SA", expedicionReal),
                rhoTablesRepository.findAllAsMap("SA_INT", expedicionReal),
                rhoTablesRepository.findAllAsMap("XPCIGIC", expedicionReal));

        return new ExpedicionDTO(rho, detalle);
    }

    public TableResponse<Map<String, Object>> getTable(
            String tableName,
            String expedicion) {
        String normalizedTableName = normalizeTableName(tableName);
        if (!DEDICATED_TABLES.contains(normalizedTableName)) {
            throw new IllegalArgumentException(
                    "La tabla " + normalizedTableName + " tiene un servicio especifico");
        }
        if (!rhoTablesRepository.supportsTable(normalizedTableName)) {
            throw new IllegalArgumentException("La tabla solicitada no esta incluida en los manifiestos");
        }
        if (expedicion.isEmpty() ) {
            throw new IllegalArgumentException("debe indicar una expedicion");
        }

        String expedicionReal;
        try {
            expedicionReal=rhoTablesRepository.getRefDom(expedicion);
            if (expedicionReal==null) {expedicionReal=expedicion;}

        } catch (Exception e) {expedicionReal=expedicion;}

        List<Map<String, Object>> elements =
                rhoTablesRepository.findAllAsMap(normalizedTableName, expedicionReal);
        long totalRecords = rhoTablesRepository.countAll(normalizedTableName,expedicionReal);
        String description = elements.isEmpty()
                ? "No se encontraron registros en la pagina indicada"
                : "Consulta realizada correctamente";

        return new TableResponse<>(
                new TableResponse.Summary(
                        normalizedTableName,
                        elements.isEmpty() ? "ERROR" : "SUCCESS",
                        description,
                        1,
                        (long) elements.size(),
                        totalRecords,
                        true
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
