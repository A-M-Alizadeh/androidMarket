package com.example.aalizade.mbazar_base_app.network.apiInterface;

import com.example.aalizade.mbazar_base_app.network.models.grid.GridRequestDataModel;
import com.example.aalizade.mbazar_base_app.network.models.grid.GridResponseDataModel;
import com.example.aalizade.mbazar_base_app.network.models.payment.RedirectModel;
import com.example.aalizade.mbazar_base_app.network.models.payment.TokenRedirectModel;
import com.example.aalizade.mbazar_base_app.network.models.product.FindByVitrinModel;
import com.example.aalizade.mbazar_base_app.network.models.product.ProductTypeFullFrontModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by sbayatani on 4/15/2018.
 */

public interface CheckoutAPIInterface {

    @POST("w/purchaseOrder/submaster/listGrid")
    Call<GridResponseDataModel> getUserCheckoutView(@Body GridRequestDataModel gridRequestDataModel);

    @POST("w/purchaseOrderCarrierGroup/submaster/listGrid")
    Call<GridResponseDataModel> getOrderCarrier(@Body GridRequestDataModel gridRequestDataModel);

    @POST("w/purchaseOrderDetail/submaster/listGrid")
    Call<GridResponseDataModel> getProductDescription(@Body GridRequestDataModel gridRequestDataModel);

    @POST("w/cart/submaster/getTokenRedirect")
    Call<TokenRedirectModel> getTokenRedirect(@Body RedirectModel redirectModel);

}
