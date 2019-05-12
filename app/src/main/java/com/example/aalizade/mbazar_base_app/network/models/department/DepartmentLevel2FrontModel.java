/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.aalizade.mbazar_base_app.network.models.department;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Set;

/**
 *
 * @author Dev3
 */
public class DepartmentLevel2FrontModel  extends DepartmentFrontModel{

    @SerializedName("columnIndex")
    private String columnIndex;

    @SerializedName("departmentLevel3FrontModels")
    private List<DepartmentLevel3FrontModel> departmentLevel3FrontModels;

    public String getColumnIndex() {
        return columnIndex;
    }

    public void setColumnIndex(String columnIndex) {
        this.columnIndex = columnIndex;
    }

    public List<DepartmentLevel3FrontModel> getDepartmentLevel3FrontModels() {
        return departmentLevel3FrontModels;
    }

    public void setDepartmentLevel3FrontModels(List<DepartmentLevel3FrontModel> departmentLevel3FrontModels) {
        this.departmentLevel3FrontModels = departmentLevel3FrontModels;
    }

    @Override
    public String toString() {
        return "DepartmentLevel2FrontModel{" +
                "columnIndex=" + columnIndex +
                ", departmentLevel3FrontModels=" + departmentLevel3FrontModels +
                '}';
    }


}
