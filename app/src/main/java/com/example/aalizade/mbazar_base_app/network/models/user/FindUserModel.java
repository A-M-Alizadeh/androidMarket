/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.aalizade.mbazar_base_app.network.models.user;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author Administrator
 */
public class FindUserModel {
    //===========================//
    @SerializedName("upDiscriminator")
    private String upDiscriminator;

    @SerializedName("param")
    private String param;

    public String getUpDiscriminator() {
        return upDiscriminator;
    }

    public void setUpDiscriminator(String upDiscriminator) {
        this.upDiscriminator = upDiscriminator;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public FindUserModel(String upDiscriminator, String param) {
        this.upDiscriminator = upDiscriminator;
        this.param = param;
    }

    public FindUserModel() {
    }

    @Override
    public String toString() {
        return "FindUserModel{" +
                "upDiscriminator=" + upDiscriminator +
                ", param='" + param + '\'' +
                '}';
    }
}
