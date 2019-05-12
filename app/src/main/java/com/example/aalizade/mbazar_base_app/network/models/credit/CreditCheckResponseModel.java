package com.example.aalizade.mbazar_base_app.network.models.credit;

import com.example.aalizade.mbazar_base_app.network.models.general.CustomDate;

/**
 * Created by sbayatani on 4/8/2018.
 */

public class CreditCheckResponseModel {
    private String code;
    private String donatorId;
    private String hidden;
    private String id;
    private String invalid;
    private String productTypeGroup_id;
    private String productTypeGroup_title;
    private String remainAmount;
    private String type_id;
    private String userGiftRequest_id;
    private CustomDate userGiftRequest_expireDate;
    private CustomDate lastUsageDate;

    public CreditCheckResponseModel(String code, String donatorId, String hidden, String id, String invalid, String productTypeGroup_id, String productTypeGroup_title, String remainAmount, String type_id, String userGiftRequest_id, CustomDate userGiftRequest_expireDate, CustomDate lastUsageDate) {
        this.code = code;
        this.donatorId = donatorId;
        this.hidden = hidden;
        this.id = id;
        this.invalid = invalid;
        this.productTypeGroup_id = productTypeGroup_id;
        this.productTypeGroup_title = productTypeGroup_title;
        this.remainAmount = remainAmount;
        this.type_id = type_id;
        this.userGiftRequest_id = userGiftRequest_id;
        this.userGiftRequest_expireDate = userGiftRequest_expireDate;
        this.lastUsageDate = lastUsageDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDonatorId() {
        return donatorId;
    }

    public void setDonatorId(String donatorId) {
        this.donatorId = donatorId;
    }

    public String getHidden() {
        return hidden;
    }

    public void setHidden(String hidden) {
        this.hidden = hidden;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInvalid() {
        return invalid;
    }

    public void setInvalid(String invalid) {
        this.invalid = invalid;
    }

    public String getProductTypeGroup_id() {
        return productTypeGroup_id;
    }

    public void setProductTypeGroup_id(String productTypeGroup_id) {
        this.productTypeGroup_id = productTypeGroup_id;
    }

    public String getProductTypeGroup_title() {
        return productTypeGroup_title;
    }

    public void setProductTypeGroup_title(String productTypeGroup_title) {
        this.productTypeGroup_title = productTypeGroup_title;
    }

    public String getRemainAmount() {
        return remainAmount;
    }

    public void setRemainAmount(String remainAmount) {
        this.remainAmount = remainAmount;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public String getUserGiftRequest_id() {
        return userGiftRequest_id;
    }

    public void setUserGiftRequest_id(String userGiftRequest_id) {
        this.userGiftRequest_id = userGiftRequest_id;
    }

    public CustomDate getUserGiftRequest_expireDate() {
        return userGiftRequest_expireDate;
    }

    public void setUserGiftRequest_expireDate(CustomDate userGiftRequest_expireDate) {
        this.userGiftRequest_expireDate = userGiftRequest_expireDate;
    }

    public CustomDate getLastUsageDate() {
        return lastUsageDate;
    }

    public void setLastUsageDate(CustomDate lastUsageDate) {
        this.lastUsageDate = lastUsageDate;
    }

    @Override
    public String toString() {
        return "CreditCheckResponseModel{" +
                "code='" + code + '\'' +
                ", donatorId='" + donatorId + '\'' +
                ", hidden='" + hidden + '\'' +
                ", id='" + id + '\'' +
                ", invalid='" + invalid + '\'' +
                ", productTypeGroup_id='" + productTypeGroup_id + '\'' +
                ", productTypeGroup_title='" + productTypeGroup_title + '\'' +
                ", remainAmount='" + remainAmount + '\'' +
                ", type_id='" + type_id + '\'' +
                ", userGiftRequest_id='" + userGiftRequest_id + '\'' +
                ", userGiftRequest_expireDate=" + userGiftRequest_expireDate +
                ", lastUsageDate=" + lastUsageDate +
                '}';
    }
}
