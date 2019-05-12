/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.aalizade.mbazar_base_app.network.models.cart;


import com.example.aalizade.mbazar_base_app.network.models.general.GenericModel;

import java.util.ArrayList;
import java.util.List;

public class CartModel extends GenericModel {

//    public interface ValidationUpdateProductTypeList extends Default {
//        // validation group marker interface
//    }
//
//    public interface ValidationUpdateSocialGroup extends Default {
//        // validation group marker interface
//    }
//
//    public interface ValidationUpdateUserContact extends Default {
//        // validation group marker interface
//    }
//
//    public interface ValidationUpdateCarrier extends Default {
//        // validation group marker interface
//    }
//
//    public interface ValidationCartPayment extends Default {
//        // validation group marker interface
//    }

    private Integer id;

    //آیدی کانون
    private Integer socialGroup_id;

    //نام کانون
    private String socialGroup_name;

    //کد کانون
    private String socialGroup_code;

    //نام و نام خاتوادگی تحویل گیرنده
    private String reciverName;

    //تلفن همراه تحویل گیرنده
    private String reciverMobileNo;

    //نشانی حمل
    private Integer userContact_id;

    //توضیحات خرید
    private String description;

    //آیا اقساطی خریداری شده است؟
    private Boolean isFacility;

    //مدل بهترین انبار انتخابی که طبق فرمول بهینه حساب میشود
    //@Required
    private List<CartBestWarehouseModel> cartBestWarehouseModelList = new ArrayList<>(); //todo uncomment

    //آیدی ویترین سایت
    private Integer vitrin_id;

    //آیدی مشتری
    private Integer customer_id;

    // ورژن تب های جدیدی که کلاینت در سبد خرید نییو میکند
    private String clientRevision;

    private Integer stepDone;

    //اگر مقدارش ترو باشد به استپ اول برود و اگر فالس باشد به استپ اول برنگردد
    private Boolean returnToStepOne = false;
    
    //لیست خطاهای cartError را برمیگرداند
    private StepNoWithCartErrorModel stepNoWithCartErrorModel; //todo uncomment

    public Integer getStepDone() {
        return stepDone;
    }

    public void setStepDone(Integer stepDone) {
        this.stepDone = stepDone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReciverName() {
        return reciverName;
    }

    public void setReciverName(String reciverName) {
        this.reciverName = reciverName;
    }

    public String getReciverMobileNo() {
        return reciverMobileNo;
    }

    public void setReciverMobileNo(String reciverMobileNo) {
        this.reciverMobileNo = reciverMobileNo;
    }

    public Integer getUserContact_id() {
        return userContact_id;
    }

    public void setUserContact_id(Integer userContact_id) {
        this.userContact_id = userContact_id;
    }

    public Integer getSocialGroup_id() {
        return socialGroup_id;
    }

    public void setSocialGroup_id(Integer socialGroup_id) {
        this.socialGroup_id = socialGroup_id;
    }

    public String getSocialGroup_name() {
        return socialGroup_name;
    }

    public void setSocialGroup_name(String socialGroup_name) {
        this.socialGroup_name = socialGroup_name;
    }

    public String getSocialGroup_code() {
        return socialGroup_code;
    }

    public void setSocialGroup_code(String socialGroup_code) {
        this.socialGroup_code = socialGroup_code;
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    public Integer getVitrin_id() {
        return vitrin_id;
    }

    public void setVitrin_id(Integer vitrin_id) {
        this.vitrin_id = vitrin_id;
    }

    public Boolean getIsFacility() {
        return isFacility;
    }

    public void setIsFacility(Boolean isFacility) {
        this.isFacility = isFacility;
    }

    public List<CartBestWarehouseModel> getCartBestWarehouseModelList() {
        return cartBestWarehouseModelList;
    }

    public void setCartBestWarehouseModelList(List<CartBestWarehouseModel> cartBestWarehouseModelList) {
        this.cartBestWarehouseModelList = cartBestWarehouseModelList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClientRevision() {
        return clientRevision;
    }

    public void setClientRevision(String clientRevision) {
        this.clientRevision = clientRevision;
    }

    public Boolean getReturnToStepOne() {
        return returnToStepOne;
    }

    public void setReturnToStepOne(Boolean returnToStepOne) {
        this.returnToStepOne = returnToStepOne;
    }

    public StepNoWithCartErrorModel getStepNoWithCartErrorModel() {
        return stepNoWithCartErrorModel;
    }

    public void setStepNoWithCartErrorModel(StepNoWithCartErrorModel stepNoWithCartErrorModel) {
        this.stepNoWithCartErrorModel = stepNoWithCartErrorModel;
    }
}
