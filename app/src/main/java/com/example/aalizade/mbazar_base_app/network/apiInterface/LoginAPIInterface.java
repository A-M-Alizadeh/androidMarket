package com.example.aalizade.mbazar_base_app.network.apiInterface;

import com.example.aalizade.mbazar_base_app.network.models.customer.CustomerFrontModelCreate;
import com.example.aalizade.mbazar_base_app.network.models.general.ConfirmationCodeFrontModel;
import com.example.aalizade.mbazar_base_app.network.models.membershiprequest.FindModel;
import com.example.aalizade.mbazar_base_app.network.models.general.PrimitiveResponse;
import com.example.aalizade.mbazar_base_app.network.models.TokenModel;
import com.example.aalizade.mbazar_base_app.network.models.loggedin.LoggedInModel;
import com.example.aalizade.mbazar_base_app.network.models.membershiprequest.MembershipRequestFrontModelCreate;
import com.example.aalizade.mbazar_base_app.network.models.membershiprequest.MembershipRequestFrontModelUpdate;
import com.example.aalizade.mbazar_base_app.network.models.user.ChangeFirstPasswordModel;
import com.example.aalizade.mbazar_base_app.network.models.user.FindUserModel;
import com.example.aalizade.mbazar_base_app.network.models.user.FullUserFrontModel;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by aalizade on 12/5/2017.
 */

public interface LoginAPIInterface {

    @GET("logout")
    Call<ResponseBody> logout();

    @Headers({
            "Connection: keep-alive","Authorization: Basic d2ViLWNsaWVudDoxMjM0NTY3ODk=","Accept: application/json",
            "Content-Type: application/x-www-form-urlencoded","Referer: http://www.mbazar.net/"
    })
    @POST("oauth/token")
    @FormUrlEncoded
    Call <TokenModel> requestForToken(@Field("grant_type") String grant_type , @Field("username") String username
            , @Field("password") String password );

    @POST("frontPanel/general/getLoggedInModel")
    Call <LoggedInModel> reqforUserModel();

    @POST("w/confirmationCode/send")
    Call <ResponseBody> sendVerifyMessage(@Body ConfirmationCodeFrontModel confirmationCodeFrontModel); //response is primitive type

    @POST("w/customer/create")
    Call <ResponseBody> mbazarUserCreateRequest(@Body CustomerFrontModelCreate customerFrontModelCreate);

    @Headers({
            "Accept: application/json","Content-Type: application/json"
    })
    @POST("w/membershipRequest/create")
    Call<ResponseBody> RegisterMember(@Body MembershipRequestFrontModelCreate membershipRequestFrontModelCreate);

    @Headers({
            "Accept: application/json","Content-Type: application/json"
    })
//    @POST("frontPanel/membershipRequest/submaster/getMembershipRequestIdByLoggedInUser")dfsgdfg
    @POST("w/membershipRequest/submaster/getMembershipRequestIdByLoggedInUser")
    Call<PrimitiveResponse> ifUserisMemberRequest();

    @Headers({
            "Accept: application/json","Content-Type: application/json"
    })
    @POST("frontPanel/membershipRequest/submaster/find")
    Call<List<MembershipRequestFrontModelUpdate>> getMemberToShowRequest(@Body FindModel findModel);

    @Headers({
            "Accept: application/json","Content-Type: application/json"
    })
    @POST("w/user/firstLogin")
    Call<PrimitiveResponse> changeFirstLoginPasswordRequest(@Body ChangeFirstPasswordModel changeFirstPasswordModel);

    @GET("/frontPanel/general/changeCurrentUserPosition/id/{id}")
    Call <ResponseBody> changeCurrentUserPosition(@Path(value="id") String id);


}
