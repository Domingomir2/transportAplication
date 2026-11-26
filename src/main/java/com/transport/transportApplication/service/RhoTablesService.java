package com.transport.transportApplication.service;

import com.transport.transportApplication.model.ExpedicionDTO;
import com.transport.transportApplication.model.Rho;
import com.transport.transportApplication.model.TableResponse;
import com.transport.transportApplication.repository.RhoTablesRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

@Service
@ConditionalOnProperty(prefix = "bidea.datasource", name = "url")
public class RhoTablesService {

    static final String HUELLA_CARBONO_DET = "HUELLA_CARBONO_DET";
    static final String RHO_IMPORTADOR = "RHO_IMPORTADOR";
    static final String IE_EDP= "IE_EDP";
    static final String REF_CLI= "REF_CLI";
    static final String XPCIGIC= "XPCIGIC";
    static final String RHO_INT= "RHO_INT";
    static final String RHO_COSTES_NAC= "RHO_COSTES_NAC";
    static final String SA_INT= "SA_INT";
    static final String ADR_EXPEDICION= "ADR_EXPEDICION";
    static final String EXPEDICION= "EXPEDICION";
    static final String RHO_PROC_ADU= "RHO_PROC_ADU";
    static final String REF_ADU_INFO= "REF_ADU_INFO";

    //Aquí indicamos las tablas que se pueden consultar desde aquí, no son maestras
    private static final Set<String> DEDICATED_TABLES =
            Set.of("ADR","HES",HUELLA_CARBONO_DET,IE_EDP,"RCH","RCL","RCO","RCO_INCIDENCIAS","RCO_REF",
                    "REF",REF_ADU_INFO,"REF_ADU_IMP",REF_CLI,"RHO",RHO_COSTES_NAC,RHO_INT,RHO_PROC_ADU,
                    "SA",SA_INT,XPCIGIC);

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
        Map<String, Object> rho = rhoRows.isEmpty() ? null : rhoRows.getFirst();

        ExpedicionDTO.Detalle detalle = new ExpedicionDTO.Detalle(
                rhoTablesRepository.findAllAsMap("ADR", ADR_EXPEDICION, expedicionReal),
                rhoTablesRepository.findAllAsMap("HES", expedicionReal),
                rhoTablesRepository.findAllAsMap(HUELLA_CARBONO_DET, expedicionReal),
                rhoTablesRepository.findAllAsMap(IE_EDP, expedicionReal),
                rhoTablesRepository.findAllAsMap("REF", expedicionReal),
                rhoTablesRepository.findAllAsMap(REF_ADU_INFO, expedicionReal),
                rhoTablesRepository.findAllAsMap("REF_ADU_INFO_IMP", expedicionReal),
                rhoTablesRepository.findAllAsMap(REF_CLI, expedicionReal),
                rhoTablesRepository.findAllAsMap(RHO_COSTES_NAC, expedicionReal),
                rhoTablesRepository.findAllAsMap(RHO_IMPORTADOR, expedicionReal),
                rhoTablesRepository.findAllAsMap(RHO_INT, expedicionReal),
                rhoTablesRepository.findAllAsMap(RHO_PROC_ADU, expedicionReal),
                rhoTablesRepository.findAllAsMap("SA", expedicionReal),
                rhoTablesRepository.findAllAsMap(SA_INT, expedicionReal),
                rhoTablesRepository.findAllAsMap(XPCIGIC, expedicionReal));

