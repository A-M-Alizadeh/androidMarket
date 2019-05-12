package com.example.aalizade.mbazar_base_app.network.models.product;

/**
 * Created by aalizade on 4/30/2018.
 */

public class VendorOtherConditionsCustomModel {
    private String guarantyName;
    private String guarantyCode;
    private String colorName;
    private String colorCode;
    private String productId;
    private String price;

    public VendorOtherConditionsCustomModel() {
    }

    public VendorOtherConditionsCustomModel(String guarantyName, String guarantyCode, String colorName, String colorCode, String productId, String price) {
        this.guarantyName = guarantyName;
        this.guarantyCode = guarantyCode;
        this.colorName = colorName;
        this.colorCode = colorCode;
        this.productId = productId;
        this.price = price;
    }

    public String getGuarantyName() {
        return guarantyName;
    }

    public void setGuarantyName(String guarantyName) {
        this.guarantyName = guarantyName;
    }

    public String getGuarantyCode() {
        return guarantyCode;
    }

    public void setGuarantyCode(String guarantyCode) {
        this.guarantyCode = guarantyCode;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "VendorOtherConditionsCustomModel{" +
                "guarantyName='" + guarantyName + '\'' +
                ", guarantyCode=" + guarantyCode +
                ", colorName='" + colorName + '\'' +
                ", colorCode='" + colorCode + '\'' +
                ", productId='" + productId + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
