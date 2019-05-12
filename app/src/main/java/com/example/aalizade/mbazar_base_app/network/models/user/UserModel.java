
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.aalizade.mbazar_base_app.network.models.user;

import com.example.aalizade.mbazar_base_app.network.models.general.CustomDate;
import com.example.aalizade.mbazar_base_app.network.models.general.AutoCompleteModel;
import com.example.aalizade.mbazar_base_app.network.models.general.GenericModel;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 *
 * @author administrator
 */
public class UserModel extends GenericModel implements Serializable {
    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;
    @SerializedName("name")
    private String name;
    @SerializedName("family")
    private String family;
    @SerializedName("gender_id")
    private String gender_id;
    @SerializedName("shNo")
    private String shNo;
    @SerializedName("faragirCode")
    private String faragirCode;
    @SerializedName("nationalCode")
    private String nationalCode;
    @SerializedName("fatherName")
    private String fatherName;
    @SerializedName("shLocation")
    private AutoCompleteModel shLocation;
    @SerializedName("shSerialNo")
    private String shSerialNo;
    @SerializedName("dateOfBorn")
    private CustomDate dateOfBorn = new CustomDate();
    @SerializedName("bornLocation")
    private AutoCompleteModel bornLocation;
    @SerializedName("maritalStatus_id")
    private String maritalStatus_id;
    @SerializedName("childNumber")
    private String childNumber;
    @SerializedName("jobTitle")
    private String jobTitle;
    @SerializedName("jobCompanyName")
    private String jobCompanyName;
    @SerializedName("dateOfMarriage")
    private CustomDate dateOfMarriage = new CustomDate();
    @SerializedName("favorite")
    private String favorite;
    @SerializedName("newsLetter")
    private Boolean newsLetter;

    //defaultUserEducation and Set
    @SerializedName("userEducationSet")
    private Set<UserEducationModel> userEducationSet;
    @SerializedName("defaultUserEducation_certificateLevel_id")
    private String defaultUserEducation_certificateLevel_id;
    @SerializedName("defaultUserEducation_certificateField")
    private String defaultUserEducation_certificateField;
    @SerializedName("defaultUserEducation_otherSkill")
    private String defaultUserEducation_otherSkill;

    //defaultUserContact
    @SerializedName("defaultUserContact_postalCode")
    private String defaultUserContact_postalCode;
    @SerializedName("defaultUserContact_emailAddress")
    private String defaultUserContact_emailAddress;
    @SerializedName("defaultUserContact_city")
    private AutoCompleteModel defaultUserContact_city;
    @SerializedName("defaultUserContact_address")
    private String defaultUserContact_address;
    @SerializedName("defaultUserContact_mobileNo")
    private String defaultUserContact_mobileNo;
    @SerializedName("defaultUserContact_phoneNo")
    private String defaultUserContact_phoneNo;
    @SerializedName("defaultUserContact_jobAddress")
    private String defaultUserContact_jobAddress;
    @SerializedName("defaultUserContact_jobPostalCode")
    private String defaultUserContact_jobPostalCode;
    @SerializedName("defaultUserContact_jobCity")
    private AutoCompleteModel defaultUserContact_jobCity;
    @SerializedName("defaultUserContact_jobFaxNo")
    private String defaultUserContact_jobFaxNo;
    @SerializedName("defaultUserContact_jobPhoneNo")
    private String defaultUserContact_jobPhoneNo;

    //defaultUserPosition:
    @SerializedName("defaultUserPosition_id")
    private String defaultUserPosition_id;

    //skill:
    @SerializedName("skillList")
    private List<String> skillList;


    //defaultUserAccount:
    @SerializedName("defaultUserAccount_bankBranch_id")
    private String defaultUserAccount_bankBranch_id;
    @SerializedName("defaultUserAccount_accountNo1")
    private String defaultUserAccount_accountNo1;
    @SerializedName("defaultUserAccount_accountNo2")
    private String defaultUserAccount_accountNo2;
    @SerializedName("defaultUserAccount_accountType_id")
    private String defaultUserAccount_accountType_id;

    public UserModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getGender_id() {
        return gender_id;
    }

    public void setGender_id(String gender_id) {
        this.gender_id = gender_id;
    }

    public String getShNo() {
        return shNo;
    }

    public void setShNo(String shNo) {
        this.shNo = shNo;
    }

    public String getFaragirCode() {
        return faragirCode;
    }

