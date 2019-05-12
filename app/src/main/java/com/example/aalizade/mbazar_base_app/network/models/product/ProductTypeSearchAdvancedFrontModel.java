/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.aalizade.mbazar_base_app.network.models.product;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Dev1
 */
public class ProductTypeSearchAdvancedFrontModel extends ProductTypeSearchFrontModel {

    //شاخه کالایی
    private String productCategoryId;
    //دپارتمان
    private String departmentId;
    //جستجو
    private String simpleSearch;
    // مقدار مشخصه ها
    private Set<Integer> attributeValueIdSet = new HashSet<>();
    //دارای موجودی
    private Boolean hasQuantity = false;
    //دارای تخفیف
    private Boolean hasDiscount = false;
    // حراج
    private Boolean isOnSale = false;
    //وضعیت
//    private Set<ProductTypeUsedStatusEnum> usedStatusSet = new HashSet();
    //عرضه کننده ها
    private Set<Integer> vendorIdSet = new HashSet<>();
    // حداقل قیمت
    private Long unitPriceMinimum;
    //حداکثر قیمت
    private Long unitPriceMaximmum;
    // تولید کننده - برند
    //key => تولید کننده
    // value => برندها
    private HashMap<Integer, Set<Integer>> manufacturerProductBrandIdHashMap = new HashMap<>();

    //=======================================================//


    public String getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(String productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public Boolean getOnSale() {
        return isOnSale;
    }

    public void setOnSale(Boolean onSale) {
        isOnSale = onSale;
    }

    public String getSimpleSearch() {
        return simpleSearch;
    }

    public void setSimpleSearch(String simpleSearch) {
        this.simpleSearch = simpleSearch;
    }

    public Set<Integer> getAttributeValueIdSet() {
        return attributeValueIdSet;
    }

    public void setAttributeValueIdSet(Set<Integer> attributeValueIdSet) {
        this.attributeValueIdSet = attributeValueIdSet;
    }

    public Boolean getHasQuantity() {
        return hasQuantity;
    }

    public void setHasQuantity(Boolean hasQuantity) {
        this.hasQuantity = hasQuantity;
    }

    public Boolean getHasDiscount() {
        return hasDiscount;
    }

    public void setHasDiscount(Boolean hasDiscount) {
        this.hasDiscount = hasDiscount;
    }

    public Boolean getIsOnSale() {
        return isOnSale;
    }

    public void setIsOnSale(Boolean isOnSale) {
        this.isOnSale = isOnSale;
    }

//    public Set<ProductTypeUsedStatusEnum> getUsedStatusSet() {
//        return usedStatusSet;
//    }
//
//    public void setUsedStatusSet(Set<ProductTypeUsedStatusEnum> usedStatusSet) {
//        this.usedStatusSet = usedStatusSet;
//    }

    public Set<Integer> getVendorIdSet() {
        return vendorIdSet;
    }

    public void setVendorIdSet(Set<Integer> vendorIdSet) {
        this.vendorIdSet = vendorIdSet;
    }

    public Long getUnitPriceMinimum() {
        return unitPriceMinimum;
    }

    public void setUnitPriceMinimum(Long unitPriceMinimum) {
        this.unitPriceMinimum = unitPriceMinimum;
    }

    public Long getUnitPriceMaximmum() {
        return unitPriceMaximmum;
    }

    public void setUnitPriceMaximmum(Long unitPriceMaximmum) {
        this.unitPriceMaximmum = unitPriceMaximmum;
    }


    public HashMap<Integer, Set<Integer>> getManufacturerProductBrandIdHashMap() {
        return manufacturerProductBrandIdHashMap;
    }

    public void setManufacturerProductBrandIdHashMap(HashMap<Integer, Set<Integer>> manufacturerProductBrandIdHashMap) {
        this.manufacturerProductBrandIdHashMap = manufacturerProductBrandIdHashMap;
    }

    @Override
    public String toString() {
        return "ProductTypeSearchAdvancedFrontModel{" +
                "productCategoryId='" + productCategoryId + '\'' +
                ", departmentId='" + departmentId + '\'' +
                ", simpleSearch='" + simpleSearch + '\'' +
                ", attributeValueIdSet=" + attributeValueIdSet +
                ", hasQuantity=" + hasQuantity +
                ", hasDiscount=" + hasDiscount +
                ", isOnSale=" + isOnSale +
                ", vendorIdSet=" + vendorIdSet +
                ", unitPriceMinimum=" + unitPriceMinimum +
                ", unitPriceMaximmum=" + unitPriceMaximmum +
                ", manufacturerProductBrandIdHashMap=" + manufacturerProductBrandIdHashMap +
                '}';
    }
}
