/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.aalizade.mbazar_base_app.network.models.product;

/**
 *
 * @author Dev1
 */
public class RelatedProductTypeFullPageModel {

    /**
     * محصولات مرتبط
     */
    private ProductTypeBriefListDataFrontModel relatedProducts;
    /**
     * لوازم جانبی محصول
     */
    private ProductTypeBriefListDataFrontModel accessories;
    /**
     * سبد کالایی مرتبط با محصول
     */
    private ProductTypeBriefListDataFrontModel isBasketRelatedProducts;

    //===================================================//
    /**
     * محصولات مرتبط
     *
     * @return the relatedProducts
     */
    public ProductTypeBriefListDataFrontModel getRelatedProducts() {
        return relatedProducts;
    }

    /**
     * محصولات مرتبط
     *
     * @param relatedProducts the relatedProducts to set
     */
    public void setRelatedProducts(ProductTypeBriefListDataFrontModel relatedProducts) {
        this.relatedProducts = relatedProducts;
    }

    /**
     * لوازم جانبی محصول
     *
     * @return the accessories
     */
    public ProductTypeBriefListDataFrontModel getAccessories() {
        return accessories;
    }

    /**
     * لوازم جانبی محصول
     *
     * @param accessories the accessories to set
     */
    public void setAccessories(ProductTypeBriefListDataFrontModel accessories) {
        this.accessories = accessories;
    }

    /**
     * سبد کالایی مرتبط با محصول
     *
     * @return the isBasketRelatedProducts
     */
    public ProductTypeBriefListDataFrontModel getIsBasketRelatedProducts() {
        return isBasketRelatedProducts;
    }

    /**
     * سبد کالایی مرتبط با محصول
     *
     * @param isBasketRelatedProducts the isBasketRelatedProducts to set
     */
    public void setIsBasketRelatedProducts(ProductTypeBriefListDataFrontModel isBasketRelatedProducts) {
        this.isBasketRelatedProducts = isBasketRelatedProducts;
    }

}
