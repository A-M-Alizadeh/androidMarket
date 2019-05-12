/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.aalizade.mbazar_base_app.network.models.user;

import com.google.gson.annotations.SerializedName;

public class UserModelUpdate extends UserModel {

    @SerializedName("id")
    private String id;
    @SerializedName("gender_langKey")
    private String gender_langKey;
    @SerializedName("defaultUserEducation_certificateLevel_langKey")
    private String defaultUserEducation_certificateLevel_langKey;
    @SerializedName("maritalStatus_langKey")
    private String maritalStatus_langKey;
    @SerializedName("maritalStatus_category1")
    private String maritalStatus_category1;
    //private Boolean passwordChange = false;
    //private Boolean usernameChange = false;
    @SerializedName("isMember")
    private Boolean isMember = false;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGender_langKey() {
        return gender_langKey;
    }

    public void setGender_langKey(String gender_langKey) {
        this.gender_langKey = gender_langKey;
    }

    public String getDefaultUserEducation_certificateLevel_langKey() {
        return defaultUserEducation_certificateLevel_langKey;
    }

    public void setDefaultUserEducation_certificateLevel_langKey(String defaultUserEducation_certificateLevel_langKey) {
        this.defaultUserEducation_certificateLevel_langKey = defaultUserEducation_certificateLevel_langKey;
    }

    public String getMaritalStatus_langKey() {
        return maritalStatus_langKey;
    }

    public void setMaritalStatus_langKey(String maritalStatus_langKey) {
        this.maritalStatus_langKey = maritalStatus_langKey;
    }

    public String getMaritalStatus_category1() {
        return maritalStatus_category1;
    }

    public void setMaritalStatus_category1(String maritalStatus_category1) {
        this.maritalStatus_category1 = maritalStatus_category1;
    }

    public Boolean getMember() {
        return isMember;
    }

    public void setMember(Boolean member) {
        isMember = member;
    }

    @Override
    public String toString() {
        return "UserModelUpdate{" +
                "id='" + id + '\'' +
                ", gender_langKey='" + gender_langKey + '\'' +
                ", defaultUserEducation_certificateLevel_langKey='" + defaultUserEducation_certificateLevel_langKey + '\'' +
                ", maritalStatus_langKey='" + maritalStatus_langKey + '\'' +
                ", maritalStatus_category1='" + maritalStatus_category1 + '\'' +
                ", isMember=" + isMember +
                '}';
    }
}
