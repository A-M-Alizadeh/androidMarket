/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.aalizade.mbazar_base_app.network.models.membershiprequest;

import com.example.aalizade.mbazar_base_app.network.models.general.CustomDate;
import com.example.aalizade.mbazar_base_app.network.models.general.CustomDateTime;
import com.google.gson.annotations.SerializedName;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Administrator
 */
public class MembershipRequestFrontModelUpdate extends MembershipRequestFrontModel {

    @SerializedName("id")
    private Integer id;
    @SerializedName("code")
    private String code;
    @SerializedName("registerDateTime")
    private CustomDateTime registerDateTime;
    @SerializedName("meetingDate")
    private CustomDate meetingDate;
    @SerializedName("meetingTime")
    private String meetingTime;
    @SerializedName("meetingDoneDateTime")
    private CustomDateTime meetingDoneDateTime;
    @SerializedName("rejectDateTime")
    private CustomDateTime rejectDateTime;
    @SerializedName("rejectDescription")
    private String rejectDescription;
    @SerializedName("meetingRetry")
    private Boolean meetingRetry;
    @SerializedName("status_langKey")
    private String status_langKey;
    @SerializedName("status_value")
    private String status_value;
    @SerializedName("statusDateTime")
    private CustomDateTime statusDateTime;

    //user:
    @SerializedName("user_gender_langKey")
    private String user_gender_langKey;
    @SerializedName("user_maritalStatus_langKey")
    private String user_maritalStatus_langKey;
    @SerializedName("user_maritalStatus_value")
    private String user_maritalStatus_value;

    //socialGroup:
    @SerializedName("socialGroup_name")
    private String socialGroup_name;
    @SerializedName("socialGroup_code")
    private String socialGroup_code;
    @SerializedName("socialGroup_trend_name")
    private String socialGroup_trend_name;
    @SerializedName("socialGroup_defaultSocialEntityContact_city_name")
    private String socialGroup_defaultSocialEntityContact_city_name;
    @SerializedName("socialGroup_defaultSocialEntityContact_address")
    private String socialGroup_defaultSocialEntityContact_address;
    @SerializedName("socialGroup_defaultSocialEntityContact_postalCode")
    private String socialGroup_defaultSocialEntityContact_postalCode;
    @SerializedName("socialGroup_defaultSocialEntityContact_phoneNo")
    private String socialGroup_defaultSocialEntityContact_phoneNo;
    @SerializedName("socialGroup_defaultSocialEntityContact_faxNo")
    private String socialGroup_defaultSocialEntityContact_faxNo;

    //history:
    private Set<RequestStatusHistoryFrontModel> statusHistorySet = new HashSet<>();

    //setter-getter:
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSocialGroup_name() {
        return socialGroup_name;
    }

    public void setSocialGroup_name(String socialGroup_name) {
        this.socialGroup_name = socialGroup_name;
    }

    public String getSocialGroup_code() {
        return socialGroup_code;
    }

    public void setSocialGroup_code(String socialGroup_code) {
        this.socialGroup_code = socialGroup_code;
    }

    public String getSocialGroup_trend_name() {
        return socialGroup_trend_name;
    }

    public void setSocialGroup_trend_name(String socialGroup_trend_name) {
        this.socialGroup_trend_name = socialGroup_trend_name;
    }

    public String getSocialGroup_defaultSocialEntityContact_city_name() {
        return socialGroup_defaultSocialEntityContact_city_name;
    }

    public void setSocialGroup_defaultSocialEntityContact_city_name(String socialGroup_defaultSocialEntityContact_city_name) {
        this.socialGroup_defaultSocialEntityContact_city_name = socialGroup_defaultSocialEntityContact_city_name;
    }

    public String getSocialGroup_defaultSocialEntityContact_address() {
        return socialGroup_defaultSocialEntityContact_address;
    }

    public void setSocialGroup_defaultSocialEntityContact_address(String socialGroup_defaultSocialEntityContact_address) {
        this.socialGroup_defaultSocialEntityContact_address = socialGroup_defaultSocialEntityContact_address;
    }

