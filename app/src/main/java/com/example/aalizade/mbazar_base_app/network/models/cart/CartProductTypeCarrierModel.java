/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.aalizade.mbazar_base_app.network.models.cart;

import java.util.Objects;

/**
 *
 * @author Dev2
 */
public class CartProductTypeCarrierModel {

    Integer id;

    //عنوان
    private String title;

    //نام شرکت حمل
    private String carrierCompany_memberCompany_organization_name;

    //  پشتیبانی از حمل گروهی
    private Boolean groupCarrying;

    //روش حمل
    private String costPolicy_id;

    private String costPolicy_langKey;

    private String costPolicy_value;

    //حداقل خرید برای ارسال رایگان
    private Long freeMinimumAmount;

    //مدت زمان حمل
    private String duration;

    //هزینه
    //در سرور بر مبنای وزن و حجم و ثابت و رایگان طبق هر دستورالعمل میتواند محاسبه شود
    private String fixedCostTaxInclude;

    //واحد 
    private String costPolicyUnit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCarrierCompany_memberCompany_organization_name() {
        return carrierCompany_memberCompany_organization_name;
    }

    public void setCarrierCompany_memberCompany_organization_name(String carrierCompany_memberCompany_organization_name) {
        this.carrierCompany_memberCompany_organization_name = carrierCompany_memberCompany_organization_name;
    }

    public Boolean getGroupCarrying() {
        return groupCarrying;
    }

    public void setGroupCarrying(Boolean groupCarrying) {
        this.groupCarrying = groupCarrying;
    }

    public String getCostPolicy_id() {
        return costPolicy_id;
    }

    public void setCostPolicy_id(String costPolicy_id) {
        this.costPolicy_id = costPolicy_id;
    }

    public String getCostPolicy_langKey() {
        return costPolicy_langKey;
    }

    public void setCostPolicy_langKey(String costPolicy_langKey) {
        this.costPolicy_langKey = costPolicy_langKey;
    }

    public Long getFreeMinimumAmount() {
        return freeMinimumAmount;
    }

    public void setFreeMinimumAmount(Long freeMinimumAmount) {
        this.freeMinimumAmount = freeMinimumAmount;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getFixedCostTaxInclude() {
        return fixedCostTaxInclude;
    }

    public void setFixedCostTaxInclude(String fixedCostTaxInclude) {
        this.fixedCostTaxInclude = fixedCostTaxInclude;
    }

    public String getCostPolicyUnit() {
        return costPolicyUnit;
    }

    public void setCostPolicyUnit(String costPolicyUnit) {
        this.costPolicyUnit = costPolicyUnit;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCostPolicy_value() {
        return costPolicy_value;
    }

    public void setCostPolicy_value(String costPolicy_value) {
        this.costPolicy_value = costPolicy_value;
    }
    
    

    //never Remove hashCode & equals
    //used in cartProductTypeService In step4
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CartProductTypeCarrierModel other = (CartProductTypeCarrierModel) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CartProductTypeCarrierModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", carrierCompany_memberCompany_organization_name='" + carrierCompany_memberCompany_organization_name + '\'' +
                ", groupCarrying=" + groupCarrying +
                ", costPolicy_id='" + costPolicy_id + '\'' +
                ", costPolicy_langKey='" + costPolicy_langKey + '\'' +
                ", costPolicy_value='" + costPolicy_value + '\'' +
                ", freeMinimumAmount=" + freeMinimumAmount +
                ", duration='" + duration + '\'' +
                ", fixedCostTaxInclude='" + fixedCostTaxInclude + '\'' +
                ", costPolicyUnit='" + costPolicyUnit + '\'' +
                '}';
    }
}
