package com.example.aalizade.mbazar_base_app.network.models.credit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sbayatani on 4/8/2018.
 */

public class CreditSubmitRequestModel {

    @SerializedName("id")
    @Expose
    private String id;

    public CreditSubmitRequestModel(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CreditSubmitRequestModel{" +
                "id='" + id + '\'' +
                '}';
    }
}
