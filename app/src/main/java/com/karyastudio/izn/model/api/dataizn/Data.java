package com.karyastudio.izn.model.api.dataizn;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

    @SerializedName("indeksZakatNasional")
    @Expose
    private List<IndeksZakatNasionalPojo> indeksZakatNasional = null;

    public List<IndeksZakatNasionalPojo> getIndeksZakatNasional() {
        return indeksZakatNasional;
    }

    public void setIndeksZakatNasional(List<IndeksZakatNasionalPojo> indeksZakatNasional) {
        this.indeksZakatNasional = indeksZakatNasional;
    }

}