package com.example.aalizade.mbazar_base_app.network.RequestServices;

import android.util.Log;

import com.example.aalizade.mbazar_base_app.utility.interfaces.UtilInterface;
import com.example.aalizade.mbazar_base_app.network.apiInterface.GeneralRetrofitAPIInterface;
import com.example.aalizade.mbazar_base_app.network.models.general.ComboRequestModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by aalizade on 5/13/2018.
 */

public class ComboRequestService {
    public void getComboResult(GeneralRetrofitAPIInterface generalRetrofitAPIInterface , Map<String,ComboRequestModel> enteryMap, final UtilInterface utilInterface) {
        Call<Map<String, ArrayList<ArrayList<String>>>> call = generalRetrofitAPIInterface.getCombo(enteryMap);
        call.enqueue(new Callback<Map<String, ArrayList<ArrayList<String>>>>() {
            @Override
            public void onResponse(Call<Map<String, ArrayList<ArrayList<String>>>> call, Response<Map<String, ArrayList<ArrayList<String>>>> response) {
                if (response.isSuccessful()) {
                    utilInterface.HandleAfterResponse();
                } else {
                    try {
                        Log.d("Fail Combo", String.valueOf(response.errorBody().string()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Map<String, ArrayList<ArrayList<String>>>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
