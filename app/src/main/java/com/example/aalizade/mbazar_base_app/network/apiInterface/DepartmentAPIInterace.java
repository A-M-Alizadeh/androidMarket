package com.example.aalizade.mbazar_base_app.network.apiInterface;

import com.example.aalizade.mbazar_base_app.network.models.department.DepartmentFullfrontModel;
import com.example.aalizade.mbazar_base_app.network.models.department.DepartmentProductTypeBriefListFrontModel;
import com.example.aalizade.mbazar_base_app.network.models.department.DepartmentProductTypeFindFrontModel;
import com.example.aalizade.mbazar_base_app.network.models.department.DepartmentSidebarFrontModel;
import com.example.aalizade.mbazar_base_app.network.models.department.FindProductTypeByProductTypeGroupFrontTypeFrontModel;
import com.example.aalizade.mbazar_base_app.network.models.product.ProductTypeBriefFrontModel;

import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by aalizade on 3/8/2018.
 */

public interface DepartmentAPIInterace {
    @Headers({
            "Accept: application/json", "Content-Type: application/json"
    })
    @POST("w/department/departmentFrontGet")
    Call<DepartmentFullfrontModel> fetchDeprtmentData(@Body Object emptyObject);

//    @GET("fso/download/single/eshop/department/{id}/image/")
//    Call<ResponseBody> getDepartmentPhotos(@Path(value = "id") String id);


    @POST("w/city/getVitrinModel/{cityId}")
    Call<ResponseBody> getVitrinModelRequest(@Path(value = "cityId") String cityId);

    @POST("w/productType/findByProductTypeGroupFrontType")
    Call<Map<String, List<ProductTypeBriefFrontModel>>> getOfferedProducts(@Body FindProductTypeByProductTypeGroupFrontTypeFrontModel shitModel);

    @POST("w/productType/departmentProductTypeList")
    Call<DepartmentProductTypeBriefListFrontModel> getDepartmentAllPageLists(@Body DepartmentProductTypeFindFrontModel departmentProductTypeFindFrontModel);

    @POST("w/department/departmentSidebarGet")
    Call<DepartmentSidebarFrontModel> getsideProductTypes(@Body DepartmentSidebarFrontModel departmentSidebarFrontModel);


}
