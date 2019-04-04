package com.karyastudio.izn.model.api.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Petugas {

    @SerializedName("u_id")
    @Expose
    private String uId;
    @SerializedName("u_username")
    @Expose
    private String uUsername;
    @SerializedName("u_name")
    @Expose
    private String uName;
    @SerializedName("u_email")
    @Expose
    private String uEmail;
    @SerializedName("u_phone")
    @Expose
    private String uPhone;
    @SerializedName("u_rule")
    @Expose
    private String uRule;
    @SerializedName("u_status")
    @Expose
    private String uStatus;

    public String getUId() {
        return uId;
    }

    public void setUId(String uId) {
        this.uId = uId;
    }

    public String getUUsername() {
        return uUsername;
    }

    public void setUUsername(String uUsername) {
        this.uUsername = uUsername;
    }

    public String getUName() {
        return uName;
    }

    public void setUName(String uName) {
        this.uName = uName;
    }

    public String getUEmail() {
        return uEmail;
    }

    public void setUEmail(String uEmail) {
        this.uEmail = uEmail;
    }

    public String getUPhone() {
        return uPhone;
    }

    public void setUPhone(String uPhone) {
        this.uPhone = uPhone;
    }

    public String getURule() {
        return uRule;
    }

    public void setURule(String uRule) {
        this.uRule = uRule;
    }

    public String getUStatus() {
        return uStatus;
    }

    public void setUStatus(String uStatus) {
        this.uStatus = uStatus;
    }

}