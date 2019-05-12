package com.example.aalizade.mbazar_base_app.network.models.product;

import com.example.aalizade.mbazar_base_app.network.models.cart.CartProductTypeModel;

import java.util.List;

/**
 * Created by aalizade on 4/8/2018.
 */

public class CustomCategorizeTransportingItems {
    private String carrierId;
    private String carrierName;
    private List<CartProductTypeModel> carriedItems;

    public CustomCategorizeTransportingItems() {
    }

    public CustomCategorizeTransportingItems(String carrierId, String carrierName) {
        this.carrierId = carrierId;
        this.carrierName = carrierName;
    }

    public CustomCategorizeTransportingItems(String carrierId, String carrierName, List<CartProductTypeModel> carriedItems) {
        this.carrierId = carrierId;
        this.carrierName = carrierName;
        this.carriedItems = carriedItems;
    }

    public String getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(String carrierId) {
        this.carrierId = carrierId;
    }

    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }

    public List<CartProductTypeModel> getCarriedItems() {
        return carriedItems;
    }

    public void setCarriedItems(List<CartProductTypeModel> carriedItems) {
        this.carriedItems = carriedItems;
    }

    @Override
    public String toString() {
        return "CustomCategorizeTransportingItems{" +
                "carrierId='" + carrierId + '\'' +
                ", carrierName='" + carrierName + '\'' +
                ", carriedItems=" + carriedItems +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        final CustomCategorizeTransportingItems other = (CustomCategorizeTransportingItems) obj;
        if (this.carrierId.equals(other.getCarrierId())) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
