package com.example.aalizade.mbazar_base_app.activities.basic_parts;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.activities.user.MemberShipRequestActivity;
import com.example.aalizade.mbazar_base_app.activities.user.VisitMembershipRequestActivity;
import com.example.aalizade.mbazar_base_app.network.RequestServices.HasRequestForMembershipRequestService;
import com.example.aalizade.mbazar_base_app.utility.GoOffline;
import com.example.aalizade.mbazar_base_app.utility.MySnackBar;
import com.example.aalizade.mbazar_base_app.utility.ProgressBarShower;
import com.example.aalizade.mbazar_base_app.network.MBZ_Token_Prefs;
import com.example.aalizade.mbazar_base_app.network.apiInterface.LoginAPIInterface;
import com.example.aalizade.mbazar_base_app.network.models.general.PrimitiveResponse;
import com.example.aalizade.mbazar_base_app.network.retrofit.CallbackWithRetry;
import com.example.aalizade.mbazar_base_app.network.retrofit.RetrofitOAuthClient;
import com.example.aalizade.mbazar_base_app.utility.interfaces.IResponseHandler;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class RegisterOptionActivity extends AppCompatActivity { //کاربردی ندارد - انواع درخواست های عضویت
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }



    LinearLayout progressWrapper;
    Button memberRegisterBtn;
    RelativeLayout registerOptionMotherLayout;

    LoginAPIInterface oauthClient;
    HasRequestForMembershipRequestService hasRequestForMembershipRequestService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MBZ_Token_Prefs.initTokenSharedPrefs(getApplicationContext());
        setContentView(R.layout.activity_register_option);
        initialFindViews();
        InitialAPI_RequestService();
        memberRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hasRequestForMembershipRequestService.hasRequestforMembership(oauthClient, new IResponseHandler() {
                    @Override
                    public void HandleAfterResponse(Object o) {
                        String USERORMEMBERID = o.toString();
                        if (!o.toString().matches("")) {
                            Toast.makeText(getApplicationContext(), "شما قبلا درخواست داده اید", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterOptionActivity.this, VisitMembershipRequestActivity.class);
                            String s = USERORMEMBERID.substring(0, USERORMEMBERID.lastIndexOf('.'));
                            intent.putExtra("USERORMEMBERID", s);
                            startActivity(intent);
                        } else {
                            startActivity(new Intent(RegisterOptionActivity.this, MemberShipRequestActivity.class));
                        }
                    }
                });
            }
        });

    }

    public void temporaryOnclick(View v) {
        MySnackBar.snackBarWithNoAction("در حال توسعه و پیاده سازی", findViewById(android.R.id.content));
    }

    public void test() {
        Toast.makeText(getApplicationContext(), "TEST", Toast.LENGTH_SHORT).show();
    }

    public void initialFindViews(){
        progressWrapper = (LinearLayout) findViewById(R.id.progress_wrapper_id);
        registerOptionMotherLayout = (RelativeLayout)findViewById(R.id.register_option_mother_layout_id);
        memberRegisterBtn = (Button) findViewById(R.id.real_person_register_btn_id);
    }

    public void InitialAPI_RequestService(){
        hasRequestForMembershipRequestService = new HasRequestForMembershipRequestService(getApplicationContext(),progressWrapper);

        oauthClient = RetrofitOAuthClient.getOauthClient(getApplicationContext(),registerOptionMotherLayout).create(LoginAPIInterface.class);
    }

    public void memberRegisterRequest(View v) {
        GoOffline.GoOffline(getApplicationContext(), RegisterOptionActivity.this);
    }

//    public void hasRequestforMembership(final LoginAPIInterface userStuff, final View view,final Activity activity) {
//        Call<PrimitiveResponse> call = userStuff.ifUserisMemberRequest();
//        ProgressBarShower.StartMyProgressBar(this, progressWrapper);
//        call.enqueue(new CallbackWithRetry<PrimitiveResponse>(call,this,progressWrapper) {
//            @Override
//            public void onResponse(Call<PrimitiveResponse> call, Response<PrimitiveResponse> response) {
//                ProgressBarShower.StopMyProgressBar(activity, progressWrapper);
//                if (response.isSuccessful()) {
//                    String USERORMEMBERID = response.body().getResponse().toString();
//                    Object resp = response.body().getResponse();
//                    if (!response.body().getResponse().toString().matches("")) {
//                        Toast.makeText(getApplicationContext(), "شما قبلا درخواست داده اید", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(RegisterOptionActivity.this, VisitMembershipRequestActivity.class);
//                        String s = USERORMEMBERID.substring(0, USERORMEMBERID.lastIndexOf('.'));
//                        intent.putExtra("USERORMEMBERID", s);
//                        startActivity(intent);
//                    } else {
//                        startActivity(new Intent(RegisterOptionActivity.this, MemberShipRequestActivity.class));
//                    }
//
//                } else {
//                    Toast.makeText(view.getContext(), "مشکل ارتباط با سرور", Toast.LENGTH_SHORT).show();
//                    try {
//                        Log.d("usermember", String.valueOf(response.errorBody().string()));
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//        });
//    }

}
