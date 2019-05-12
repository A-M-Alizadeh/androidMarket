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
public class FullAttributeGroupFrontModel extends AttributeGroupModel {

    private Set<FullAttributeTitleFrontModel> attributeTitleModelSet = new HashSet<>();

    public Set<FullAttributeTitleFrontModel> getAttributeTitleModelSet() {
        return attributeTitleModelSet;
    }

    public void setAttributeTitleModelSet(Set<FullAttributeTitleFrontModel> attributeTitleModelSet) {
        this.attributeTitleModelSet = attributeTitleModelSet;
    }

    @Override
    public String toString() {
        return "FullAttributeGroupFrontModel{" +
                "attributeTitleModelSet=" + attributeTitleModelSet +
                '}';
    }
}
