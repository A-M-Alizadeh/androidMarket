/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.aalizade.mbazar_base_app.network.models.loggedin;


import com.google.gson.annotations.SerializedName;

/**
 *
 * @author Administrator
 */
public class LoggedInUserPositionRoleUnitModel {

    @SerializedName("id")
    private String id;
    @SerializedName("loggedInOrganizationUnitModel")
    private LoggedInOrganizationUnitModel loggedInOrganizationUnitModel;
    @SerializedName("loggedInRole")
    private LoggedInRoleModel loggedInRole;
    @SerializedName("loggedInUserPositionModel")
    private LoggedInUserPositionModel loggedInUserPositionModel;
//    @SerializedName("gridBtnList")
//    private HashMap<String, List<GridNavBtnModel>> gridBtnList;
//    @SerializedName("menuList")
//    private HashMap<String, List<JstreeNodeModel>> menuList;
    @SerializedName("relatedCityIdCsv")
    private String relatedCityIdCsv;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

//    public HashMap<String, List<GridNavBtnModel>> getGridBtnList() {
//        return gridBtnList;
//    }
//
//    public void setGridBtnList(HashMap<String, List<GridNavBtnModel>> gridBtnList) {
//        this.gridBtnList = gridBtnList;
//    }

    public LoggedInOrganizationUnitModel getLoggedInOrganizationUnitModel() {
        return loggedInOrganizationUnitModel;
    }

    public void setLoggedInOrganizationUnitModel(LoggedInOrganizationUnitModel loggedInOrganizationUnitModel) {
        this.loggedInOrganizationUnitModel = loggedInOrganizationUnitModel;
    }

    public LoggedInRoleModel getLoggedInRole() {
        return loggedInRole;
    }

    public void setLoggedInRole(LoggedInRoleModel loggedInRole) {
        this.loggedInRole = loggedInRole;
    }

    public LoggedInUserPositionModel getLoggedInUserPositionModel() {
        return loggedInUserPositionModel;
    }

    public void setLoggedInUserPositionModel(LoggedInUserPositionModel loggedInUserPositionModel) {
        this.loggedInUserPositionModel = loggedInUserPositionModel;
    }

//    public HashMap<String, List<JstreeNodeModel>> getMenuList() {
//        return menuList;
//    }
//
//    public void setMenuList(HashMap<String, List<JstreeNodeModel>> menuList) {
//        this.menuList = menuList;
//    }

    public String getRelatedCityIdCsv() {
        return relatedCityIdCsv;
    }

    public void setRelatedCityIdCsv(String relatedCityIdCsv) {
        this.relatedCityIdCsv = relatedCityIdCsv;
    }

    @Override
    public String toString() {
        return "LoggedInUserPositionRoleUnitModel{" +
                "id=" + id +
                ", loggedInOrganizationUnitModel=" + loggedInOrganizationUnitModel +
                ", loggedInRole=" + loggedInRole +
                ", loggedInUserPositionModel=" + loggedInUserPositionModel +
                ", relatedCityIdCsv='" + relatedCityIdCsv + '\'' +
                '}';
    }
}
