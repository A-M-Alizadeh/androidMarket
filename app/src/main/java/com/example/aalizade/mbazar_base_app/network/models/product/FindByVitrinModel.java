/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.aalizade.mbazar_base_app.network.models.product;

/**
 *
 * @author Dev1
 */
public class FindByVitrinModel {
    private String id;
    private String vitrinId;
    //========================//

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVitrinId() {
        return vitrinId;
    }

    public void setVitrinId(String vitrinId) {
        this.vitrinId = vitrinId;
    }

    @Override
    public String toString() {
        return "FindByVitrinModel{" +
                "id='" + id + '\'' +
                ", vitrinId='" + vitrinId + '\'' +
                '}';
    }
}
