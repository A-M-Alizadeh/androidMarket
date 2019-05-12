package com.example.aalizade.mbazar_base_app.network.models.user;

import com.example.aalizade.mbazar_base_app.network.models.general.CustomDate;
import com.example.aalizade.mbazar_base_app.network.models.general.AutoCompleteModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Set;

/**
 * Created by aalizade on 1/13/2018.
 */

public class UserModelUpdateForm {
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("username")
    @Expose
    public String username;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("family")
    @Expose
    public String family;
    @SerializedName("fatherName")
    @Expose
    public String fatherName;
    @SerializedName("nationalCode")
    @Expose
    public String nationalCode;
    @SerializedName("shNo")
    @Expose
    public String shNo;
    @SerializedName("shSerialNo")
    @Expose
    public String shSerialNo;
    @SerializedName("shLocation")
    @Expose
    public AutoCompleteModel shLocation;
    @SerializedName("bornLocation")
    @Expose
    public AutoCompleteModel bornLocation;
    @SerializedName("dateOfBorn")
    @Expose
    public CustomDate dateOfBorn;
    @SerializedName("gender_id")
    @Expose
    public String genderId;
    @SerializedName("maritalStatus_id")
    @Expose
    public String maritalStatusId;
    @SerializedName("childNumber")
    @Expose
    public String childNumber;
    @SerializedName("jobTitle")
    @Expose
    public String jobTitle;
    @SerializedName("favorite")
    @Expose
    public String favorite;
    @SerializedName("defaultUserContact_city")
    @Expose
    public AutoCompleteModel defaultUserContactCity;
    @SerializedName("defaultUserContact_address")
    @Expose
    public String defaultUserContactAddress;
    @SerializedName("defaultUserContact_postalCode")
    @Expose
    public String defaultUserContactPostalCode;
    @SerializedName("defaultUserContact_emailAddress")
    @Expose
    public String defaultUserContactEmailAddress;
    @SerializedName("defaultUserContact_mobileNo")
    @Expose
    public String defaultUserContactMobileNo;
    @SerializedName("defaultUserContact_phoneNo")
    @Expose
    public String defaultUserContactPhoneNo;
    @SerializedName("defaultUserContact_jobFaxNo")
    @Expose
    public String defaultUserContactJobFaxNo;
    @SerializedName("defaultUserContact_jobAddress")
    @Expose
    public String defaultUserContactJobAddress;
    @SerializedName("defaultUserContact_jobPostalCode")
    @Expose
    public String defaultUserContactJobPostalCode;
    @SerializedName("defaultUserContact_jobPhoneNo")
    @Expose
    public String defaultUserContactJobPhoneNo;
    @SerializedName("userEducationSet")
    @Expose
    public Set<UserEducationModel> userEducationSet = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public String getShNo() {
        return shNo;
    }

    public void setShNo(String shNo) {
        this.shNo = shNo;
    }

    public String getShSerialNo() {
        return shSerialNo;
    }

    public void setShSerialNo(String shSerialNo) {
        this.shSerialNo = shSerialNo;
    }

    public AutoCompleteModel getShLocation() {
        return shLocation;
    }

    public void setShLocation(AutoCompleteModel shLocation) {
        this.shLocation = shLocation;
    }

    public AutoCompleteModel getBornLocation() {
        return bornLocation;
    }

    public void setBornLocation(AutoCompleteModel bornLocation) {
        this.bornLocation = bornLocation;
    }

    public CustomDate getDateOfBorn() {
        return dateOfBorn;
    }

    public void setDateOfBorn(CustomDate dateOfBorn) {
        this.dateOfBorn = dateOfBorn;
    }

    public String getGenderId() {
        return genderId;
    }

    public void setGenderId(String genderId) {
        this.genderId = genderId;
    }

    public String getMaritalStatusId() {
        return maritalStatusId;
    }

    public void setMaritalStatusId(String maritalStatusId) {
        this.maritalStatusId = maritalStatusId;
    }

    public String getChildNumber() {
        return childNumber;
    }

    public void setChildNumber(String childNumber) {
        this.childNumber = childNumber;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }

    public AutoCompleteModel getDefaultUserContactCity() {
        return defaultUserContactCity;
    }

    public void setDefaultUserContactCity(AutoCompleteModel defaultUserContactCity) {
        this.defaultUserContactCity = defaultUserContactCity;
    }

    public String getDefaultUserContactAddress() {
        return defaultUserContactAddress;
    }

