package com.transport.transportApplication.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Act {
    @JsonProperty("KEYACT")
    private Integer keyact;

    @JsonProperty("DESCRIP")
    private String descrip;

    public Act() {
    }

    public Integer getKeyact() {
        return keyact;
    }

    public void setKeyact(Integer keyact) {
        this.keyact = keyact;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }
}
