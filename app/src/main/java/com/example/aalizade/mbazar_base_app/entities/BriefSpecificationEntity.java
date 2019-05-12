package com.example.aalizade.mbazar_base_app.entities;

/**
 * Created by aalizade on 3/1/2018.
 */

public class BriefSpecificationEntity {
    String specTitle;
    String specValue;

    public BriefSpecificationEntity(String specTitle, String specValue) {
        this.specTitle = specTitle;
        this.specValue = specValue;
    }

    public String getSpecTitle() {
        return specTitle;
    }

    public void setSpecTitle(String specTitle) {
        this.specTitle = specTitle;
    }

    public String getSpecValue() {
        return specValue;
    }

    public void setSpecValue(String specValue) {
        this.specValue = specValue;
    }

    @Override
    public String toString() {
        return "BriefSpecificationEntity{" +
                "specTitle='" + specTitle + '\'' +
                ", specValue='" + specValue + '\'' +
                '}';
    }
}
