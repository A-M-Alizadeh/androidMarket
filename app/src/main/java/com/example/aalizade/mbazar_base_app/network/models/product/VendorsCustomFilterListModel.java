package com.example.aalizade.mbazar_base_app.network.models.product;

import java.util.List;

/**
 * Created by aalizade on 4/26/2018.
 */

public class VendorsCustomFilterListModel {
    private String Title;
    private Integer id;
    private List<GeneralAttributeModel> attributeModels;

    public VendorsCustomFilterListModel() {
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<GeneralAttributeModel> getAttributeModels() {
        return attributeModels;
    }

    public void setAttributeModels(List<GeneralAttributeModel> attributeModels) {
        this.attributeModels = attributeModels;
    }

    public VendorsCustomFilterListModel(String title, Integer id, List<GeneralAttributeModel> attributeModels) {
        Title = title;
        this.id = id;
        this.attributeModels = attributeModels;
    }

    @Override
    public String toString() {
        return "VendorsCustomFilterListModel{" +
                "Title='" + Title + '\'' +
                ", id=" + id +
                ", attributeModels=" + attributeModels +
                '}';
    }
}
