package com.example.aalizade.mbazar_base_app.network.models.credit;

/**
 * Created by sbayatani on 4/11/2018.
 */

public class GiftRequestModel {
    private String id;
    private String code;
    private String type;
    private String userGiftRequest_requestDate;
    private String userGiftRequest_confirmRejectDate;
    private String userGiftRequest_expireDate;
    private String quantity;
    private String unitAmount;
    private String amount;
    private String paymentMethod;
    private String paymentReference;
    private String moneyTransferDate;
    private String paymentType_id;
    private String productTypeGroup_id;
    private String productTypeGroup_title;
    private String userGiftRequest_printed;
    private String status_langkey;

    public GiftRequestModel(String id, String code, String type, String userGiftRequest_requestDate, String userGiftRequest_confirmRejectDate, String userGiftRequest_expireDate, String quantity, String unitAmount, String amount, String paymentMethod, String paymentReference, String moneyTransferDate, String paymentType_id, String productTypeGroup_id, String productTypeGroup_title, String userGiftRequest_printed, String status_langkey) {
        this.id = id;
        this.code = code;
        this.type = type;
        this.userGiftRequest_requestDate = userGiftRequest_requestDate;
        this.userGiftRequest_confirmRejectDate = userGiftRequest_confirmRejectDate;
        this.userGiftRequest_expireDate = userGiftRequest_expireDate;
        this.quantity = quantity;
        this.unitAmount = unitAmount;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paymentReference = paymentReference;
        this.moneyTransferDate = moneyTransferDate;
        this.paymentType_id = paymentType_id;
        this.productTypeGroup_id = productTypeGroup_id;
        this.productTypeGroup_title = productTypeGroup_title;
        this.userGiftRequest_printed = userGiftRequest_printed;
        this.status_langkey = status_langkey;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getUnitAmount() {
        return unitAmount;
    }

    public void setUnitAmount(String unitAmount) {
        this.unitAmount = unitAmount;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentReference() {
        return paymentReference;
    }

    public void setPaymentReference(String paymentReference) {
        this.paymentReference = paymentReference;
    }

    public String getMoneyTransferDate() {
        return moneyTransferDate;
    }

    public void setMoneyTransferDate(String moneyTransferDate) {
        this.moneyTransferDate = moneyTransferDate;
    }

    public String getPaymentType_id() {
        return paymentType_id;
    }

    public void setPaymentType_id(String paymentType_id) {
        this.paymentType_id = paymentType_id;
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

    public String getStatus_langkey() {
        return status_langkey;
    }

    public void setStatus_langkey(String status_langkey) {
        this.status_langkey = status_langkey;
    }

    @Override
    public String toString() {
        return "GiftRequestModel{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", type='" + type + '\'' +
                ", userGiftRequest_requestDate='" + userGiftRequest_requestDate + '\'' +
                ", userGiftRequest_confirmRejectDate='" + userGiftRequest_confirmRejectDate + '\'' +
                ", userGiftRequest_expireDate='" + userGiftRequest_expireDate + '\'' +
                ", quantity='" + quantity + '\'' +
                ", unitAmount='" + unitAmount + '\'' +
                ", amount='" + amount + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", paymentReference='" + paymentReference + '\'' +
                ", moneyTransferDate='" + moneyTransferDate + '\'' +
                ", paymentType_id='" + paymentType_id + '\'' +
                ", productTypeGroup_id='" + productTypeGroup_id + '\'' +
                ", productTypeGroup_title='" + productTypeGroup_title + '\'' +
                ", userGiftRequest_printed='" + userGiftRequest_printed + '\'' +
                ", status_langkey='" + status_langkey + '\'' +
                '}';
    }
}
