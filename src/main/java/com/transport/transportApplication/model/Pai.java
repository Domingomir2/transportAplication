package com.transport.transportApplication.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Pai {
    @JsonProperty("KEYPAI")
    private String keypai;

    @JsonProperty("DENOM")
    private String denom;

    @JsonProperty("CODIGO_ISO")
    private String codigoIso;

    @JsonProperty("COMUNITARIO")
    private Integer comunitario;

    @JsonProperty("PLA_INTER")
    private Integer plaInter;

    public Pai() {
    }

    public String getKeypai() {
        return keypai;
    }

    public void setKeypai(String keypai) {
        this.keypai = keypai;
    }

    public String getDenom() {
        return denom;
    }

    public void setDenom(String denom) {
        this.denom = denom;
    }

    public String getCodigoIso() {
        return codigoIso;
    }

    public void setCodigoIso(String codigoIso) {
        this.codigoIso = codigoIso;
    }

    public Integer getComunitario() {
        return comunitario;
    }

    public void setComunitario(Integer comunitario) {
        this.comunitario = comunitario;
    }

    public Integer getPlaInter() {
        return plaInter;
    }

    public void setPlaInter(Integer plaInter) {
        this.plaInter = plaInter;
    }
}
