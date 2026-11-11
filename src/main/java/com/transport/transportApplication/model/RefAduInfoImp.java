package com.transport.transportApplication.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

public class RefAduInfoImp {
    @JsonProperty("EXPEDICION")
    private Long expedicion;

    @JsonProperty("REFERENCIA")
    private String referencia;

    @JsonProperty("TIPO_REF")
    private String tipoRef;

    @JsonProperty("FECHA_GRABA")
    private LocalDateTime fechaGraba;

    public Long getExpedicion() {
        return expedicion;
    }

    public void setExpedicion(Long expedicion) {
        this.expedicion = expedicion;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getTipoRef() {
        return tipoRef;
    }

    public void setTipoRef(String tipoRef) {
        this.tipoRef = tipoRef;
    }

    public LocalDateTime getFechaGraba() {
        return fechaGraba;
    }

    public void setFechaGraba(LocalDateTime fechaGraba) {
        this.fechaGraba = fechaGraba;
    }
}
