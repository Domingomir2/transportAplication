package com.transport.transportApplication.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

public class RhoProcAdu {
    @JsonProperty("EXPEDICION")
    private Long expedicion;

    @JsonProperty("DESC_PROC_ADU")
    private String descProcAdu;

    @JsonProperty("FECHA_GRABA")
    private LocalDateTime fechaGraba;

    public Long getExpedicion() {
        return expedicion;
    }

    public void setExpedicion(Long expedicion) {
        this.expedicion = expedicion;
    }

    public String getDescProcAdu() {
        return descProcAdu;
    }

    public void setDescProcAdu(String descProcAdu) {
        this.descProcAdu = descProcAdu;
    }

    public LocalDateTime getFechaGraba() {
        return fechaGraba;
    }

    public void setFechaGraba(LocalDateTime fechaGraba) {
        this.fechaGraba = fechaGraba;
    }
}
