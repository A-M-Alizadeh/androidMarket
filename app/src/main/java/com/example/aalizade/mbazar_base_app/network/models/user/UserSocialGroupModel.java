/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.aalizade.mbazar_base_app.network.models.user;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author Dev2
 */
public class UserSocialGroupModel {
    @SerializedName("name")
    private String name;
    @SerializedName("code")
    private String code;
    @SerializedName("trend_name")
    private String trend_name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTrend_name() {
        return trend_name;
    }

    public void setTrend_name(String trend_name) {
        this.trend_name = trend_name;
    }

    @Override
    public String toString() {
        return "UserSocialGroupModel{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", trend_name='" + trend_name + '\'' +
                '}';
    }
}
