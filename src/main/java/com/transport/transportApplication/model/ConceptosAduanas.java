package com.transport.transportApplication.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

public class ConceptosAduanas {
    @JsonProperty("KEYCONC")
    private String keyconc;

    @JsonProperty("DESCRIPCION")
    private String descripcion;

    @JsonProperty("PORCENTAJE")
    private BigDecimal porcentaje;

    @JsonProperty("CTACONT")
    private String ctacont;

    public ConceptosAduanas() {
    }

    public String getKeyconc() {
        return keyconc;
    }

    public void setKeyconc(String keyconc) {
        this.keyconc = keyconc;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(BigDecimal porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getCtacont() {
        return ctacont;
    }

    public void setCtacont(String ctacont) {
        this.ctacont = ctacont;
    }
}
