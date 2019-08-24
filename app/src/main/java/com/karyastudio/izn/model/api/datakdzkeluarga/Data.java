package com.karyastudio.izn.model.api.datakdzkeluarga;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

    @SerializedName("kajianDampakZakatItems")
    @Expose
    private List<DataKDZKeluargaPojo> kajianDampakZakatItems = null;

    public List<DataKDZKeluargaPojo> getKajianDampakZakatItems() {
        return kajianDampakZakatItems;
    }

    public void setKajianDampakZakatItems(List<DataKDZKeluargaPojo> kajianDampakZakatItems) {
        this.kajianDampakZakatItems = kajianDampakZakatItems;
    }

}
