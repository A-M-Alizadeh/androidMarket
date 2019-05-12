package com.example.aalizade.mbazar_base_app.network.models.checkout;

/**
 * Created by sbayatani on 4/15/2018.
 */

public class ProductDescriptionModel {
    private String id;
    private String purchaseOrderCarrierGroup;
    private String productTypeId;
    private String productTypeTitle;
    private String taxRate;
    private String productTypeUnitPriceTaxInclude;
    private String discountTotalPercentage;
    private String productTypeQuantityPrice;
    private String vendorId;
    private String quantity;

    public ProductDescriptionModel(String id, String purchaseOrderCarrierGroup, String productTypeId, String productTypeTitle, String taxRate, String productTypeUnitPriceTaxInclude, String discountTotalPercentage, String productTypeQuantityPrice, String vendorId, String quantity) {
        this.id = id;
        this.purchaseOrderCarrierGroup = purchaseOrderCarrierGroup;
        this.productTypeId = productTypeId;
        this.productTypeTitle = productTypeTitle;
        this.taxRate = taxRate;
        this.productTypeUnitPriceTaxInclude = productTypeUnitPriceTaxInclude;
        this.discountTotalPercentage = discountTotalPercentage;
        this.productTypeQuantityPrice = productTypeQuantityPrice;
        this.vendorId = vendorId;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPurchaseOrderCarrierGroup() {
        return purchaseOrderCarrierGroup;
    }

    public void setPurchaseOrderCarrierGroup(String purchaseOrderCarrierGroup) {
        this.purchaseOrderCarrierGroup = purchaseOrderCarrierGroup;
    }

    public String getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(String productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getProductTypeTitle() {
        return productTypeTitle;
    }

    public void setProductTypeTitle(String productTypeTitle) {
        this.productTypeTitle = productTypeTitle;
    }

    public String getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(String taxRate) {
        this.taxRate = taxRate;
    }

    public String getProductTypeUnitPriceTaxInclude() {
        return productTypeUnitPriceTaxInclude;
    }

    public void setProductTypeUnitPriceTaxInclude(String productTypeUnitPriceTaxInclude) {
        this.productTypeUnitPriceTaxInclude = productTypeUnitPriceTaxInclude;
    }

    public String getDiscountTotalPercentage() {
        return discountTotalPercentage;
    }

    public void setDiscountTotalPercentage(String discountTotalPercentage) {
        this.discountTotalPercentage = discountTotalPercentage;
    }

    public String getProductTypeQuantityPrice() {
        return productTypeQuantityPrice;
    }

    public void setProductTypeQuantityPrice(String productTypeQuantityPrice) {
        this.productTypeQuantityPrice = productTypeQuantityPrice;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
