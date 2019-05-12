/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.aalizade.mbazar_base_app.network.models.cart;


import com.example.aalizade.mbazar_base_app.network.models.general.GenericModel;

import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author Dev2
 */
public class StepNoWithCartErrorModel extends GenericModel {

    HashMap<Integer, Set<CartErrorModel>> cartErrorHashMap = new HashMap<>();

    public HashMap<Integer, Set<CartErrorModel>> getCartErrorHashMap() {
        return cartErrorHashMap;
    }

    public void setCartErrorHashMap(HashMap<Integer, Set<CartErrorModel>> cartErrorHashMap) {
        this.cartErrorHashMap = cartErrorHashMap;
    }

}
