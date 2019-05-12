/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.aalizade.mbazar_base_app.network.models.membershiprequest;


import com.example.aalizade.mbazar_base_app.network.models.general.CustomDate;
import com.example.aalizade.mbazar_base_app.network.models.general.AutoCompleteModel;
import com.example.aalizade.mbazar_base_app.network.models.general.GenericModel;
import com.example.aalizade.mbazar_base_app.network.models.user.UserEducationModel;
import com.google.gson.annotations.SerializedName;

import java.util.Set;

/**
 * @author Administrator
 */
public class MembershipRequestFrontModel extends GenericModel {

    //socialGroup:
    @SerializedName("socialGroup_id")
    private String socialGroup_id;

    //user:
    @SerializedName("user_username")
    private String user_username;
    @SerializedName("user_name")
    private String user_name;
    @SerializedName("user_family")
    private String user_family;
    @SerializedName("user_fatherName")
    private String user_fatherName;
    @SerializedName("user_nationalCode")
    private String user_nationalCode;
    @SerializedName("user_shNo")
    private String user_shNo;
    @SerializedName("user_shSerialNo")
    private String user_shSerialNo;
    @SerializedName("user_shLocation")
    private AutoCompleteModel user_shLocation;
    @SerializedName("user_bornLocation")
    private AutoCompleteModel user_bornLocation;
    @SerializedName("user_dateOfBorn")
    private CustomDate user_dateOfBorn;
    @SerializedName("user_gender_id")
    private String user_gender_id;
    @SerializedName("user_maritalStatus_id")
    private String user_maritalStatus_id;
    @SerializedName("user_childNumber")
    private String user_childNumber;
    @SerializedName("user_jobTitle")
    private String user_jobTitle;
    @SerializedName("user_jobCompanyName")
    private String user_jobCompanyName;
    @SerializedName("user_favorite")
    private String user_favorite;
    @SerializedName("user_userEducationSet")
    private Set<UserEducationModel> user_userEducationSet;

    //user_defaultUserContact:
    @SerializedName("user_defaultUserContact_city")
    private AutoCompleteModel user_defaultUserContact_city;
    @SerializedName("user_defaultUserContact_address")
    private String user_defaultUserContact_address;
    @SerializedName("user_defaultUserContact_postalCode")
    private String user_defaultUserContact_postalCode;
    @SerializedName("user_defaultUserContact_emailAddress")
    private String user_defaultUserContact_emailAddress;
    @SerializedName("user_defaultUserContact_mobileNo")
    private String user_defaultUserContact_mobileNo;
    @SerializedName("user_defaultUserContact_phoneNo")
    private String user_defaultUserContact_phoneNo;
    @SerializedName("user_defaultUserContact_jobAddress")
    private String user_defaultUserContact_jobAddress;
    @SerializedName("user_defaultUserContact_jobPostalCode")
    private String user_defaultUserContact_jobPostalCode;
    @SerializedName("user_defaultUserContact_jobPhoneNo")
    private String user_defaultUserContact_jobPhoneNo;

    //introducer:
    @SerializedName("introducer_username")
    private String introducer_username;
    @SerializedName("introducer_name")
    private String introducer_name;
    @SerializedName("introducer_family")
    private String introducer_family;
    @SerializedName("introducer_nationalCode")
    private String introducer_nationalCode;

    //setter-getter:
    public String getSocialGroup_id() {
        return socialGroup_id;
    }

    public void setSocialGroup_id(String socialGroup_id) {
        this.socialGroup_id = socialGroup_id;
    }

    public String getUser_username() {
        return user_username;
    }

