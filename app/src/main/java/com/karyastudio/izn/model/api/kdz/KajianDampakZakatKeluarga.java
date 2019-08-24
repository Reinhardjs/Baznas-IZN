package com.karyastudio.izn.model.api.kdz;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KajianDampakZakatKeluarga {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("fki_id")
    private String fki_id;

    public String getFki_id() {
        return fki_id;
    }

    public void setFki_id(String fki_id) {
        this.fki_id = fki_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
