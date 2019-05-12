/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.aalizade.mbazar_base_app.network.models.product;

import com.example.aalizade.mbazar_base_app.network.models.general.GenericModel;

/**
 *
 * @author Dev1
 */
public class ProductTypeSearchFrontModel extends GenericModel {

    private String vitrinId;

    private String page;

    private String rows;
//    private ProductTypeSearchSortEnum sortBy;
    private Boolean orderAsc = true;

    //======================================//

    public String getVitrinId() {
        return vitrinId;
    }

    public void setVitrinId(String vitrinId) {
        this.vitrinId = vitrinId;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getRows() {
        return rows;
    }

    public void setRows(String rows) {
        this.rows = rows;
    }


//    public ProductTypeSearchSortEnum getSortBy() {
//        return sortBy;
//    }
//
//    public void setSortBy(ProductTypeSearchSortEnum sortBy) {
//        this.sortBy = sortBy;
//    }

    public Boolean getOrderAsc() {
        return orderAsc;
    }

    public void setOrderAsc(Boolean orderAsc) {
        this.orderAsc = orderAsc;
    }


    @Override
    public String toString() {
        return "ProductTypeSearchFrontModel{" +
                "vitrinId='" + vitrinId + '\'' +
                ", page='" + page + '\'' +
                ", rows='" + rows + '\'' +
                ", orderAsc=" + orderAsc +
                '}';
    }
}
