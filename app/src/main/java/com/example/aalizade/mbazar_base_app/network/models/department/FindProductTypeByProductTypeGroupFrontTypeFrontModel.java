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
public class FindProductTypeByProductTypeGroupFrontTypeFrontModel {

    private List<String> frontTypeList = new ArrayList<>();
    private String vitrinId;
    private String rows;
    private String page;

    //========================================================//


    public FindProductTypeByProductTypeGroupFrontTypeFrontModel() {
    }

    public List<String> getFrontTypeList() {
        return frontTypeList;
    }

    public void setFrontTypeList(List<String> frontTypeList) {
        this.frontTypeList = frontTypeList;
    }

    public String getVitrinId() {
        return vitrinId;
    }

    public void setVitrinId(String vitrinId) {
        this.vitrinId = vitrinId;
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

    public FindProductTypeByProductTypeGroupFrontTypeFrontModel(List<String> frontTypeList, String vitrinId, String rows, String page) {
        this.frontTypeList = frontTypeList;
        this.vitrinId = vitrinId;
        this.rows = rows;
        this.page = page;
    }

    @Override
    public String toString() {
        return "FindProductTypeByProductTypeGroupFrontTypeFrontModel{" +
                "frontTypeList=" + frontTypeList +
                ", vitrinId='" + vitrinId + '\'' +
                ", rows='" + rows + '\'' +
                ", page='" + page + '\'' +
                '}';
    }
}
