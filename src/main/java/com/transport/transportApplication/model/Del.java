package com.transport.transportApplication.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Del {
    @JsonProperty("KEYDEL")
    private String keydel;

    @JsonProperty("NOMBRE")
    private String nombre;

    @JsonProperty("DELPOB")
    private String delpob;

    @JsonProperty("KEYPAI")
    private String keypai;

    @JsonProperty("DIRECCION")
    private String direccion;

    @JsonProperty("POBLACION")
    private String poblacion;

    @JsonProperty("CODPOS")
    private String codpos;

    @JsonProperty("NIFDNI")
    private String nifdni;

    @JsonProperty("TELEFON1")
    private String telefon1;

    @JsonProperty("FAX")
    private String fax;

    @JsonProperty("INTERNACIONAL")
    private Integer internacional;

    @JsonProperty("PRINCIPAL_RECOGIDAS")
    private String principalRecogidas;

    @JsonProperty("PRINCIPAL_LECTURAS")
    private String principalLecturas;

    @JsonProperty("COD_DACHSER")
    private String codDachser;

    @JsonProperty("DEL_PRINCIPAL_INTER")
    private String delPrincipalInter;

    @JsonProperty("IDENT_DACHSER")
    private String identDachser;

    public Del() {
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

    public String getDelpob() {
        return delpob;
    }

    public void setDelpob(String delpob) {
        this.delpob = delpob;
    }

    public String getKeypai() {
        return keypai;
    }

    public void setKeypai(String keypai) {
        this.keypai = keypai;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public String getCodpos() {
        return codpos;
    }

    public void setCodpos(String codpos) {
        this.codpos = codpos;
    }

    public String getNifdni() {
        return nifdni;
    }

    public void setNifdni(String nifdni) {
        this.nifdni = nifdni;
    }

    public String getTelefon1() {
        return telefon1;
    }

    public void setTelefon1(String telefon1) {
        this.telefon1 = telefon1;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public Integer getInternacional() {
        return internacional;
    }

    public void setInternacional(Integer internacional) {
        this.internacional = internacional;
    }

    public String getPrincipalRecogidas() {
        return principalRecogidas;
    }

    public void setPrincipalRecogidas(String principalRecogidas) {
        this.principalRecogidas = principalRecogidas;
    }

    public String getPrincipalLecturas() {
        return principalLecturas;
    }

    public void setPrincipalLecturas(String principalLecturas) {
        this.principalLecturas = principalLecturas;
    }

    public String getCodDachser() {
        return codDachser;
    }

    public void setCodDachser(String codDachser) {
        this.codDachser = codDachser;
    }

    public String getDelPrincipalInter() {
        return delPrincipalInter;
    }

    public void setDelPrincipalInter(String delPrincipalInter) {
        this.delPrincipalInter = delPrincipalInter;
    }

    public String getIdentDachser() {
        return identDachser;
    }

    public void setIdentDachser(String identDachser) {
        this.identDachser = identDachser;
    }
}
