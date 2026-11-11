package com.transport.transportApplication.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AdrClases {
    @JsonProperty("KEYADR_CLASES")
    private String keyadrClases;

    @JsonProperty("CLASES_DESC")
    private String clasesDesc;

    public String getKeyadrClases() {
        return keyadrClases;
    }

    public void setKeyadrClases(String keyadrClases) {
        this.keyadrClases = keyadrClases;
    }

    public String getClasesDesc() {
        return clasesDesc;
    }

    public void setClasesDesc(String clasesDesc) {
        this.clasesDesc = clasesDesc;
    }
}
