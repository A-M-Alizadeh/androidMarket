/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.aalizade.mbazar_base_app.network.models.membershiprequest;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author Administrator
 */
public class MembershipRequestFrontModelCreate extends MembershipRequestFrontModel {
    @SerializedName("id")
    private Integer id;

    //setter-getter:
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
