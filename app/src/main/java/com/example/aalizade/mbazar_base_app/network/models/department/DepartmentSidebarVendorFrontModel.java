/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.aalizade.mbazar_base_app.network.models.department;

/**
 *
 * @author Dev1
 */
public class DepartmentSidebarVendorFrontModel extends SidebarGeneralDataFrontModel{
    private String vitrinUrl;
    public DepartmentSidebarVendorFrontModel(String id, String title,String vitrinUrl, String count) {
        super(id, title, count);
        this.vitrinUrl = vitrinUrl;
    }

    public String getVitrinUrl() {
        return vitrinUrl;
    }

    public void setVitrinUrl(String vitrinUrl) {
        this.vitrinUrl = vitrinUrl;
    }

    @Override
    public String toString() {
        return "DepartmentSidebarVendorFrontModel{" +
                "vitrinUrl='" + vitrinUrl + '\'' +
                '}';
    }
}
