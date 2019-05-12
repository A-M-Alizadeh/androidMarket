/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.aalizade.mbazar_base_app.network.models.product;

import java.util.HashMap;

/**
 *
 * @author Dev1
 */
public class ProductTypeLine {

    //شناسه محصول
    private String id;
    //مقدار تخفیف
    private String discountValue;
    //مقدار تخفیف عرضه کننده
    private String discountVendorPercentage;
    //عنوان تخفیف عرضه کننده
    private String discountVendorTitle;
    //مقدار تخفیف بازار اعضا
    private Double discountMBazarPercentage;
    //عنوان تخفیف بازار اعضا
    private String discountMBazarTitle;
    //درصد مالیات
    private String tax_currentTotalRate;
    //حراج است؟
    private Boolean isOnSale = false;
    // تخفیف دارد؟
    private Boolean hasDiscount = false;
    //قیمت با مالیات
    private String unitPriceTaxInclude;
    //قیمت با تخفیف و مالیات
    private String unitPriceTaxIncludeDiscountInclude;
    /*
     * Integer => parentId
     * **parent productTypeGuaranty Id => 0
     */
    private HashMap<Integer, GeneralAttributeModel> attributeModelHashMap = new HashMap<>();

    public ProductTypeLine(String id) {
        this.id = id;
    }
    public ProductTypeLine() {
        
    }
    
    
    
    //===================================================//


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(String discountValue) {
        this.discountValue = discountValue;
    }

    public String getDiscountVendorPercentage() {
        return discountVendorPercentage;
    }

    public void setDiscountVendorPercentage(String discountVendorPercentage) {
        this.discountVendorPercentage = discountVendorPercentage;
    }

    public String getDiscountVendorTitle() {
        return discountVendorTitle;
    }

    public void setDiscountVendorTitle(String discountVendorTitle) {
        this.discountVendorTitle = discountVendorTitle;
    }

    public Double getDiscountMBazarPercentage() {
        return discountMBazarPercentage;
    }

    public void setDiscountMBazarPercentage(Double discountMBazarPercentage) {
        this.discountMBazarPercentage = discountMBazarPercentage;
    }

    public String getDiscountMBazarTitle() {
        return discountMBazarTitle;
    }

    public void setDiscountMBazarTitle(String discountMBazarTitle) {
        this.discountMBazarTitle = discountMBazarTitle;
    }

    public String getTax_currentTotalRate() {
        return tax_currentTotalRate;
    }

    public void setTax_currentTotalRate(String tax_currentTotalRate) {
        this.tax_currentTotalRate = tax_currentTotalRate;
    }

    public Boolean getOnSale() {
        return isOnSale;
    }

    public void setOnSale(Boolean onSale) {
        isOnSale = onSale;
    }

    public Boolean getHasDiscount() {
        return hasDiscount;
    }

    public void setHasDiscount(Boolean hasDiscount) {
        this.hasDiscount = hasDiscount;
    }

    public String getUnitPriceTaxInclude() {
        return unitPriceTaxInclude;
    }

    public void setUnitPriceTaxInclude(String unitPriceTaxInclude) {
        this.unitPriceTaxInclude = unitPriceTaxInclude;
    }

    public String getUnitPriceTaxIncludeDiscountInclude() {
        return unitPriceTaxIncludeDiscountInclude;
    }

    public void setUnitPriceTaxIncludeDiscountInclude(String unitPriceTaxIncludeDiscountInclude) {
        this.unitPriceTaxIncludeDiscountInclude = unitPriceTaxIncludeDiscountInclude;
    }

    public HashMap<Integer, GeneralAttributeModel> getAttributeModelHashMap() {
        return attributeModelHashMap;
    }

    public void setAttributeModelHashMap(HashMap<Integer, GeneralAttributeModel> attributeModelHashMap) {
        this.attributeModelHashMap = attributeModelHashMap;
    }

    @Override
    public String toString() {
        return "ProductTypeLine{" +
                "id='" + id + '\'' +
                ", discountValue='" + discountValue + '\'' +
                ", discountVendorPercentage='" + discountVendorPercentage + '\'' +
                ", discountVendorTitle='" + discountVendorTitle + '\'' +
                ", discountMBazarPercentage=" + discountMBazarPercentage +
                ", discountMBazarTitle='" + discountMBazarTitle + '\'' +
                ", tax_currentTotalRate='" + tax_currentTotalRate + '\'' +
                ", isOnSale=" + isOnSale +
                ", hasDiscount=" + hasDiscount +
                ", unitPriceTaxInclude='" + unitPriceTaxInclude + '\'' +
                ", unitPriceTaxIncludeDiscountInclude='" + unitPriceTaxIncludeDiscountInclude + '\'' +
                ", attributeModelHashMap=" + attributeModelHashMap +
                '}';
    }
}
