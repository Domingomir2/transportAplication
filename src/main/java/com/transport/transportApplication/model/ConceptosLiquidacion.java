package com.transport.transportApplication.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConceptosLiquidacion {
    @JsonProperty("KEYCONCEPTO")
    private String keyconcepto;

    @JsonProperty("DESCRIPCONCEPTO")
    private String descripconcepto;

    public ConceptosLiquidacion() {
    }

    public String getKeyconcepto() {
        return keyconcepto;
    }

    public void setKeyconcepto(String keyconcepto) {
        this.keyconcepto = keyconcepto;
    }

    public String getDescripconcepto() {
        return descripconcepto;
    }

    public void setDescripconcepto(String descripconcepto) {
        this.descripconcepto = descripconcepto;
    }
}
