package com.example.aalizade.mbazar_base_app.network.models.checkout;

/**
 * Created by sbayatani on 4/15/2018.
 */

public class OrderCarrierModel {
    private String id;
    private String code;
    private String carrierId;
    private String carrierType;
    private String productCunt;
    private String totalWeight;
    private String totalMass;
    private String carrierAmount;
    private String duration;
    private String dateOfSend;
    private String dateOfRecive;
    private String sendStatus;

    public OrderCarrierModel(String id, String code, String carrierId, String carrierType, String productCunt, String totalWeight, String totalMass, String carrierAmount, String duration, String dateOfSend, String dateOfRecive, String sendStatus) {
        this.id = id;
        this.code = code;
        this.carrierId = carrierId;
        this.carrierType = carrierType;
        this.productCunt = productCunt;
        this.totalWeight = totalWeight;
        this.totalMass = totalMass;
        this.carrierAmount = carrierAmount;
        this.duration = duration;
        this.dateOfSend = dateOfSend;
        this.dateOfRecive = dateOfRecive;
        this.sendStatus = sendStatus;
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

    public String getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(String carrierId) {
        this.carrierId = carrierId;
    }

    public String getCarrierType() {
        return carrierType;
    }

    public void setCarrierType(String carrierType) {
        this.carrierType = carrierType;
    }

    public String getProductCunt() {
        return productCunt;
    }

    public void setProductCunt(String productCunt) {
        this.productCunt = productCunt;
    }

    public String getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(String totalWeight) {
        this.totalWeight = totalWeight;
    }

    public String getTotalMass() {
        return totalMass;
    }

    public void setTotalMass(String totalMass) {
        this.totalMass = totalMass;
    }

    public String getCarrierAmount() {
        return carrierAmount;
    }

    public void setCarrierAmount(String carrierAmount) {
        this.carrierAmount = carrierAmount;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDateOfSend() {
        return dateOfSend;
    }

    public void setDateOfSend(String dateOfSend) {
        this.dateOfSend = dateOfSend;
    }

    public String getDateOfRecive() {
        return dateOfRecive;
    }

    public void setDateOfRecive(String dateOfRecive) {
        this.dateOfRecive = dateOfRecive;
    }

    public String getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus;
    }
}
