package com.transport.transportApplication.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RhoImportador {
    @JsonProperty("EXPEDICION")
    private Long expedicion;

    @JsonProperty("EORI_CONS")
    private String eoriCons;

    @JsonProperty("EORI_IMP")
    private String eoriImp;

    @JsonProperty("NOMBRE1_IMP")
    private String nombre1Imp;

    @JsonProperty("NOMBRE2_IMP")
    private String nombre2Imp;

    @JsonProperty("NOMBRE3_IMP")
    private String nombre3Imp;

    @JsonProperty("DIRECCION_IMP")
    private String direccionImp;

    @JsonProperty("PAIS_IMP")
    private String paisImp;

    @JsonProperty("CP_IMP")
    private String cpImp;

    @JsonProperty("POBLACION_IMP")
    private String poblacionImp;

    @JsonProperty("E_MAIL_IMP")
    private String eMailImp;

    @JsonProperty("TELEFONO_IMP")
    private String telefonoImp;

    @JsonProperty("CONTACTO_IMP")
    private String contactoImp;

    @JsonProperty("CONSIGNATARIO")
    private Integer consignatario;

    @JsonProperty("IMP_DIREC2")
    private String impDirec2;

    @JsonProperty("IMP_SURPLUS")
    private String impSurplus;

    public Long getExpedicion() {
        return expedicion;
    }

    public void setExpedicion(Long expedicion) {
        this.expedicion = expedicion;
    }

    public String getEoriCons() {
        return eoriCons;
    }

    public void setEoriCons(String eoriCons) {
        this.eoriCons = eoriCons;
    }

    public String getEoriImp() {
        return eoriImp;
    }

    public void setEoriImp(String eoriImp) {
        this.eoriImp = eoriImp;
    }

    public String getNombre1Imp() {
        return nombre1Imp;
    }

    public void setNombre1Imp(String nombre1Imp) {
        this.nombre1Imp = nombre1Imp;
    }

    public String getNombre2Imp() {
        return nombre2Imp;
    }

    public void setNombre2Imp(String nombre2Imp) {
        this.nombre2Imp = nombre2Imp;
    }

    public String getNombre3Imp() {
        return nombre3Imp;
    }

    public void setNombre3Imp(String nombre3Imp) {
        this.nombre3Imp = nombre3Imp;
    }

    public String getDireccionImp() {
        return direccionImp;
    }

    public void setDireccionImp(String direccionImp) {
        this.direccionImp = direccionImp;
    }

    public String getPaisImp() {
        return paisImp;
    }

    public void setPaisImp(String paisImp) {
        this.paisImp = paisImp;
    }

    public String getCpImp() {
        return cpImp;
    }

    public void setCpImp(String cpImp) {
        this.cpImp = cpImp;
    }

    public String getPoblacionImp() {
        return poblacionImp;
    }

    public void setPoblacionImp(String poblacionImp) {
        this.poblacionImp = poblacionImp;
    }

    public String getEMailImp() {
        return eMailImp;
    }

    public void setEMailImp(String eMailImp) {
        this.eMailImp = eMailImp;
    }

    public String getTelefonoImp() {
        return telefonoImp;
    }

    public void setTelefonoImp(String telefonoImp) {
        this.telefonoImp = telefonoImp;
    }

    public String getContactoImp() {
        return contactoImp;
    }

    public void setContactoImp(String contactoImp) {
        this.contactoImp = contactoImp;
    }

    public Integer getConsignatario() {
        return consignatario;
    }

    public void setConsignatario(Integer consignatario) {
        this.consignatario = consignatario;
    }

    public String getImpDirec2() {
        return impDirec2;
    }

    public void setImpDirec2(String impDirec2) {
        this.impDirec2 = impDirec2;
    }

    public String getImpSurplus() {
        return impSurplus;
    }

    public void setImpSurplus(String impSurplus) {
        this.impSurplus = impSurplus;
    }
}
