/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.aalizade.mbazar_base_app.network.models.cart;

import com.example.aalizade.mbazar_base_app.network.models.general.GenericModel;

/**
 *
 * @author Dev2
 */
public class CartErrorModel extends GenericModel {

    private Integer id;

    //کارت
    private Integer cart_id;

    Integer entityId;

    String entityName;

    String errorMessage;

    //اگر مقدارش ترو باشد یعنی خطاهای جهت اطلاع به مشتری است که مشتری نمیتواند این خطاهارو رفع کند و این خطاها به محض ورود به استپ یک پاک میشوند
    Boolean forYourInformation;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCart_id() {
        return cart_id;
    }

    public void setCart_id(Integer cart_id) {
        this.cart_id = cart_id;
    }

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Boolean getForYourInformation() {
        return forYourInformation;
    }

    public void setForYourInformation(Boolean forYourInformation) {
        this.forYourInformation = forYourInformation;
    }

//    @Override
//    public int hashCode() {
//        int hash = 7;
//        hash = 37 * hash + Objects.hashCode(this.id);
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
//        final CartErrorModel other = (CartErrorModel) obj;
//        if (!Objects.equals(this.id, other.id)) {
//            return false;
//        }
//        return true;
//    }
    
    
}
