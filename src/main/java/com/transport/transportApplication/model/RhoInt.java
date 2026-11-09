package com.transport.transportApplication.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

public class RhoInt {
    @JsonProperty("EXPEDICION")
    private Long expedicion;

    @JsonProperty("F_CONCERTADA")
    private LocalDateTime fConcertada;

    @JsonProperty("KEYEMB")
    private String keyemb;

    @JsonProperty("NUM_EMBALAJES")
    private Integer numEmbalajes;

    @JsonProperty("GEST_ADUANAS")
    private String gestAduanas;

    @JsonProperty("OBS_ADUANAS")
    private String obsAduanas;

    @JsonProperty("KEYPROVE")
    private Integer keyprove;

    @JsonProperty("TIPO_COD")
    private String tipoCod;

    @JsonProperty("KEYPAI")
    private String keypai;

    @JsonProperty("KEYAGENCIA_ORI")
    private String keyagenciaOri;

    @JsonProperty("KEYAGENCIA_DES")
    private String keyagenciaDes;

    @JsonProperty("DIVISA_FACTURA_C")
    private String divisaFacturaC;

    @JsonProperty("ENT_DIR")
    private String entDir;

    @JsonProperty("REQUIERE_CONCERTACION")
    private Integer requiereConcertacion;

    @JsonProperty("WEIGHT_FACTOR")
    private Integer weightFactor;

    public RhoInt() {
    }

    public Long getExpedicion() {
        return expedicion;
    }

    public void setExpedicion(Long expedicion) {
        this.expedicion = expedicion;
    }

    public LocalDateTime getFConcertada() {
        return fConcertada;
    }

    public void setFConcertada(LocalDateTime fConcertada) {
        this.fConcertada = fConcertada;
    }

    public String getKeyemb() {
        return keyemb;
    }

    public void setKeyemb(String keyemb) {
        this.keyemb = keyemb;
    }

    public Integer getNumEmbalajes() {
        return numEmbalajes;
    }

    public void setNumEmbalajes(Integer numEmbalajes) {
        this.numEmbalajes = numEmbalajes;
    }

    public String getGestAduanas() {
        return gestAduanas;
    }

    public void setGestAduanas(String gestAduanas) {
        this.gestAduanas = gestAduanas;
    }

    public String getObsAduanas() {
        return obsAduanas;
    }

    public void setObsAduanas(String obsAduanas) {
        this.obsAduanas = obsAduanas;
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

    public String getKeypai() {
        return keypai;
    }

    public void setKeypai(String keypai) {
        this.keypai = keypai;
    }

    public String getKeyagenciaOri() {
        return keyagenciaOri;
    }

    public void setKeyagenciaOri(String keyagenciaOri) {
        this.keyagenciaOri = keyagenciaOri;
    }

    public String getKeyagenciaDes() {
        return keyagenciaDes;
    }

    public void setKeyagenciaDes(String keyagenciaDes) {
        this.keyagenciaDes = keyagenciaDes;
    }

    public String getDivisaFacturaC() {
        return divisaFacturaC;
    }

    public void setDivisaFacturaC(String divisaFacturaC) {
        this.divisaFacturaC = divisaFacturaC;
    }

    public String getEntDir() {
        return entDir;
    }

    public void setEntDir(String entDir) {
        this.entDir = entDir;
    }

    public Integer getRequiereConcertacion() {
        return requiereConcertacion;
    }

    public void setRequiereConcertacion(Integer requiereConcertacion) {
        this.requiereConcertacion = requiereConcertacion;
    }

    public Integer getWeightFactor() {
        return weightFactor;
    }

    public void setWeightFactor(Integer weightFactor) {
        this.weightFactor = weightFactor;
    }
}
