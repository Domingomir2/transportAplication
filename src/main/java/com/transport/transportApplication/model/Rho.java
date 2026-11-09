package com.transport.transportApplication.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Rho {
    @JsonProperty("EXPEDICION")
    private Long expedicion;

    @JsonProperty("KEYPAI1")
    private String keypai1;

    @JsonProperty("CODPLA1")
    private String codpla1;

    @JsonProperty("KEYPAI2")
    private String keypai2;

    @JsonProperty("CODPLA2")
    private String codpla2;

    @JsonProperty("FECHA")
    private LocalDateTime fecha;

    @JsonProperty("ORIGEN")
    private String origen;

    @JsonProperty("DESTINO")
    private String destino;

    @JsonProperty("CODREMI")
    private String codremi;

    @JsonProperty("NIFREMI")
    private String nifremi;

    @JsonProperty("NOMRTE")
    private String nomrte;

    @JsonProperty("DOMRTE")
    private String domrte;

    @JsonProperty("POBRTE")
    private String pobrte;

    @JsonProperty("CODCONS")
    private String codcons;

    @JsonProperty("NIFCONS")
    private String nifcons;

    @JsonProperty("NOMCONS")
    private String nomcons;

    @JsonProperty("DOMCONS")
    private String domcons;

    @JsonProperty("POBCONS")
    private String pobcons;

    @JsonProperty("CP_ORI")
    private String cpOri;

    @JsonProperty("CP_REX")
    private String cpRex;

    @JsonProperty("BULTOS")
    private Integer bultos;

    @JsonProperty("PALETS")
    private Integer palets;

    @JsonProperty("MERCAN")
    private String mercan;

    @JsonProperty("VOL")
    private BigDecimal vol;

    @JsonProperty("ML")
    private BigDecimal ml;

    @JsonProperty("KILOS")
    private BigDecimal kilos;

    @JsonProperty("PVKILOS")
    private BigDecimal pvkilos;

    @JsonProperty("PRKILOS")
    private BigDecimal prkilos;

    @JsonProperty("SEMPOR")
    private String sempor;

    @JsonProperty("SEMPOR_R")
    private String semporR;

    @JsonProperty("SEMPOR_CR")
    private String semporCr;

    @JsonProperty("VALSEMER")
    private BigDecimal valsemer;

    @JsonProperty("PORTEPAG")
    private BigDecimal portepag;

    @JsonProperty("SEGURPAG")
    private BigDecimal segurpag;

    @JsonProperty("IMPSEMERP")
    private BigDecimal impsemerp;

    @JsonProperty("REEXPAG")
    private BigDecimal reexpag;

    @JsonProperty("COMREE_P")
    private BigDecimal comreeP;

    @JsonProperty("PORTEPAG_1")
    private BigDecimal portepag1;

    @JsonProperty("PORTEPAG_2")
    private BigDecimal portepag2;

    @JsonProperty("IVAPAG")
    private BigDecimal ivapag;

    @JsonProperty("PORTEDEB")
    private BigDecimal portedeb;

    @JsonProperty("SEGURDEB")
    private BigDecimal segurdeb;

    @JsonProperty("IMPSEMERD")
    private BigDecimal impsemerd;

    @JsonProperty("DESEMBOL")
    private BigDecimal desembol;

    @JsonProperty("REEMBOLSO")
    private BigDecimal reembolso;

    @JsonProperty("COMREE_D")
    private BigDecimal comreeD;

    @JsonProperty("REEXDEB")
    private BigDecimal reexdeb;

    @JsonProperty("PORTEDEB_1")
    private BigDecimal portedeb1;

    @JsonProperty("PORTEDEB_2")
    private BigDecimal portedeb2;

    @JsonProperty("IVADEB")
    private BigDecimal ivadeb;

    @JsonProperty("REFERENCIA")
    private String referencia;

    @JsonProperty("OBSER1")
    private String obser1;

    @JsonProperty("OBSER2")
    private String obser2;

    @JsonProperty("FECHA_EA")
    private LocalDateTime fechaEa;

    @JsonProperty("KEYTSV")
    private Integer keytsv;

    @JsonProperty("KEYCLIFAC")
    private String keyclifac;

    @JsonProperty("KEYRCO")
    private Long keyrco;

    @JsonProperty("NENVIO")
    private String nenvio;

    @JsonProperty("PLKILOS")
    private BigDecimal plkilos;

    @JsonProperty("PDKILOS")
    private BigDecimal pdkilos;

    @JsonProperty("RVOL")
    private BigDecimal rvol;

    @JsonProperty("KEYACT")
    private Integer keyact;

    @JsonProperty("KILOSDOC")
    private BigDecimal kilosdoc;

    @JsonProperty("VOLDOC")
    private BigDecimal voldoc;

    @JsonProperty("KEYPROVE")
    private Integer keyprove;

    @JsonProperty("KEY_IE_IEC")
    private String keyIeIec;

    @JsonProperty("KEYTST")
    private Integer keytst;

    @JsonProperty("FACTURA_C")
    private String facturaC;

    @JsonProperty("FECHA_FACTURA_C")
    private LocalDateTime fechaFacturaC;

    @JsonProperty("IMP_FACTURA_C")
    private BigDecimal impFacturaC;

    @JsonProperty("MINREEXPAG")
    private BigDecimal minreexpag;

    @JsonProperty("MINREEXDEB")
    private BigDecimal minreexdeb;

    @JsonProperty("RECARGO_COMBUSTIBLE")
    private BigDecimal recargoCombustible;

    @JsonProperty("KEYIE_DUA_I")
    private Long keyieDuaI;

    @JsonProperty("KEYIE_DUA_E")
    private Long keyieDuaE;

    @JsonProperty("VD_ADUANAS")
    private BigDecimal vdAduanas;

    @JsonProperty("KEYEMB")
    private String keyemb;

    @JsonProperty("NUM_EMBALAJES")
    private Integer numEmbalajes;

    @JsonProperty("NOMORD")
    private String nomord;

    @JsonProperty("DIRORD")
    private String dirord;

    @JsonProperty("LOCORD")
    private String locord;

    @JsonProperty("PAIORD")
    private String paiord;

    @JsonProperty("KEYCLI_ORD")
    private String keycliOrd;

    @JsonProperty("F_GRABA")
    private LocalDateTime fGraba;

    @JsonProperty("AG_DACH_CLAS")
    private String agDachClas;

    @JsonProperty("CLI_DOM_CONS")
    private String cliDomCons;

    @JsonProperty("CLI_DOM_RTE")
    private String cliDomRte;

    public Rho() {
    }

    public Long getExpedicion() {
        return expedicion;
    }

    public void setExpedicion(Long expedicion) {
        this.expedicion = expedicion;
    }

    public String getKeypai1() {
        return keypai1;
    }

    public void setKeypai1(String keypai1) {
        this.keypai1 = keypai1;
    }

    public String getCodpla1() {
        return codpla1;
    }

    public void setCodpla1(String codpla1) {
        this.codpla1 = codpla1;
    }

    public String getKeypai2() {
        return keypai2;
    }

    public void setKeypai2(String keypai2) {
        this.keypai2 = keypai2;
    }

    public String getCodpla2() {
        return codpla2;
    }

    public void setCodpla2(String codpla2) {
        this.codpla2 = codpla2;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getCodremi() {
        return codremi;
    }

    public void setCodremi(String codremi) {
        this.codremi = codremi;
    }

    public String getNifremi() {
        return nifremi;
    }

    public void setNifremi(String nifremi) {
        this.nifremi = nifremi;
    }

    public String getNomrte() {
        return nomrte;
    }

    public void setNomrte(String nomrte) {
        this.nomrte = nomrte;
    }

    public String getDomrte() {
        return domrte;
    }

    public void setDomrte(String domrte) {
        this.domrte = domrte;
    }

    public String getPobrte() {
        return pobrte;
    }

    public void setPobrte(String pobrte) {
        this.pobrte = pobrte;
    }

    public String getCodcons() {
        return codcons;
    }

    public void setCodcons(String codcons) {
        this.codcons = codcons;
    }

    public String getNifcons() {
        return nifcons;
    }

    public void setNifcons(String nifcons) {
        this.nifcons = nifcons;
    }

    public String getNomcons() {
        return nomcons;
    }

    public void setNomcons(String nomcons) {
        this.nomcons = nomcons;
    }

    public String getDomcons() {
        return domcons;
    }

    public void setDomcons(String domcons) {
        this.domcons = domcons;
    }

    public String getPobcons() {
        return pobcons;
    }

    public void setPobcons(String pobcons) {
        this.pobcons = pobcons;
    }

    public String getCpOri() {
        return cpOri;
    }

    public void setCpOri(String cpOri) {
        this.cpOri = cpOri;
    }

    public String getCpRex() {
        return cpRex;
    }

    public void setCpRex(String cpRex) {
        this.cpRex = cpRex;
    }

    public Integer getBultos() {
        return bultos;
    }

    public void setBultos(Integer bultos) {
        this.bultos = bultos;
    }

    public Integer getPalets() {
        return palets;
    }

    public void setPalets(Integer palets) {
        this.palets = palets;
    }

    public String getMercan() {
        return mercan;
    }

    public void setMercan(String mercan) {
        this.mercan = mercan;
    }

    public BigDecimal getVol() {
        return vol;
    }

    public void setVol(BigDecimal vol) {
        this.vol = vol;
    }

    public BigDecimal getMl() {
        return ml;
    }

    public void setMl(BigDecimal ml) {
        this.ml = ml;
    }

    public BigDecimal getKilos() {
        return kilos;
    }

    public void setKilos(BigDecimal kilos) {
        this.kilos = kilos;
    }

    public BigDecimal getPvkilos() {
        return pvkilos;
    }

    public void setPvkilos(BigDecimal pvkilos) {
        this.pvkilos = pvkilos;
    }

    public BigDecimal getPrkilos() {
        return prkilos;
    }

    public void setPrkilos(BigDecimal prkilos) {
        this.prkilos = prkilos;
    }

    public String getSempor() {
        return sempor;
    }

    public void setSempor(String sempor) {
        this.sempor = sempor;
    }

    public String getSemporR() {
        return semporR;
    }

    public void setSemporR(String semporR) {
        this.semporR = semporR;
    }

    public String getSemporCr() {
        return semporCr;
    }

    public void setSemporCr(String semporCr) {
        this.semporCr = semporCr;
    }

    public BigDecimal getValsemer() {
        return valsemer;
    }

    public void setValsemer(BigDecimal valsemer) {
        this.valsemer = valsemer;
    }

    public BigDecimal getPortepag() {
        return portepag;
    }

    public void setPortepag(BigDecimal portepag) {
        this.portepag = portepag;
    }

    public BigDecimal getSegurpag() {
        return segurpag;
    }

    public void setSegurpag(BigDecimal segurpag) {
        this.segurpag = segurpag;
    }

    public BigDecimal getImpsemerp() {
        return impsemerp;
    }

    public void setImpsemerp(BigDecimal impsemerp) {
        this.impsemerp = impsemerp;
    }

    public BigDecimal getReexpag() {
        return reexpag;
    }

    public void setReexpag(BigDecimal reexpag) {
        this.reexpag = reexpag;
    }

    public BigDecimal getComreeP() {
        return comreeP;
    }

    public void setComreeP(BigDecimal comreeP) {
        this.comreeP = comreeP;
    }

    public BigDecimal getPortepag1() {
        return portepag1;
    }

    public void setPortepag1(BigDecimal portepag1) {
        this.portepag1 = portepag1;
    }

    public BigDecimal getPortepag2() {
        return portepag2;
    }

    public void setPortepag2(BigDecimal portepag2) {
        this.portepag2 = portepag2;
    }

    public BigDecimal getIvapag() {
        return ivapag;
    }

    public void setIvapag(BigDecimal ivapag) {
        this.ivapag = ivapag;
    }

    public BigDecimal getPortedeb() {
        return portedeb;
    }

    public void setPortedeb(BigDecimal portedeb) {
        this.portedeb = portedeb;
    }

    public BigDecimal getSegurdeb() {
        return segurdeb;
    }

    public void setSegurdeb(BigDecimal segurdeb) {
        this.segurdeb = segurdeb;
    }

    public BigDecimal getImpsemerd() {
        return impsemerd;
    }

    public void setImpsemerd(BigDecimal impsemerd) {
        this.impsemerd = impsemerd;
    }

    public BigDecimal getDesembol() {
        return desembol;
    }

    public void setDesembol(BigDecimal desembol) {
        this.desembol = desembol;
    }

    public BigDecimal getReembolso() {
        return reembolso;
    }

    public void setReembolso(BigDecimal reembolso) {
        this.reembolso = reembolso;
    }

    public BigDecimal getComreeD() {
        return comreeD;
    }

    public void setComreeD(BigDecimal comreeD) {
        this.comreeD = comreeD;
    }

    public BigDecimal getReexdeb() {
        return reexdeb;
    }

    public void setReexdeb(BigDecimal reexdeb) {
        this.reexdeb = reexdeb;
    }

    public BigDecimal getPortedeb1() {
        return portedeb1;
    }

    public void setPortedeb1(BigDecimal portedeb1) {
        this.portedeb1 = portedeb1;
    }

    public BigDecimal getPortedeb2() {
        return portedeb2;
    }

    public void setPortedeb2(BigDecimal portedeb2) {
        this.portedeb2 = portedeb2;
    }

    public BigDecimal getIvadeb() {
        return ivadeb;
    }

    public void setIvadeb(BigDecimal ivadeb) {
        this.ivadeb = ivadeb;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getObser1() {
        return obser1;
    }

    public void setObser1(String obser1) {
        this.obser1 = obser1;
    }

    public String getObser2() {
        return obser2;
    }

    public void setObser2(String obser2) {
        this.obser2 = obser2;
    }

    public LocalDateTime getFechaEa() {
        return fechaEa;
    }

    public void setFechaEa(LocalDateTime fechaEa) {
        this.fechaEa = fechaEa;
    }

    public Integer getKeytsv() {
        return keytsv;
    }

    public void setKeytsv(Integer keytsv) {
        this.keytsv = keytsv;
    }

    public String getKeyclifac() {
        return keyclifac;
    }

    public void setKeyclifac(String keyclifac) {
        this.keyclifac = keyclifac;
    }

    public Long getKeyrco() {
        return keyrco;
    }

    public void setKeyrco(Long keyrco) {
        this.keyrco = keyrco;
    }

    public String getNenvio() {
        return nenvio;
    }

    public void setNenvio(String nenvio) {
        this.nenvio = nenvio;
    }

    public BigDecimal getPlkilos() {
        return plkilos;
    }

    public void setPlkilos(BigDecimal plkilos) {
        this.plkilos = plkilos;
    }

    public BigDecimal getPdkilos() {
        return pdkilos;
    }

    public void setPdkilos(BigDecimal pdkilos) {
        this.pdkilos = pdkilos;
    }

    public BigDecimal getRvol() {
        return rvol;
    }

    public void setRvol(BigDecimal rvol) {
        this.rvol = rvol;
    }

    public Integer getKeyact() {
        return keyact;
    }

    public void setKeyact(Integer keyact) {
        this.keyact = keyact;
    }

    public BigDecimal getKilosdoc() {
        return kilosdoc;
    }

    public void setKilosdoc(BigDecimal kilosdoc) {
        this.kilosdoc = kilosdoc;
    }

    public BigDecimal getVoldoc() {
        return voldoc;
    }

    public void setVoldoc(BigDecimal voldoc) {
        this.voldoc = voldoc;
    }

    public Integer getKeyprove() {
        return keyprove;
    }

    public void setKeyprove(Integer keyprove) {
        this.keyprove = keyprove;
    }

    public String getKeyIeIec() {
        return keyIeIec;
    }

    public void setKeyIeIec(String keyIeIec) {
        this.keyIeIec = keyIeIec;
    }

    public Integer getKeytst() {
        return keytst;
    }

    public void setKeytst(Integer keytst) {
        this.keytst = keytst;
    }

    public String getFacturaC() {
        return facturaC;
    }

    public void setFacturaC(String facturaC) {
        this.facturaC = facturaC;
    }

    public LocalDateTime getFechaFacturaC() {
        return fechaFacturaC;
    }

    public void setFechaFacturaC(LocalDateTime fechaFacturaC) {
        this.fechaFacturaC = fechaFacturaC;
    }

    public BigDecimal getImpFacturaC() {
        return impFacturaC;
    }

    public void setImpFacturaC(BigDecimal impFacturaC) {
        this.impFacturaC = impFacturaC;
    }

    public BigDecimal getMinreexpag() {
        return minreexpag;
    }

    public void setMinreexpag(BigDecimal minreexpag) {
        this.minreexpag = minreexpag;
    }

    public BigDecimal getMinreexdeb() {
        return minreexdeb;
    }

    public void setMinreexdeb(BigDecimal minreexdeb) {
        this.minreexdeb = minreexdeb;
    }

    public BigDecimal getRecargoCombustible() {
        return recargoCombustible;
    }

    public void setRecargoCombustible(BigDecimal recargoCombustible) {
        this.recargoCombustible = recargoCombustible;
    }

    public Long getKeyieDuaI() {
        return keyieDuaI;
    }

    public void setKeyieDuaI(Long keyieDuaI) {
        this.keyieDuaI = keyieDuaI;
    }

    public Long getKeyieDuaE() {
        return keyieDuaE;
    }

    public void setKeyieDuaE(Long keyieDuaE) {
        this.keyieDuaE = keyieDuaE;
    }

    public BigDecimal getVdAduanas() {
        return vdAduanas;
    }

    public void setVdAduanas(BigDecimal vdAduanas) {
        this.vdAduanas = vdAduanas;
    }

    public String getKeyemb() {
        return keyemb;
    }

    public void setKeyemb(String keyemb) {
        this.keyemb = keyemb;
    }

    public Integer getNumEmbalajes() {
        return numEmbalajes;
    }

    public void setNumEmbalajes(Integer numEmbalajes) {
        this.numEmbalajes = numEmbalajes;
    }

    public String getNomord() {
        return nomord;
    }

    public void setNomord(String nomord) {
        this.nomord = nomord;
    }

    public String getDirord() {
        return dirord;
    }

    public void setDirord(String dirord) {
        this.dirord = dirord;
    }

    public String getLocord() {
        return locord;
    }

    public void setLocord(String locord) {
        this.locord = locord;
    }

    public String getPaiord() {
        return paiord;
    }

    public void setPaiord(String paiord) {
        this.paiord = paiord;
    }

    public String getKeycliOrd() {
        return keycliOrd;
    }

    public void setKeycliOrd(String keycliOrd) {
        this.keycliOrd = keycliOrd;
    }

    public LocalDateTime getFGraba() {
        return fGraba;
    }

    public void setFGraba(LocalDateTime fGraba) {
        this.fGraba = fGraba;
    }

    public String getAgDachClas() {
        return agDachClas;
    }

    public void setAgDachClas(String agDachClas) {
        this.agDachClas = agDachClas;
    }

    public String getCliDomCons() {
        return cliDomCons;
    }

    public void setCliDomCons(String cliDomCons) {
        this.cliDomCons = cliDomCons;
    }

    public String getCliDomRte() {
        return cliDomRte;
    }

    public void setCliDomRte(String cliDomRte) {
        this.cliDomRte = cliDomRte;
    }
}
