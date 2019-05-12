package com.example.aalizade.mbazar_base_app.network.apiInterface;

import com.example.aalizade.mbazar_base_app.network.models.credit.CreditCheckRequestModel;
import com.example.aalizade.mbazar_base_app.network.models.credit.CreditCheckResponseModel;
import com.example.aalizade.mbazar_base_app.network.models.credit.CreditSubmitRequestModel;
import com.example.aalizade.mbazar_base_app.network.models.grid.GridRequestDataModel;
import com.example.aalizade.mbazar_base_app.network.models.grid.GridResponseDataModel;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by sbayatani on 4/8/2018.
 */

public interface CreditAPIInterface {
    @POST("frontPanel/userGift/submaster/listGrid")
    Call<GridResponseDataModel> getCreditList(@Body GridRequestDataModel gridRequestDataModel);

    @POST("frontPanel/userGift/submaster/checkByCode")
    Call<CreditCheckResponseModel> checkCredit(@Body CreditCheckRequestModel creditCheckRequestModel);

    @POST("frontPanel/userGift/setOwner")
    Call<ResponseBody> submitAttacmentCredit(@Body CreditSubmitRequestModel creditSubmitRequestModel);

    @POST("frontPanel/userGiftRequest/submaster/listGrid")
    Call<GridResponseDataModel> getRequestGiftList(@Body GridRequestDataModel gridRequestDataModel);

    @POST("frontPanel/userGift/submaster/listGrid")
    Call<GridResponseDataModel> getPublishedGiftList(@Body GridRequestDataModel gridRequestDataModel);

    @POST("frontPanel/userCredit/submaster/listGrid")
    Call<GridResponseDataModel> getUserCreditList(@Body GridRequestDataModel gridRequestDataModel);
}
