package com.transport.transportApplication.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

public class Rcl {
    @JsonProperty("KEYRCO")
    private Long keyrco;

    @JsonProperty("USR_PLAZA")
    private String usrPlaza;

    @JsonProperty("CODCONS")
    private String codcons;

    @JsonProperty("NOMCONS")
    private String nomcons;

    @JsonProperty("DOMCONS")
    private String domcons;

    @JsonProperty("POBCONS")
    private String pobcons;

    @JsonProperty("CP_REX")
    private String cpRex;

    @JsonProperty("TELCONS")
    private String telcons;

    @JsonProperty("NIFCONS")
    private String nifcons;

    @JsonProperty("CONTACTO2")
    private String contacto2;

    @JsonProperty("BULTOS")
    private Integer bultos;

    @JsonProperty("KILOS")
    private BigDecimal kilos;

    @JsonProperty("PVKILOS")
    private BigDecimal pvkilos;

    @JsonProperty("PRKILOS")
    private BigDecimal prkilos;

    @JsonProperty("VOL")
    private BigDecimal vol;

    @JsonProperty("NENVIO")
    private String nenvio;

    @JsonProperty("ORDEN")
    private Integer orden;

    public Long getKeyrco() {
        return keyrco;
    }

    public void setKeyrco(Long keyrco) {
        this.keyrco = keyrco;
    }

    public String getUsrPlaza() {
        return usrPlaza;
    }

    public void setUsrPlaza(String usrPlaza) {
        this.usrPlaza = usrPlaza;
    }

    public String getCodcons() {
        return codcons;
    }

    public void setCodcons(String codcons) {
        this.codcons = codcons;
    }

    public String getNomcons() {
        return nomcons;
    }

    public void setNomcons(String nomcons) {
        this.nomcons = nomcons;
    }

    public String getDomcons() {
        return domcons;
    }

    public void setDomcons(String domcons) {
        this.domcons = domcons;
    }

    public String getPobcons() {
        return pobcons;
    }

    public void setPobcons(String pobcons) {
        this.pobcons = pobcons;
    }

    public String getCpRex() {
        return cpRex;
    }

    public void setCpRex(String cpRex) {
        this.cpRex = cpRex;
    }

    public String getTelcons() {
        return telcons;
    }

    public void setTelcons(String telcons) {
        this.telcons = telcons;
    }

    public String getNifcons() {
        return nifcons;
    }

    public void setNifcons(String nifcons) {
        this.nifcons = nifcons;
    }

    public String getContacto2() {
        return contacto2;
    }

    public void setContacto2(String contacto2) {
        this.contacto2 = contacto2;
    }

    public Integer getBultos() {
        return bultos;
    }

    public void setBultos(Integer bultos) {
        this.bultos = bultos;
    }

    public BigDecimal getKilos() {
        return kilos;
    }

    public void setKilos(BigDecimal kilos) {
        this.kilos = kilos;
    }

    public BigDecimal getPvkilos() {
        return pvkilos;
    }

    public void setPvkilos(BigDecimal pvkilos) {
        this.pvkilos = pvkilos;
    }

    public BigDecimal getPrkilos() {
        return prkilos;
    }

    public void setPrkilos(BigDecimal prkilos) {
        this.prkilos = prkilos;
    }

    public BigDecimal getVol() {
        return vol;
    }

    public void setVol(BigDecimal vol) {
        this.vol = vol;
    }

    public String getNenvio() {
        return nenvio;
    }

    public void setNenvio(String nenvio) {
        this.nenvio = nenvio;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }
}
