package com.transport.transportApplication.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Aid {
    @JsonProperty("COD_PLAZA_BIDEA")
    private String codPlazaBidea;

    @JsonProperty("COD_DACHSER")
    private String codDachser;

    @JsonProperty("AGENCIA_NOM")
    private String agenciaNom;

    @JsonProperty("POBLACION")
    private String poblacion;

    @JsonProperty("COUNTRY")
    private String country;

    @JsonProperty("INTERNACIONAL")
    private String internacional;

    public Aid() {
    }

    public String getCodPlazaBidea() {
        return codPlazaBidea;
    }

    public void setCodPlazaBidea(String codPlazaBidea) {
        this.codPlazaBidea = codPlazaBidea;
    }

    public String getCodDachser() {
        return codDachser;
    }

    public void setCodDachser(String codDachser) {
        this.codDachser = codDachser;
    }

    public String getAgenciaNom() {
        return agenciaNom;
    }

    public void setAgenciaNom(String agenciaNom) {
        this.agenciaNom = agenciaNom;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getInternacional() {
        return internacional;
    }

    public void setInternacional(String internacional) {
        this.internacional = internacional;
    }
}
