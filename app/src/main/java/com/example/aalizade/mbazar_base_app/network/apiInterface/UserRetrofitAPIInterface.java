package com.example.aalizade.mbazar_base_app.network.apiInterface;

import com.example.aalizade.mbazar_base_app.network.models.general.PrimitiveResponse;
import com.example.aalizade.mbazar_base_app.network.models.user.FindUserModel;
import com.example.aalizade.mbazar_base_app.network.models.user.FullUserFrontModel;
import com.example.aalizade.mbazar_base_app.network.models.user.UserChangePasswordFrontModel;
import com.example.aalizade.mbazar_base_app.network.models.user.UserModelUpdateForm;
import com.example.aalizade.mbazar_base_app.network.models.user.UserNotificationModel;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by aalizade on 1/10/2018.
 */

public interface UserRetrofitAPIInterface {
    @Headers({
            "Accept: application/json","Content-Type: application/json"
    })
    @POST("frontPanel/user/changePassword")
    Call<ResponseBody> changePassword(@Body UserChangePasswordFrontModel userChangePasswordFrontModel);

    @Headers({
            "Accept: application/json","Content-Type: application/json"
    })
    @POST("frontPanel/user/submaster/notificationType")
    Call<UserNotificationModel> sendUsernametoremeberPass(@Body HashMap<String, Object> userNames);

    @Headers({
            "Accept: application/json","Content-Type: application/json"
    })
    @POST("frontPanel/user/submaster/findByUsername")
    Call<FullUserFrontModel> findUserInfoByUsername(@Body FindUserModel findUserModel);

    @Headers({
            "Content-Type: application/json"
    })
    @POST("frontPanel/user/update")
    Call<ResponseBody> updateUserPersonalInfo(@Body UserModelUpdateForm userModelUpdateForm);

    @POST("w/membershipRequest/createForMember")
    Call<ResponseBody> submitMemberRequest(@Body HashMap<String,String> socialGroupId);

}
