package com.transport.transportApplication.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Ref {
    @JsonProperty("EXPEDICION")
    private Long expedicion;

    @JsonProperty("REFERENCIA")
    private String referencia;

    @JsonProperty("TIPO")
    private String tipo;

    public Ref() {
    }

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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
