package com.example.aalizade.mbazar_base_app.entities;

/**
 * Created by aalizade on 3/6/2018.
 */

public class UserCreditEntity {
    private String id;
    private String type;
    private String remainAmount;
    private String productTypeGroupId;
    private String productTypeGroup;
    private String lastUsageDate;
    private String donator_id;
    private String donator_name;

    public UserCreditEntity(String id, String type, String remainAmount, String productTypeGroupId, String productTypeGroup, String lastUsageDate, String donator_id, String donator_name) {
        this.id = id;
        this.type = type;
        this.remainAmount = remainAmount;
        this.productTypeGroupId = productTypeGroupId;
        this.productTypeGroup = productTypeGroup;
        this.lastUsageDate = lastUsageDate;
        this.donator_id = donator_id;
        this.donator_name = donator_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemainAmount() {
        return remainAmount;
    }

    public void setRemainAmount(String remainAmount) {
        this.remainAmount = remainAmount;
    }

    public String getProductTypeGroupId() {
        return productTypeGroupId;
    }

    public void setProductTypeGroupId(String productTypeGroupId) {
        this.productTypeGroupId = productTypeGroupId;
    }

    public String getProductTypeGroup() {
        return productTypeGroup;
    }

    public void setProductTypeGroup(String productTypeGroup) {
        this.productTypeGroup = productTypeGroup;
    }

    public String getLastUsageDate() {
        return lastUsageDate;
    }

    public void setLastUsageDate(String lastUsageDate) {
        this.lastUsageDate = lastUsageDate;
    }

    public String getDonator_id() {
        return donator_id;
    }

    public void setDonator_id(String donator_id) {
        this.donator_id = donator_id;
    }

    public String getDonator_name() {
        return donator_name;
    }

    public void setDonator_name(String donator_name) {
        this.donator_name = donator_name;
    }

    @Override
    public String toString() {
        return "UserCreditEntity{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", remainAmount='" + remainAmount + '\'' +
                ", productTypeGroupId='" + productTypeGroupId + '\'' +
                ", productTypeGroup='" + productTypeGroup + '\'' +
                ", lastUsageDate='" + lastUsageDate + '\'' +
                ", donator_id='" + donator_id + '\'' +
                ", donator_name='" + donator_name + '\'' +
                '}';
    }
}
