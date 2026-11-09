package com.transport.transportApplication.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CliExpImp {
    @JsonProperty("KEYCLIEXPIMP")
    private Integer keycliexpimp;

    @JsonProperty("KEYDEL")
    private String keydel;

    @JsonProperty("KEYCLI")
    private String keycli;

    @JsonProperty("NOMBRE")
    private String nombre;

    @JsonProperty("NIFDNI")
    private String nifdni;

    @JsonProperty("DIRECCION")
    private String direccion;

    @JsonProperty("NUMERO")
    private String numero;

    @JsonProperty("CODPOS")
    private String codpos;

    @JsonProperty("POBLACION")
    private String poblacion;

    @JsonProperty("PROVINCIA")
    private String provincia;

    @JsonProperty("KEYPAI")
    private String keypai;

    @JsonProperty("KEYACT_CAN")
    private Integer keyactCan;

    @JsonProperty("TIPO")
    private String tipo;

    public CliExpImp() {
    }

    public Integer getKeycliexpimp() {
        return keycliexpimp;
    }

    public void setKeycliexpimp(Integer keycliexpimp) {
        this.keycliexpimp = keycliexpimp;
    }

    public String getKeydel() {
        return keydel;
    }

    public void setKeydel(String keydel) {
        this.keydel = keydel;
    }

    public String getKeycli() {
        return keycli;
    }

    public void setKeycli(String keycli) {
        this.keycli = keycli;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNifdni() {
        return nifdni;
    }

    public void setNifdni(String nifdni) {
        this.nifdni = nifdni;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCodpos() {
        return codpos;
    }

    public void setCodpos(String codpos) {
        this.codpos = codpos;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getKeypai() {
        return keypai;
    }

    public void setKeypai(String keypai) {
        this.keypai = keypai;
    }

    public Integer getKeyactCan() {
        return keyactCan;
    }

    public void setKeyactCan(Integer keyactCan) {
        this.keyactCan = keyactCan;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