    public String getSocialGroup_defaultSocialEntityContact_postalCode() {
        return socialGroup_defaultSocialEntityContact_postalCode;
    }

    public void setSocialGroup_defaultSocialEntityContact_postalCode(String socialGroup_defaultSocialEntityContact_postalCode) {
        this.socialGroup_defaultSocialEntityContact_postalCode = socialGroup_defaultSocialEntityContact_postalCode;
    }

    public String getSocialGroup_defaultSocialEntityContact_phoneNo() {
        return socialGroup_defaultSocialEntityContact_phoneNo;
    }

    public void setSocialGroup_defaultSocialEntityContact_phoneNo(String socialGroup_defaultSocialEntityContact_phoneNo) {
        this.socialGroup_defaultSocialEntityContact_phoneNo = socialGroup_defaultSocialEntityContact_phoneNo;
    }

    public String getSocialGroup_defaultSocialEntityContact_faxNo() {
        return socialGroup_defaultSocialEntityContact_faxNo;
    }

    public void setSocialGroup_defaultSocialEntityContact_faxNo(String socialGroup_defaultSocialEntityContact_faxNo) {
        this.socialGroup_defaultSocialEntityContact_faxNo = socialGroup_defaultSocialEntityContact_faxNo;
    }

    public CustomDateTime getRegisterDateTime() {
        return registerDateTime;
    }

    public void setRegisterDateTime(CustomDateTime registerDateTime) {
        this.registerDateTime = registerDateTime;
    }

    public CustomDate getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(CustomDate meetingDate) {
        this.meetingDate = meetingDate;
    }

    public String getMeetingTime() {
        return meetingTime;
    }

    public void setMeetingTime(String meetingTime) {
        this.meetingTime = meetingTime;
    }

    public CustomDateTime getStatusDateTime() {
        return statusDateTime;
    }

    public void setStatusDateTime(CustomDateTime statusDateTime) {
        this.statusDateTime = statusDateTime;
    }

    public Set<RequestStatusHistoryFrontModel> getStatusHistorySet() {
        return statusHistorySet;
    }

    public void setStatusHistorySet(Set<RequestStatusHistoryFrontModel> statusHistorySet) {
        this.statusHistorySet = statusHistorySet;
    }

    public Boolean getMeetingRetry() {
        return meetingRetry;
    }

    public void setMeetingRetry(Boolean meetingRetry) {
        this.meetingRetry = meetingRetry;
    }

    public String getStatus_langKey() {
        return status_langKey;
    }

    public void setStatus_langKey(String status_langKey) {
        this.status_langKey = status_langKey;
    }

    public String getStatus_value() {
        return status_value;
    }

    public void setStatus_value(String status_value) {
        this.status_value = status_value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUser_gender_langKey() {
        return user_gender_langKey;
    }

    public void setUser_gender_langKey(String user_gender_langKey) {
        this.user_gender_langKey = user_gender_langKey;
    }

    public String getUser_maritalStatus_langKey() {
        return user_maritalStatus_langKey;
    }

    public void setUser_maritalStatus_langKey(String user_maritalStatus_langKey) {
        this.user_maritalStatus_langKey = user_maritalStatus_langKey;
    }

    public String getUser_maritalStatus_value() {
        return user_maritalStatus_value;
    }

    public void setUser_maritalStatus_value(String user_maritalStatus_value) {
        this.user_maritalStatus_value = user_maritalStatus_value;
    }

    public CustomDateTime getMeetingDoneDateTime() {
        return meetingDoneDateTime;
    }

    public void setMeetingDoneDateTime(CustomDateTime meetingDoneDateTime) {
        this.meetingDoneDateTime = meetingDoneDateTime;
    }

    public CustomDateTime getRejectDateTime() {
        return rejectDateTime;
    }

    public void setRejectDateTime(CustomDateTime rejectDateTime) {
        this.rejectDateTime = rejectDateTime;
    }

    public String getRejectDescription() {
        return rejectDescription;
    }

    public void setRejectDescription(String rejectDescription) {
        this.rejectDescription = rejectDescription;
    }

}
