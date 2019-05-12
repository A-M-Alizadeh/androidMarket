/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.aalizade.mbazar_base_app.network.models.product;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Dev1
 */
public class OtherProductTypeWithFilterModel {

    private List<OtherProductTypeModel> otherModelList;
    private HashMap<Integer, String> filterHashMap = new HashMap<>();

    public HashMap<Integer, String> getFilterHashMap() {
        return filterHashMap;
    }

    public void setFilterHashMap(HashMap<Integer, String> filterHashMap) {
        this.filterHashMap = filterHashMap;
    }

    public List<OtherProductTypeModel> getOtherModelList() {
        return otherModelList;
    }

    public void setOtherModelList(List<OtherProductTypeModel> otherModelList) {
        this.otherModelList = otherModelList;
    }

    @Override
    public String toString() {
        return "OtherProductTypeWithFilterModel{" +
                "otherModelList=" + otherModelList +
                ", filterHashMap=" + filterHashMap +
                '}';
    }
}
