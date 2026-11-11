package com.transport.transportApplication.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Tst {
    @JsonProperty("KEYTST")
    private Integer keytst;

    @JsonProperty("DENOM")
    private String denom;

    @JsonProperty("COD_DACHSER")
    private Integer codDachser;

    public Integer getKeytst() {
        return keytst;
    }

    public void setKeytst(Integer keytst) {
        this.keytst = keytst;
    }

    public String getDenom() {
        return denom;
    }

    public void setDenom(String denom) {
        this.denom = denom;
    }

    public Integer getCodDachser() {
        return codDachser;
    }

    public void setCodDachser(Integer codDachser) {
        this.codDachser = codDachser;
    }
}
