package com.example.aalizade.mbazar_base_app.network.models.cart;

import java.util.HashMap;
import java.util.Set;

/**
 * Created by aalizade on 4/17/2018.
 */

public class CustomBaghBaghuModel {

    private String id;
    private String vitrin_id;
    private String clientRevision;
    private HashMap<String, HashMap<String, Set<CartBaghuBriefModel>>> cartProductTypeBriefModelHashMap = new HashMap<>();

    public CustomBaghBaghuModel(String id, String vitrin_id, String clientRevision, HashMap<String, HashMap<String, Set<CartBaghuBriefModel>>> cartProductTypeModelHashMap) {
        this.id = id;
        this.vitrin_id = vitrin_id;
        this.clientRevision = clientRevision;
        this.cartProductTypeBriefModelHashMap = cartProductTypeModelHashMap;
    }

    public CustomBaghBaghuModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public HashMap<String, HashMap<String, Set<CartBaghuBriefModel>>> getCartProductTypeModelHashMap() {
        return cartProductTypeBriefModelHashMap;
    }

    public void setCartProductTypeModelHashMap(HashMap<String, HashMap<String, Set<CartBaghuBriefModel>>> cartProductTypeModelHashMap) {
        this.cartProductTypeBriefModelHashMap = cartProductTypeModelHashMap;
    }

    @Override
    public String toString() {
        return "CustomBaghBaghuModel{" +
                "id='" + id + '\'' +
                ", vitrin_id='" + vitrin_id + '\'' +
                ", clientRevision='" + clientRevision + '\'' +
                ", cartProductTypeModelHashMap=" + cartProductTypeBriefModelHashMap +
                '}';
    }
}

