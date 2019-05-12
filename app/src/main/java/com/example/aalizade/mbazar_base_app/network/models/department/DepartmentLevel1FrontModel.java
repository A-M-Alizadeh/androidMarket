/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.aalizade.mbazar_base_app.network.models.department;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 *
 * @author Dev3
 */
public class DepartmentLevel1FrontModel  extends DepartmentFrontModel{

    @SerializedName("columnNumber")
    private String columnNumber;

    @SerializedName("hasImage")
    private Boolean hasImage;

    @SerializedName("imageHashedPath")
    private String imageHashedPath;

    @SerializedName("departmentLevel2ColumnIndex1FrontModels")
    private List<DepartmentLevel2FrontModel> departmentLevel2ColumnIndex1FrontModels;

    @SerializedName("departmentLevel2ColumnIndex2FrontModels")
    private List<DepartmentLevel2FrontModel> departmentLevel2ColumnIndex2FrontModels;

    public String getColumnNumber() {
        return columnNumber;
    }

    public void setColumnNumber(String columnNumber) {
        this.columnNumber = columnNumber;
    }

    public Boolean getHasImage() {
        return hasImage;
    }

    public void setHasImage(Boolean hasImage) {
        this.hasImage = hasImage;
    }

    public List<DepartmentLevel2FrontModel> getDepartmentLevel2ColumnIndex1FrontModels() {
        return departmentLevel2ColumnIndex1FrontModels;
    }

    public void setDepartmentLevel2ColumnIndex1FrontModels(List<DepartmentLevel2FrontModel> departmentLevel2ColumnIndex1FrontModels) {
        this.departmentLevel2ColumnIndex1FrontModels = departmentLevel2ColumnIndex1FrontModels;
    }

    public List<DepartmentLevel2FrontModel> getDepartmentLevel2ColumnIndex2FrontModels() {
        return departmentLevel2ColumnIndex2FrontModels;
    }

    public void setDepartmentLevel2ColumnIndex2FrontModels(List<DepartmentLevel2FrontModel> departmentLevel2ColumnIndex2FrontModels) {
        this.departmentLevel2ColumnIndex2FrontModels = departmentLevel2ColumnIndex2FrontModels;
    }

    public String getImageHashedPath() {
        return imageHashedPath;
    }

    public void setImageHashedPath(String imageHashedPath) {
        this.imageHashedPath = imageHashedPath;
    }


    @Override
    public String toString() {
        return "DepartmentLevel1FrontModel{" +
                "columnNumber=" + columnNumber +
                ", hasImage=" + hasImage +
                ", imageHashedPath='" + imageHashedPath + '\'' +
                ", departmentLevel2ColumnIndex1FrontModels=" + departmentLevel2ColumnIndex1FrontModels +
                ", departmentLevel2ColumnIndex2FrontModels=" + departmentLevel2ColumnIndex2FrontModels +
                '}';
    }
}
