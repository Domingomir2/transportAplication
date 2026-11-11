package com.transport.transportApplication.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RefAduLista {
    @JsonProperty("TIPO_REF")
    private String tipoRef;

    @JsonProperty("DESC_REF")
    private String descRef;

    @JsonProperty("COD_DACHSER")
    private String codDachser;

    public String getTipoRef() {
        return tipoRef;
    }

    public void setTipoRef(String tipoRef) {
        this.tipoRef = tipoRef;
    }

    public String getDescRef() {
        return descRef;
    }

    public void setDescRef(String descRef) {
        this.descRef = descRef;
    }

    public String getCodDachser() {
        return codDachser;
    }

    public void setCodDachser(String codDachser) {
        this.codDachser = codDachser;
    }
}
