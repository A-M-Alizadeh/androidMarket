package com.example.aalizade.mbazar_base_app.network.RequestServices;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.aalizade.mbazar_base_app.activities.user.VisitMembershipRequestActivity;
import com.example.aalizade.mbazar_base_app.network.apiInterface.LoginAPIInterface;
import com.example.aalizade.mbazar_base_app.network.models.general.PrimitiveResponse;
import com.example.aalizade.mbazar_base_app.network.retrofit.CallbackWithRetry;
import com.example.aalizade.mbazar_base_app.utility.ProgressBarShower;
import com.example.aalizade.mbazar_base_app.utility.interfaces.IResponseHandler;
import com.example.aalizade.mbazar_base_app.utility.interfaces.UtilInterface;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by aalizade on 5/21/2018.
 */

public class HasRequestForMembershipRequestService {

    Context _context;
    View view;

    public HasRequestForMembershipRequestService(Context context, View view) {
        this._context = context;
        this.view = view;
    }

    public void hasRequestforMembership(LoginAPIInterface loginAPIInterface, final IResponseHandler iResponseHandler) {
        Call<PrimitiveResponse> call = loginAPIInterface.ifUserisMemberRequest();
        ProgressBarShower.StartMyProgressBar((Activity) _context, view);
        call.enqueue(new CallbackWithRetry<PrimitiveResponse>(call, (Activity) _context, view) {
            @Override
            public void onResponse(Call<PrimitiveResponse> call, Response<PrimitiveResponse> response) {
                ProgressBarShower.StopMyProgressBar((Activity) _context, view);
                if (response.isSuccessful()) {

                    iResponseHandler.HandleAfterResponse(response.body().getResponse());

                } else {
                    Toast.makeText(view.getContext(), "مشکل ارتباط با سرور", Toast.LENGTH_SHORT).show();
                    try {
                        Log.d("usermember", String.valueOf(response.errorBody().string()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        });
    }
}
