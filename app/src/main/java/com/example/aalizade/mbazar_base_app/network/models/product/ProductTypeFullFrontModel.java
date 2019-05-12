/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.aalizade.mbazar_base_app.network.models.product;

import com.example.aalizade.mbazar_base_app.network.models.attributes.FullAttributeGroupFrontModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class ProductTypeFullFrontModel {

    //شناسه محصول
    private String id;
    private String product_id;
    //عنوان محصول
    private String product_title;
    //کد محصول
    private String code;
    // جزیی کاور دارد؟
    private Boolean hasProductTypeCover = true;
    //عنوان محصول
    private String title;
    //عنوان شاخه کالایی
    private String product_productCategory_title;
    //شناسه شاخه کالایی
    private String product_productCategory_id;
    //شناسه عرضه کننده
    private String vendor_id;
    //نام عرضه کننده
    private String vendor_name;
    //بهترین انبار
    private String bestWarehouse_id;
    //درصد مالیات
    private String tax_currentTotalRate;
    //rate stars
    //todo=> [dev1]====>[fixRate]
    private String rate = "3.65";
    //موجودی کل
    private String quantityTotal;
    //وضعیت استفاده
    private String usedStatus_langKey;
    //توضیحات
//    private String description;//todo probably changed
    //ترکیب پیش فرض
    private Boolean isDefaultItem = false;
    //عکس بارکد
    private Boolean hasQRBarcode = false;
    //عکس اسلایدر
    private Boolean hasSliderPicture = false;
    //لیست تصاویر اسلایدر
    private ProductTypeFileHashModel sliderModel;
    //عکس کاور
    private Boolean hasCoverPicture = false;
    //عکس سه بعدی
    private Boolean has3DPicture = false;
    //آپلود فیلم
    private Boolean hasMedia = false;
    //نمایش دکمه سبد خرید؟
    private Boolean viewAddToCart = true;
    //نمایش موجودی محصول؟
    private Boolean viewQuantity = false;
    //آیا موجودی دارد؟
    private Boolean hasQuantity = true;
    //ضمیمه دارد؟
    private Boolean hasAttachment = false;
    //مشخصات فنی محصول
    private List<FullAttributeGroupFrontModel> attributeGroupList = new ArrayList<>();
    //اطلاعات عرضه کننده و قیمت محصول
    private ProductTypeLine thisProductType;
    //سایر
    private List<ProductTypeLine> otherProductTypeList = new ArrayList<>();
    //فیلتر صفحه محصول
    private HashMap<Integer,ProductTypeFullFilterModel> filterModelHashMap = new HashMap<>();
    // جزیی کاور دارد؟
    private Boolean hasProductTypeSlider = true;
    //attachment
    private ProductTypeFileHashModel fileAttachmentModel;
    //=====================================================//


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getProduct_title() {
        return product_title;
    }

    public void setProduct_title(String product_title) {
        this.product_title = product_title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getProduct_productCategory_title() {
        return product_productCategory_title;
    }

    public void setProduct_productCategory_title(String product_productCategory_title) {
        this.product_productCategory_title = product_productCategory_title;
    }

    public String getProduct_productCategory_id() {
        return product_productCategory_id;
    }

    public void setProduct_productCategory_id(String product_productCategory_id) {
        this.product_productCategory_id = product_productCategory_id;
    }

    public String getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(String vendor_id) {
        this.vendor_id = vendor_id;
    }

    public String getVendor_name() {
        return vendor_name;
    }

    public void setVendor_name(String vendor_name) {
        this.vendor_name = vendor_name;
    }

    public String getBestWarehouse_id() {
        return bestWarehouse_id;
    }

    public void setBestWarehouse_id(String bestWarehouse_id) {
        this.bestWarehouse_id = bestWarehouse_id;
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

    public String getQuantityTotal() {
        return quantityTotal;
    }

    public void setQuantityTotal(String quantityTotal) {
        this.quantityTotal = quantityTotal;
    }

    public String getUsedStatus_langKey() {
        return usedStatus_langKey;
    }

    public void setUsedStatus_langKey(String usedStatus_langKey) {
        this.usedStatus_langKey = usedStatus_langKey;
    }

    public Boolean getDefaultItem() {
        return isDefaultItem;
    }

    public void setDefaultItem(Boolean defaultItem) {
        isDefaultItem = defaultItem;
    }

    public Boolean getHasQRBarcode() {
        return hasQRBarcode;
    }

    public void setHasQRBarcode(Boolean hasQRBarcode) {
        this.hasQRBarcode = hasQRBarcode;
    }

    public Boolean getHasSliderPicture() {
        return hasSliderPicture;
    }

    public void setHasSliderPicture(Boolean hasSliderPicture) {
        this.hasSliderPicture = hasSliderPicture;
    }

    public ProductTypeFileHashModel getSliderModel() {
        return sliderModel;
    }

    public void setSliderModel(ProductTypeFileHashModel sliderModel) {
        this.sliderModel = sliderModel;
    }

    public Boolean getHasCoverPicture() {
        return hasCoverPicture;
    }

    public void setHasCoverPicture(Boolean hasCoverPicture) {
        this.hasCoverPicture = hasCoverPicture;
    }

    public Boolean getHas3DPicture() {
        return has3DPicture;
    }

    public void setHas3DPicture(Boolean has3DPicture) {
        this.has3DPicture = has3DPicture;
    }

    public Boolean getHasMedia() {
        return hasMedia;
    }

    public void setHasMedia(Boolean hasMedia) {
        this.hasMedia = hasMedia;
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

    public Boolean getHasQuantity() {
        return hasQuantity;
    }

    public void setHasQuantity(Boolean hasQuantity) {
        this.hasQuantity = hasQuantity;
    }

    public Boolean getHasAttachment() {
        return hasAttachment;
    }

    public void setHasAttachment(Boolean hasAttachment) {
        this.hasAttachment = hasAttachment;
    }

    public List<FullAttributeGroupFrontModel> getAttributeGroupList() {
        return attributeGroupList;
    }

    public void setAttributeGroupList(List<FullAttributeGroupFrontModel> attributeGroupList) {
        this.attributeGroupList = attributeGroupList;
    }

    public ProductTypeLine getThisProductType() {
        return thisProductType;
    }

    public void setThisProductType(ProductTypeLine thisProductType) {
        this.thisProductType = thisProductType;
    }

    public List<ProductTypeLine> getOtherProductTypeList() {
        return otherProductTypeList;
    }

    public void setOtherProductTypeList(List<ProductTypeLine> otherProductTypeList) {
        this.otherProductTypeList = otherProductTypeList;
    }

    public HashMap<Integer, ProductTypeFullFilterModel> getFilterModelHashMap() {
        return filterModelHashMap;
    }

    public void setFilterModelHashMap(HashMap<Integer, ProductTypeFullFilterModel> filterModelHashMap) {
        this.filterModelHashMap = filterModelHashMap;
    }

    public Boolean getHasProductTypeSlider() {
        return hasProductTypeSlider;
    }

    public void setHasProductTypeSlider(Boolean hasProductTypeSlider) {
        this.hasProductTypeSlider = hasProductTypeSlider;
    }

    public ProductTypeFileHashModel getFileAttachmentModel() {
        return fileAttachmentModel;
    }

    public void setFileAttachmentModel(ProductTypeFileHashModel fileAttachmentModel) {
        this.fileAttachmentModel = fileAttachmentModel;
    }

//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }

    @Override
    public String toString() {
        return "ProductTypeFullFrontModel{" +
                "id=" + id +
                ", product_id=" + product_id +
                ", product_title='" + product_title + '\'' +
                ", code='" + code + '\'' +
                ", hasProductTypeCover=" + hasProductTypeCover +
                ", title='" + title + '\'' +
                ", product_productCategory_title='" + product_productCategory_title + '\'' +
                ", product_productCategory_id=" + product_productCategory_id +
                ", vendor_id=" + vendor_id +
                ", vendor_name='" + vendor_name + '\'' +
                ", bestWarehouse_id=" + bestWarehouse_id +
                ", tax_currentTotalRate=" + tax_currentTotalRate +
                ", rate=" + rate +
                ", quantityTotal=" + quantityTotal +
                ", usedStatus_langKey='" + usedStatus_langKey + '\'' +
                ", isDefaultItem=" + isDefaultItem +
                ", hasQRBarcode=" + hasQRBarcode +
                ", hasSliderPicture=" + hasSliderPicture +
                ", sliderModel=" + sliderModel +
                ", hasCoverPicture=" + hasCoverPicture +
                ", has3DPicture=" + has3DPicture +
                ", hasMedia=" + hasMedia +
                ", viewAddToCart=" + viewAddToCart +
                ", viewQuantity=" + viewQuantity +
                ", hasQuantity=" + hasQuantity +
                ", hasAttachment=" + hasAttachment +
                ", attributeGroupList=" + attributeGroupList +
                ", thisProductType=" + thisProductType +
                ", otherProductTypeList=" + otherProductTypeList +
                ", filterModelHashMap=" + filterModelHashMap +
                ", hasProductTypeSlider=" + hasProductTypeSlider +
                ", fileAttachmentModel=" + fileAttachmentModel +
                '}';
    }
}
