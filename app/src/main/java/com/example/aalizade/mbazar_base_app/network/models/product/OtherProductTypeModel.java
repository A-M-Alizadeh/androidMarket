/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.aalizade.mbazar_base_app.network.models.product;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dev1
 */
public class OtherProductTypeModel {

    private Integer vendorId;
    private String vendorName;
    private String vendorUrl;
    private List<ProductTypeLine> productTypeLineList = new ArrayList<>();
   
    
    //================================//
    public OtherProductTypeModel(Integer vendorId, String vendorName, String vendorUrl) {
        this.vendorId = vendorId;
        this.vendorName = vendorName;
        this.vendorUrl = vendorUrl;
    }
    //================================//
    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorUrl() {
        return vendorUrl;
    }

    public void setVendorUrl(String vendorUrl) {
        this.vendorUrl = vendorUrl;
    }

    public List<ProductTypeLine> getProductTypeLineList() {
        return productTypeLineList;
    }

    public void setProductTypeLineList(List<ProductTypeLine> productTypeLineList) {
        this.productTypeLineList = productTypeLineList;
    }

    @Override
    public String toString() {
        return "OtherProductTypeModel{" +
                "vendorId=" + vendorId +
                ", vendorName='" + vendorName + '\'' +
                ", vendorUrl='" + vendorUrl + '\'' +
                ", productTypeLineList=" + productTypeLineList +
                '}';
    }
}
