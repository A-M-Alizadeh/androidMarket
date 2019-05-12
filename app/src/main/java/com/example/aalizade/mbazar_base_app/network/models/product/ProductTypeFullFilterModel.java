/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.aalizade.mbazar_base_app.network.models.product;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Dev1
 */
public class ProductTypeFullFilterModel {

    private String id;
    private String title;
    private Set<GeneralAttributeModel> attributeModelSet = new HashSet<>();

    //============================================//
    public ProductTypeFullFilterModel(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<GeneralAttributeModel> getAttributeModelSet() {
        return attributeModelSet;
    }

    public void setAttributeModelSet(Set<GeneralAttributeModel> attributeModelSet) {
        this.attributeModelSet = attributeModelSet;
    }

    @Override
    public String toString() {
        return "ProductTypeFullFilterModel{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", attributeModelSet=" + attributeModelSet +
                '}';
    }
}
