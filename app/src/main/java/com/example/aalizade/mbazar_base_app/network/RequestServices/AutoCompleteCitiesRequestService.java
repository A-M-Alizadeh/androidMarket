package com.example.aalizade.mbazar_base_app.network.RequestServices;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.aalizade.mbazar_base_app.utility.interfaces.IResponseHandler;
import com.example.aalizade.mbazar_base_app.network.apiInterface.GeneralRetrofitAPIInterface;
import com.example.aalizade.mbazar_base_app.network.models.general.AutoCompleteModel;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by aalizade on 5/14/2018.
 */

public class AutoCompleteCitiesRequestService {
    Context context;
    View view;
    public AutoCompleteCitiesRequestService(Context context,View view) {
        this.context = context;
        this.view = view;
    }

    public void populateCities( GeneralRetrofitAPIInterface generalRetrofitAPIInterface, String city, final IResponseHandler iResponseHandler) {
        String url = "frontPanel/general/autocomplete?entity=city&value=" + city + "&parametersMode=organizationUnitCityFilter";
        Call<List<AutoCompleteModel>> call = generalRetrofitAPIInterface.cityAutocompleteRequest(url);
        call.enqueue(new Callback<List<AutoCompleteModel>>() {
            @Override
            public void onResponse(Call<List<AutoCompleteModel>> call, Response<List<AutoCompleteModel>> response) {
                if (response.isSuccessful()) {
                    iResponseHandler.HandleAfterResponse(response.body());
                } else {

                    Toast.makeText(context, "مشکل ارتباط با سرور", Toast.LENGTH_SHORT).show();
                    try {
                        Log.d("CITY", String.valueOf(response.errorBody().string()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<AutoCompleteModel>> call, Throwable t) {
                Toast.makeText(context, "مشکل ارتباط با سرور", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }
}
