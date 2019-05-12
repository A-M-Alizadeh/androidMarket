/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.aalizade.mbazar_base_app.network.models.contact;

import com.example.aalizade.mbazar_base_app.network.models.general.AutoCompleteModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 *
 * @author Administrator
 */
public class UserContactModel {

    @Expose
    @SerializedName("city")
    private AutoCompleteModel city;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("mobileNo")
    @Expose
    private String mobileNo;

    @SerializedName("phoneNo")
    @Expose
    private String phoneNo;

    @SerializedName("emailAddress")
    @Expose
    private String emailAddress;

    @SerializedName("postalCode")
    @Expose
    private String postalCode;

    @SerializedName("defaultContact")
    @Expose
    private Boolean defaultContact;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Boolean getDefaultContact() {
        return defaultContact;
    }

    public void setDefaultContact(Boolean defaultContact) {
        this.defaultContact = defaultContact;
    }

    public AutoCompleteModel getCity() {
        return city;
    }

    public void setCity(AutoCompleteModel city) {
        this.city = city;
    }

    public UserContactModel() {
    }

    @Override
    public String toString() {
        return "UserContactModel{" +
                "city=" + city +
                ", address='" + address + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", defaultContact=" + defaultContact +
                '}';
    }
}
