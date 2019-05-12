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
public class ProductTypeLineSidebarFindModel extends FindByVitrinModel {

    /**
     * عرضه کننده ها
     */
    private Set<Integer> vendorIdSet = new HashSet<>();
    /**
     * گارانتی ها
     */
    private Set<Integer> productTypeGuarantyIdSet = new HashSet<>();
    /**
     * مقادیر مشخصه با کلید عنوان مشخصه
     */
    private HashMap<Integer, Set<Integer>> attributeValueIdSetHashMap = new HashMap<>();

    //==================================================//
    /**
     * عرضه کننده ها
     *
     * @return the vendorIdSet
     */
    public Set<Integer> getVendorIdSet() {
        return vendorIdSet;
    }

    /**
     * عرضه کننده ها
     *
     * @param vendorIdSet the vendorIdSet to set
     */
    public void setVendorIdSet(Set<Integer> vendorIdSet) {
        this.vendorIdSet = vendorIdSet;
    }

    /**
     * گارانتی ها
     *
     * @return the productTypeGuarantyIdSet
     */
    public Set<Integer> getProductTypeGuarantyIdSet() {
        return productTypeGuarantyIdSet;
    }

    /**
     * گارانتی ها
     *
     * @param productTypeGuarantyIdSet the productTypeGuarantyIdSet to set
     */
    public void setProductTypeGuarantyIdSet(Set<Integer> productTypeGuarantyIdSet) {
        this.productTypeGuarantyIdSet = productTypeGuarantyIdSet;
    }

    /**
     * مقادیر مشخصه با کلید عنوان مشخصه
     *
     * @return the attributeValueIdSetHashMap
     */
    public HashMap<Integer, Set<Integer>> getAttributeValueIdSetHashMap() {
        return attributeValueIdSetHashMap;
    }

    /**
     * مقادیر مشخصه با کلید عنوان مشخصه
     *
     * @param attributeValueIdSetHashMap the attributeValueIdSetHashMap to set
     */
    public void setAttributeValueIdSetHashMap(HashMap<Integer, Set<Integer>> attributeValueIdSetHashMap) {
        this.attributeValueIdSetHashMap = attributeValueIdSetHashMap;
    }

    @Override
    public String toString() {
        return "ProductTypeLineSidebarFindModel{" +
                "vendorIdSet=" + vendorIdSet +
                ", productTypeGuarantyIdSet=" + productTypeGuarantyIdSet +
                ", attributeValueIdSetHashMap=" + attributeValueIdSetHashMap +
                '}';
    }
}
