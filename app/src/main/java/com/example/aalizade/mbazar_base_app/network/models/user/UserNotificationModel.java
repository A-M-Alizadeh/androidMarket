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
public class UserNotificationModel {
    @SerializedName("email")
    private String email;
    @SerializedName("mobile")
    private String mobile;

    public UserNotificationModel(String emaile, String mobile) {
        this.email = emaile;
        this.mobile = mobile;
    }

    public UserNotificationModel() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "UserNotificationModel{" +
                "email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }
}
