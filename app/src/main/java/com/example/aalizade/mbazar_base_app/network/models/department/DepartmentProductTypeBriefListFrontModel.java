/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.aalizade.mbazar_base_app.network.models.department;

import com.example.aalizade.mbazar_base_app.network.models.product.ProductTypeBriefListDataFrontModel;

/**
 * مدل لیست محصولات دپارتمانس
 *
 * @author Dev1
 */
public class DepartmentProductTypeBriefListFrontModel {

    //محصولات با بیشترین تخفیف
    private ProductTypeBriefListDataFrontModel maxDiscountList;
    //پرفروشترین محصولات
    private ProductTypeBriefListDataFrontModel bestSellingList;
    //بهترین فروشنده ها
    private ProductTypeBriefListDataFrontModel bestVendorList;
    //محصولات اقساطی
    private ProductTypeBriefListDataFrontModel bestFacilityList;
    //محصولات جدید
    private ProductTypeBriefListDataFrontModel newProductTypeList;
    //محصولات پیشنهادی
    private ProductTypeBriefListDataFrontModel recomendedList;

    //=========================================================================//
    /**
     * محصولات با بیشترین تخفیف
     *
     * @return
     */
    public ProductTypeBriefListDataFrontModel getMaxDiscountList() {
        return maxDiscountList;
    }

    /**
     * محصولات با بیشترین تخفیف
     *
     * @param maxDiscountList
     */
    public void setMaxDiscountList(ProductTypeBriefListDataFrontModel maxDiscountList) {
        this.maxDiscountList = maxDiscountList;
    }

    /**
     * پرفروشترین محصولات
     *
     * @return
     */
    public ProductTypeBriefListDataFrontModel getBestSellingList() {
        return bestSellingList;
    }

    /**
     * پرفروشترین محصولات
     *
     * @param bestSellingList
     */
    public void setBestSellingList(ProductTypeBriefListDataFrontModel bestSellingList) {
        this.bestSellingList = bestSellingList;
    }

    /**
     * بهترین فروشنده ها
     *
     * @return
     */
    public ProductTypeBriefListDataFrontModel getBestVendorList() {
        return bestVendorList;
    }

    /**
     * بهترین فروشنده ها
     *
     * @param bestVendorList
     */
    public void setBestVendorList(ProductTypeBriefListDataFrontModel bestVendorList) {
        this.bestVendorList = bestVendorList;
    }

    /**
     * محصولات اقساطی
     *
     * @return
     */
    public ProductTypeBriefListDataFrontModel getBestFacilityList() {
        return bestFacilityList;
    }

    /**
     * محصولات اقساطی
     *
     * @param bestFacilityList
     */
    public void setBestFacilityList(ProductTypeBriefListDataFrontModel bestFacilityList) {
        this.bestFacilityList = bestFacilityList;
    }

    /**
     * محصولات جدید
     *
     * @return
     */
    public ProductTypeBriefListDataFrontModel getNewProductTypeList() {
        return newProductTypeList;
    }

    /**
     * محصولات جدید
     *
     * @param newProductTypeList
     */
    public void setNewProductTypeList(ProductTypeBriefListDataFrontModel newProductTypeList) {
        this.newProductTypeList = newProductTypeList;
    }

    /**
     * محصولات پیشنهادی
     *
     * @return
     */
    public ProductTypeBriefListDataFrontModel getRecomendedList() {
        return recomendedList;
    }

    /**
     * محصولات پیشنهادی
     *
     * @param recomendedList
     */
    public void setRecomendedList(ProductTypeBriefListDataFrontModel recomendedList) {
        this.recomendedList = recomendedList;
    }

}
