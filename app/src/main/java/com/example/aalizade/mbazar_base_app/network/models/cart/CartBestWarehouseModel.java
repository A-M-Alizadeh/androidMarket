/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.aalizade.mbazar_base_app.network.models.cart;


import com.example.aalizade.mbazar_base_app.network.models.general.GenericModel;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dev2
 */
public class CartBestWarehouseModel extends GenericModel {

    private Integer id;

    private String title;

    //مدل بهترین حامل انتخابی که در استپ  یک و دو وسه طبق فرمول بهینه حساب میشودو در استپ چهار و سی چهار و پنج از دیتابیس خوانده میشود
    private List<CartCarrierGroupModel> cartCarrierGroupModelList = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<CartCarrierGroupModel> getCartCarrierGroupModelList() {
        return cartCarrierGroupModelList;
    }

    public void setCartCarrierGroupModelList(List<CartCarrierGroupModel> cartCarrierGroupModelList) {
        this.cartCarrierGroupModelList = cartCarrierGroupModelList;
    }

//    @Override
//    public int hashCode() {
//        int hash = 7;
//        hash = 97 * hash + Objects.hashCode(this.id);
//        return hash;
//    }

//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        }
//        if (obj == null) {
//            return false;
//        }
//        if (getClass() != obj.getClass()) {
//            return false;
//        }
//        final CartBestWarehouseModel other = (CartBestWarehouseModel) obj;
//        if (!Objects.equals(this.id, other.id)) {
//            return false;
//        }
//        return true;
//    }

}
