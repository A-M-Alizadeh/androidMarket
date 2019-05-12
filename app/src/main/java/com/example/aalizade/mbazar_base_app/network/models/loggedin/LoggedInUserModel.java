/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.aalizade.mbazar_base_app.network.models.loggedin;


import com.example.aalizade.mbazar_base_app.network.models.general.CustomDate;
import com.google.gson.annotations.SerializedName;

/**
 *
 * @author Administrator
 */
public class LoggedInUserModel {

    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;
    @SerializedName("family")
    private String family;
    @SerializedName("username")
    private String username;
    @SerializedName("nationalCode")
    private String nationalCode;
    @SerializedName("gender_langKey")
    private String gender_langKey;
    @SerializedName("gender_id")
    private String gender_id;
    @SerializedName("city_id")
    private String city_id;
    @SerializedName("dateOfBorn")
    private CustomDate dateOfBorn = new CustomDate();
    @SerializedName("maritalStatus_langKey")
    private String maritalStatus_langKey;
    @SerializedName("maritalStatus_id")
    private String maritalStatus_id;
    @SerializedName("defaultUserContact_mobileNo")
    private String defaultUserContact_mobileNo;
    @SerializedName("defaultUserContact_emailAddress")
    private String defaultUserContact_emailAddress;
    @SerializedName("newsLetter")
    private Boolean newsLetter;
    @SerializedName("firstLogin")
    private Boolean firstLogin;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public String getGender_langKey() {
        return gender_langKey;
    }

    public void setGender_langKey(String gender_langKey) {
        this.gender_langKey = gender_langKey;
    }

    public String getGender_id() {
        return gender_id;
    }

    public void setGender_id(String gender_id) {
        this.gender_id = gender_id;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public CustomDate getDateOfBorn() {
        return dateOfBorn;
    }

    public void setDateOfBorn(CustomDate dateOfBorn) {
        this.dateOfBorn = dateOfBorn;
    }

    public String getMaritalStatus_langKey() {
        return maritalStatus_langKey;
    }

    public void setMaritalStatus_langKey(String maritalStatus_langKey) {
        this.maritalStatus_langKey = maritalStatus_langKey;
    }

    public String getMaritalStatus_id() {
        return maritalStatus_id;
    }

    public void setMaritalStatus_id(String maritalStatus_id) {
        this.maritalStatus_id = maritalStatus_id;
    }

    public String getDefaultUserContact_mobileNo() {
        return defaultUserContact_mobileNo;
    }

    public void setDefaultUserContact_mobileNo(String defaultUserContact_mobileNo) {
        this.defaultUserContact_mobileNo = defaultUserContact_mobileNo;
    }

    public String getDefaultUserContact_emailAddress() {
        return defaultUserContact_emailAddress;
    }

    public void setDefaultUserContact_emailAddress(String defaultUserContact_emailAddress) {
        this.defaultUserContact_emailAddress = defaultUserContact_emailAddress;
    }

    public Boolean getNewsLetter() {
        return newsLetter;
    }

    public void setNewsLetter(Boolean newsLetter) {
        this.newsLetter = newsLetter;
    }

    public Boolean getFirstLogin() {
        return firstLogin;
    }

    public void setFirstLogin(Boolean firstLogin) {
        this.firstLogin = firstLogin;
    }

    @Override
    public String toString() {
        return "LoggedInUserModel{" + "id=" + id + ", name=" + name + ", family=" + family + ", username=" + username + ", nationalCode=" + nationalCode + ", gender_langKey=" + gender_langKey + ", gender_id=" + gender_id + ", dateOfBorn=" + dateOfBorn + ", maritalStatus_langKey=" + maritalStatus_langKey + ", maritalStatus_id=" + maritalStatus_id + ", defaultUserContact_mobileNo=" + defaultUserContact_mobileNo + ", defaultUserContact_emailAddress=" + defaultUserContact_emailAddress + ", newsLetter=" + newsLetter + '}';
    }

}
