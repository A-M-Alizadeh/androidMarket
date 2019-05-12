/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.aalizade.mbazar_base_app.network.models.product;

import java.util.HashSet;
import java.util.Set;

/**
 * مدل مقدار مشخصه های سایدبار عرضه کننده محصول
 */
public class ProductTypeLineSidebarAttributeTitleModel {

    private Integer id;
    private String title;
    private Set<GeneralAttributeModel> attributeValueSet = new HashSet<>();

    public ProductTypeLineSidebarAttributeTitleModel(Integer id, String title) {
        this.id = id;
        this.title = title;
    }
    //=========================================//

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

    public Set<GeneralAttributeModel> getAttributeValueSet() {
        return attributeValueSet;
    }

    public void setAttributeValueSet(Set<GeneralAttributeModel> attributeValueSet) {
        this.attributeValueSet = attributeValueSet;
    }

    @Override
    public String toString() {
        return "ProductTypeLineSidebarAttributeTitleModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", attributeValueSet=" + attributeValueSet +
                '}';
    }
}
