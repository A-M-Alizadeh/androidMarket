/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.aalizade.mbazar_base_app.network.models.cart;


import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static org.androidannotations.annotations.EBean.Scope.Default;

/**
 *
 * @author Dev2
 */
public class CartProductTypeModel {

//    public interface ValidationUpdateProductTypeList extends Default {
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

    private String id;

    //آیدی محصول انتخابی
    private String productType_id;

    //عنوان محصول انتخابی
    private String productType_title;

    //آیدی عرضه کننده محصول انتخابی
    private String productType_vendor_id;

    //نام عرضه کننده محصول انتخابی
    private String productType_vendor_name;

    //مقدار مالیات محصول انتخابی
    private String productType_tax_currentTotalRate;

    //قیمت با مالیات محصول انتخابی
    private String productType_unitPriceTaxInclude;

    //عنوان تخفیف عرضه کننده
    private String discountVendorTitle;

    //درصد تخفیف عرضه کننده
    private String discountVendorPercentage;

    //عنوان تخفیف بازاراعضا
    private String discountMBazarTitle;

    //درصد تخفیف بازاراعضا 
    private String discountMBazarPercentage;

    //درصد تخفیف کل - جمع درصد تخفیف عرضه کننده و بازار اعضا
    private String discountTotalPercentage;

    //قیمت نهایی (با تخفیف)
    //productType_unitPriceTaxIncluded - discountTotalPercentage
    private String productTypeUnitPriceTaxIncludeDiscountInclude;

    //تعداد
    private String quantity;

    //قیمت تعداد
    //quantity * productTypeUnitPriceTaxIncludeDiscountInclude
    private String productTypeQuantityPrice;

    //آیدی حامل انتخابی - مشتری باید پر کند
    private String carrier_id;

    //هزینه حمل بهترین حامل انتخابی - در سرویس مرحله انتخاب حامل پر میشود
    private String carrier_unitPriceTaxInclude;

    //عنوان حامل انتخابی 
    private String carrier_title;

    //حداقل خرید برای ارسال رایگان
    private String carrier_freeMinimumAmount;

    //لیست حامل های قابل انتخاب بهترین انبار محصول - برای کامبو انتخاب حامل
    private Set<CartProductTypeCarrierModel> cartProductTypeCarrierModelSet = new HashSet<>();

    //لیست گزینه های پرداخت قابل انتخاب هر محصول - برای نمایش در ستون گزینه های پرداخت اعضا
    //پرداخت غیراقساطی
    private Set<CartPaymentSystemModel> paymentSystemCartModelSet = new HashSet<>();

    //خطای مربوط به کارت در متد getModel بجای ترو کردن اکسپشن
//    private Set<ErrorCartProductTypeEnum> errorCartProductTypeEnumSet;

    //اگر جزیی عکس کاور داشته باشد ترو ست میشود 
    private Boolean hasProductTypeCover;
    
    //اگر ذاتی عکس کاور داشته باشد ترو ست میشود 
    private Boolean hasProductCover;

    //موجودی کل
    private String productType_quantityTotal;

    //نمایش موجودی محصول؟
    private Boolean productType_viewQuantity = false;

    //نمایش دکمه سبد خرید؟
    private Boolean productType_viewAddToCart = true;

    //پرداخت غیراقساطی
    //private Set<CartPaymentSystemModel> cartPaymentSystemModelSet = new HashSet<>();

    //for me:
    private String carrier_costPolicy_value;
    private String carrier_costPolicyUnit;
    private String carrier_costPolicyUnitExchange;
    private String sumWeightAllProductTypeInCarrier;
    private String sumUnitPriceTaxIncludeProductTypeInCarrier;

    private String productType_weightUnit;
    private String productType_weight;
    private String productType_weightExchange;

    private String productType_dimentionalUnit;
    private String productType_dimentionalLength;
    private String productType_dimentionalHeight;
    private String productType_dimentionalWidth;
    private String productType_dimentionalExchange;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductType_id() {
        return productType_id;
    }

    public void setProductType_id(String productType_id) {
        this.productType_id = productType_id;
    }

    public String getProductType_title() {
        return productType_title;
    }

