/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.aalizade.mbazar_base_app.network.models.cart;

/**
 *
 * @author Dev3
 */
public enum PaymentSystemCartEnum {
    
    SPECIALUSERCREDIT("SPECIALUSERCREDIT"),//اعتبارات خاص
    SPECIALUSERGIFT("SPECIALUSERGIFT"),//بن های هدیه خاص
    PRODUCTCREDIT("PRODUCTCREDIT"),//نسیه های تامین کالا
    WORKCREDIT("WORKCREDIT"),//نسیه های کار و زندگی
    CREDITFACILITY("CREDITFACILITY"),//نسیه اقساط
    CREDIT("CREDIT"),//نسیه
    USERGIFT("USERGIFT"),//بن هدیه
    DONATEUSERCREDIT("DONATEUSERCREDIT"),//اعتبارات کاربری اهدایی
    SELFUSERCREDIT("SELFUSERCREDIT"),//اعتبارات کاربری شخصی
    BANKGATEWAY("BANKGATEWAY");//نقد درگاه بانک
    
    private final String value;

    private PaymentSystemCartEnum(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
