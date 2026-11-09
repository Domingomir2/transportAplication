package com.transport.transportApplication.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SamNew {
    @JsonProperty("KEYSAM")
    private Integer keysam;

    @JsonProperty("DESCRIP")
    private String descrip;

    @JsonProperty("KEYSAM_DOM")
    private String keysamDom;

    public SamNew() {
    }

    public Integer getKeysam() {
        return keysam;
    }

    public void setKeysam(Integer keysam) {
        this.keysam = keysam;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public String getKeysamDom() {
        return keysamDom;
    }

    public void setKeysamDom(String keysamDom) {
        this.keysamDom = keysamDom;
    }
}
