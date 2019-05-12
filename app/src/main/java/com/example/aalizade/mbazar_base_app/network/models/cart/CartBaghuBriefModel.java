package com.example.aalizade.mbazar_base_app.network.models.cart;

import com.example.aalizade.mbazar_base_app.network.models.product.CustomCategorizeTransportingItems;

/**
 * Created by aalizade on 4/17/2018.
 */

public class CartBaghuBriefModel {
    private String quantity;
    private String productType_id;

    public CartBaghuBriefModel(String quantity, String productType_id) {
        this.quantity = quantity;
        this.productType_id = productType_id;
    }

    public CartBaghuBriefModel() {
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getProductType_id() {
        return productType_id;
    }

    public void setProductType_id(String productType_id) {
        this.productType_id = productType_id;
    }

    @Override
    public String toString() {
        return "CartBaghuBriefModel{" +
                "quantity='" + quantity + '\'' +
                ", productType_id='" + productType_id + '\'' +
                '}';
    }

    //equal method for adding quantity
    @Override
    public boolean equals(Object obj) {
        CartBaghuBriefModel cartBaghuBriefModel = (CartBaghuBriefModel)obj;
        if (this.productType_id.equals(cartBaghuBriefModel.getProductType_id())){
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
