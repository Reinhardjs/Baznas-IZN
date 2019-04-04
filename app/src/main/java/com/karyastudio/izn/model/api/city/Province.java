package com.karyastudio.izn.model.api.city;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Province {

    @SerializedName("pro_id")
    @Expose
    private String proId;
    @SerializedName("pro_code")
    @Expose
    private String proCode;

    @SerializedName("cit_code")
    @Expose
    private String citCode;

    @SerializedName("pro_province")
    @Expose
    private String proProvince;
    @SerializedName("pro_city")
    @Expose
    private String proCity;

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

    public String getCitCode() {
        return citCode;
    }

    public void setCitCode(String citCode) {
        this.citCode = citCode;
    }

    public String getProProvince() {
        return proProvince;
    }

    public void setProProvince(String proProvince) {
        this.proProvince = proProvince;
    }

    public String getProCity() {
        return proCity;
    }

    public void setProCity(String proCity) {
        this.proCity = proCity;
    }

}