        return new ExpedicionDTO(rho, detalle);
    }

    public String getExpedicionCompletaSql(String expedicion) {
        return PostgresInsertScriptGenerator.generate(getExpedicionCompleta(expedicion));
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
        if (expedicion == null || expedicion.isBlank()) {
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

    public void writeExpedicionesCompletaSql(List<Rho> expeditions, Writer writer) throws IOException {
        if (expeditions == null || expeditions.isEmpty()) {
            return;
        }

        List<String> requested = expeditions.stream()
                .map(Rho::getExpedicion)
                .map(String::valueOf)
                .toList();

        Map<String, String> realByRequested;
        try {
            realByRequested = rhoTablesRepository.findRealExpeditions(requested);
        } catch (RuntimeException exception) {
            realByRequested = new HashMap<>();
        }

        Map<String, String> resolvedByRequested = new LinkedHashMap<>();
        List<String> resolved = new ArrayList<>(requested.size());
        for (String reference : requested) {
            String real = realByRequested.getOrDefault(reference, reference);
            resolvedByRequested.put(reference, real);
            if (!resolved.contains(real)) {
                resolved.add(real);
            }
        }

        Map<String, Map<String, Object>> rhoRows =
                indexRows(rhoTablesRepository.findAllAsMapByExpeditions("RHO", EXPEDICION, resolved), resolved);
        Map<String, List<Map<String, Object>>> adrRows =
                indexRowsByExpeditions(rhoTablesRepository.findAllAsMapByExpeditions("ADR", ADR_EXPEDICION, resolved), resolved);
        Map<String, List<Map<String, Object>>> hesRows =
                indexRowsByExpeditions(rhoTablesRepository.findAllAsMapByExpeditions("HES", EXPEDICION, resolved), resolved);
        Map<String, List<Map<String, Object>>> huellaRows =
                indexRowsByExpeditions(rhoTablesRepository.findAllAsMapByExpeditions(HUELLA_CARBONO_DET, EXPEDICION, resolved), resolved);
        Map<String, List<Map<String, Object>>> ieEdpRows =
                indexRowsByExpeditions(rhoTablesRepository.findAllAsMapByExpeditions(IE_EDP, EXPEDICION, resolved), resolved);
        Map<String, List<Map<String, Object>>> refRows =
                indexRowsByExpeditions(rhoTablesRepository.findAllAsMapByExpeditions("REF", EXPEDICION, resolved), resolved);
        Map<String, List<Map<String, Object>>> refAduInfoRows =
                indexRowsByExpeditions(rhoTablesRepository.findAllAsMapByExpeditions(REF_ADU_INFO, EXPEDICION, resolved), resolved);
        Map<String, List<Map<String, Object>>> refAduInfoImpRows =
                indexRowsByExpeditions(rhoTablesRepository.findAllAsMapByExpeditions("REF_ADU_INFO_IMP", EXPEDICION, resolved), resolved);
        Map<String, List<Map<String, Object>>> refCliRows =
                indexRowsByExpeditions(rhoTablesRepository.findAllAsMapByExpeditions(REF_CLI, EXPEDICION, resolved), resolved);
        Map<String, List<Map<String, Object>>> costesRows =
                indexRowsByExpeditions(rhoTablesRepository.findAllAsMapByExpeditions(RHO_COSTES_NAC, EXPEDICION, resolved), resolved);
        Map<String, List<Map<String, Object>>> importadorRows =
                indexRowsByExpeditions(rhoTablesRepository.findAllAsMapByExpeditions(RHO_IMPORTADOR, EXPEDICION, resolved), resolved);
        Map<String, List<Map<String, Object>>> rhoIntRows =
                indexRowsByExpeditions(rhoTablesRepository.findAllAsMapByExpeditions(RHO_INT, EXPEDICION, resolved), resolved);
        Map<String, List<Map<String, Object>>> procAduRows =
                indexRowsByExpeditions(rhoTablesRepository.findAllAsMapByExpeditions(RHO_PROC_ADU, EXPEDICION, resolved), resolved);
        Map<String, List<Map<String, Object>>> saRows =
                indexRowsByExpeditions(rhoTablesRepository.findAllAsMapByExpeditions("SA", EXPEDICION, resolved), resolved);
        Map<String, List<Map<String, Object>>> saIntRows =
                indexRowsByExpeditions(rhoTablesRepository.findAllAsMapByExpeditions(SA_INT, EXPEDICION, resolved), resolved);
        Map<String, List<Map<String, Object>>> xpcigicRows =
                indexRowsByExpeditions(rhoTablesRepository.findAllAsMapByExpeditions(XPCIGIC, EXPEDICION, resolved), resolved);

        for (String reference : requested) {
            String real = resolvedByRequested.get(reference);
            ExpedicionDTO.Detalle detalle = new ExpedicionDTO.Detalle(
                    adrRows.getOrDefault(real, List.of()),
                    hesRows.getOrDefault(real, List.of()),
                    huellaRows.getOrDefault(real, List.of()),
                    ieEdpRows.getOrDefault(real, List.of()),
                    refRows.getOrDefault(real, List.of()),
                    refAduInfoRows.getOrDefault(real, List.of()),
                    refAduInfoImpRows.getOrDefault(real, List.of()),
                    refCliRows.getOrDefault(real, List.of()),
                    costesRows.getOrDefault(real, List.of()),
                    importadorRows.getOrDefault(real, List.of()),
                    rhoIntRows.getOrDefault(real, List.of()),
                    procAduRows.getOrDefault(real, List.of()),
                    saRows.getOrDefault(real, List.of()),
                    saIntRows.getOrDefault(real, List.of()),
                    xpcigicRows.getOrDefault(real, List.of()));

            writer.write(PostgresInsertScriptGenerator.generate(
                    new ExpedicionDTO(rhoRows.get(real), detalle)));
        }
    }

    private Map<String, Map<String, Object>> indexRows(
            List<Map<String, Object>> rows,
            List<String> expeditions) {
        Map<String, Map<String, Object>> result = new HashMap<>();
        for (Map<String, Object> row : rows) {
            String expedition = rowExpedition(row);
            if (expedition != null) {
                result.putIfAbsent(expedition, row);
            }
        }
        for (String expedition : expeditions) {
            result.putIfAbsent(expedition, null);
        }
        return result;
    }

    private Map<String, List<Map<String, Object>>> indexRowsByExpeditions(
            List<Map<String, Object>> rows,
            List<String> expeditions) {
        Map<String, List<Map<String, Object>>> result = new HashMap<>();
        for (String expedition : expeditions) {
            result.put(expedition, new ArrayList<>());
        }
        for (Map<String, Object> row : rows) {
            String expedition = rowExpedition(row);
            if (expedition != null) {
                result.computeIfAbsent(expedition, ignored -> new ArrayList<>()).add(row);
            }
        }
        return result;
    }

    private String rowExpedition(Map<String, Object> row) {
        Object value = row.get(EXPEDICION);
        if (value == null) {
            value = row.get(ADR_EXPEDICION);
        }
        if (value == null) {
            value = row.get(EXPEDICION);
        }
        return value == null ? null : value.toString();
    }
}
