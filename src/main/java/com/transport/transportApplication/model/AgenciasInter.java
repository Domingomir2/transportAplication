package com.transport.transportApplication.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AgenciasInter {
    @JsonProperty("KEYAGENCIA")
    private String keyagencia;

    @JsonProperty("KEYDEL")
    private String keydel;

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

    @JsonProperty("FAX")
    private String fax;

    @JsonProperty("CONTACTO1")
    private String contacto1;

    @JsonProperty("TLF_CONTACTO1")
    private String tlfContacto1;

    @JsonProperty("FAX_CONTACTO1")
    private String faxContacto1;

    @JsonProperty("MAIL_CONTACTO1")
    private String mailContacto1;

    @JsonProperty("CONTACTO2")
    private String contacto2;

    @JsonProperty("TLF_CONTACTO2")
    private String tlfContacto2;

    @JsonProperty("FAX_CONTACTO2")
    private String faxContacto2;

    @JsonProperty("MAIL_CONTACTO2")
    private String mailContacto2;

    @JsonProperty("COD_DACHSER")
    private String codDachser;

    public AgenciasInter() {
    }

    public String getKeyagencia() {
        return keyagencia;
    }

    public void setKeyagencia(String keyagencia) {
        this.keyagencia = keyagencia;
    }

    public String getKeydel() {
        return keydel;
    }

    public void setKeydel(String keydel) {
        this.keydel = keydel;
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

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getContacto1() {
        return contacto1;
    }

    public void setContacto1(String contacto1) {
        this.contacto1 = contacto1;
    }

    public String getTlfContacto1() {
        return tlfContacto1;
    }

    public void setTlfContacto1(String tlfContacto1) {
        this.tlfContacto1 = tlfContacto1;
    }

    public String getFaxContacto1() {
        return faxContacto1;
    }

    public void setFaxContacto1(String faxContacto1) {
        this.faxContacto1 = faxContacto1;
    }

    public String getMailContacto1() {
        return mailContacto1;
    }

    public void setMailContacto1(String mailContacto1) {
        this.mailContacto1 = mailContacto1;
    }

    public String getContacto2() {
        return contacto2;
    }

    public void setContacto2(String contacto2) {
        this.contacto2 = contacto2;
    }

    public String getTlfContacto2() {
        return tlfContacto2;
    }

    public void setTlfContacto2(String tlfContacto2) {
        this.tlfContacto2 = tlfContacto2;
    }

    public String getFaxContacto2() {
        return faxContacto2;
    }

    public void setFaxContacto2(String faxContacto2) {
        this.faxContacto2 = faxContacto2;
    }

    public String getMailContacto2() {
        return mailContacto2;
    }

    public void setMailContacto2(String mailContacto2) {
        this.mailContacto2 = mailContacto2;
    }

    public String getCodDachser() {
        return codDachser;
    }

    public void setCodDachser(String codDachser) {
        this.codDachser = codDachser;
    }
}
