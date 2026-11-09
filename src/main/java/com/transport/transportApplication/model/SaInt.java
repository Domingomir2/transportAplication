package com.transport.transportApplication.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class SaInt {
    @JsonProperty("EXPEDICION")
    private Long expedicion;

    @JsonProperty("KEYSA")
    private Integer keysa;

    @JsonProperty("SOCIO_PAG_IMPORTE")
    private BigDecimal socioPagImporte;

    @JsonProperty("SOCIO_PAG_CONCERTADO")
    private Integer socioPagConcertado;

    @JsonProperty("SOCIO_FAC_IMPORTE")
    private BigDecimal socioFacImporte;

    @JsonProperty("SOCIO_FAC_CONCERTADO")
    private Integer socioFacConcertado;

    @JsonProperty("CLIENTE_FAC_IMPORTE")
    private BigDecimal clienteFacImporte;

    @JsonProperty("IMPORTE_LIQUIDAR")
    private BigDecimal importeLiquidar;

    @JsonProperty("FACTURADO")
    private Integer facturado;

    @JsonProperty("LIQUIDADO")
    private Integer liquidado;

    @JsonProperty("FECHA")
    private LocalDateTime fecha;

    @JsonProperty("DONDEAPLICA")
    private String dondeaplica;

    @JsonProperty("BORRADO")
    private Integer borrado;

    public SaInt() {
    }

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

    public BigDecimal getSocioPagImporte() {
        return socioPagImporte;
    }

    public void setSocioPagImporte(BigDecimal socioPagImporte) {
        this.socioPagImporte = socioPagImporte;
    }

    public Integer getSocioPagConcertado() {
        return socioPagConcertado;
    }

    public void setSocioPagConcertado(Integer socioPagConcertado) {
        this.socioPagConcertado = socioPagConcertado;
    }

    public BigDecimal getSocioFacImporte() {
        return socioFacImporte;
    }

    public void setSocioFacImporte(BigDecimal socioFacImporte) {
        this.socioFacImporte = socioFacImporte;
    }

    public Integer getSocioFacConcertado() {
        return socioFacConcertado;
    }

    public void setSocioFacConcertado(Integer socioFacConcertado) {
        this.socioFacConcertado = socioFacConcertado;
    }

    public BigDecimal getClienteFacImporte() {
        return clienteFacImporte;
    }

    public void setClienteFacImporte(BigDecimal clienteFacImporte) {
        this.clienteFacImporte = clienteFacImporte;
    }

    public BigDecimal getImporteLiquidar() {
        return importeLiquidar;
    }

    public void setImporteLiquidar(BigDecimal importeLiquidar) {
        this.importeLiquidar = importeLiquidar;
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

    public String getDondeaplica() {
        return dondeaplica;
    }

    public void setDondeaplica(String dondeaplica) {
        this.dondeaplica = dondeaplica;
    }

    public Integer getBorrado() {
        return borrado;
    }

    public void setBorrado(Integer borrado) {
        this.borrado = borrado;
    }
}
