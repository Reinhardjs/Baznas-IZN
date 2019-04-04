package com.karyastudio.izn.model.api.datakdz;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

    @SerializedName("indeksZakatNasional")
    @Expose
    private List<IndeksZakatNasional> indeksZakatNasional = null;

    public List<IndeksZakatNasional> getIndeksZakatNasional() {
        return indeksZakatNasional;
    }

    public void setIndeksZakatNasional(List<IndeksZakatNasional> indeksZakatNasional) {
        this.indeksZakatNasional = indeksZakatNasional;
    }

}