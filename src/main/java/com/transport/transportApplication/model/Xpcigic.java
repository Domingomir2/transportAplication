package com.transport.transportApplication.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Xpcigic {
    @JsonProperty("DELEGACION")
    private String delegacion;

    @JsonProperty("SECUENCIA")
    private Integer secuencia;

    @JsonProperty("NUMCONCEPTO")
    private Integer numconcepto;

    @JsonProperty("BASE_IMPONIBLE")
    private BigDecimal baseImponible;

    @JsonProperty("IGIC")
    private BigDecimal igic;

    @JsonProperty("IMPORTE")
    private BigDecimal importe;

    @JsonProperty("FECHA")
    private LocalDateTime fecha;

    @JsonProperty("EXPEDICION")
    private Long expedicion;

    @JsonProperty("KEY_IE_IEC")
    private String keyIeIec;

    @JsonProperty("SEMPOR")
    private String sempor;

    public String getDelegacion() {
        return delegacion;
    }

    public void setDelegacion(String delegacion) {
        this.delegacion = delegacion;
    }

    public Integer getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(Integer secuencia) {
        this.secuencia = secuencia;
    }

    public Integer getNumconcepto() {
        return numconcepto;
    }

    public void setNumconcepto(Integer numconcepto) {
        this.numconcepto = numconcepto;
    }

    public BigDecimal getBaseImponible() {
        return baseImponible;
    }

    public void setBaseImponible(BigDecimal baseImponible) {
        this.baseImponible = baseImponible;
    }

    public BigDecimal getIgic() {
        return igic;
    }

    public void setIgic(BigDecimal igic) {
        this.igic = igic;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Long getExpedicion() {
        return expedicion;
    }

    public void setExpedicion(Long expedicion) {
        this.expedicion = expedicion;
    }

    public String getKeyIeIec() {
        return keyIeIec;
    }

    public void setKeyIeIec(String keyIeIec) {
        this.keyIeIec = keyIeIec;
    }

    public String getSempor() {
        return sempor;
    }

    public void setSempor(String sempor) {
        this.sempor = sempor;
    }
}
