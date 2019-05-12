package com.example.aalizade.mbazar_base_app.network.models.product;

import com.example.aalizade.mbazar_base_app.network.models.attributes.AttributeValueFrontModel;
import com.example.aalizade.mbazar_base_app.network.models.attributes.FullAttributeGroupFrontModel;

import java.util.List;

/**
 * Created by aalizade on 4/26/2018.
 */

public class ProductSpecificationListCustomModel {
    private String Title;
    private List<AttributeValueFrontModel> Specs;

    public ProductSpecificationListCustomModel() {
    }

    public ProductSpecificationListCustomModel(String title, List<AttributeValueFrontModel> specs) {
        Title = title;
        Specs = specs;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public List<AttributeValueFrontModel> getSpecs() {
        return Specs;
    }

    public void setSpecs(List<AttributeValueFrontModel> specs) {
        Specs = specs;
    }

    @Override
    public String toString() {
        return "ProductSpecificationListCustomModel{" +
                "Title='" + Title + '\'' +
                ", Specs=" + Specs +
                '}';
    }
}
