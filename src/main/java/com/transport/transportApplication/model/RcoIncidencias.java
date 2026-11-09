package com.transport.transportApplication.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

public class RcoIncidencias {
    @JsonProperty("KEYRCO")
    private Long keyrco;

    @JsonProperty("USR_PLAZA")
    private String usrPlaza;

    @JsonProperty("FECHA")
    private LocalDateTime fecha;

    @JsonProperty("PLAZA")
    private String plaza;

    @JsonProperty("KEYFTI")
    private String keyfti;

    @JsonProperty("DESFTI")
    private String desfti;

    @JsonProperty("AMPFTI")
    private String ampfti;

    @JsonProperty("F_GRABA")
    private LocalDateTime fGraba;

    public RcoIncidencias() {
    }

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

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getPlaza() {
        return plaza;
    }

    public void setPlaza(String plaza) {
        this.plaza = plaza;
    }

    public String getKeyfti() {
        return keyfti;
    }

    public void setKeyfti(String keyfti) {
        this.keyfti = keyfti;
    }

    public String getDesfti() {
        return desfti;
    }

    public void setDesfti(String desfti) {
        this.desfti = desfti;
    }

    public String getAmpfti() {
        return ampfti;
    }

    public void setAmpfti(String ampfti) {
        this.ampfti = ampfti;
    }

    public LocalDateTime getFGraba() {
        return fGraba;
    }

    public void setFGraba(LocalDateTime fGraba) {
        this.fGraba = fGraba;
    }
}
