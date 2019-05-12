/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.aalizade.mbazar_base_app.network.models.cart;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Dev1
 */
public class PaymentSystemCartByMemberModel {
    private PaymentSystemCartEnum key;
    private Set<PaymentSystemCartModel> paymentSystemCartModelSet = new HashSet<>();

    
    /**
     * @return the paymentSystemCartModelSet
     */
    public Set<PaymentSystemCartModel> getPaymentSystemCartModelSet() {
        return paymentSystemCartModelSet;
    }

    /**
     * @param paymentSystemCartModelSet the paymentSystemCartModelSet to set
     */
    public void setPaymentSystemCartModelSet(Set<PaymentSystemCartModel> paymentSystemCartModelSet) {
        this.paymentSystemCartModelSet = paymentSystemCartModelSet;
    }

    /**
     * @return the key
     */
    public PaymentSystemCartEnum getKey() {
        return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(PaymentSystemCartEnum key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "PaymentSystemCartByMemberModel{" +
                "key=" + key +
                ", paymentSystemCartModelSet=" + paymentSystemCartModelSet +
                '}';
    }
}
