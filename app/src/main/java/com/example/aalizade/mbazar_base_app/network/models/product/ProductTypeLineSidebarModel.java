/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.aalizade.mbazar_base_app.network.models.product;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Dev1
 */
public class ProductTypeLineSidebarModel {
    
    //عرضه کننده
    private Set<GeneralAttributeModel> vendorSet = new HashSet<>();
    //گارانتی
    private Set<GeneralAttributeModel> productTypeGuarantySet = new HashSet<>();
    //قالب مشخصات
    private Set<ProductTypeLineSidebarAttributeTitleModel> attributeTitleSet = new HashSet<>();
    
    //----------------------------------------------//

    public Set<GeneralAttributeModel> getVendorSet() {
        return vendorSet;
    }

    public void setVendorSet(Set<GeneralAttributeModel> vendorSet) {
        this.vendorSet = vendorSet;
    }

    public Set<GeneralAttributeModel> getProductTypeGuarantySet() {
        return productTypeGuarantySet;
    }

    public void setProductTypeGuarantySet(Set<GeneralAttributeModel> productTypeGuarantySet) {
        this.productTypeGuarantySet = productTypeGuarantySet;
    }

    public Set<ProductTypeLineSidebarAttributeTitleModel> getAttributeTitleSet() {
        return attributeTitleSet;
    }

    public void setAttributeTitleSet(Set<ProductTypeLineSidebarAttributeTitleModel> attributeTitleSet) {
        this.attributeTitleSet = attributeTitleSet;
    }

    @Override
    public String toString() {
        return "ProductTypeLineSidebarModel{" +
                "vendorSet=" + vendorSet +
                ", productTypeGuarantySet=" + productTypeGuarantySet +
                ", attributeTitleSet=" + attributeTitleSet +
                '}';
    }
}
