/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.aalizade.mbazar_base_app.network.models.department;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Dev1
 */
public class DepartmentProductTypeFindFrontModel {

    //شناسه دپارتمان
    private String departmentId;
    //شناسه ویترین
    private String vitrinId;
    //لیست شناسه های شاخه کالایی
    private Set<String> productCategoryIdSet = new HashSet<>();
    //لیست شناسه های عرضه کنندگان
    private Set<String> vendorIdSet = new HashSet<>();
    //تعداد محصولات هر گروه
    private String rows;
    //صفحه
    private String page;

    //=========================================//


    public DepartmentProductTypeFindFrontModel(String departmentId, String vitrinId, Set<String> productCategoryIdSet, Set<String> vendorIdSet, String rows, String page) {
        this.departmentId = departmentId;
        this.vitrinId = vitrinId;
        this.productCategoryIdSet = productCategoryIdSet;
        this.vendorIdSet = vendorIdSet;
        this.rows = rows;
        this.page = page;
    }

    public DepartmentProductTypeFindFrontModel() {
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getVitrinId() {
        return vitrinId;
    }

    public void setVitrinId(String vitrinId) {
        this.vitrinId = vitrinId;
    }

    public Set<String> getProductCategoryIdSet() {
        return productCategoryIdSet;
    }

    public void setProductCategoryIdSet(Set<String> productCategoryIdSet) {
        this.productCategoryIdSet = productCategoryIdSet;
    }

    public Set<String> getVendorIdSet() {
        return vendorIdSet;
    }

    public void setVendorIdSet(Set<String> vendorIdSet) {
        this.vendorIdSet = vendorIdSet;
    }

    public String getRows() {
        return rows;
    }

    public void setRows(String rows) {
        this.rows = rows;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "DepartmentProductTypeFindFrontModel{" +
                "departmentId='" + departmentId + '\'' +
                ", vitrinId='" + vitrinId + '\'' +
                ", productCategoryIdSet=" + productCategoryIdSet +
                ", vendorIdSet=" + vendorIdSet +
                ", rows='" + rows + '\'' +
                ", page='" + page + '\'' +
                '}';
    }
}
