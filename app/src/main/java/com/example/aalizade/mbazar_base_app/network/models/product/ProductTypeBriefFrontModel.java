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
public class ProductTypeBriefFrontModel {

    private String id;
    // جزیی کاور دارد؟
    private Boolean hasProductTypeCover = true;
    //عنوان محصول
    private String title;
    //قیمت با مالیات
    private Long unitPriceTaxInclude;
    //قیمت با تخفیف و مالیات
    private String productTypeUnitPriceTaxIncludeDiscountInclude;
    //حراج است؟
    private Boolean isOnSale = false;
    // تخفیف دارد؟
    private Boolean hasDiscount = false;
    //عنوان شاخه کالایی
    private String productCategoryTitle;
    //شناسه شاخه کالایی
    private Integer productCategoryId;
    //عرضه کننده
    private Integer vendor_id;
    private String vendor_name;
    //بهترین انبار
    private Integer bestWarehouse_id;
    //مقدار تخفیف عرضه کننده
    private String discountVendorPercentage;
    //عنوان تخفیف عرضه کننده
    private String discountVendorTitle;
    //مقدار تخفیف بازار اعضا
    private Double discountMBazarPercentage;
    //عنوان تخفیف بازار اعضا
    private String discountMBazarTitle;
    //درصد مالیات
    private String tax_currentTotalRate;
    //rate stars
    //todo=> [dev1]====>[fixRate]
    private String rate = "3.65";
    //نمایش دکمه سبد خرید؟
    private Boolean viewAddToCart = true;
    //نمایش موجودی محصول؟
    private Boolean viewQuantity = false;
    //آیا موجودی دارد؟
    private Boolean productTypeHasQuantity = true;
    //موجودی کل
    private String quantityTotal;

