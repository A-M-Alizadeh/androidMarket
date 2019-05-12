package com.example.aalizade.mbazar_base_app.utility.sectiond_recyclerview;

/**
 * Created by aalizade on 3/5/2018.
 */

public class Child {

    String typeLineId;
    Boolean isOnSale;
    String unitPericeTaxIncludeDiscountInclude;
    String guarantyTitle;
    String colorTitle;

    public Child(String typeLineId, Boolean isOnSale, String unitPericeTaxIncludeDiscountInclude, String guarantyTitle, String colorTitle) {
        this.typeLineId = typeLineId;
        this.isOnSale = isOnSale;
        this.unitPericeTaxIncludeDiscountInclude = unitPericeTaxIncludeDiscountInclude;
        this.guarantyTitle = guarantyTitle;
        this.colorTitle = colorTitle;
    }

    public Child() {
    }

    public String getTypeLineId() {
        return typeLineId;
    }

    public void setTypeLineId(String typeLineId) {
        this.typeLineId = typeLineId;
    }

    public Boolean getOnSale() {
        return isOnSale;
    }

    public void setOnSale(Boolean onSale) {
        isOnSale = onSale;
    }

    public String getUnitPericeTaxIncludeDiscountInclude() {
        return unitPericeTaxIncludeDiscountInclude;
    }

    public void setUnitPericeTaxIncludeDiscountInclude(String unitPericeTaxIncludeDiscountInclude) {
        this.unitPericeTaxIncludeDiscountInclude = unitPericeTaxIncludeDiscountInclude;
    }

    public String getGuarantyTitle() {
        return guarantyTitle;
    }

    public void setGuarantyTitle(String guarantyTitle) {
        this.guarantyTitle = guarantyTitle;
    }

    public String getColorTitle() {
        return colorTitle;
    }

    public void setColorTitle(String colorTitle) {
        this.colorTitle = colorTitle;
    }

    @Override
    public String toString() {
        return "Child{" +
                "typeLineId='" + typeLineId + '\'' +
                ", isOnSale=" + isOnSale +
                ", unitPericeTaxIncludeDiscountInclude='" + unitPericeTaxIncludeDiscountInclude + '\'' +
                ", guarantyTitle='" + guarantyTitle + '\'' +
                ", colorTitle='" + colorTitle + '\'' +
                '}';
    }

}