package com.example.aalizade.mbazar_base_app.network.models.general;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by aalizade on 12/27/2017.
 */

public class PrimitiveResponse {
    @SerializedName("response")
    @Expose
    private Object response;

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

    public PrimitiveResponse(Object response) {
        this.response = response;
    }

    public PrimitiveResponse() {
    }
}
