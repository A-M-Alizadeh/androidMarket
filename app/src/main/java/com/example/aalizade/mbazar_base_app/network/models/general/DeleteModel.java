/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.aalizade.mbazar_base_app.network.models.general;


import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 *
 * @author Administrator
 */
public class DeleteModel {

    @SerializedName("idList")
    private List<Integer> idList;

    public List<Integer> getIdList() {
        return idList;
    }

    public void setIdList(List<Integer> idList) {
        this.idList = idList;
    }

}
