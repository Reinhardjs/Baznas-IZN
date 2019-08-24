package com.karyastudio.izn.model.api.izn;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IndeksZakatNasional {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("fi_id")
    @Expose
    private Long fi_id;

    public Long getFi_id() {
        return fi_id;
    }

    public void setFi_id(Long fi_id) {
        this.fi_id = fi_id;
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