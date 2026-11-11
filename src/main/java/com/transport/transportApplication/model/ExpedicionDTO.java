package com.transport.transportApplication.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public record ExpedicionDTO(
        Map<String, Object> rho,
        Detalle detalle) {

    public record Detalle(
            List<Map<String, Object>> adr,
            List<Map<String, Object>> hes,
            @JsonProperty("huella_carbono_det") List<Map<String, Object>> huellaCarbDet,
            @JsonProperty("ie_edp") List<Map<String, Object>> ieEdp,
            List<Map<String, Object>> ref,
            @JsonProperty("ref_adu_info") List<Map<String, Object>> refAduInfo,
            @JsonProperty("ref_adu_info_mp") List<Map<String, Object>> refAduInfoMp,
            @JsonProperty("ref_cli") List<Map<String, Object>> refCli,
            @JsonProperty("rho_costes_nac") List<Map<String, Object>> rhoCostesNac,
            @JsonProperty("rho_importador") List<Map<String, Object>> rhoImportador,
            @JsonProperty("rho_int") List<Map<String, Object>> rhoInt,
            @JsonProperty("rho_proc_adu") List<Map<String, Object>> rhoProcAdu,
            List<Map<String, Object>> sa,
            @JsonProperty("sa_int") List<Map<String, Object>> saInt,
            List<Map<String, Object>> xpcigic) {
    }
}
