package com.transport.transportApplication.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Cli {
    @JsonProperty("KEYCLI")
    private String keycli;

    @JsonProperty("NOMBRE")
    private String nombre;

    @JsonProperty("KEYDEL")
    private String keydel;

    public Cli() {
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

    public String getKeydel() {
        return keydel;
    }

    public void setKeydel(String keydel) {
        this.keydel = keydel;
    }
}
