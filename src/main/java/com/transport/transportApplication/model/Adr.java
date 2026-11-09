package com.transport.transportApplication.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

public class Adr {
    @JsonProperty("ADR_EXPEDICION")
    private Long adrExpedicion;

    @JsonProperty("ADR_SECUENCIA")
    private Integer adrSecuencia;

    @JsonProperty("KEYADR_CLASES")
    private String keyadrClases;

    @JsonProperty("KEYADR_CLASIFICACION")
    private String keyadrClasificacion;

    @JsonProperty("ADR_CLASIF_DES")
    private String adrClasifDes;

    @JsonProperty("KEYADR_UN")
    private Integer keyadrUn;

    @JsonProperty("ADR_EMBALAJE")
    private String adrEmbalaje;

    @JsonProperty("ADR_BULTOS")
    private Integer adrBultos;

    @JsonProperty("ADR_KILOS")
    private BigDecimal adrKilos;

    @JsonProperty("ADR_PELIGROS")
    private String adrPeligros;

    @JsonProperty("ADR_TECNICO")
    private String adrTecnico;

    @JsonProperty("ADR_EMBALAJE_DESC")
    private String adrEmbalajeDesc;

    @JsonProperty("ADR_CATTRANS")
    private Integer adrCattrans;

    @JsonProperty("ADR_CRT")
    private String adrCrt;

    @JsonProperty("ADR_LQ")
    private Integer adrLq;

    @JsonProperty("KEYADR_UNSEC")
    private Integer keyadrUnsec;

    @JsonProperty("ADR_NOMBRE_SUSTANCIA_PRINCIPAL")
    private String adrNombreSustanciaPrincipal;

    @JsonProperty("ADR_TECNICO_GB")
    private String adrTecnicoGb;

    @JsonProperty("ADR_TECNICO_PT")
    private String adrTecnicoPt;

    @JsonProperty("ADR_ETIQUETA")
    private String adrEtiqueta;

    @JsonProperty("ADR_PMA")
    private Integer adrPma;

    @JsonProperty("ADR_FP")
    private Integer adrFp;

    @JsonProperty("ADR_CHECK_LITROS")
    private Integer adrCheckLitros;

    public Adr() {
    }

    public Long getAdrExpedicion() {
        return adrExpedicion;
    }

    public void setAdrExpedicion(Long adrExpedicion) {
        this.adrExpedicion = adrExpedicion;
    }

    public Integer getAdrSecuencia() {
        return adrSecuencia;
    }

    public void setAdrSecuencia(Integer adrSecuencia) {
        this.adrSecuencia = adrSecuencia;
    }

    public String getKeyadrClases() {
        return keyadrClases;
    }

    public void setKeyadrClases(String keyadrClases) {
        this.keyadrClases = keyadrClases;
    }

    public String getKeyadrClasificacion() {
        return keyadrClasificacion;
    }

    public void setKeyadrClasificacion(String keyadrClasificacion) {
        this.keyadrClasificacion = keyadrClasificacion;
    }

    public String getAdrClasifDes() {
        return adrClasifDes;
    }

    public void setAdrClasifDes(String adrClasifDes) {
        this.adrClasifDes = adrClasifDes;
    }

    public Integer getKeyadrUn() {
        return keyadrUn;
    }

    public void setKeyadrUn(Integer keyadrUn) {
        this.keyadrUn = keyadrUn;
    }

    public String getAdrEmbalaje() {
        return adrEmbalaje;
    }

    public void setAdrEmbalaje(String adrEmbalaje) {
        this.adrEmbalaje = adrEmbalaje;
    }

    public Integer getAdrBultos() {
        return adrBultos;
    }

    public void setAdrBultos(Integer adrBultos) {
        this.adrBultos = adrBultos;
    }

    public BigDecimal getAdrKilos() {
        return adrKilos;
    }

    public void setAdrKilos(BigDecimal adrKilos) {
        this.adrKilos = adrKilos;
    }

    public String getAdrPeligros() {
        return adrPeligros;
    }

    public void setAdrPeligros(String adrPeligros) {
        this.adrPeligros = adrPeligros;
    }

    public String getAdrTecnico() {
        return adrTecnico;
    }

    public void setAdrTecnico(String adrTecnico) {
        this.adrTecnico = adrTecnico;
    }

    public String getAdrEmbalajeDesc() {
        return adrEmbalajeDesc;
    }

    public void setAdrEmbalajeDesc(String adrEmbalajeDesc) {
        this.adrEmbalajeDesc = adrEmbalajeDesc;
    }

    public Integer getAdrCattrans() {
        return adrCattrans;
    }

    public void setAdrCattrans(Integer adrCattrans) {
        this.adrCattrans = adrCattrans;
    }

    public String getAdrCrt() {
        return adrCrt;
    }

    public void setAdrCrt(String adrCrt) {
        this.adrCrt = adrCrt;
    }

    public Integer getAdrLq() {
        return adrLq;
    }

    public void setAdrLq(Integer adrLq) {
        this.adrLq = adrLq;
    }

    public Integer getKeyadrUnsec() {
        return keyadrUnsec;
    }

    public void setKeyadrUnsec(Integer keyadrUnsec) {
        this.keyadrUnsec = keyadrUnsec;
    }

    public String getAdrNombreSustanciaPrincipal() {
        return adrNombreSustanciaPrincipal;
    }

    public void setAdrNombreSustanciaPrincipal(String adrNombreSustanciaPrincipal) {
        this.adrNombreSustanciaPrincipal = adrNombreSustanciaPrincipal;
    }

    public String getAdrTecnicoGb() {
        return adrTecnicoGb;
    }

    public void setAdrTecnicoGb(String adrTecnicoGb) {
        this.adrTecnicoGb = adrTecnicoGb;
    }

    public String getAdrTecnicoPt() {
        return adrTecnicoPt;
    }

    public void setAdrTecnicoPt(String adrTecnicoPt) {
        this.adrTecnicoPt = adrTecnicoPt;
    }

    public String getAdrEtiqueta() {
        return adrEtiqueta;
    }

    public void setAdrEtiqueta(String adrEtiqueta) {
        this.adrEtiqueta = adrEtiqueta;
    }

    public Integer getAdrPma() {
        return adrPma;
    }

    public void setAdrPma(Integer adrPma) {
        this.adrPma = adrPma;
    }

    public Integer getAdrFp() {
        return adrFp;
    }

    public void setAdrFp(Integer adrFp) {
        this.adrFp = adrFp;
    }

    public Integer getAdrCheckLitros() {
        return adrCheckLitros;
    }

    public void setAdrCheckLitros(Integer adrCheckLitros) {
        this.adrCheckLitros = adrCheckLitros;
    }
}
