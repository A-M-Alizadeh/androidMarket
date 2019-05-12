/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.aalizade.mbazar_base_app.network.models.department;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author Dev3
 */
public class DepartmentFrontModel {

    @SerializedName("id")
    private Integer id;
    @SerializedName("title")
    private String title;
    @SerializedName("isSeprator")
    private Boolean isSeprator;

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

    public Boolean getIsSeprator() {
        return isSeprator;
    }

    public void setIsSeprator(Boolean isSeprator) {
        this.isSeprator = isSeprator;
    }

    @Override
    public String toString() {
        return "DepartmentFrontModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", isSeprator=" + isSeprator +
                '}';
    }
}
