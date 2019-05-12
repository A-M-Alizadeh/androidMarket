/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.aalizade.mbazar_base_app.network.models.general;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author Administrator
 */
public class ConfirmationCodeModel {

    @SerializedName("username")
    private String username;
    //----------------------------------------------
    @SerializedName("confirmationCode")
    private String confirmationCode;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getConfirmationCode() {
        return confirmationCode;
    }

    public void setConfirmationCode(String confirmationCode) {
        this.confirmationCode = confirmationCode;
    }

    @Override
    public String toString() {
        return "ConfirmationCodeModel{" +
                "username='" + username + '\'' +
                ", confirmationCode='" + confirmationCode + '\'' +
                '}';
    }
}
