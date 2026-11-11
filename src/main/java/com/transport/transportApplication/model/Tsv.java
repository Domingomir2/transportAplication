package com.transport.transportApplication.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Tsv {
    @JsonProperty("KEYTSV")
    private Integer keytsv;

    @JsonProperty("DENOM")
    private String denom;

    public Integer getKeytsv() {
        return keytsv;
    }

    public void setKeytsv(Integer keytsv) {
        this.keytsv = keytsv;
    }

    public String getDenom() {
        return denom;
    }

    public void setDenom(String denom) {
        this.denom = denom;
    }
}