    public void setFaragirCode(String faragirCode) {
        this.faragirCode = faragirCode;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public AutoCompleteModel getShLocation() {
        return shLocation;
    }

    public void setShLocation(AutoCompleteModel shLocation) {
        this.shLocation = shLocation;
    }

    public String getShSerialNo() {
        return shSerialNo;
    }

    public void setShSerialNo(String shSerialNo) {
        this.shSerialNo = shSerialNo;
    }

    public CustomDate getDateOfBorn() {
        return dateOfBorn;
    }

    public void setDateOfBorn(CustomDate dateOfBorn) {
        this.dateOfBorn = dateOfBorn;
    }

    public AutoCompleteModel getBornLocation() {
        return bornLocation;
    }

    public void setBornLocation(AutoCompleteModel bornLocation) {
        this.bornLocation = bornLocation;
    }

    public String getMaritalStatus_id() {
        return maritalStatus_id;
    }

    public void setMaritalStatus_id(String maritalStatus_id) {
        this.maritalStatus_id = maritalStatus_id;
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

    public String getJobCompanyName() {
        return jobCompanyName;
    }

    public void setJobCompanyName(String jobCompanyName) {
        this.jobCompanyName = jobCompanyName;
    }

    public CustomDate getDateOfMarriage() {
        return dateOfMarriage;
    }

    public void setDateOfMarriage(CustomDate dateOfMarriage) {
        this.dateOfMarriage = dateOfMarriage;
    }

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }

    public Boolean getNewsLetter() {
        return newsLetter;
    }

    public void setNewsLetter(Boolean newsLetter) {
        this.newsLetter = newsLetter;
    }

    public Set<UserEducationModel> getUserEducationSet() {
        return userEducationSet;
    }

    public void setUserEducationSet(Set<UserEducationModel> userEducationSet) {
        this.userEducationSet = userEducationSet;
    }

    public String getDefaultUserEducation_certificateLevel_id() {
        return defaultUserEducation_certificateLevel_id;
    }

    public void setDefaultUserEducation_certificateLevel_id(String defaultUserEducation_certificateLevel_id) {
        this.defaultUserEducation_certificateLevel_id = defaultUserEducation_certificateLevel_id;
    }

    public String getDefaultUserEducation_certificateField() {
        return defaultUserEducation_certificateField;
    }

    public void setDefaultUserEducation_certificateField(String defaultUserEducation_certificateField) {
        this.defaultUserEducation_certificateField = defaultUserEducation_certificateField;
    }

    public String getDefaultUserEducation_otherSkill() {
        return defaultUserEducation_otherSkill;
    }

    public void setDefaultUserEducation_otherSkill(String defaultUserEducation_otherSkill) {
        this.defaultUserEducation_otherSkill = defaultUserEducation_otherSkill;
    }

    public String getDefaultUserContact_postalCode() {
        return defaultUserContact_postalCode;
    }

    public void setDefaultUserContact_postalCode(String defaultUserContact_postalCode) {
        this.defaultUserContact_postalCode = defaultUserContact_postalCode;
    }

    public String getDefaultUserContact_emailAddress() {
        return defaultUserContact_emailAddress;
    }

    public void setDefaultUserContact_emailAddress(String defaultUserContact_emailAddress) {
        this.defaultUserContact_emailAddress = defaultUserContact_emailAddress;
    }

    public AutoCompleteModel getDefaultUserContact_city() {
        return defaultUserContact_city;
    }

    public void setDefaultUserContact_city(AutoCompleteModel defaultUserContact_city) {
        this.defaultUserContact_city = defaultUserContact_city;
    }

    public String getDefaultUserContact_address() {
        return defaultUserContact_address;
    }

    public void setDefaultUserContact_address(String defaultUserContact_address) {
        this.defaultUserContact_address = defaultUserContact_address;
    }

    public String getDefaultUserContact_mobileNo() {
        return defaultUserContact_mobileNo;
    }

    public void setDefaultUserContact_mobileNo(String defaultUserContact_mobileNo) {
        this.defaultUserContact_mobileNo = defaultUserContact_mobileNo;
    }

    public String getDefaultUserContact_phoneNo() {
        return defaultUserContact_phoneNo;
    }

    public void setDefaultUserContact_phoneNo(String defaultUserContact_phoneNo) {
        this.defaultUserContact_phoneNo = defaultUserContact_phoneNo;
    }

    public String getDefaultUserContact_jobAddress() {
        return defaultUserContact_jobAddress;
    }

    public void setDefaultUserContact_jobAddress(String defaultUserContact_jobAddress) {
        this.defaultUserContact_jobAddress = defaultUserContact_jobAddress;
    }

    public String getDefaultUserContact_jobPostalCode() {
        return defaultUserContact_jobPostalCode;
    }

    public void setDefaultUserContact_jobPostalCode(String defaultUserContact_jobPostalCode) {
        this.defaultUserContact_jobPostalCode = defaultUserContact_jobPostalCode;
    }

    public AutoCompleteModel getDefaultUserContact_jobCity() {
        return defaultUserContact_jobCity;
    }

    public void setDefaultUserContact_jobCity(AutoCompleteModel defaultUserContact_jobCity) {
        this.defaultUserContact_jobCity = defaultUserContact_jobCity;
    }

    public String getDefaultUserContact_jobFaxNo() {
        return defaultUserContact_jobFaxNo;
    }

    public void setDefaultUserContact_jobFaxNo(String defaultUserContact_jobFaxNo) {
        this.defaultUserContact_jobFaxNo = defaultUserContact_jobFaxNo;
    }

    public String getDefaultUserContact_jobPhoneNo() {
        return defaultUserContact_jobPhoneNo;
    }

    public void setDefaultUserContact_jobPhoneNo(String defaultUserContact_jobPhoneNo) {
        this.defaultUserContact_jobPhoneNo = defaultUserContact_jobPhoneNo;
    }

    public String getDefaultUserPosition_id() {
        return defaultUserPosition_id;
    }

    public void setDefaultUserPosition_id(String defaultUserPosition_id) {
        this.defaultUserPosition_id = defaultUserPosition_id;
    }

    public List<String> getSkillList() {
        return skillList;
    }

    public void setSkillList(List<String> skillList) {
        this.skillList = skillList;
    }

    public String getDefaultUserAccount_bankBranch_id() {
        return defaultUserAccount_bankBranch_id;
    }

    public void setDefaultUserAccount_bankBranch_id(String defaultUserAccount_bankBranch_id) {
        this.defaultUserAccount_bankBranch_id = defaultUserAccount_bankBranch_id;
    }

    public String getDefaultUserAccount_accountNo1() {
        return defaultUserAccount_accountNo1;
    }

    public void setDefaultUserAccount_accountNo1(String defaultUserAccount_accountNo1) {
        this.defaultUserAccount_accountNo1 = defaultUserAccount_accountNo1;
    }

    public String getDefaultUserAccount_accountNo2() {
        return defaultUserAccount_accountNo2;
    }

    public void setDefaultUserAccount_accountNo2(String defaultUserAccount_accountNo2) {
        this.defaultUserAccount_accountNo2 = defaultUserAccount_accountNo2;
    }

    public String getDefaultUserAccount_accountType_id() {
        return defaultUserAccount_accountType_id;
    }

    public void setDefaultUserAccount_accountType_id(String defaultUserAccount_accountType_id) {
        this.defaultUserAccount_accountType_id = defaultUserAccount_accountType_id;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", family='" + family + '\'' +
                ", gender_id=" + gender_id +
                ", shNo='" + shNo + '\'' +
                ", faragirCode='" + faragirCode + '\'' +
                ", nationalCode='" + nationalCode + '\'' +
                ", fatherName='" + fatherName + '\'' +
                ", shLocation=" + shLocation +
                ", shSerialNo='" + shSerialNo + '\'' +
                ", dateOfBorn=" + dateOfBorn +
                ", bornLocation=" + bornLocation +
                ", maritalStatus_id=" + maritalStatus_id +
                ", childNumber=" + childNumber +
                ", jobTitle='" + jobTitle + '\'' +
                ", jobCompanyName='" + jobCompanyName + '\'' +
                ", dateOfMarriage=" + dateOfMarriage +
                ", favorite='" + favorite + '\'' +
                ", newsLetter=" + newsLetter +
                ", userEducationSet=" + userEducationSet +
                ", defaultUserEducation_certificateLevel_id=" + defaultUserEducation_certificateLevel_id +
                ", defaultUserEducation_certificateField='" + defaultUserEducation_certificateField + '\'' +
                ", defaultUserEducation_otherSkill='" + defaultUserEducation_otherSkill + '\'' +
                ", defaultUserContact_postalCode='" + defaultUserContact_postalCode + '\'' +
                ", defaultUserContact_emailAddress='" + defaultUserContact_emailAddress + '\'' +
                ", defaultUserContact_city=" + defaultUserContact_city +
                ", defaultUserContact_address='" + defaultUserContact_address + '\'' +
                ", defaultUserContact_mobileNo='" + defaultUserContact_mobileNo + '\'' +
                ", defaultUserContact_phoneNo='" + defaultUserContact_phoneNo + '\'' +
                ", defaultUserContact_jobAddress='" + defaultUserContact_jobAddress + '\'' +
                ", defaultUserContact_jobPostalCode='" + defaultUserContact_jobPostalCode + '\'' +
                ", defaultUserContact_jobCity=" + defaultUserContact_jobCity +
                ", defaultUserContact_jobFaxNo='" + defaultUserContact_jobFaxNo + '\'' +
                ", defaultUserContact_jobPhoneNo='" + defaultUserContact_jobPhoneNo + '\'' +
                ", defaultUserPosition_id=" + defaultUserPosition_id +
                ", skillList=" + skillList +
                ", defaultUserAccount_bankBranch_id=" + defaultUserAccount_bankBranch_id +
                ", defaultUserAccount_accountNo1='" + defaultUserAccount_accountNo1 + '\'' +
                ", defaultUserAccount_accountNo2='" + defaultUserAccount_accountNo2 + '\'' +
                ", defaultUserAccount_accountType_id=" + defaultUserAccount_accountType_id +
                '}';
    }
}
