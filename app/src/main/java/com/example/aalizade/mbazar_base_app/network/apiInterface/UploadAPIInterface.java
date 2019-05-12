package com.example.aalizade.mbazar_base_app.network.apiInterface;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by sbayatani on 4/21/2018.
 */

public interface UploadAPIInterface {

    @Multipart
    @POST("fso/upload/eshop/userGiftRequest")
    Call<ResponseBody> upload(@Part MultipartBody.Part file, @Part("params") RequestBody params);
}
