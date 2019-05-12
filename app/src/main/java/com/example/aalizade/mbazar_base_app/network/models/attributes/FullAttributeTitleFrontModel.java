/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.aalizade.mbazar_base_app.network.models.attributes;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Dev1
 */
public class FullAttributeTitleFrontModel extends AttributeTitleFrontModel{
    private Set<AttributeValueFrontModel> attributeValueModelSet = new HashSet<>();

    public Set<AttributeValueFrontModel> getAttributeValueModelSet() {
        return attributeValueModelSet;
    }

    public void setAttributeValueModelSet(Set<AttributeValueFrontModel> attributeValueModelSet) {
        this.attributeValueModelSet = attributeValueModelSet;
    }

    @Override
    public String toString() {
        return "FullAttributeTitleFrontModel{" +
                "attributeValueModelSet=" + attributeValueModelSet +
                '}';
    }
}
