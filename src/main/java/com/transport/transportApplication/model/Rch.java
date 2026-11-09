package com.transport.transportApplication.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Rch {
    @JsonProperty("KEYRCO")
    private Long keyrco;

    @JsonProperty("USR_PLAZA")
    private String usrPlaza;

    @JsonProperty("FECHA")
    private LocalDateTime fecha;

    @JsonProperty("KEYCLI")
    private String keycli;

    @JsonProperty("NOMBRE")
    private String nombre;

    @JsonProperty("DIRECCION")
    private String direccion;

    @JsonProperty("POBLACION")
    private String poblacion;

    @JsonProperty("CONTACTO")
    private String contacto;

    @JsonProperty("TELEFON1")
    private String telefon1;

    @JsonProperty("TELEFON2")
    private String telefon2;

    @JsonProperty("NIFREMI")
    private String nifremi;

    @JsonProperty("KEYREP")
    private Integer keyrep;

    @JsonProperty("CODPOS")
    private String codpos;

    @JsonProperty("KEYFTI")
    private String keyfti;

    @JsonProperty("DESFTI")
    private String desfti;

    @JsonProperty("AMPFTI")
    private String ampfti;

    @JsonProperty("KEYTSV")
    private Integer keytsv;

    @JsonProperty("PLAZA_ORD")
    private String plazaOrd;

    @JsonProperty("KEYACT")
    private Integer keyact;

    @JsonProperty("KEYCLI_ORD")
    private String keycliOrd;

    @JsonProperty("NOMBRE_ORD")
    private String nombreOrd;

    @JsonProperty("DIRECCION_ORD")
    private String direccionOrd;

    @JsonProperty("POBLACION_ORD")
    private String poblacionOrd;

    @JsonProperty("CODPOS_ORD")
    private String codposOrd;

    @JsonProperty("CONTACTO_ORD")
    private String contactoOrd;

    @JsonProperty("TELEFON1_ORD")
    private String telefon1Ord;

    @JsonProperty("TELEFON2_ORD")
    private String telefon2Ord;

    @JsonProperty("NIF_ORD")
    private String nifOrd;

    @JsonProperty("KEYPAI1")
    private String keypai1;

    @JsonProperty("EXPEDICION")
    private BigDecimal expedicion;

    @JsonProperty("NAME1_RTE")
    private String name1Rte;

    @JsonProperty("NAME2_RTE")
    private String name2Rte;

    @JsonProperty("DIREC2_RTE")
    private String direc2Rte;

    @JsonProperty("NOMORD2")
    private String nomord2;

    @JsonProperty("NOMORD3")
    private String nomord3;

    @JsonProperty("DOMORD2")
    private String domord2;

    @JsonProperty("DOMORD3")
    private String domord3;

    @JsonProperty("KEYPAIORD")
    private String keypaiord;

    @JsonProperty("CLI_DOM_RTE")
    private String cliDomRte;

    @JsonProperty("CLI_DOM_ORD")
    private String cliDomOrd;

    public Rch() {
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

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getTelefon1() {
        return telefon1;
    }

    public void setTelefon1(String telefon1) {
        this.telefon1 = telefon1;
    }

    public String getTelefon2() {
        return telefon2;
    }

    public void setTelefon2(String telefon2) {
        this.telefon2 = telefon2;
    }

    public String getNifremi() {
        return nifremi;
    }

    public void setNifremi(String nifremi) {
        this.nifremi = nifremi;
    }

    public Integer getKeyrep() {
        return keyrep;
    }

    public void setKeyrep(Integer keyrep) {
        this.keyrep = keyrep;
    }

    public String getCodpos() {
        return codpos;
    }

    public void setCodpos(String codpos) {
        this.codpos = codpos;
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

    public Integer getKeytsv() {
        return keytsv;
    }

    public void setKeytsv(Integer keytsv) {
        this.keytsv = keytsv;
    }

    public String getPlazaOrd() {
        return plazaOrd;
    }

    public void setPlazaOrd(String plazaOrd) {
        this.plazaOrd = plazaOrd;
    }

    public Integer getKeyact() {
        return keyact;
    }

    public void setKeyact(Integer keyact) {
        this.keyact = keyact;
    }

    public String getKeycliOrd() {
        return keycliOrd;
    }

    public void setKeycliOrd(String keycliOrd) {
        this.keycliOrd = keycliOrd;
    }

    public String getNombreOrd() {
        return nombreOrd;
    }

    public void setNombreOrd(String nombreOrd) {
        this.nombreOrd = nombreOrd;
    }

    public String getDireccionOrd() {
        return direccionOrd;
    }

    public void setDireccionOrd(String direccionOrd) {
        this.direccionOrd = direccionOrd;
    }

    public String getPoblacionOrd() {
        return poblacionOrd;
    }

    public void setPoblacionOrd(String poblacionOrd) {
        this.poblacionOrd = poblacionOrd;
    }

    public String getCodposOrd() {
        return codposOrd;
    }

    public void setCodposOrd(String codposOrd) {
        this.codposOrd = codposOrd;
    }

    public String getContactoOrd() {
        return contactoOrd;
    }

    public void setContactoOrd(String contactoOrd) {
        this.contactoOrd = contactoOrd;
    }

    public String getTelefon1Ord() {
        return telefon1Ord;
    }

    public void setTelefon1Ord(String telefon1Ord) {
        this.telefon1Ord = telefon1Ord;
    }

    public String getTelefon2Ord() {
        return telefon2Ord;
    }

    public void setTelefon2Ord(String telefon2Ord) {
        this.telefon2Ord = telefon2Ord;
    }

    public String getNifOrd() {
        return nifOrd;
    }

    public void setNifOrd(String nifOrd) {
        this.nifOrd = nifOrd;
    }

    public String getKeypai1() {
        return keypai1;
    }

    public void setKeypai1(String keypai1) {
        this.keypai1 = keypai1;
    }

    public BigDecimal getExpedicion() {
        return expedicion;
    }

    public void setExpedicion(BigDecimal expedicion) {
        this.expedicion = expedicion;
    }

    public String getName1Rte() {
        return name1Rte;
    }

    public void setName1Rte(String name1Rte) {
        this.name1Rte = name1Rte;
    }

    public String getName2Rte() {
        return name2Rte;
    }

    public void setName2Rte(String name2Rte) {
        this.name2Rte = name2Rte;
    }

    public String getDirec2Rte() {
        return direc2Rte;
    }

    public void setDirec2Rte(String direc2Rte) {
        this.direc2Rte = direc2Rte;
    }

    public String getNomord2() {
        return nomord2;
    }

    public void setNomord2(String nomord2) {
        this.nomord2 = nomord2;
    }

    public String getNomord3() {
        return nomord3;
    }

    public void setNomord3(String nomord3) {
        this.nomord3 = nomord3;
    }

    public String getDomord2() {
        return domord2;
    }

    public void setDomord2(String domord2) {
        this.domord2 = domord2;
    }

    public String getDomord3() {
        return domord3;
    }

    public void setDomord3(String domord3) {
        this.domord3 = domord3;
    }

    public String getKeypaiord() {
        return keypaiord;
    }

    public void setKeypaiord(String keypaiord) {
        this.keypaiord = keypaiord;
    }

    public String getCliDomRte() {
        return cliDomRte;
    }

    public void setCliDomRte(String cliDomRte) {
        this.cliDomRte = cliDomRte;
    }

    public String getCliDomOrd() {
        return cliDomOrd;
    }

    public void setCliDomOrd(String cliDomOrd) {
        this.cliDomOrd = cliDomOrd;
    }
}
