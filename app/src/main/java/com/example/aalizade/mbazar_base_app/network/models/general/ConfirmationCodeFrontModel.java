/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.aalizade.mbazar_base_app.network.models.general;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author Dev1
 */
public class ConfirmationCodeFrontModel {

    @SerializedName("sendBy")
    private String sendBy;

    @SerializedName("username")
    private String username;

    @SerializedName("type")
    private String type;

    public String getSendBy() {
        return sendBy;
    }

    public void setSendBy(String sendBy) {
        this.sendBy = sendBy;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ConfirmationCodeFrontModel() {
    }

    public ConfirmationCodeFrontModel(String sendBy, String username, String type) {
        this.sendBy = sendBy;
        this.username = username;
        this.type = type;
    }

    @Override
    public String toString() {
        return "ConfirmationCodeFrontModel{" +
                "sendBy='" + sendBy + '\'' +
                ", username='" + username + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
