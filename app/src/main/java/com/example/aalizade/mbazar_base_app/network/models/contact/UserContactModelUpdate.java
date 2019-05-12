/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.aalizade.mbazar_base_app.network.models.contact;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 * @author Administrator
 */
public class UserContactModelUpdate extends UserContactModel {
    @SerializedName("id")
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserContactModelUpdate() {
    }

    @Override
    public String toString() {
        return "UserContactModelUpdate{" +
                "id=" + id +
                '}';
    }
}
