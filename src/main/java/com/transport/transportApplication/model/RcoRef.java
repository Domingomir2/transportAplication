package com.transport.transportApplication.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RcoRef {
    @JsonProperty("KEYRCO")
    private Long keyrco;

    @JsonProperty("TIPO")
    private String tipo;

    @JsonProperty("REF")
    private String ref;

    public Long getKeyrco() {
        return keyrco;
    }

    public void setKeyrco(Long keyrco) {
        this.keyrco = keyrco;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }
}
