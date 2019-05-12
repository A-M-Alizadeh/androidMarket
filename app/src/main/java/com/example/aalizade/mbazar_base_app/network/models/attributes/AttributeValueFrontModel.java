/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.aalizade.mbazar_base_app.network.models.attributes;

import com.example.aalizade.mbazar_base_app.network.models.general.GenericModel;

import java.util.Objects;

/**
 *
 * @author Dev2
 */
public class AttributeValueFrontModel extends GenericModel {

    private String id;

    private String value;

    private String attributeTitle_title;

    private String attributeTitle_attributeGroup_title;

    private Integer attributeTitle_id;

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.getValue());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AttributeValueFrontModel other = (AttributeValueFrontModel) obj;
        if (!Objects.equals(this.value, other.value)) {
            return false;
        }
        return true;
    }
    
    //========================================//
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getAttributeTitle_title() {
        return attributeTitle_title;
    }

    public Integer getAttributeTitle_id() {
        return attributeTitle_id;
    }

    public void setAttributeTitle_id(Integer attributeTitle_id) {
        this.attributeTitle_id = attributeTitle_id;
    }

    public String getAttributeTitle_attributeGroup_title() {
        return attributeTitle_attributeGroup_title;
    }

    public void setAttributeTitle_attributeGroup_title(String attributeTitle_attributeGroup_title) {
        this.attributeTitle_attributeGroup_title = attributeTitle_attributeGroup_title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAttributeTitle_title(String attributeTitle_title) {
        this.attributeTitle_title = attributeTitle_title;
    }

    @Override
    public String toString() {
        return "AttributeValueFrontModel{" +
                "id='" + id + '\'' +
                ", value='" + value + '\'' +
                ", attributeTitle_title='" + attributeTitle_title + '\'' +
                ", attributeTitle_attributeGroup_title='" + attributeTitle_attributeGroup_title + '\'' +
                ", attributeTitle_id=" + attributeTitle_id +
                '}';
    }
}