    public void setProductType_title(String productType_title) {
        this.productType_title = productType_title;
    }

    public String getProductType_vendor_id() {
        return productType_vendor_id;
    }

    public void setProductType_vendor_id(String productType_vendor_id) {
        this.productType_vendor_id = productType_vendor_id;
    }

    public String getProductType_vendor_name() {
        return productType_vendor_name;
    }

    public void setProductType_vendor_name(String productType_vendor_name) {
        this.productType_vendor_name = productType_vendor_name;
    }

    public String getProductType_tax_currentTotalRate() {
        return productType_tax_currentTotalRate;
    }

    public void setProductType_tax_currentTotalRate(String productType_tax_currentTotalRate) {
        this.productType_tax_currentTotalRate = productType_tax_currentTotalRate;
    }

    public String getProductType_unitPriceTaxInclude() {
        return productType_unitPriceTaxInclude;
    }

    public void setProductType_unitPriceTaxInclude(String productType_unitPriceTaxInclude) {
        this.productType_unitPriceTaxInclude = productType_unitPriceTaxInclude;
    }

    public String getDiscountVendorTitle() {
        return discountVendorTitle;
    }

    public void setDiscountVendorTitle(String discountVendorTitle) {
        this.discountVendorTitle = discountVendorTitle;
    }

    public String getDiscountVendorPercentage() {
        return discountVendorPercentage;
    }

    public void setDiscountVendorPercentage(String discountVendorPercentage) {
        this.discountVendorPercentage = discountVendorPercentage;
    }

    public String getDiscountMBazarTitle() {
        return discountMBazarTitle;
    }

    public void setDiscountMBazarTitle(String discountMBazarTitle) {
        this.discountMBazarTitle = discountMBazarTitle;
    }

    public String getDiscountMBazarPercentage() {
        return discountMBazarPercentage;
    }

    public void setDiscountMBazarPercentage(String discountMBazarPercentage) {
        this.discountMBazarPercentage = discountMBazarPercentage;
    }

    public String getDiscountTotalPercentage() {
        return discountTotalPercentage;
    }

    public void setDiscountTotalPercentage(String discountTotalPercentage) {
        this.discountTotalPercentage = discountTotalPercentage;
    }

    public String getProductTypeUnitPriceTaxIncludeDiscountInclude() {
        return productTypeUnitPriceTaxIncludeDiscountInclude;
    }

