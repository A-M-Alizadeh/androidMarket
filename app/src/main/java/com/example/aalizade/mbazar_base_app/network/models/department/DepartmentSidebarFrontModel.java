/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.aalizade.mbazar_base_app.network.models.department;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dev1
 */
public class DepartmentSidebarFrontModel {

    private String departmentId;
    private String vitrinId;
    private String departmentType;
    //شاخه کالایی های دپارتمان
//    private List<???> otherProductCategoyList = new ArrayList<>();
    private List<DepartmentSidebarProductCategoryFrontModel> productCategoyList = new ArrayList<>();
    // عرضه کننده های دپارتمان
    private List<DepartmentSidebarVendorFrontModel> vendorIdList = new ArrayList<>();

    //==========================================================//

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

    public List<DepartmentSidebarProductCategoryFrontModel> getProductCategoyIdList() {
        return productCategoyList;
    }

    public void setProductCategoyIdList(List<DepartmentSidebarProductCategoryFrontModel> productCategoyIdList) {
        this.productCategoyList = productCategoyIdList;
    }

    public List<DepartmentSidebarVendorFrontModel> getVendorIdList() {
        return vendorIdList;
    }

    public void setVendorIdList(List<DepartmentSidebarVendorFrontModel> vendorIdList) {
        this.vendorIdList = vendorIdList;
    }

    public String getDepartmentType() {
        return departmentType;
    }

    public void setDepartmentType(String departmentType) {
        this.departmentType = departmentType;
    }

    @Override
    public String toString() {
        return "DepartmentSidebarFrontModel{" +
                "departmentId='" + departmentId + '\'' +
                ", vitrinId='" + vitrinId + '\'' +
                ", departmentType='" + departmentType + '\'' +
                ", productCategoyIdList=" + productCategoyList +
                ", vendorIdList=" + vendorIdList +
                '}';
    }
}
