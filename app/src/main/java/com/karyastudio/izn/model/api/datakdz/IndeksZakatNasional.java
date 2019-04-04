package com.karyastudio.izn.model.api.datakdz;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IndeksZakatNasional {

    @SerializedName("fk_id")
    @Expose
    private Object fkId;
    @SerializedName("fk_date_created")
    @Expose
    private String fkDateCreated;
    @SerializedName("fk_date_updated")
    @Expose
    private String fkDateUpdated;
    @SerializedName("fk_nama")
    @Expose
    private String fkNama;

    @SerializedName("countKeluarga")
    @Expose
    private String countKeluarga;

    public Object getFkId() {
        return fkId;
    }

    public void setFkId(Object fkId) {
        this.fkId = fkId;
    }

    public String getFkDateCreated() {
        return fkDateCreated;
    }

    public void setFkDateCreated(String fkDateCreated) {
        this.fkDateCreated = fkDateCreated;
    }

    public String getFkDateUpdated() {
        return fkDateUpdated;
    }

    public void setFkDateUpdated(String fkDateUpdated) {
        this.fkDateUpdated = fkDateUpdated;
    }

    public String getFkNama() {
        return fkNama;
    }

    public void setFkNama(String fkNama) {
        this.fkNama = fkNama;
    }

    public String getCountKeluarga() {
        return countKeluarga;
    }

    public void setCountKeluarga(String countKeluarga) {
        this.countKeluarga = countKeluarga;
    }
}