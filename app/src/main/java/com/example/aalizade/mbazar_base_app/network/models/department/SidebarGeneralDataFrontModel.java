/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.aalizade.mbazar_base_app.network.models.department;

import java.util.Objects;

/**
 *
 * @author Dev1
 */
//==============================================//
//مدل عمومی داده های سایدبار فرانت 
//==============================================//
public class SidebarGeneralDataFrontModel {

    private String id;
    private String count;
    private String title;

    //========================//
    public SidebarGeneralDataFrontModel(String id, String title, String count) {
        this.id = id;
        this.count = count;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.id);
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
        final SidebarGeneralDataFrontModel other = (SidebarGeneralDataFrontModel) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public String toString() {
        return "SidebarGeneralDataFrontModel{" +
                "id='" + id + '\'' +
                ", count='" + count + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
