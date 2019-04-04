package com.karyastudio.izn.model.api.province;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Province {

    @SerializedName("pro_id")
    @Expose
    private String proId;
    @SerializedName("pro_code")
    @Expose
    private String proCode;
    @SerializedName("pro_province")
    @Expose
    private String proProvince;

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getProCode() {
        return proCode;
    }

    public void setProCode(String proCode) {
        this.proCode = proCode;
    }

    public String getProProvince() {
        return proProvince;
    }

    public void setProProvince(String proProvince) {
        this.proProvince = proProvince;
    }

}