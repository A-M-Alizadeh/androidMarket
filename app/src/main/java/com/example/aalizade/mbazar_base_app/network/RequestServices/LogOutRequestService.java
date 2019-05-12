package com.example.aalizade.mbazar_base_app.network.RequestServices;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.aalizade.mbazar_base_app.network.MBZ_Token_Prefs;
import com.example.aalizade.mbazar_base_app.network.apiInterface.LoginAPIInterface;
import com.example.aalizade.mbazar_base_app.network.retrofit.CallbackWithRetry;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by aalizade on 5/15/2018.
 */

public class LogOutRequestService {
    Context context;
    View view;

    public LogOutRequestService(Context context, View view) {
        this.context = context;
        this.view = view;
        MBZ_Token_Prefs.initTokenSharedPrefs(context);
    }

    public void LogOutRequest(LoginAPIInterface loginApiInterface) {
        Call<ResponseBody> call = loginApiInterface.logout();
        call.enqueue(new CallbackWithRetry<ResponseBody>(call, (Activity)context, view) {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(context, "خارج شدید", Toast.LENGTH_LONG).show();
                    MBZ_Token_Prefs.setTokenValues(MBZ_Token_Prefs.com_example_aalizade_mbazar_base_app_EXPIRES_IN, "0");
                    MBZ_Token_Prefs.setTokenEncryptedValues(MBZ_Token_Prefs.com_example_aalizade_mbazar_base_app_ACCESS_TOKEN, "");
                } else {
                    try {
                        Log.d("MYERROR", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
//                    Toast.makeText(context, "عدم موفقیت در خروج", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }
}