    public void setProductTypeUnitPriceTaxIncludeDiscountInclude(String productTypeUnitPriceTaxIncludeDiscountInclude) {
        this.productTypeUnitPriceTaxIncludeDiscountInclude = productTypeUnitPriceTaxIncludeDiscountInclude;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getProductTypeQuantityPrice() {
        return productTypeQuantityPrice;
    }

    public void setProductTypeQuantityPrice(String productTypeQuantityPrice) {
        this.productTypeQuantityPrice = productTypeQuantityPrice;
    }

    public String getCarrier_id() {
        return carrier_id;
    }

    public void setCarrier_id(String carrier_id) {
        this.carrier_id = carrier_id;
    }

    public String getCarrier_unitPriceTaxInclude() {
        return carrier_unitPriceTaxInclude;
    }

    public void setCarrier_unitPriceTaxInclude(String carrier_unitPriceTaxInclude) {
        this.carrier_unitPriceTaxInclude = carrier_unitPriceTaxInclude;
    }

    public String getCarrier_title() {
        return carrier_title;
    }

    public void setCarrier_title(String carrier_title) {
        this.carrier_title = carrier_title;
    }

    public String getCarrier_freeMinimumAmount() {
        return carrier_freeMinimumAmount;
    }

    public void setCarrier_freeMinimumAmount(String carrier_freeMinimumAmount) {
        this.carrier_freeMinimumAmount = carrier_freeMinimumAmount;
    }

    public Set<CartProductTypeCarrierModel> getCartProductTypeCarrierModelSet() {
        return cartProductTypeCarrierModelSet;
    }

    public void setCartProductTypeCarrierModelSet(Set<CartProductTypeCarrierModel> cartProductTypeCarrierModelSet) {
        this.cartProductTypeCarrierModelSet = cartProductTypeCarrierModelSet;
    }

    public Set<CartPaymentSystemModel> getPaymentSystemCartModelSet() {
        return paymentSystemCartModelSet;
    }

    public void setPaymentSystemCartModelSet(Set<CartPaymentSystemModel> paymentSystemCartModelSet) {
        this.paymentSystemCartModelSet = paymentSystemCartModelSet;
    }

    public Boolean getHasProductTypeCover() {
        return hasProductTypeCover;
    }

    public void setHasProductTypeCover(Boolean hasProductTypeCover) {
        this.hasProductTypeCover = hasProductTypeCover;
    }

    public Boolean getHasProductCover() {
        return hasProductCover;
    }

    public void setHasProductCover(Boolean hasProductCover) {
        this.hasProductCover = hasProductCover;
    }

    public String getProductType_quantityTotal() {
        return productType_quantityTotal;
    }

    public void setProductType_quantityTotal(String productType_quantityTotal) {
        this.productType_quantityTotal = productType_quantityTotal;
    }

    public Boolean getProductType_viewQuantity() {
        return productType_viewQuantity;
    }

    public void setProductType_viewQuantity(Boolean productType_viewQuantity) {
        this.productType_viewQuantity = productType_viewQuantity;
    }

    public Boolean getProductType_viewAddToCart() {
        return productType_viewAddToCart;
    }

    public void setProductType_viewAddToCart(Boolean productType_viewAddToCart) {
        this.productType_viewAddToCart = productType_viewAddToCart;
    }

    public String getCarrier_costPolicy_value() {
        return carrier_costPolicy_value;
    }

    public void setCarrier_costPolicy_value(String carrier_costPolicy_value) {
        this.carrier_costPolicy_value = carrier_costPolicy_value;
    }

    public String getCarrier_costPolicyUnit() {
        return carrier_costPolicyUnit;
    }

    public void setCarrier_costPolicyUnit(String carrier_costPolicyUnit) {
        this.carrier_costPolicyUnit = carrier_costPolicyUnit;
    }

    public String getCarrier_costPolicyUnitExchange() {
        return carrier_costPolicyUnitExchange;
    }

    public void setCarrier_costPolicyUnitExchange(String carrier_costPolicyUnitExchange) {
        this.carrier_costPolicyUnitExchange = carrier_costPolicyUnitExchange;
    }

    public String getSumWeightAllProductTypeInCarrier() {
        return sumWeightAllProductTypeInCarrier;
    }

    public void setSumWeightAllProductTypeInCarrier(String sumWeightAllProductTypeInCarrier) {
        this.sumWeightAllProductTypeInCarrier = sumWeightAllProductTypeInCarrier;
    }

    public String getSumUnitPriceTaxIncludeProductTypeInCarrier() {
        return sumUnitPriceTaxIncludeProductTypeInCarrier;
    }

    public void setSumUnitPriceTaxIncludeProductTypeInCarrier(String sumUnitPriceTaxIncludeProductTypeInCarrier) {
        this.sumUnitPriceTaxIncludeProductTypeInCarrier = sumUnitPriceTaxIncludeProductTypeInCarrier;
    }

    public String getProductType_weightUnit() {
        return productType_weightUnit;
    }

    public void setProductType_weightUnit(String productType_weightUnit) {
        this.productType_weightUnit = productType_weightUnit;
    }

    public String getProductType_weight() {
        return productType_weight;
    }

    public void setProductType_weight(String productType_weight) {
        this.productType_weight = productType_weight;
    }

    public String getProductType_weightExchange() {
        return productType_weightExchange;
    }

    public void setProductType_weightExchange(String productType_weightExchange) {
        this.productType_weightExchange = productType_weightExchange;
    }

    public String getProductType_dimentionalUnit() {
        return productType_dimentionalUnit;
    }

    public void setProductType_dimentionalUnit(String productType_dimentionalUnit) {
        this.productType_dimentionalUnit = productType_dimentionalUnit;
    }

    public String getProductType_dimentionalLength() {
        return productType_dimentionalLength;
    }

    public void setProductType_dimentionalLength(String productType_dimentionalLength) {
        this.productType_dimentionalLength = productType_dimentionalLength;
    }

    public String getProductType_dimentionalHeight() {
        return productType_dimentionalHeight;
    }

    public void setProductType_dimentionalHeight(String productType_dimentionalHeight) {
        this.productType_dimentionalHeight = productType_dimentionalHeight;
    }

    public String getProductType_dimentionalWidth() {
        return productType_dimentionalWidth;
    }

    public void setProductType_dimentionalWidth(String productType_dimentionalWidth) {
        this.productType_dimentionalWidth = productType_dimentionalWidth;
    }

    public String getProductType_dimentionalExchange() {
        return productType_dimentionalExchange;
    }

    public void setProductType_dimentionalExchange(String productType_dimentionalExchange) {
        this.productType_dimentionalExchange = productType_dimentionalExchange;
    }

    ////////////////never remove hashCode & equals
    //**user for compare two model in cartService
    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public boolean equals(Object obj) {
        CartProductTypeModel other = (CartProductTypeModel)obj;
        if (this.productType_id.equals(other.getProductType_id())) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "CartProductTypeModel{" +
                "id=" + id +
                ", productType_id='" + productType_id + '\'' +
                ", productType_title='" + productType_title + '\'' +
                ", productType_vendor_id='" + productType_vendor_id + '\'' +
                ", productType_vendor_name='" + productType_vendor_name + '\'' +
                ", productType_tax_currentTotalRate='" + productType_tax_currentTotalRate + '\'' +
                ", productType_unitPriceTaxInclude='" + productType_unitPriceTaxInclude + '\'' +
                ", discountVendorTitle='" + discountVendorTitle + '\'' +
                ", discountVendorPercentage='" + discountVendorPercentage + '\'' +
                ", discountMBazarTitle='" + discountMBazarTitle + '\'' +
                ", discountMBazarPercentage='" + discountMBazarPercentage + '\'' +
                ", discountTotalPercentage='" + discountTotalPercentage + '\'' +
                ", productTypeUnitPriceTaxIncludeDiscountInclude='" + productTypeUnitPriceTaxIncludeDiscountInclude + '\'' +
                ", quantity='" + quantity + '\'' +
                ", productTypeQuantityPrice='" + productTypeQuantityPrice + '\'' +
                ", carrier_id='" + carrier_id + '\'' +
                ", carrier_unitPriceTaxInclude='" + carrier_unitPriceTaxInclude + '\'' +
                ", carrier_title='" + carrier_title + '\'' +
                ", carrier_freeMinimumAmount='" + carrier_freeMinimumAmount + '\'' +
                ", cartProductTypeCarrierModelSet=" + cartProductTypeCarrierModelSet +
                ", paymentSystemCartModelSet=" + paymentSystemCartModelSet +
                ", hasProductTypeCover=" + hasProductTypeCover +
                ", hasProductCover=" + hasProductCover +
                ", productType_quantityTotal='" + productType_quantityTotal + '\'' +
                ", productType_viewQuantity=" + productType_viewQuantity +
                ", productType_viewAddToCart=" + productType_viewAddToCart +
                ", carrier_costPolicy_value='" + carrier_costPolicy_value + '\'' +
                ", carrier_costPolicyUnit='" + carrier_costPolicyUnit + '\'' +
                ", carrier_costPolicyUnitExchange='" + carrier_costPolicyUnitExchange + '\'' +
                ", sumWeightAllProductTypeInCarrier='" + sumWeightAllProductTypeInCarrier + '\'' +
                ", sumUnitPriceTaxIncludeProductTypeInCarrier='" + sumUnitPriceTaxIncludeProductTypeInCarrier + '\'' +
                ", productType_weightUnit='" + productType_weightUnit + '\'' +
                ", productType_weight='" + productType_weight + '\'' +
                ", productType_weightExchange='" + productType_weightExchange + '\'' +
                ", productType_dimentionalUnit='" + productType_dimentionalUnit + '\'' +
                ", productType_dimentionalLength='" + productType_dimentionalLength + '\'' +
                ", productType_dimentionalHeight='" + productType_dimentionalHeight + '\'' +
                ", productType_dimentionalWidth='" + productType_dimentionalWidth + '\'' +
                ", productType_dimentionalExchange='" + productType_dimentionalExchange + '\'' +
                '}';
    }
}