    //===================================================//
    /**
     * @param "id,"//0
     * @param "title,"
     * @param "unitPriceTaxInclude,"//2
     * @param "product.productCategory.title,"
     * @param "product.productCategory.id,"//4
     * @param "product.id,"
     * @param "vendor.id,"//6
     * @param "vendor.name,"
     * @param "tax.currentTotalRate,"
     * @param "viewAddToCart,"//9
     * @param "viewQuantity,"
     * @param "quantityTotal";//11
     * @param productArray
     *
     */
    public ProductTypeBriefFrontModel(Object[] productArray) {
        this.id = (String) productArray[0];
        this.title = (String) productArray[1];
        this.unitPriceTaxInclude = (Long) productArray[2];
        this.productCategoryTitle = (String) productArray[3];
        this.productCategoryId = (Integer) productArray[4];
        this.vendor_id = (Integer) productArray[6];
        this.vendor_name = (String) productArray[7];
        this.tax_currentTotalRate = (String) productArray[8];
        this.viewAddToCart = (Boolean) productArray[9];
        this.viewQuantity = (Boolean) productArray[10];
        //در سرویس بررسی میشود که آیا مجاز هستیم تعداد را بفرستیم یا خیر
        this.quantityTotal = (String) productArray[11];
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getHasProductTypeCover() {
        return hasProductTypeCover;
    }

    public void setHasProductTypeCover(Boolean hasProductTypeCover) {
        this.hasProductTypeCover = hasProductTypeCover;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getUnitPriceTaxInclude() {
        return unitPriceTaxInclude;
    }

    public void setUnitPriceTaxInclude(Long unitPriceTaxInclude) {
        this.unitPriceTaxInclude = unitPriceTaxInclude;
    }

    public String getProductTypeUnitPriceTaxIncludeDiscountInclude() {
        return productTypeUnitPriceTaxIncludeDiscountInclude;
    }

    public void setProductTypeUnitPriceTaxIncludeDiscountInclude(String productTypeUnitPriceTaxIncludeDiscountInclude) {
        this.productTypeUnitPriceTaxIncludeDiscountInclude = productTypeUnitPriceTaxIncludeDiscountInclude;
    }

    public Boolean getIsOnSale() {
        return isOnSale;
    }

    public void setIsOnSale(Boolean isOnSale) {
        this.isOnSale = isOnSale;
    }

    public Boolean getHasDiscount() {
        return hasDiscount;
    }

    public void setHasDiscount(Boolean hasDiscount) {
        this.hasDiscount = hasDiscount;
    }

    public String getProductCategoryTitle() {
        return productCategoryTitle;
    }

    public void setProductCategoryTitle(String productCategoryTitle) {
        this.productCategoryTitle = productCategoryTitle;
    }

    public Integer getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Integer productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public Integer getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(Integer vendor_id) {
        this.vendor_id = vendor_id;
    }

    public String getVendor_name() {
        return vendor_name;
    }

    public void setVendor_name(String vendor_name) {
        this.vendor_name = vendor_name;
    }

    public Integer getBestWarehouse_id() {
        return bestWarehouse_id;
    }

    public void setBestWarehouse_id(Integer bestWarehouse_id) {
        this.bestWarehouse_id = bestWarehouse_id;
    }

    public String getDiscountVendorPercentage() {
        return discountVendorPercentage;
    }

    public void setDiscountVendorPercentage(String discountVendorPercentage) {
        this.discountVendorPercentage = discountVendorPercentage;
    }

    public String getDiscountVendorTitle() {
        return discountVendorTitle;
    }

    public void setDiscountVendorTitle(String discountVendorTitle) {
        this.discountVendorTitle = discountVendorTitle;
    }

    public Double getDiscountMBazarPercentage() {
        return discountMBazarPercentage;
    }

    public void setDiscountMBazarPercentage(Double discountMBazarPercentage) {
        this.discountMBazarPercentage = discountMBazarPercentage;
    }

    public String getDiscountMBazarTitle() {
        return discountMBazarTitle;
    }

    public void setDiscountMBazarTitle(String discountMBazarTitle) {
        this.discountMBazarTitle = discountMBazarTitle;
    }

    public String getTax_currentTotalRate() {
        return tax_currentTotalRate;
    }

    public void setTax_currentTotalRate(String tax_currentTotalRate) {
        this.tax_currentTotalRate = tax_currentTotalRate;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public Boolean getViewAddToCart() {
        return viewAddToCart;
    }

    public void setViewAddToCart(Boolean viewAddToCart) {
        this.viewAddToCart = viewAddToCart;
    }

    public Boolean getViewQuantity() {
        return viewQuantity;
    }

    public void setViewQuantity(Boolean viewQuantity) {
        this.viewQuantity = viewQuantity;
    }

    public Boolean getProductTypeHasQuantity() {
        return productTypeHasQuantity;
    }

    public void setProductTypeHasQuantity(Boolean productTypeHasQuantity) {
        this.productTypeHasQuantity = productTypeHasQuantity;
    }

    public String getQuantityTotal() {
        return quantityTotal;
    }

    public void setQuantityTotal(String quantityTotal) {
        this.quantityTotal = quantityTotal;
    }


    @Override
    public String toString() {
        return "ProductTypeBriefFrontModel{" +
                "id=" + id +
                ", hasProductTypeCover=" + hasProductTypeCover +
                ", title='" + title + '\'' +
                ", unitPriceTaxInclude=" + unitPriceTaxInclude +
                ", productTypeUnitPriceTaxIncludeDiscountInclude=" + productTypeUnitPriceTaxIncludeDiscountInclude +
                ", isOnSale=" + isOnSale +
                ", hasDiscount=" + hasDiscount +
                ", productCategoryTitle='" + productCategoryTitle + '\'' +
                ", productCategoryId=" + productCategoryId +
                ", vendor_id=" + vendor_id +
                ", vendor_name='" + vendor_name + '\'' +
                ", bestWarehouse_id=" + bestWarehouse_id +
                ", discountVendorPercentage=" + discountVendorPercentage +
                ", discountVendorTitle='" + discountVendorTitle + '\'' +
                ", discountMBazarPercentage=" + discountMBazarPercentage +
                ", discountMBazarTitle='" + discountMBazarTitle + '\'' +
                ", tax_currentTotalRate=" + tax_currentTotalRate +
                ", rate=" + rate +
                ", viewAddToCart=" + viewAddToCart +
                ", viewQuantity=" + viewQuantity +
                ", productTypeHasQuantity=" + productTypeHasQuantity +
                ", quantityTotal=" + quantityTotal +
                '}';
    }
}
