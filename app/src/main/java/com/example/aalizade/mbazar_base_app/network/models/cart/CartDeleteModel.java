/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.aalizade.mbazar_base_app.network.models.cart;


import com.example.aalizade.mbazar_base_app.network.models.general.GenericModel;

/**
 *
 * @author dev2
 */
public class CartDeleteModel extends GenericModel {

    //آیدی محصولات
    private String productTypeId;

    //ویترین
    private String vitrinId;

    // ورژن تب های جدیدی که کلاینت در سبد خرید نییو میکند
    private String clientRevision;
    
    //از سرویس پر میشود
    Boolean hasCart;

    public String getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(String productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getVitrinId() {
        return vitrinId;
    }

    public void setVitrinId(String vitrinId) {
        this.vitrinId = vitrinId;
    }

    public String getClientRevision() {
        return clientRevision;
    }

    public void setClientRevision(String clientRevision) {
        this.clientRevision = clientRevision;
    }

    public Boolean getHasCart() {
        return hasCart;
    }

    public void setHasCart(Boolean hasCart) {
        this.hasCart = hasCart;
    }

    @Override
    public String toString() {
        return "CartDeleteModel{" +
                "productTypeId=" + productTypeId +
                ", vitrinId='" + vitrinId + '\'' +
                ", clientRevision='" + clientRevision + '\'' +
                ", hasCart=" + hasCart +
                '}';
    }
}
