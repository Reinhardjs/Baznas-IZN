package com.karyastudio.izn.model.api.datakdz;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

    @SerializedName("kajianDampakZakat")
    @Expose
    private List<KajianDampakZakatResponse> kajianDampakZakat = null;

    public List<KajianDampakZakatResponse> getKajianDampakZakat() {
        return kajianDampakZakat;
    }

    public void setKajianDampakZakat(List<KajianDampakZakatResponse> kajianDampakZakat) {
        this.kajianDampakZakat = kajianDampakZakat;
    }

}