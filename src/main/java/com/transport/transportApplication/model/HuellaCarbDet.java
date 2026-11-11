package com.transport.transportApplication.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.math.BigInteger;

public class HuellaCarbDet {
    @JsonProperty("EXPEDICION")
    private Long expedicion;

    @JsonProperty("REFERENCIA")
    private String referencia;

    @JsonProperty("CO2_TANK_TO_WHEEL")
    private BigDecimal co2TankToWheel;

    @JsonProperty("CO2_WELL_TO_WHEEL")
    private BigDecimal co2WellToWheel;

    @JsonProperty("CO2_TANK_TO_WHEEL_EQ")
    private BigDecimal co2TankToWheelEq;

    @JsonProperty("CO2_WELL_TO_WHEEL_EQ")
    private BigDecimal co2WellToWheelEq;

    @JsonProperty("OXIDO_SULFURO")
    private BigDecimal oxidoSulfuro;

    @JsonProperty("PARTICULAS")
    private BigDecimal particulas;

    @JsonProperty("TIPO")
    private String tipo;

    @JsonProperty("USR_ORIGEN")
    private String usrOrigen;

    @JsonProperty("DISTANCE")
    private BigDecimal distance;

    @JsonProperty("TRANSPORTID")
    private BigInteger transportid;

    public Long getExpedicion() {
        return expedicion;
    }

    public void setExpedicion(Long expedicion) {
        this.expedicion = expedicion;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public BigDecimal getCo2TankToWheel() {
        return co2TankToWheel;
    }

    public void setCo2TankToWheel(BigDecimal co2TankToWheel) {
        this.co2TankToWheel = co2TankToWheel;
    }

    public BigDecimal getCo2WellToWheel() {
        return co2WellToWheel;
    }

    public void setCo2WellToWheel(BigDecimal co2WellToWheel) {
        this.co2WellToWheel = co2WellToWheel;
    }

    public BigDecimal getCo2TankToWheelEq() {
        return co2TankToWheelEq;
    }

    public void setCo2TankToWheelEq(BigDecimal co2TankToWheelEq) {
        this.co2TankToWheelEq = co2TankToWheelEq;
    }

    public BigDecimal getCo2WellToWheelEq() {
        return co2WellToWheelEq;
    }

    public void setCo2WellToWheelEq(BigDecimal co2WellToWheelEq) {
        this.co2WellToWheelEq = co2WellToWheelEq;
    }

    public BigDecimal getOxidoSulfuro() {
        return oxidoSulfuro;
    }

    public void setOxidoSulfuro(BigDecimal oxidoSulfuro) {
        this.oxidoSulfuro = oxidoSulfuro;
    }

    public BigDecimal getParticulas() {
        return particulas;
    }

    public void setParticulas(BigDecimal particulas) {
        this.particulas = particulas;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUsrOrigen() {
        return usrOrigen;
    }

    public void setUsrOrigen(String usrOrigen) {
        this.usrOrigen = usrOrigen;
    }

    public BigDecimal getDistance() {
        return distance;
    }

    public void setDistance(BigDecimal distance) {
        this.distance = distance;
    }

    public BigInteger getTransportid() {
        return transportid;
    }

    public void setTransportid(BigInteger transportid) {
        this.transportid = transportid;
    }
}