    public void setUser_username(String user_username) {
        this.user_username = user_username;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_family() {
        return user_family;
    }

    public void setUser_family(String user_family) {
        this.user_family = user_family;
    }

    public String getUser_fatherName() {
        return user_fatherName;
    }

    public void setUser_fatherName(String user_fatherName) {
        this.user_fatherName = user_fatherName;
    }

    public String getUser_nationalCode() {
        return user_nationalCode;
    }

    public void setUser_nationalCode(String user_nationalCode) {
        this.user_nationalCode = user_nationalCode;
    }

    public String getUser_shNo() {
        return user_shNo;
    }

    public void setUser_shNo(String user_shNo) {
        this.user_shNo = user_shNo;
    }

    public String getUser_shSerialNo() {
        return user_shSerialNo;
    }

    public void setUser_shSerialNo(String user_shSerialNo) {
        this.user_shSerialNo = user_shSerialNo;
    }

    public AutoCompleteModel getUser_shLocation() {
        return user_shLocation;
    }

    public void setUser_shLocation(AutoCompleteModel user_shLocation) {
        this.user_shLocation = user_shLocation;
    }

    public AutoCompleteModel getUser_bornLocation() {
        return user_bornLocation;
    }

    public void setUser_bornLocation(AutoCompleteModel user_bornLocation) {
        this.user_bornLocation = user_bornLocation;
    }

    public CustomDate getUser_dateOfBorn() {
        return user_dateOfBorn;
    }

    public void setUser_dateOfBorn(CustomDate user_dateOfBorn) {
        this.user_dateOfBorn = user_dateOfBorn;
    }

    public String getUser_gender_id() {
        return user_gender_id;
    }

    public void setUser_gender_id(String user_gender_id) {
        this.user_gender_id = user_gender_id;
    }

    public String getUser_maritalStatus_id() {
        return user_maritalStatus_id;
    }

    public void setUser_maritalStatus_id(String user_maritalStatus_id) {
        this.user_maritalStatus_id = user_maritalStatus_id;
    }

    public String getUser_childNumber() {
        return user_childNumber;
    }

    public void setUser_childNumber(String user_childNumber) {
        this.user_childNumber = user_childNumber;
    }

    public String getUser_jobTitle() {
        return user_jobTitle;
    }

    public void setUser_jobTitle(String user_jobTitle) {
        this.user_jobTitle = user_jobTitle;
    }

    public String getUser_jobCompanyName() {
        return user_jobCompanyName;
    }

    public void setUser_jobCompanyName(String user_jobCompanyName) {
        this.user_jobCompanyName = user_jobCompanyName;
    }

    public String getUser_favorite() {
        return user_favorite;
    }

    public void setUser_favorite(String user_favorite) {
        this.user_favorite = user_favorite;
    }

    public Set<UserEducationModel> getUser_userEducationSet() {
        return user_userEducationSet;
    }

    public void setUser_userEducationSet(Set<UserEducationModel> user_userEducationSet) {
        this.user_userEducationSet = user_userEducationSet;
    }

    public AutoCompleteModel getUser_defaultUserContact_city() {
        return user_defaultUserContact_city;
    }

    public void setUser_defaultUserContact_city(AutoCompleteModel user_defaultUserContact_city) {
        this.user_defaultUserContact_city = user_defaultUserContact_city;
    }

    public String getUser_defaultUserContact_address() {
        return user_defaultUserContact_address;
    }

    public void setUser_defaultUserContact_address(String user_defaultUserContact_address) {
        this.user_defaultUserContact_address = user_defaultUserContact_address;
    }

    public String getUser_defaultUserContact_postalCode() {
        return user_defaultUserContact_postalCode;
    }

    public void setUser_defaultUserContact_postalCode(String user_defaultUserContact_postalCode) {
        this.user_defaultUserContact_postalCode = user_defaultUserContact_postalCode;
    }

    public String getUser_defaultUserContact_emailAddress() {
        return user_defaultUserContact_emailAddress;
    }

    public void setUser_defaultUserContact_emailAddress(String user_defaultUserContact_emailAddress) {
        this.user_defaultUserContact_emailAddress = user_defaultUserContact_emailAddress;
    }

    public String getUser_defaultUserContact_mobileNo() {
        return user_defaultUserContact_mobileNo;
    }

    public void setUser_defaultUserContact_mobileNo(String user_defaultUserContact_mobileNo) {
        this.user_defaultUserContact_mobileNo = user_defaultUserContact_mobileNo;
    }

    public String getUser_defaultUserContact_phoneNo() {
        return user_defaultUserContact_phoneNo;
    }

    public void setUser_defaultUserContact_phoneNo(String user_defaultUserContact_phoneNo) {
        this.user_defaultUserContact_phoneNo = user_defaultUserContact_phoneNo;
    }

    public String getUser_defaultUserContact_jobAddress() {
        return user_defaultUserContact_jobAddress;
    }

    public void setUser_defaultUserContact_jobAddress(String user_defaultUserContact_jobAddress) {
        this.user_defaultUserContact_jobAddress = user_defaultUserContact_jobAddress;
    }

    public String getUser_defaultUserContact_jobPostalCode() {
        return user_defaultUserContact_jobPostalCode;
    }

    public void setUser_defaultUserContact_jobPostalCode(String user_defaultUserContact_jobPostalCode) {
        this.user_defaultUserContact_jobPostalCode = user_defaultUserContact_jobPostalCode;
    }

    public String getUser_defaultUserContact_jobPhoneNo() {
        return user_defaultUserContact_jobPhoneNo;
    }

    public void setUser_defaultUserContact_jobPhoneNo(String user_defaultUserContact_jobPhoneNo) {
        this.user_defaultUserContact_jobPhoneNo = user_defaultUserContact_jobPhoneNo;
    }

    public String getIntroducer_username() {
        return introducer_username;
    }

    public void setIntroducer_username(String introducer_username) {
        this.introducer_username = introducer_username;
    }

    public String getIntroducer_name() {
        return introducer_name;
    }

    public void setIntroducer_name(String introducer_name) {
        this.introducer_name = introducer_name;
    }

    public String getIntroducer_family() {
        return introducer_family;
    }

    public void setIntroducer_family(String introducer_family) {
        this.introducer_family = introducer_family;
    }

    public String getIntroducer_nationalCode() {
        return introducer_nationalCode;
    }

    public void setIntroducer_nationalCode(String introducer_nationalCode) {
        this.introducer_nationalCode = introducer_nationalCode;
    }

}
