package com.example.aalizade.mbazar_base_app.network.apiInterface;

import com.example.aalizade.mbazar_base_app.network.models.cart.CartDeleteModel;
import com.example.aalizade.mbazar_base_app.network.models.cart.CartGetModel;
import com.example.aalizade.mbazar_base_app.network.models.cart.CartModel;
import com.example.aalizade.mbazar_base_app.network.models.cart.CustomBaghBaghuModel;
import com.example.aalizade.mbazar_base_app.network.models.cart.PaymentSystemCartByMemberModel;
import com.example.aalizade.mbazar_base_app.network.models.department.Empty;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by aalizade on 3/26/2018.
 */

public interface CartAPIInterface {
    @Headers({
            "Accept: application/json", "Content-Type: application/json"
    })
    @POST("w/cart/submaster/updateProductTypeList")
    Call<CartModel> addItemToCartRequest(@Body CartModel cartModel);

    @Headers({
            "Accept: application/json", "Content-Type: application/json"
    })
    @POST("w/cart/submaster/findProductTypeBriefList")
    Call<CartModel> addItemToCartRequestWithoutToken(@Body CustomBaghBaghuModel customBaghBaghuModel);

    @Headers({
            "Accept: application/json", "Content-Type: application/json"
    })
    @POST("w/cart/submaster/getModel")
    Call<CartModel> getCartwhenLoggedIn(@Body CartGetModel cartGetModel);

    @Headers({
            "Accept: application/json", "Content-Type: application/json"
    })
    @POST("w/cart/submaster/deleteCartProductType")
    Call<ResponseBody> removeItemFromCartRequest(@Body CartDeleteModel cartDeleteModel);

    @Headers({
            "Accept: application/json", "Content-Type: application/json"
    })
    @POST("w/cart/submaster/updateSocialGroup")
    Call<CartModel> submitSelectedSocialGroup(@Body CartModel cartModel);

    @Headers({
            "Accept: application/json", "Content-Type: application/json"
    })
    @POST("w/cart/submaster/updateUserContact")
    Call<CartModel> submitSelectedAddress(@Body CartModel cartModel);

    @Headers({
            "Accept: application/json", "Content-Type: application/json"
    })
    @POST("w/cart/submaster/updateCarrier")
    Call<CartModel> updateCarrierOption(@Body CartModel cartModel);


    @Headers({
            "Accept: application/json", "Content-Type: application/json"
    })
    @POST("w/cart/submaster/paymentSystem")
    Call<List<PaymentSystemCartByMemberModel>> paymentOptions(@Body Empty empty);

}
