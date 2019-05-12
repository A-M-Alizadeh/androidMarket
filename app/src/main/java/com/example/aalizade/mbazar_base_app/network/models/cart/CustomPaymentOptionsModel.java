package com.example.aalizade.mbazar_base_app.network.models.cart;

import java.util.List;

/**
 * Created by aalizade on 4/10/2018.
 */

public class CustomPaymentOptionsModel {
    private String title;
    private List<PaymentSystemCartModel> paymentSystemCartModels;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<PaymentSystemCartModel> getPaymentSystemCartModels() {
        return paymentSystemCartModels;
    }

    public void setPaymentSystemCartModels(List<PaymentSystemCartModel> paymentSystemCartModels) {
        this.paymentSystemCartModels = paymentSystemCartModels;
    }

    public CustomPaymentOptionsModel(String title, List<PaymentSystemCartModel> paymentSystemCartModels) {
        this.title = title;
        this.paymentSystemCartModels = paymentSystemCartModels;
    }

    public CustomPaymentOptionsModel() {
    }

    @Override
    public String toString() {
        return "CustomPaymentOptionsModel{" +
                "title='" + title + '\'' +
                ", paymentSystemCartModels=" + paymentSystemCartModels +
                '}';
    }
}
