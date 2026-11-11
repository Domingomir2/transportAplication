package com.transport.transportApplication.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

public class Hes {
    @JsonProperty("EXPEDICION")
    private Long expedicion;

    @JsonProperty("FECHA")
    private LocalDateTime fecha;

    @JsonProperty("PLAZA")
    private String plaza;

    @JsonProperty("F_GRABA")
    private LocalDateTime fGraba;

    @JsonProperty("HORA")
    private LocalDateTime hora;

    @JsonProperty("KEYFTI")
    private String keyfti;

    @JsonProperty("DESFTI")
    private String desfti;

    @JsonProperty("AMPFTI")
    private String ampfti;

    @JsonProperty("USUARIO")
    private String usuario;

    @JsonProperty("AMPFTICLI")
    private String ampfticli;

    @JsonProperty("KEYDOM_INC")
    private String keydomInc;

    @JsonProperty("KEYDOM_AMP")
    private Integer keydomAmp;

    @JsonProperty("PERSONA")
    private String persona;

    @JsonProperty("FECHA_EA")
    private LocalDateTime fechaEa;

    @JsonProperty("HORA_INICIO")
    private String horaInicio;

    @JsonProperty("HORA_FIN")
    private String horaFin;

    @JsonProperty("DESCKEYDOM_AMP")
    private String desckeydomAmp;

    @JsonProperty("AGENCIA_DACHSER")
    private String agenciaDachser;

    public Long getExpedicion() {
        return expedicion;
    }

    public void setExpedicion(Long expedicion) {
        this.expedicion = expedicion;
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

    public LocalDateTime getFGraba() {
        return fGraba;
    }

    public void setFGraba(LocalDateTime fGraba) {
        this.fGraba = fGraba;
    }

    public LocalDateTime getHora() {
        return hora;
    }

    public void setHora(LocalDateTime hora) {
        this.hora = hora;
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getAmpfticli() {
        return ampfticli;
    }

    public void setAmpfticli(String ampfticli) {
        this.ampfticli = ampfticli;
    }

    public String getKeydomInc() {
        return keydomInc;
    }

    public void setKeydomInc(String keydomInc) {
        this.keydomInc = keydomInc;
    }

    public Integer getKeydomAmp() {
        return keydomAmp;
    }

    public void setKeydomAmp(Integer keydomAmp) {
        this.keydomAmp = keydomAmp;
    }

    public String getPersona() {
        return persona;
    }

    public void setPersona(String persona) {
        this.persona = persona;
    }

    public LocalDateTime getFechaEa() {
        return fechaEa;
    }

    public void setFechaEa(LocalDateTime fechaEa) {
        this.fechaEa = fechaEa;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public String getDesckeydomAmp() {
        return desckeydomAmp;
    }

    public void setDesckeydomAmp(String desckeydomAmp) {
        this.desckeydomAmp = desckeydomAmp;
    }

    public String getAgenciaDachser() {
        return agenciaDachser;
    }

    public void setAgenciaDachser(String agenciaDachser) {
        this.agenciaDachser = agenciaDachser;
    }
}
