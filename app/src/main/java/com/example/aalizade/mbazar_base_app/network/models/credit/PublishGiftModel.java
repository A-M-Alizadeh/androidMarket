package com.example.aalizade.mbazar_base_app.network.models.credit;

/**
 * Created by sbayatani on 4/12/2018.
 */

public class PublishGiftModel {

    private String id;
    private String giftCode;
    private String type;
    private String userGiftRequest_requestDate;
    private String userGiftRequest_confirmRejectDate;
    private String userGiftRequest_expireDate;
    private String publishedAmount;
    private String remainAmount;
    private String productTypeGroup_id;
    private String productTypeGroup_title;
    private String userGiftRequest_printed;
    private String lastUsageDate;

    public PublishGiftModel(String id, String giftCode, String type, String userGiftRequest_requestDate, String userGiftRequest_confirmRejectDate, String userGiftRequest_expireDate, String publishedAmount, String remainAmount, String productTypeGroup_id, String productTypeGroup_title, String userGiftRequest_printed, String lastUsageDate) {
        this.id = id;
        this.giftCode = giftCode;
        this.type = type;
        this.userGiftRequest_requestDate = userGiftRequest_requestDate;
        this.userGiftRequest_confirmRejectDate = userGiftRequest_confirmRejectDate;
        this.userGiftRequest_expireDate = userGiftRequest_expireDate;
        this.publishedAmount = publishedAmount;
        this.remainAmount = remainAmount;
        this.productTypeGroup_id = productTypeGroup_id;
        this.productTypeGroup_title = productTypeGroup_title;
        this.userGiftRequest_printed = userGiftRequest_printed;
        this.lastUsageDate = lastUsageDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGiftCode() {
        return giftCode;
    }

    public void setGiftCode(String giftCode) {
        this.giftCode = giftCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserGiftRequest_requestDate() {
        return userGiftRequest_requestDate;
    }

    public void setUserGiftRequest_requestDate(String userGiftRequest_requestDate) {
        this.userGiftRequest_requestDate = userGiftRequest_requestDate;
    }

    public String getUserGiftRequest_confirmRejectDate() {
        return userGiftRequest_confirmRejectDate;
    }

    public void setUserGiftRequest_confirmRejectDate(String userGiftRequest_confirmRejectDate) {
        this.userGiftRequest_confirmRejectDate = userGiftRequest_confirmRejectDate;
    }

    public String getUserGiftRequest_expireDate() {
        return userGiftRequest_expireDate;
    }

    public void setUserGiftRequest_expireDate(String userGiftRequest_expireDate) {
        this.userGiftRequest_expireDate = userGiftRequest_expireDate;
    }

    public String getPublishedAmount() {
        return publishedAmount;
    }

    public void setPublishedAmount(String publishedAmount) {
        this.publishedAmount = publishedAmount;
    }

    public String getRemainAmount() {
        return remainAmount;
    }

    public void setRemainAmount(String remainAmount) {
        this.remainAmount = remainAmount;
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

    public String getUserGiftRequest_printed() {
        return userGiftRequest_printed;
    }

    public void setUserGiftRequest_printed(String userGiftRequest_printed) {
        this.userGiftRequest_printed = userGiftRequest_printed;
    }

    public String getLastUsageDate() {
        return lastUsageDate;
    }

    public void setLastUsageDate(String lastUsageDate) {
        this.lastUsageDate = lastUsageDate;
    }

    @Override
    public String toString() {
        return "PublishGiftModel{" +
                "id='" + id + '\'' +
                ", giftCode='" + giftCode + '\'' +
                ", type='" + type + '\'' +
                ", userGiftRequest_requestDate='" + userGiftRequest_requestDate + '\'' +
                ", userGiftRequest_confirmRejectDate='" + userGiftRequest_confirmRejectDate + '\'' +
                ", userGiftRequest_expireDate='" + userGiftRequest_expireDate + '\'' +
                ", publishedAmount='" + publishedAmount + '\'' +
                ", remainAmount='" + remainAmount + '\'' +
                ", productTypeGroup_id='" + productTypeGroup_id + '\'' +
                ", productTypeGroup_title='" + productTypeGroup_title + '\'' +
                ", userGiftRequest_printed='" + userGiftRequest_printed + '\'' +
                ", lastUsageDate='" + lastUsageDate + '\'' +
                '}';
    }
}
