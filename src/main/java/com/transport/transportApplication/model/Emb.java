package com.transport.transportApplication.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Emb {
    @JsonProperty("KEYEMB")
    private String keyemb;

    @JsonProperty("TIPO")
    private String tipo;

    @JsonProperty("DENOM")
    private String denom;

    public Emb() {
    }

    public String getKeyemb() {
        return keyemb;
    }

    public void setKeyemb(String keyemb) {
        this.keyemb = keyemb;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDenom() {
        return denom;
    }

    public void setDenom(String denom) {
        this.denom = denom;
    }
}
