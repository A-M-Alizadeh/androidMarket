package com.example.aalizade.mbazar_base_app.network.apiInterface;

import com.example.aalizade.mbazar_base_app.network.models.general.ComboRequestModel;
import com.example.aalizade.mbazar_base_app.network.models.general.PrimitiveResponse;
import com.example.aalizade.mbazar_base_app.network.models.general.AutoCompleteModel;
import com.example.aalizade.mbazar_base_app.network.models.general.ConfirmationCodeFrontModel;
import com.example.aalizade.mbazar_base_app.network.models.general.ConfirmationCodeModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * Created by aalizade on 1/10/2018.
 */

public interface GeneralRetrofitAPIInterface {
    @Headers({
            "Accept: application/json","Content-Type: application/json"
    })
    @POST("w/confirmationCode/send")
    Call<PrimitiveResponse> sendforgetPasswordVerificationCode(@Body ConfirmationCodeFrontModel confirmationCodeFrontModel);

    @Headers({
            "Accept: application/json","Content-Type: application/json"
    })
    @POST("w/user/resetPassword")
    Call<PrimitiveResponse> resetforgetPassword(@Body ConfirmationCodeModel confirmationCodeModel);

    @Headers({"Content-Type: application/json"})
    @POST("w/general/getCombo")
    Call<Map<String,ArrayList<ArrayList<String>>>> getCombo(@Body Map<String,ComboRequestModel> combos);

    @GET
    Call<List<AutoCompleteModel>> cityAutocompleteRequest(@Url String url);


    @Headers({"Content-Type: application/json"})
    @POST("w/general/getCombo")
    Call<Map<String, ArrayList<ArrayList<String>>>> getCartCombo(@Body Map<String,ComboRequestModel> combos);


}
