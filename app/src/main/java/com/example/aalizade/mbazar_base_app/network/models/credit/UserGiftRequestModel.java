package com.example.aalizade.mbazar_base_app.network.models.credit;

import com.example.aalizade.mbazar_base_app.network.models.general.AutoCompleteModel;
import com.example.aalizade.mbazar_base_app.network.models.general.CustomDate;

/**
 * Created by sbayatani on 4/23/2018.
 */

public class UserGiftRequestModel {

    private String amount;//مبلغ کل بن ها

    private String unitAmount;//مبلغ هر بن

    private String quantity;//تعداد بن

    private String type_id;//نوع

    private String paymentMethod_id;//نحوه پرداخت

    private String paymentReference;//کد بن هدیه/شناسه درگاه بانکی/شماره قبض واریزی

    private String paymentType_id;//نوع واریز به حساب

    private String paymentType_value;

    private String paymentDocument_id;//سند واریز وجه

    private String productTypeGroup_id;//گروه محصول

    private CustomDate moneyTransferDate;//تاریخ واریز به حساب قبض واریزی

    private String moneyTransferAmount;//مبلغ پرداختی قبض واریزی

    private String moneyTransferSource;//شماره حساب مبدا قبض واریزی

    private String moneyTransferDescription;//توضیحات قبض واریزی

    private String fileKeyMoneyTransferImage;//تصویر قبض واریزی

    private AutoCompleteModel productTypeGroup;//گروه محصول

    private CustomDate expireDate;//تاریخ انقضا

    private String printed ;//درخواست چاپ

    //نشانی تحویل نسخه چاپی

    private String userContactTemp ;//نشانی موقت درست..نشانی موجود نادرست

    private String userContact_id;//نشانی

    private AutoCompleteModel contactCity;//شهر

    private String contactAddress;//نشانی

