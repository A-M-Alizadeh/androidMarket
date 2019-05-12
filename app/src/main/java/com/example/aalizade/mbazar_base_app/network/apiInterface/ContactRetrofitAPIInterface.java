package com.example.aalizade.mbazar_base_app.network.apiInterface;

import com.example.aalizade.mbazar_base_app.network.models.general.PrimitiveResponse;
import com.example.aalizade.mbazar_base_app.network.models.contact.UserContactModel;
import com.example.aalizade.mbazar_base_app.network.models.contact.UserContactModelUpdate;
import com.example.aalizade.mbazar_base_app.network.models.general.DeleteModel;

import java.util.HashMap;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by aalizade on 1/14/2018.
 */

public interface ContactRetrofitAPIInterface {
    @Headers({
            "Accept: application/json", "Content-Type: application/json"
    })
    @POST("frontPanel/contact/submaster/getContact")
    Call<List<UserContactModelUpdate>> getContactInfos(@Body HashMap<String, String> postParam);

    @Headers({
            "Accept: application/json", "Content-Type: application/json"
    })
    @POST("frontPanel/contact/create")
    Call<ResponseBody> updateContactListafterAdd(@Body UserContactModel userContactModel); //List<UserContactModelUpdate>

    @Headers({
            "Accept: application/json", "Content-Type: application/json"
    })
    @POST("frontPanel/contact/submaster/setDefault")
    Call<PrimitiveResponse> setdefaultContactItem(@Body HashMap<String,String> postId);

    @Headers({
            "Accept: application/json", "Content-Type: application/json"
    })
    @POST("frontPanel/contact/update")
    Call<ResponseBody> updateContactListItem(@Body UserContactModelUpdate userContactModelUpdate);


    @Headers({
            "Accept: application/json", "Content-Type: application/json"
    })
    @POST("frontPanel/contact/delete")
    Call<List<UserContactModelUpdate>> deleteContactListItem(@Body DeleteModel deleteModel);


}
