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
public class UserChangePasswordModel {
    @SerializedName("id")
    private String id;
    @SerializedName("password")
    private String password;

    @SerializedName("newPassword")
    private String newPassword;

    @SerializedName("renewPassword")
    private String renewPassword;

    public UserChangePasswordModel() {
    }

    public UserChangePasswordModel(String id, String password, String newPassword, String renewPassword) {
        this.id = id;
        this.password = password;
        this.newPassword = newPassword;
        this.renewPassword = renewPassword;
    }
    public UserChangePasswordModel(String password, String newPassword, String renewPassword) {
        this.id = id;
        this.password = password;
        this.newPassword = newPassword;
        this.renewPassword = renewPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getRenewPassword() {
        return renewPassword;
    }

    public void setRenewPassword(String renewPassword) {
        this.renewPassword = renewPassword;
    }

    public String getId() {
        return id;
    }
  
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserChangePasswordModel{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", newPassword='" + newPassword + '\'' +
                ", renewPassword='" + renewPassword + '\'' +
                '}';
    }
}
