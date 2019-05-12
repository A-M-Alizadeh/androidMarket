/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.aalizade.mbazar_base_app.network.models.cart;

import com.example.aalizade.mbazar_base_app.network.models.general.GenericModel;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dev2
 */
public class CartCarrierGroupModel extends GenericModel {

    //آیدی CartCarrierGroup
    private Integer id;
    
    //آیدی حامل انتخابی 
    private Integer carrier_id;

    //هزینه حمل بهترین حامل انتخابی که در سرویس محاسبه میشود 
    private Double carrier_unitPriceTaxInclude;

    //عنوان حامل انتخابی 
    private String carrier_title;

    //حداقل خرید برای ارسال رایگان
    private Long carrier_freeMinimumAmount;
    
    //معیار
    private String carrier_costPolicy_value;
    
    //واحد
    private String carrier_costPolicyUnit_value;
    
    //ضریب
    private Integer carrier_costPolicyUnitExchange;

    //محصولات داخل سبد
    private List<CartProductTypeModel> cartProductTypeModelList = new ArrayList<>();

    /*private String sumWeightAllProductTypeInCarrier;
    private String sumUnitPriceTaxIncludeProductTypeInCarrier;

    private String productType_weightUnit;
    private Double productType_weight;
    private Integer productType_weightExchange;

    private String productType_dimentionalUnit;
    private Double productType_dimentionalLength;
    private Double productType_dimentionalHeight;
    private Double productType_dimentionalWidth;
    private Integer productType_dimentionalExchange;*/
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCarrier_id() {
        return carrier_id;
    }

    public void setCarrier_id(Integer carrier_id) {
        this.carrier_id = carrier_id;
    }

    public Double getCarrier_unitPriceTaxInclude() {
        return carrier_unitPriceTaxInclude;
    }

    public void setCarrier_unitPriceTaxInclude(Double carrier_unitPriceTaxInclude) {
        this.carrier_unitPriceTaxInclude = carrier_unitPriceTaxInclude;
    }

    public String getCarrier_title() {
        return carrier_title;
    }

    public void setCarrier_title(String carrier_title) {
        this.carrier_title = carrier_title;
    }

    public Long getCarrier_freeMinimumAmount() {
        return carrier_freeMinimumAmount;
    }

    public void setCarrier_freeMinimumAmount(Long carrier_freeMinimumAmount) {
        this.carrier_freeMinimumAmount = carrier_freeMinimumAmount;
    }

    public String getCarrier_costPolicy_value() {
        return carrier_costPolicy_value;
    }

    public void setCarrier_costPolicy_value(String carrier_costPolicy_value) {
        this.carrier_costPolicy_value = carrier_costPolicy_value;
    }

    public String getCarrier_costPolicyUnit_value() {
        return carrier_costPolicyUnit_value;
    }

    public void setCarrier_costPolicyUnit_value(String carrier_costPolicyUnit_value) {
        this.carrier_costPolicyUnit_value = carrier_costPolicyUnit_value;
    }

    public Integer getCarrier_costPolicyUnitExchange() {
        return carrier_costPolicyUnitExchange;
    }

    public void setCarrier_costPolicyUnitExchange(Integer carrier_costPolicyUnitExchange) {
        this.carrier_costPolicyUnitExchange = carrier_costPolicyUnitExchange;
    }

    public List<CartProductTypeModel> getCartProductTypeModelList() {
        return cartProductTypeModelList;
    }

    public void setCartProductTypeModelList(List<CartProductTypeModel> cartProductTypeModelList) {
        this.cartProductTypeModelList = cartProductTypeModelList;
    }

//    @Override
//    public int hashCode() {
//        int hash = 5;
//        hash = 13 * hash + Objects.hashCode(this.id);
//        hash = 13 * hash + Objects.hashCode(this.carrier_id);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        }
//        if (obj == null) {
//            return false;
//        }
//        if (getClass() != obj.getClass()) {
//            return false;
//        }
//        final CartCarrierGroupModel other = (CartCarrierGroupModel) obj;
//        if (!Objects.equals(this.id, other.id)) {
//            return false;
//        }
//        if (!Objects.equals(this.carrier_id, other.carrier_id)) {
//            return false;
//        }
//        return true;
//    }

}
