/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.aalizade.mbazar_base_app.network.models.cart;

import com.example.aalizade.mbazar_base_app.network.models.general.GenericModel;

import java.io.Serializable;

/**
 *
 * @author Dev3
 */
public class PaymentSystemCartModel  extends GenericModel implements Serializable {
    
    private String id;
    private String title;
    
    private String remainAmount;
    
    private String paymentSystemCart_id;
    private String paymentSystemCart_langKey;
    private String paymentSystemCart_value;

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

    public String getRemainAmount() {
        return remainAmount;
    }

    public void setRemainAmount(String remainAmount) {
        this.remainAmount = remainAmount;
    }

    public String getPaymentSystemCart_id() {
        return paymentSystemCart_id;
    }

    public void setPaymentSystemCart_id(String paymentSystemCart_id) {
        this.paymentSystemCart_id = paymentSystemCart_id;
    }

    public String getPaymentSystemCart_langKey() {
        return paymentSystemCart_langKey;
    }

    public void setPaymentSystemCart_langKey(String paymentSystemCart_langKey) {
        this.paymentSystemCart_langKey = paymentSystemCart_langKey;
    }

    public String getPaymentSystemCart_value() {
        return paymentSystemCart_value;
    }

    public void setPaymentSystemCart_value(String paymentSystemCart_value) {
        this.paymentSystemCart_value = paymentSystemCart_value;
    }

    @Override
    public String toString() {
        return "PaymentSystemCartModel{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", remainAmount='" + remainAmount + '\'' +
                ", paymentSystemCart_id='" + paymentSystemCart_id + '\'' +
                ", paymentSystemCart_langKey='" + paymentSystemCart_langKey + '\'' +
                ", paymentSystemCart_value='" + paymentSystemCart_value + '\'' +
                '}';
    }
}
