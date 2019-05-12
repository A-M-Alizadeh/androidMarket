package com.example.aalizade.mbazar_base_app.network.models.cart;

/**
 * Created by aalizade on 4/10/2018.
 */

public class CustomListItemForAdapter {
    private Integer isFirst;
    private PaymentSystemCartModel paymentSystemCartModel;

    public Integer getIsFirst() {
        return isFirst;
    }

    public void setIsFirst(Integer isFirst) {
        this.isFirst = isFirst;
    }

    public PaymentSystemCartModel getPaymentSystemCartModel() {
        return paymentSystemCartModel;
    }

    public void setPaymentSystemCartModel(PaymentSystemCartModel paymentSystemCartModel) {
        this.paymentSystemCartModel = paymentSystemCartModel;
    }

    public CustomListItemForAdapter(Integer isFirst ,PaymentSystemCartModel paymentSystemCartModel) {
        this.isFirst = isFirst;
        this.paymentSystemCartModel = paymentSystemCartModel;
    }

    @Override
    public String toString() {
        return "CustomListItemForAdapter{" +
                "isFirst=" + isFirst +
                ", paymentSystemCartModel=" + paymentSystemCartModel +
                '}';
    }
}
