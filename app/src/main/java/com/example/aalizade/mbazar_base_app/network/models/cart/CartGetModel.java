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
public class CartGetModel extends GenericModel {

    //ویترین
    private String vitrin_id;

    // ورژن تب های جدیدی که کلاینت در سبد خرید نییو میکند
    private String clientRevision;

    public String getVitrin_id() {
        return vitrin_id;
    }

    public void setVitrin_id(String vitrin_id) {
        this.vitrin_id = vitrin_id;
    }

    public String getClientRevision() {
        return clientRevision;
    }

    public void setClientRevision(String clientRevision) {
        this.clientRevision = clientRevision;
    }

    @Override
    public String toString() {
        return "CartGetModel{" +
                "vitrin_id='" + vitrin_id + '\'' +
                ", clientRevision='" + clientRevision + '\'' +
                '}';
    }
}