    public UserGiftRequestModel(String amount, String unitAmount, String quantity, String type_id, String paymentMethod_id, String paymentReference, String paymentType_id, String paymentType_value, String paymentDocument_id, String productTypeGroup_id, CustomDate moneyTransferDate, String moneyTransferAmount, String moneyTransferSource, String moneyTransferDescription, String fileKeyMoneyTransferImage, AutoCompleteModel productTypeGroup, CustomDate expireDate, String printed, String userContactTemp, String userContact_id, AutoCompleteModel contactCity, String contactAddress) {
        this.amount = amount;
        this.unitAmount = unitAmount;
        this.quantity = quantity;
        this.type_id = type_id;
        this.paymentMethod_id = paymentMethod_id;
        this.paymentReference = paymentReference;
        this.paymentType_id = paymentType_id;
        this.paymentType_value = paymentType_value;
        this.paymentDocument_id = paymentDocument_id;
        this.productTypeGroup_id = productTypeGroup_id;
        this.moneyTransferDate = moneyTransferDate;
        this.moneyTransferAmount = moneyTransferAmount;
        this.moneyTransferSource = moneyTransferSource;
        this.moneyTransferDescription = moneyTransferDescription;
        this.fileKeyMoneyTransferImage = fileKeyMoneyTransferImage;
        this.productTypeGroup = productTypeGroup;
        this.expireDate = expireDate;
        this.printed = printed;
        this.userContactTemp = userContactTemp;
        this.userContact_id = userContact_id;
        this.contactCity = contactCity;
        this.contactAddress = contactAddress;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getUnitAmount() {
        return unitAmount;
    }

    public void setUnitAmount(String unitAmount) {
        this.unitAmount = unitAmount;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public String getPaymentMethod_id() {
        return paymentMethod_id;
    }

    public void setPaymentMethod_id(String paymentMethod_id) {
        this.paymentMethod_id = paymentMethod_id;
    }

    public String getPaymentReference() {
        return paymentReference;
    }

    public void setPaymentReference(String paymentReference) {
        this.paymentReference = paymentReference;
    }

    public String getPaymentType_id() {
        return paymentType_id;
    }

    public void setPaymentType_id(String paymentType_id) {
        this.paymentType_id = paymentType_id;
    }

    public String getPaymentType_value() {
        return paymentType_value;
    }

    public void setPaymentType_value(String paymentType_value) {
        this.paymentType_value = paymentType_value;
    }

    public String getPaymentDocument_id() {
        return paymentDocument_id;
    }

    public void setPaymentDocument_id(String paymentDocument_id) {
        this.paymentDocument_id = paymentDocument_id;
    }

    public String getProductTypeGroup_id() {
        return productTypeGroup_id;
    }

    public void setProductTypeGroup_id(String productTypeGroup_id) {
        this.productTypeGroup_id = productTypeGroup_id;
    }

    public CustomDate getMoneyTransferDate() {
        return moneyTransferDate;
    }

    public void setMoneyTransferDate(CustomDate moneyTransferDate) {
        this.moneyTransferDate = moneyTransferDate;
    }

    public String getMoneyTransferAmount() {
        return moneyTransferAmount;
    }

    public void setMoneyTransferAmount(String moneyTransferAmount) {
        this.moneyTransferAmount = moneyTransferAmount;
    }

    public String getMoneyTransferSource() {
        return moneyTransferSource;
    }

    public void setMoneyTransferSource(String moneyTransferSource) {
        this.moneyTransferSource = moneyTransferSource;
    }

    public String getMoneyTransferDescription() {
        return moneyTransferDescription;
    }

    public void setMoneyTransferDescription(String moneyTransferDescription) {
        this.moneyTransferDescription = moneyTransferDescription;
    }

    public String getFileKeyMoneyTransferImage() {
        return fileKeyMoneyTransferImage;
    }

    public void setFileKeyMoneyTransferImage(String fileKeyMoneyTransferImage) {
        this.fileKeyMoneyTransferImage = fileKeyMoneyTransferImage;
    }

    public AutoCompleteModel getProductTypeGroup() {
        return productTypeGroup;
    }

    public void setProductTypeGroup(AutoCompleteModel productTypeGroup) {
        this.productTypeGroup = productTypeGroup;
    }

    public CustomDate getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(CustomDate expireDate) {
        this.expireDate = expireDate;
    }

    public String getPrinted() {
        return printed;
    }

    public void setPrinted(String printed) {
        this.printed = printed;
    }

    public String getUserContactTemp() {
        return userContactTemp;
    }

    public void setUserContactTemp(String userContactTemp) {
        this.userContactTemp = userContactTemp;
    }

    public String getUserContact_id() {
        return userContact_id;
    }

    public void setUserContact_id(String userContact_id) {
        this.userContact_id = userContact_id;
    }

    public AutoCompleteModel getContactCity() {
        return contactCity;
    }

    public void setContactCity(AutoCompleteModel contactCity) {
        this.contactCity = contactCity;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    @Override
    public String toString() {
        return "{" +
                "\"amount\":\"" + amount + '\"' +
                ", \"unitAmount\":\"" + unitAmount + '\"' +
                ", \"quantity\":\"" + quantity + '\"' +
                ", \"type_id\":\"" + type_id + '\"' +
                ", \"paymentMethod_id\":\"" + paymentMethod_id + '\"' +
                ", \"paymentReference\":\"" + paymentReference + '\"' +
                ", \"paymentType_id\":\"" + paymentType_id + '\"' +
                ", \"paymentType_value\":\"" + paymentType_value + '\"' +
                ", \"paymentDocument_id\":\"" + paymentDocument_id + '\"' +
                ", \"productTypeGroup_id\":\"" + productTypeGroup_id + '\"' +
                ", \"moneyTransferDate\":" + moneyTransferDate +
                ", \"moneyTransferAmount\":\"" + moneyTransferAmount + '\"' +
                ", \"moneyTransferSource\":\"" + moneyTransferSource + '\"' +
                ", \"moneyTransferDescription\":\"" + moneyTransferDescription + '\"' +
                ", \"fileKeyMoneyTransferImage\":\"" + fileKeyMoneyTransferImage + '\"' +
                ", \"productTypeGroup\":" + productTypeGroup +
                ", \"expireDate\":" + expireDate +
                ", \"printed\":\"" + printed + '\"' +
                ", \"userContactTemp\":\"" + userContactTemp + '\"' +
                ", \"userContact_id\":\"" + userContact_id + '\"' +
                ", \"contactCity\":" + contactCity +
                ", \"contactAddress\":\"" + contactAddress + '\"' +
                '}';
    }
}
