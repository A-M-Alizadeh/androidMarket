/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.aalizade.mbazar_base_app.network.models.loggedin;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author Administrator
 */
public class LoggedInUserPositionModel {

    @SerializedName("id")
    private String id;
    @SerializedName("upDiscriminator")
    private String upDiscriminator;
    @SerializedName("code")
    private String code;
    @SerializedName("codeCounter")
    private String codeCounter;
    @SerializedName("userPositionType_langKey")
    private String userPositionType_langKey;
    @SerializedName("socialEntity_id")
    private String socialEntity_id;
    @SerializedName("socialEntity_name")
    private String socialEntity_name;
    @SerializedName("warehouse_id")
    private String warehouse_id;
    @SerializedName("warehouse_title")
    private String warehouse_title;

    public LoggedInUserPositionModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUpDiscriminator() {
        return upDiscriminator;
    }

    public void setUpDiscriminator(String upDiscriminator) {
        this.upDiscriminator = upDiscriminator;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodeCounter() {
        return codeCounter;
    }

    public void setCodeCounter(String codeCounter) {
        this.codeCounter = codeCounter;
    }

    public String getUserPositionType_langKey() {
        return userPositionType_langKey;
    }

    public void setUserPositionType_langKey(String userPositionType_langKey) {
        this.userPositionType_langKey = userPositionType_langKey;
    }

    public String getSocialEntity_id() {
        return socialEntity_id;
    }

    public void setSocialEntity_id(String socialEntity_id) {
        this.socialEntity_id = socialEntity_id;
    }

    public String getSocialEntity_name() {
        return socialEntity_name;
    }

    public void setSocialEntity_name(String socialEntity_name) {
        this.socialEntity_name = socialEntity_name;
    }

    public String getWarehouse_id() {
        return warehouse_id;
    }

    public void setWarehouse_id(String warehouse_id) {
        this.warehouse_id = warehouse_id;
    }

    public String getWarehouse_title() {
        return warehouse_title;
    }

    public void setWarehouse_title(String warehouse_title) {
        this.warehouse_title = warehouse_title;
    }

    @Override
    public String toString() {
        return "LoggedInUserPositionModel{" + "id=" + id + ", upDiscriminator=" + upDiscriminator + ", code=" + code + ", codeCounter=" + codeCounter + ", userPositionType_langKey=" + userPositionType_langKey + ", socialEntity_id=" + socialEntity_id + ", socialEntity_name=" + socialEntity_name + ", warehouse_id=" + warehouse_id + ", warehouse_title=" + warehouse_title + '}';
    }

}
