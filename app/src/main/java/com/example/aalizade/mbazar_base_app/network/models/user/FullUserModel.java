/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.aalizade.mbazar_base_app.network.models.user;

import com.example.aalizade.mbazar_base_app.network.models.general.AutoCompleteModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Set;

/**
 *
 * @author Dev1
 */
public class FullUserModel {

    @SerializedName("user")
    private UserModelUpdate user;
    @SerializedName("userSocialGroupList")
    private Set<UserSocialGroupModel> userSocialGroupList;
    @SerializedName("upDiscriminatorList")
    private List<String> upDiscriminatorList;

    public FullUserModel() {
    }


    public UserModelUpdate getUser() {
        return user;
    }

    public void setUser(UserModelUpdate user) {
        this.user = user;
    }

    public Set<UserSocialGroupModel> getUserSocialGroupList() {
        return userSocialGroupList;
    }

    public void setUserSocialGroupList(Set<UserSocialGroupModel> userSocialGroupList) {
        this.userSocialGroupList = userSocialGroupList;
    }

    public List<String> getUpDiscriminatorList() {
        return upDiscriminatorList;
    }

    public void setUpDiscriminatorList(List<String> upDiscriminatorList) {
        this.upDiscriminatorList = upDiscriminatorList;
    }

    @Override
    public String toString() {
        return "FullUserModel{" +
                "user=" + user +
                ", userSocialGroupList=" + userSocialGroupList +
                ", upDiscriminatorList=" + upDiscriminatorList +
                '}';
    }
}
