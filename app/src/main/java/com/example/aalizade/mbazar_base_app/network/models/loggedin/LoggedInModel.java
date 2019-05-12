/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.aalizade.mbazar_base_app.network.models.loggedin;

import com.google.gson.annotations.SerializedName;
import java.util.HashMap;

/**
 *
 * @author Administrator
 */
public class LoggedInModel {

    @SerializedName("user")
    private LoggedInUserModel user;
    @SerializedName("userPositionRoleUnitCurrentId")
    private String userPositionRoleUnitCurrentId;
    @SerializedName("userPositionRoleUnitDefaultId")
    private String userPositionRoleUnitDefaultId;
    @SerializedName("loggedInUserPositionRoleUnitList")
    private HashMap<String, LoggedInUserPositionRoleUnitModel> loggedInUserPositionRoleUnitList;
    @SerializedName("softwareVersionNo")
    private Double softwareVersionNo;
    @SerializedName("softwareVersionDesc")
    private String softwareVersionDesc;
    @SerializedName("userPositionRoleUnitCurrent")
    private LoggedInUserPositionRoleUnitModel userPositionRoleUnitCurrent;

    public LoggedInModel() {
    }

    public LoggedInUserModel getUser() {
        return user;
    }

    public void setUser(LoggedInUserModel user) {
        this.user = user;
    }

    public String getUserPositionRoleUnitCurrentId() {
        return userPositionRoleUnitCurrentId;
    }

    public void setUserPositionRoleUnitCurrentId(String userPositionRoleUnitCurrentId) {
        this.userPositionRoleUnitCurrentId = userPositionRoleUnitCurrentId;
    }

    public String getUserPositionRoleUnitDefaultId() {
        return userPositionRoleUnitDefaultId;
    }

    public void setUserPositionRoleUnitDefaultId(String userPositionRoleUnitDefaultId) {
        this.userPositionRoleUnitDefaultId = userPositionRoleUnitDefaultId;
    }

    public HashMap<String, LoggedInUserPositionRoleUnitModel> getLoggedInUserPositionRoleUnitList() {
        return loggedInUserPositionRoleUnitList;
    }

    public void setLoggedInUserPositionRoleUnitList(HashMap<String, LoggedInUserPositionRoleUnitModel> loggedInUserPositionRoleUnitList) {
        this.loggedInUserPositionRoleUnitList = loggedInUserPositionRoleUnitList;
    }

    @Override
    public String toString() {
        return "LoggedInModel{" +
                "user=" + user +
                ", userPositionRoleUnitCurrentId='" + userPositionRoleUnitCurrentId + '\'' +
                ", userPositionRoleUnitDefaultId='" + userPositionRoleUnitDefaultId + '\'' +
                ", loggedInUserPositionRoleUnitList=" + loggedInUserPositionRoleUnitList +
                ", softwareVersionNo=" + softwareVersionNo +
                ", softwareVersionDesc='" + softwareVersionDesc + '\'' +
                ", userPositionRoleUnitCurrent=" + userPositionRoleUnitCurrent +
                '}';
    }

    public Double getSoftwareVersionNo() {
        return softwareVersionNo;
    }

    public void setSoftwareVersionNo(Double softwareVersionNo) {
        this.softwareVersionNo = softwareVersionNo;
    }

    public String getSoftwareVersionDesc() {
        return softwareVersionDesc;
    }

    public void setSoftwareVersionDesc(String softwareVersionDesc) {
        this.softwareVersionDesc = softwareVersionDesc;
    }

    public LoggedInUserPositionRoleUnitModel getUserPositionRoleUnitCurrent() {
        return userPositionRoleUnitCurrent;
    }

    public void setUserPositionRoleUnitCurrent(LoggedInUserPositionRoleUnitModel userPositionRoleUnitCurrent) {
        this.userPositionRoleUnitCurrent = userPositionRoleUnitCurrent;
    }

}
