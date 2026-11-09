package com.transport.transportApplication.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Fti {
    @JsonProperty("KEYFTI")
    private String keyfti;

    @JsonProperty("DESFTI")
    private String desfti;

    public Fti() {
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
}
