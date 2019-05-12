/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.aalizade.mbazar_base_app.network.models.membershiprequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class FindModel {

    public FindModel(List<Integer> idList) {
        this.idList = idList;
    }

    public FindModel(Integer id) {
        List<Integer> idList = new ArrayList<>();
        idList.add(id);
        this.idList = idList;
    }

    public FindModel() {
    }

    @SerializedName("idList")
    @Expose
    private List<Integer> idList = new ArrayList<>();

    public List<Integer> getIdList() {
        return idList;
    }

    public void setIdList(List<Integer> idList) {
        this.idList = idList;
    }

    @SerializedName("param")
    @Expose
    private String param;

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    @Override
    public String toString() {
        return "FindModel{" + "idList=" + idList + ", param=" + param + '}';
    }

}
