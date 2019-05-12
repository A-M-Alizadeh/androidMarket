
package com.example.aalizade.mbazar_base_app.network.models.product;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dev1
 */
public class ProductTypeFileHashModel {
    private Boolean hasProduct = false;
    private Boolean hasProductType = false;
    private List<String> hashedPathList = new ArrayList<>();

    /**
     * @return the hasProduct
     */
    public Boolean getHasProduct() {
        return hasProduct;
    }

    /**
     * @param hasProduct the hasProduct to set
     */
    public void setHasProduct(Boolean hasProduct) {
        this.hasProduct = hasProduct;
    }

    /**
     * @return the hasProductType
     */
    public Boolean getHasProductType() {
        return hasProductType;
    }

    /**
     * @param hasProductType the hasProductType to set
     */
    public void setHasProductType(Boolean hasProductType) {
        this.hasProductType = hasProductType;
    }

    /**
     * @return the hashedPathList
     */
    public List<String> getHashedPathList() {
        return hashedPathList;
    }

    /**
     * @param hashedPathList the hashedPathList to set
     */
    public void setHashedPathList(List<String> hashedPathList) {
        this.hashedPathList = hashedPathList;
    }

    @Override
    public String toString() {
        return "ProductTypeFileHashModel{" +
                "hasProduct=" + hasProduct +
                ", hasProductType=" + hasProductType +
                ", hashedPathList=" + hashedPathList +
                '}';
    }
}
