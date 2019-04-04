package com.karyastudio.izn.model.api.dataizn;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IndeksZakatNasional {

    @SerializedName("fi_id")
    @Expose
    private String fiId;
    @SerializedName("fi_date_created")
    @Expose
    private String fiDateCreated;
    @SerializedName("fi_date_updated")
    @Expose
    private String fiDateUpdated;
    @SerializedName("fi_jenis_lembaga")
    @Expose
    private Object fiJenisLembaga;

    @SerializedName("fi_code")
    @Expose
    private String fiCode;

    public String getFiId() {
        return fiId;
    }

    public void setFiId(String fiId) {
        this.fiId = fiId;
    }

    public String getFiDateCreated() {
        return fiDateCreated;
    }

    public void setFiDateCreated(String fiDateCreated) {
        this.fiDateCreated = fiDateCreated;
    }

    public String getFiDateUpdated() {
        return fiDateUpdated;
    }

    public void setFiDateUpdated(String fiDateUpdated) {
        this.fiDateUpdated = fiDateUpdated;
    }

    public Object getFiJenisLembaga() {
        return fiJenisLembaga;
    }

    public void setFiJenisLembaga(Object fiJenisLembaga) {
        this.fiJenisLembaga = fiJenisLembaga;
    }

    public String getFiCode() {
        return fiCode;
    }

    public void setFiCode(String fiCode) {
        this.fiCode = fiCode;
    }
}