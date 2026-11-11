package com.transport.transportApplication.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RhoCostesNac {
    @JsonProperty("EXPEDICION")
    private Long expedicion;

    @JsonProperty("FECHAGRABA")
    private LocalDateTime fechagraba;

    @JsonProperty("SEMPOR")
    private String sempor;

    @JsonProperty("KEYCONCEPTO")
    private String keyconcepto;

    @JsonProperty("IMPORTECONCEPTO")
    private BigDecimal importeconcepto;

    @JsonProperty("CODPLAQUEINCURREENCOSTE")
    private String codplaqueincurreencoste;

    @JsonProperty("CODPLAQUESOPORTAELCOSTE")
    private String codplaquesoportaelcoste;

    @JsonProperty("FECHALIQ")
    private LocalDateTime fechaliq;

    @JsonProperty("AORDENANTE")
    private Integer aordenante;

    @JsonProperty("SEMPOR_AUX")
    private String semporAux;

    @JsonProperty("FECHA_REG")
    private LocalDateTime fechaReg;

    public Long getExpedicion() {
        return expedicion;
    }

    public void setExpedicion(Long expedicion) {
        this.expedicion = expedicion;
    }

    public LocalDateTime getFechagraba() {
        return fechagraba;
    }

    public void setFechagraba(LocalDateTime fechagraba) {
        this.fechagraba = fechagraba;
    }

    public String getSempor() {
        return sempor;
    }

    public void setSempor(String sempor) {
        this.sempor = sempor;
    }

    public String getKeyconcepto() {
        return keyconcepto;
    }

    public void setKeyconcepto(String keyconcepto) {
        this.keyconcepto = keyconcepto;
    }

    public BigDecimal getImporteconcepto() {
        return importeconcepto;
    }

    public void setImporteconcepto(BigDecimal importeconcepto) {
        this.importeconcepto = importeconcepto;
    }

    public String getCodplaqueincurreencoste() {
        return codplaqueincurreencoste;
    }

    public void setCodplaqueincurreencoste(String codplaqueincurreencoste) {
        this.codplaqueincurreencoste = codplaqueincurreencoste;
    }

    public String getCodplaquesoportaelcoste() {
        return codplaquesoportaelcoste;
    }

    public void setCodplaquesoportaelcoste(String codplaquesoportaelcoste) {
        this.codplaquesoportaelcoste = codplaquesoportaelcoste;
    }

    public LocalDateTime getFechaliq() {
        return fechaliq;
    }

    public void setFechaliq(LocalDateTime fechaliq) {
        this.fechaliq = fechaliq;
    }

    public Integer getAordenante() {
        return aordenante;
    }

    public void setAordenante(Integer aordenante) {
        this.aordenante = aordenante;
    }

    public String getSemporAux() {
        return semporAux;
    }

    public void setSemporAux(String semporAux) {
        this.semporAux = semporAux;
    }

    public LocalDateTime getFechaReg() {
        return fechaReg;
    }

    public void setFechaReg(LocalDateTime fechaReg) {
        this.fechaReg = fechaReg;
    }
}
