package com.example.aalizade.mbazar_base_app.network.models.credit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sbayatani on 4/8/2018.
 */

public class CreditCheckRequestModel {

    @SerializedName("param")
    @Expose
    private String param;

    public CreditCheckRequestModel(String param) {
        this.param = param;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    @Override
    public String toString() {
        return "CreditCheckRequestModel{" +
                "param='" + param + '\'' +
                '}';
    }
}
