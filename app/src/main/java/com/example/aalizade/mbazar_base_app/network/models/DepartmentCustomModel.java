package com.example.aalizade.mbazar_base_app.network.models;

import com.example.aalizade.mbazar_base_app.network.models.department.DepartmentLevel3FrontModel;

import java.util.List;

/**
 * Created by aalizade on 4/23/2018.
 */

public class DepartmentCustomModel {
    private String Title;
    private List<DepartmentLevel3FrontModel> subItems;

    public DepartmentCustomModel(String title, List<DepartmentLevel3FrontModel> subItems) {
        Title = title;
        this.subItems = subItems;
    }

    public DepartmentCustomModel() {
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public List<DepartmentLevel3FrontModel> getSubItems() {
        return subItems;
    }

    public void setSubItems(List<DepartmentLevel3FrontModel> subItems) {
        this.subItems = subItems;
    }

    @Override
    public String toString() {
        return "DepartmentCustomModel{" +
                "Title='" + Title + '\'' +
                ", subItems=" + subItems +
                '}';
    }
}
