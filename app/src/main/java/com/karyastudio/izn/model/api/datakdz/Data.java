package com.karyastudio.izn.model.api.datakdz;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

    @SerializedName("kajianDampakZakat")
    @Expose
    private List<KajianDampakZakatPojo> kajianDampakZakat = null;

    public List<KajianDampakZakatPojo> getKajianDampakZakat() {
        return kajianDampakZakat;
    }

    public void setKajianDampakZakat(List<KajianDampakZakatPojo> kajianDampakZakat) {
        this.kajianDampakZakat = kajianDampakZakat;
    }

}