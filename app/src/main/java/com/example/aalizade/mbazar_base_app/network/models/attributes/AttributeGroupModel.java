/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.aalizade.mbazar_base_app.network.models.attributes;

public class AttributeGroupModel {

    private String id;

    private String title;

    private String productCategory_title;

    private Integer productCategory_id;

    //=============================
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProductCategory_title() {
        return productCategory_title;
    }

    public void setProductCategory_title(String productCategory_title) {
        this.productCategory_title = productCategory_title;
    }

    public Integer getProductCategory_id() {
        return productCategory_id;
    }

    public void setProductCategory_id(Integer productCategory_id) {
        this.productCategory_id = productCategory_id;
    }

}
