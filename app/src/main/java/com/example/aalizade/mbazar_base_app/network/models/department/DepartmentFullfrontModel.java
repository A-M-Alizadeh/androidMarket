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
public class DepartmentFullfrontModel {

    @SerializedName("departmentLevel1FrontModels")
    private  List<DepartmentLevel1FrontModel> departmentLevel1FrontModels;

    @SerializedName("isSpecialList")
    private List<DepartmentLevel3FrontModel> isSpecialList;

    public List<DepartmentLevel1FrontModel> getDepartmentLevel1FrontModels() {
        return departmentLevel1FrontModels;
    }

    public void setDepartmentLevel1FrontModels(List<DepartmentLevel1FrontModel> departmentLevel1FrontModels) {
        this.departmentLevel1FrontModels = departmentLevel1FrontModels;
    }

    public List<DepartmentLevel3FrontModel> getIsSpecialList() {
        return isSpecialList;
    }

    public void setIsSpecialList(List<DepartmentLevel3FrontModel> isSpecialList) {
        this.isSpecialList = isSpecialList;
    }

    @Override
    public String toString() {
        return "DepartmentFullfrontModel{" +
                "departmentLevel1FrontModels=" + departmentLevel1FrontModels +
                ", isSpecialList=" + isSpecialList +
                '}';
    }
}
