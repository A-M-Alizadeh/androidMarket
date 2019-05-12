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
public class AttributeTitleFrontModel extends GenericModel {

    private String id;
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }  

    private String title;

    private String attributeGroup_title;

    private String attributeGroup_id;

    private Boolean titleIncluded;

    private Boolean physicalImpact;

    private Boolean essential;

    private Boolean general;

    private Boolean filter;

    //===========================================//
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

  

    public Boolean getEssential() {
        return essential;
    }

    public void setEssential(Boolean essential) {
        this.essential = essential;
    }

    public Boolean getGeneral() {
        return general;
    }

    public void setGeneral(Boolean general) {
        this.general = general;
    }

    public Boolean getFilter() {
        return filter;
    }

    public void setFilter(Boolean filter) {
        this.filter = filter;
    }



    public String getAttributeGroup_id() {
        return attributeGroup_id;
    }

    public void setAttributeGroup_id(String attributeGroup_id) {
        this.attributeGroup_id = attributeGroup_id;
    }

    public Boolean getTitleIncluded() {
        return titleIncluded;
    }

    public void setTitleIncluded(Boolean titleIncluded) {
        this.titleIncluded = titleIncluded;
    }

    public Boolean getPhysicalImpact() {
        return physicalImpact;
    }

    public void setPhysicalImpact(Boolean physicalImpact) {
        this.physicalImpact = physicalImpact;
    }

    public String getAttributeGroup_title() {
        return attributeGroup_title;
    }

    public void setAttributeGroup_title(String attributeGroup_title) {
        this.attributeGroup_title = attributeGroup_title;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
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
        final AttributeTitleFrontModel other = (AttributeTitleFrontModel) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AttributeTitleFrontModel{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", attributeGroup_title='" + attributeGroup_title + '\'' +
                ", attributeGroup_id='" + attributeGroup_id + '\'' +
                ", titleIncluded=" + titleIncluded +
                ", physicalImpact=" + physicalImpact +
                ", essential=" + essential +
                ", general=" + general +
                ", filter=" + filter +
                '}';
    }
}
