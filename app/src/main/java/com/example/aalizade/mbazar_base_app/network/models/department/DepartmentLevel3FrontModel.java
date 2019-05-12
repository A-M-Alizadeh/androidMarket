/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.aalizade.mbazar_base_app.network.models.department;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author Dev3
 */
public class DepartmentLevel3FrontModel extends DepartmentFrontModel {

    @SerializedName("description")
    private String description;
    @SerializedName("departmentReferenceTypeEnum")
    private String departmentReferenceTypeEnum;
    @SerializedName("referenceUrl")
    private String referenceUrl;
    @SerializedName("productPage_id")
    private String productPage_id;
    @SerializedName("productTypeGroup_id")
    private String productTypeGroup_id;
    @SerializedName("offer_id")
    private String offer_id;
    @SerializedName("isSpecial")
    private Boolean isSpecial;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDepartmentReferenceTypeEnum() {
        return departmentReferenceTypeEnum;
    }

    public void setDepartmentReferenceTypeEnum(String departmentReferenceTypeEnum) {
        this.departmentReferenceTypeEnum = departmentReferenceTypeEnum;
    }

    public String getReferenceUrl() {
        return referenceUrl;
    }

    public void setReferenceUrl(String referenceUrl) {
        this.referenceUrl = referenceUrl;
    }

    public String getProductPage_id() {
        return productPage_id;
    }

    public void setProductPage_id(String productPage_id) {
        this.productPage_id = productPage_id;
    }

    public String getProductTypeGroup_id() {
        return productTypeGroup_id;
    }

    public void setProductTypeGroup_id(String productTypeGroup_id) {
        this.productTypeGroup_id = productTypeGroup_id;
    }

    public String getOffer_id() {
        return offer_id;
    }

    public void setOffer_id(String offer_id) {
        this.offer_id = offer_id;
    }

    public Boolean getIsSpecial() {
        return isSpecial;
    }

    public void setIsSpecial(Boolean isSpecial) {
        this.isSpecial = isSpecial;
    }

    @Override
    public String toString() {
        return "DepartmentLevel3FrontModel{" +
                "description='" + description + '\'' +
                ", departmentReferenceTypeEnum='" + departmentReferenceTypeEnum + '\'' +
                ", referenceUrl='" + referenceUrl + '\'' +
                ", productPage_id=" + productPage_id +
                ", productTypeGroup_id=" + productTypeGroup_id +
                ", offer_id=" + offer_id +
                ", isSpecial=" + isSpecial +
                '}';
    }
}
