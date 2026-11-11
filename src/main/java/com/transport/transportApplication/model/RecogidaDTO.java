package com.transport.transportApplication.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public record RecogidaDTO(
        Map<String, Object> rco,
        Detalle detalle) {

    public record Detalle(
            @JsonProperty("rcl") List<Map<String, Object>> Rcl,
            @JsonProperty("rco_incidencias") List<Map<String, Object>> Rco_incidencias,
            @JsonProperty("rco_ref") List<Map<String, Object>> Rco_ref) {
    }
}
