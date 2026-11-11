package com.transport.transportApplication.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class IeEdp {
    @JsonProperty("EXPEDICION")
    private Long expedicion;

    @JsonProperty("FACTURA")
    private String factura;

    @JsonProperty("FECHA_FACTURA")
    private LocalDateTime fechaFactura;

    @JsonProperty("IMPORTE")
    private BigDecimal importe;

    @JsonProperty("KEYCLIIMP")
    private String keycliimp;

    @JsonProperty("KEYCLIEXP")
    private String keycliexp;

    @JsonProperty("DIVISA")
    private String divisa;

    public Long getExpedicion() {
        return expedicion;
    }

    public void setExpedicion(Long expedicion) {
        this.expedicion = expedicion;
    }

    public String getFactura() {
        return factura;
    }

    public void setFactura(String factura) {
        this.factura = factura;
    }

    public LocalDateTime getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(LocalDateTime fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public String getKeycliimp() {
        return keycliimp;
    }

    public void setKeycliimp(String keycliimp) {
        this.keycliimp = keycliimp;
    }

    public String getKeycliexp() {
        return keycliexp;
    }

    public void setKeycliexp(String keycliexp) {
        this.keycliexp = keycliexp;
    }

    public String getDivisa() {
        return divisa;
    }

    public void setDivisa(String divisa) {
        this.divisa = divisa;
    }
}
