package com.transport.transportApplication.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Sa {
    @JsonProperty("EXPEDICION")
    private Long expedicion;

    @JsonProperty("KEYSA")
    private Integer keysa;

    @JsonProperty("IMPORTEP")
    private BigDecimal importep;

    @JsonProperty("IMPORTED")
    private BigDecimal imported;

    @JsonProperty("CONCERTADO")
    private Integer concertado;

    @JsonProperty("SEMPOR_SA")
    private String semporSa;

    @JsonProperty("FACTURADO")
    private Integer facturado;

    @JsonProperty("LIQUIDADO")
    private Integer liquidado;

    @JsonProperty("FECHA")
    private LocalDateTime fecha;

    @JsonProperty("IMPORTEPL")
    private BigDecimal importepl;

    @JsonProperty("IMPORTEDL")
    private BigDecimal importedl;

    @JsonProperty("CONCERTADOL")
    private Integer concertadol;

    @JsonProperty("CANTIDADL")
    private BigDecimal cantidadl;

    @JsonProperty("KEYCLI")
    private String keycli;

    @JsonProperty("KEYDEL")
    private String keydel;

    @JsonProperty("BORRADO")
    private String borrado;

    @JsonProperty("PLAZA_LIQUIDACION")
    private String plazaLiquidacion;

    public Long getExpedicion() {
        return expedicion;
    }

    public void setExpedicion(Long expedicion) {
        this.expedicion = expedicion;
    }

    public Integer getKeysa() {
        return keysa;
    }

    public void setKeysa(Integer keysa) {
        this.keysa = keysa;
    }

    public BigDecimal getImportep() {
        return importep;
    }

    public void setImportep(BigDecimal importep) {
        this.importep = importep;
    }

    public BigDecimal getImported() {
        return imported;
    }

    public void setImported(BigDecimal imported) {
        this.imported = imported;
    }

    public Integer getConcertado() {
        return concertado;
    }

    public void setConcertado(Integer concertado) {
        this.concertado = concertado;
    }

    public String getSemporSa() {
        return semporSa;
    }

    public void setSemporSa(String semporSa) {
        this.semporSa = semporSa;
    }

    public Integer getFacturado() {
        return facturado;
    }

    public void setFacturado(Integer facturado) {
        this.facturado = facturado;
    }

    public Integer getLiquidado() {
        return liquidado;
    }

    public void setLiquidado(Integer liquidado) {
        this.liquidado = liquidado;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getImportepl() {
        return importepl;
    }

    public void setImportepl(BigDecimal importepl) {
        this.importepl = importepl;
    }

    public BigDecimal getImportedl() {
        return importedl;
    }

    public void setImportedl(BigDecimal importedl) {
        this.importedl = importedl;
    }

    public Integer getConcertadol() {
        return concertadol;
    }

    public void setConcertadol(Integer concertadol) {
        this.concertadol = concertadol;
    }

    public BigDecimal getCantidadl() {
        return cantidadl;
    }

    public void setCantidadl(BigDecimal cantidadl) {
        this.cantidadl = cantidadl;
    }

    public String getKeycli() {
        return keycli;
    }

    public void setKeycli(String keycli) {
        this.keycli = keycli;
    }

    public String getKeydel() {
        return keydel;
    }

    public void setKeydel(String keydel) {
        this.keydel = keydel;
    }

    public String getBorrado() {
        return borrado;
    }

    public void setBorrado(String borrado) {
        this.borrado = borrado;
    }

    public String getPlazaLiquidacion() {
        return plazaLiquidacion;
    }

    public void setPlazaLiquidacion(String plazaLiquidacion) {
        this.plazaLiquidacion = plazaLiquidacion;
    }
}
