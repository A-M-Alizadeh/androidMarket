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
 * @author Administrator
 */
public class ProductTypeBriefListDataFrontModel {

    private List<ProductTypeBriefFrontModel> productTypeFrontModelList = new ArrayList<>();
    private String records;
    private String page;


    @Override
    public String toString() {
        return "ProductBriefListDataFrontModel{" + "productFrontModelList=" + getProductTypeFrontModelList() + ", records=" + getRecords() + ", page=" + getPage() + '}';
    }

    public List<ProductTypeBriefFrontModel> getProductTypeFrontModelList() {
        return productTypeFrontModelList;
    }

    public void setProductTypeFrontModelList(List<ProductTypeBriefFrontModel> productTypeFrontModelList) {
        this.productTypeFrontModelList = productTypeFrontModelList;
    }

    public String getRecords() {
        return records;
    }

    public void setRecords(String records) {
        this.records = records;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

}