    public void setDefaultUserContactAddress(String defaultUserContactAddress) {
        this.defaultUserContactAddress = defaultUserContactAddress;
    }

    public String getDefaultUserContactPostalCode() {
        return defaultUserContactPostalCode;
    }

    public void setDefaultUserContactPostalCode(String defaultUserContactPostalCode) {
        this.defaultUserContactPostalCode = defaultUserContactPostalCode;
    }

    public String getDefaultUserContactEmailAddress() {
        return defaultUserContactEmailAddress;
    }

    public void setDefaultUserContactEmailAddress(String defaultUserContactEmailAddress) {
        this.defaultUserContactEmailAddress = defaultUserContactEmailAddress;
    }

    public String getDefaultUserContactMobileNo() {
        return defaultUserContactMobileNo;
    }

    public void setDefaultUserContactMobileNo(String defaultUserContactMobileNo) {
        this.defaultUserContactMobileNo = defaultUserContactMobileNo;
    }

    public String getDefaultUserContactPhoneNo() {
        return defaultUserContactPhoneNo;
    }

    public void setDefaultUserContactPhoneNo(String defaultUserContactPhoneNo) {
        this.defaultUserContactPhoneNo = defaultUserContactPhoneNo;
    }

    public String getDefaultUserContactJobFaxNo() {
        return defaultUserContactJobFaxNo;
    }

    public void setDefaultUserContactJobFaxNo(String defaultUserContactJobFaxNo) {
        this.defaultUserContactJobFaxNo = defaultUserContactJobFaxNo;
    }

    public String getDefaultUserContactJobAddress() {
        return defaultUserContactJobAddress;
    }

    public void setDefaultUserContactJobAddress(String defaultUserContactJobAddress) {
        this.defaultUserContactJobAddress = defaultUserContactJobAddress;
    }

    public String getDefaultUserContactJobPostalCode() {
        return defaultUserContactJobPostalCode;
    }

    public void setDefaultUserContactJobPostalCode(String defaultUserContactJobPostalCode) {
        this.defaultUserContactJobPostalCode = defaultUserContactJobPostalCode;
    }

    public String getDefaultUserContactJobPhoneNo() {
        return defaultUserContactJobPhoneNo;
    }

    public void setDefaultUserContactJobPhoneNo(String defaultUserContactJobPhoneNo) {
        this.defaultUserContactJobPhoneNo = defaultUserContactJobPhoneNo;
    }

    public Set<UserEducationModel> getUserEducationSet() {
        return userEducationSet;
    }

    public void setUserEducationSet(Set<UserEducationModel> userEducationSet) {
        this.userEducationSet = userEducationSet;
    }

    public UserModelUpdateForm() {
    }

    @Override
    public String toString() {
        return "UserModelUpdateForm{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", family='" + family + '\'' +
                ", fatherName='" + fatherName + '\'' +
                ", nationalCode='" + nationalCode + '\'' +
                ", shNo='" + shNo + '\'' +
                ", shSerialNo='" + shSerialNo + '\'' +
                ", shLocation=" + shLocation +
                ", bornLocation=" + bornLocation +
                ", dateOfBorn=" + dateOfBorn +
                ", genderId='" + genderId + '\'' +
                ", maritalStatusId='" + maritalStatusId + '\'' +
                ", childNumber='" + childNumber + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", favorite='" + favorite + '\'' +
                ", defaultUserContactCity=" + defaultUserContactCity +
                ", defaultUserContactAddress='" + defaultUserContactAddress + '\'' +
                ", defaultUserContactPostalCode='" + defaultUserContactPostalCode + '\'' +
                ", defaultUserContactEmailAddress='" + defaultUserContactEmailAddress + '\'' +
                ", defaultUserContactMobileNo='" + defaultUserContactMobileNo + '\'' +
                ", defaultUserContactPhoneNo='" + defaultUserContactPhoneNo + '\'' +
                ", defaultUserContactJobFaxNo='" + defaultUserContactJobFaxNo + '\'' +
                ", defaultUserContactJobAddress='" + defaultUserContactJobAddress + '\'' +
                ", defaultUserContactJobPostalCode='" + defaultUserContactJobPostalCode + '\'' +
                ", defaultUserContactJobPhoneNo='" + defaultUserContactJobPhoneNo + '\'' +
                ", userEducationSet=" + userEducationSet +
                '}';
    }
}

