package com.transport.transportApplication.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FpCod {
    @JsonProperty("KEYPROVE")
    private Integer keyprove;

    @JsonProperty("TIPO_COD")
    private String tipoCod;

    @JsonProperty("DESCRI_COD")
    private String descriCod;

    public FpCod() {
    }

    public Integer getKeyprove() {
        return keyprove;
    }

    public void setKeyprove(Integer keyprove) {
        this.keyprove = keyprove;
    }

    public String getTipoCod() {
        return tipoCod;
    }

    public void setTipoCod(String tipoCod) {
        this.tipoCod = tipoCod;
    }

    public String getDescriCod() {
        return descriCod;
    }

    public void setDescriCod(String descriCod) {
        this.descriCod = descriCod;
    }
}
