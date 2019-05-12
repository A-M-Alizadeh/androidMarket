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
public class ChangeFirstPasswordModel {

    @SerializedName("oldPassword")
    private String oldPassword;
    @SerializedName("newPassword")
    private String newPassword;
    @SerializedName("rePassword")
    private String rePassword;
    @SerializedName("nationalCode")
    private String nationalCode;
    @SerializedName("enableServicesMessage")
    private Boolean enableServicesMessage;

    
    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public Boolean getEnableServicesMessage() {
        return enableServicesMessage;
    }

    public void setEnableServicesMessage(Boolean enableServicesMessage) {
        this.enableServicesMessage = enableServicesMessage;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    @Override
    public String toString() {
        return "ChangeFirstPasswordModel{" +
                "oldPassword='" + oldPassword + '\'' +
                ", newPassword='" + newPassword + '\'' +
                ", rePassword='" + rePassword + '\'' +
                ", nationalCode='" + nationalCode + '\'' +
                ", enableServicesMessage=" + enableServicesMessage +
                '}';
    }

    public ChangeFirstPasswordModel() {
    }

    public ChangeFirstPasswordModel(String oldPassword, String newPassword, String rePassword, String nationalCode, Boolean enableServicesMessage) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.rePassword = rePassword;
        this.nationalCode = nationalCode;
        this.enableServicesMessage = enableServicesMessage;
    }
}
