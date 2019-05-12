/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.aalizade.mbazar_base_app.network.models.customer;

import com.example.aalizade.mbazar_base_app.network.models.general.GenericModel;

/**
 *
 * @author Administrator
 */
public class CustomerFrontModel extends GenericModel {

    private String user_username;
    private String user_name;
    private String user_family;
    private String user_defaultUserContact_mobileNo;
    private String user_defaultUserContact_emailAddress;
    private String user_gender_value;
    private Boolean user_newsLetter;
    private String sendBy;
    private Boolean user_userAgreement;
    
    //==============================================//


    public CustomerFrontModel(String user_username, String user_name, String user_family, String user_defaultUserContact_mobileNo, String user_defaultUserContact_emailAddress, String user_gender_value, Boolean user_newsLetter, String sendBy, Boolean user_userAgreement) {
        this.user_username = user_username;
        this.user_name = user_name;
        this.user_family = user_family;
        this.user_defaultUserContact_mobileNo = user_defaultUserContact_mobileNo;
        this.user_defaultUserContact_emailAddress = user_defaultUserContact_emailAddress;
        this.user_gender_value = user_gender_value;
        this.user_newsLetter = user_newsLetter;
        this.sendBy = sendBy;
        this.user_userAgreement = user_userAgreement;
    }

    public CustomerFrontModel(String user_username, String user_name, String user_family, String user_defaultUserContact_mobileNo, String user_defaultUserContact_emailAddress, String user_gender_value, Boolean user_newsLetter, Boolean user_userAgreement) {
        this.user_username = user_username;
        this.user_name = user_name;
        this.user_family = user_family;
        this.user_defaultUserContact_mobileNo = user_defaultUserContact_mobileNo;
        this.user_defaultUserContact_emailAddress = user_defaultUserContact_emailAddress;
        this.user_gender_value = user_gender_value;
        this.user_newsLetter = user_newsLetter;
        this.user_userAgreement = user_userAgreement;
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

    public String getUser_defaultUserContact_mobileNo() {
        return user_defaultUserContact_mobileNo;
    }

    public void setUser_defaultUserContact_mobileNo(String user_defaultUserContact_mobileNo) {
        this.user_defaultUserContact_mobileNo = user_defaultUserContact_mobileNo;
    }

    public String getUser_defaultUserContact_emailAddress() {
        return user_defaultUserContact_emailAddress;
    }

    public void setUser_defaultUserContact_emailAddress(String user_defaultUserContact_emailAddress) {
        this.user_defaultUserContact_emailAddress = user_defaultUserContact_emailAddress;
    }

    public String getUser_gender_value() {
        return user_gender_value;
    }

    public void setUser_gender_value(String user_gender_value) {
        this.user_gender_value = user_gender_value;
    }

    public Boolean getUser_newsLetter() {
        return user_newsLetter;
    }

    public void setUser_newsLetter(Boolean user_newsLetter) {
        this.user_newsLetter = user_newsLetter;
    }

    public String getSendBy() {
        return sendBy;
    }

    public void setSendBy(String sendBy) {
        this.sendBy = sendBy;
    }

    public Boolean getUser_userAgreement() {
        return user_userAgreement;
    }

    public void setUser_userAgreement(Boolean user_userAgreement) {
        this.user_userAgreement = user_userAgreement;
    }

}
