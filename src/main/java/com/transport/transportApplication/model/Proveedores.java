package com.transport.transportApplication.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Proveedores {
    @JsonProperty("KEYPROVE")
    private Integer keyprove;

    @JsonProperty("NOMBRE")
    private String nombre;

    @JsonProperty("DIRECCION")
    private String direccion;

    @JsonProperty("KEYPAI")
    private String keypai;

    @JsonProperty("COD_POS")
    private String codPos;

    @JsonProperty("POBLACION")
    private String poblacion;

    @JsonProperty("NIF")
    private String nif;

    @JsonProperty("TELEFONO")
    private String telefono;

    @JsonProperty("MAIL")
    private String mail;

    @JsonProperty("GRUPO")
    private String grupo;

    public Proveedores() {
    }

    public Integer getKeyprove() {
        return keyprove;
    }

    public void setKeyprove(Integer keyprove) {
        this.keyprove = keyprove;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getKeypai() {
        return keypai;
    }

    public void setKeypai(String keypai) {
        this.keypai = keypai;
    }

    public String getCodPos() {
        return codPos;
    }

    public void setCodPos(String codPos) {
        this.codPos = codPos;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }
}
