package com.example.aalizade.mbazar_base_app.network.models.grid;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by sbayatani on 4/8/2018.
 */

public class GridRequestDataModel {
    @SerializedName("pageGridColModelList")
    @Expose
    private ArrayList<Object> pageGridColModelList;
    @SerializedName("parametersMode")
    @Expose
    private String parametersMode;
    @SerializedName("parameters")
    @Expose
    private ArrayList<Object> parameters;
    @SerializedName("search")
    @Expose
    private Boolean search;
    @SerializedName("rows")
    @Expose
    private Integer rows;
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("sidx")
    @Expose
    private String sidx;
    @SerializedName("sord")
    @Expose
    private String sord;

    public GridRequestDataModel(ArrayList<Object> pageGridColModelList, ArrayList<Object> parameters,String parametersMode, Boolean search, Integer rows, Integer page, String sidx, String sord) {
        this.pageGridColModelList = pageGridColModelList;
        this.parameters = parameters;
        this.parametersMode = parametersMode;
        this.search = search;
        this.rows = rows;
        this.page = page;
        this.sidx = sidx;
        this.sord = sord;
    }

    public GridRequestDataModel() {
    }

    public GridRequestDataModel(ArrayList<Object> pageGridColModelList, ArrayList<Object> parameters, Boolean search, Integer rows, Integer page, String sidx, String sord) {
        this.pageGridColModelList = pageGridColModelList;
        this.parameters = parameters;
        this.search = search;
        this.rows = rows;
        this.page = page;
        this.sidx = sidx;
        this.sord = sord;
    }

    public String getParametersMode() {
        return parametersMode;
    }

    public void setParametersMode(String parametersMode) {
        this.parametersMode = parametersMode;
    }

    public ArrayList<Object> getPageGridColModelList() {
        return pageGridColModelList;
    }

    public void setPageGridColModelList(ArrayList<Object> pageGridColModelList) {
        this.pageGridColModelList = pageGridColModelList;
    }

    public ArrayList<Object> getParameters() {
        return parameters;
    }

    public void setParameters(ArrayList<Object> parameters) {
        this.parameters = parameters;
    }

    public Boolean getSearch() {
        return search;
    }

    public void setSearch(Boolean search) {
        this.search = search;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getSidx() {
        return sidx;
    }

    public void setSidx(String sidx) {
        this.sidx = sidx;
    }

    public String getSord() {
        return sord;
    }

    public void setSord(String sord) {
        this.sord = sord;
    }

    @Override
    public String toString() {
        return "GridRequestDataModel{" +
                "pageGridColModelList=" + pageGridColModelList +
                ", parametersMode='" + parametersMode + '\'' +
                ", parameters=" + parameters +
                ", search=" + search +
                ", rows=" + rows +
                ", page=" + page +
                ", sidx='" + sidx + '\'' +
                ", sord='" + sord + '\'' +
                '}';
    }
}
