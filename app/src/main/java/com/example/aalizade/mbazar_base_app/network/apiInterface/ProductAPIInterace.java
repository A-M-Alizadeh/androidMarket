package com.example.aalizade.mbazar_base_app.network.apiInterface;

import com.example.aalizade.mbazar_base_app.network.models.product.FindByVitrinModel;
import com.example.aalizade.mbazar_base_app.network.models.product.OtherProductTypeWithFilterModel;
import com.example.aalizade.mbazar_base_app.network.models.product.ProductTypeBriefListDataFrontModel;
import com.example.aalizade.mbazar_base_app.network.models.product.ProductTypeFullFrontModel;
import com.example.aalizade.mbazar_base_app.network.models.product.ProductTypeLineSidebarFindModel;
import com.example.aalizade.mbazar_base_app.network.models.product.ProductTypeLineSidebarModel;
import com.example.aalizade.mbazar_base_app.network.models.product.ProductTypeSearchAdvancedFrontModel;
import com.example.aalizade.mbazar_base_app.network.models.product.ProductTypeSearchRealatedFrontModel;
import com.example.aalizade.mbazar_base_app.network.models.product.RelatedProductTypeFullPageModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;



public interface ProductAPIInterace {
    @Headers({
            "Accept: application/json", "Content-Type: application/json"
    })
    @POST("w/productType/productTypeByAdvancedSearch")
    Call<ProductTypeBriefListDataFrontModel> getProductListRequest(@Body ProductTypeSearchAdvancedFrontModel productTypeSearchAdvancedFrontModel);


    @Headers({
            "Accept: application/json", "Content-Type: application/json"
    })
    @POST("w/productType/full")
    Call<ProductTypeFullFrontModel> getProductData(@Body FindByVitrinModel findByVitrinModel);

//    @Headers({
//            "Accept: application/json", "Content-Type: application/json"
//    })
//    @POST("w/productType/getOtherListSidebar")
//    Call<ProductTypeLineSidebarModel> getProductVendorandGuarantySets(@Body FindByVitrinModel findByVitrinModel);

    @Headers({
            "Accept: application/json", "Content-Type: application/json"
    })
    @POST("w/productType/getRelatedProductTypeFullPage")
    Call<RelatedProductTypeFullPageModel> getProductPageRelatedProducts(@Body ProductTypeSearchRealatedFrontModel productTypeSearchRealatedFrontModel);

    @Headers({
            "Accept: application/json", "Content-Type: application/json"
    })
    @POST("w/productType/getOtherListSidebar")
    Call<ProductTypeLineSidebarModel> getVendorFilterList(@Body FindByVitrinModel findByVitrinModel);

    @Headers({
            "Accept: application/json", "Content-Type: application/json"
    })
    @POST("w/productType/getOtherList")
    Call<OtherProductTypeWithFilterModel> getOtherVendors(@Body ProductTypeLineSidebarFindModel productTypeLineSidebarFindModel);


}
