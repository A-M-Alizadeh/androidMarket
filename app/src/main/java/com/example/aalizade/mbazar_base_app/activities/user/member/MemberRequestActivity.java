package com.example.aalizade.mbazar_base_app.activities.user.member;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.adapters.recycler_adapters.gift.PaymentMethodAdapter1;
import com.example.aalizade.mbazar_base_app.activities.basic_parts.BaseActivity;
import com.example.aalizade.mbazar_base_app.utility.MySnackBar;
import com.example.aalizade.mbazar_base_app.utility.ProgressBarShower;
import com.example.aalizade.mbazar_base_app.network.MBZ_Token_Prefs;
import com.example.aalizade.mbazar_base_app.network.apiInterface.GeneralRetrofitAPIInterface;
import com.example.aalizade.mbazar_base_app.network.apiInterface.UserRetrofitAPIInterface;
import com.example.aalizade.mbazar_base_app.network.models.general.ComboRequestModel;
import com.example.aalizade.mbazar_base_app.network.models.general.ComboResponseModel1;
import com.example.aalizade.mbazar_base_app.network.retrofit.CallbackWithRetry;
import com.example.aalizade.mbazar_base_app.network.retrofit.RetrofitOAuthClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by sajad on 4/26/2018.
 */

public class MemberRequestActivity extends BaseActivity {
    View activityLayout;
    Button submit,reject;
    Spinner socialTrendSpn, socialNameSpn;
    EditText socialCode;
    GeneralRetrofitAPIInterface comboAPI;
    UserRetrofitAPIInterface userAPI;
    LinearLayout progressWrapper;
    PaymentMethodAdapter1 socialTrendAdapter,socialNameAdapter;
    ArrayList<ComboResponseModel1> socialNameList, socialTrendList;
    String socialId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //== main layout for this activity ================================
        activityLayout = getLayoutInflater().inflate(R.layout.activity_member_request,null);
        super.mappingWidgets(activityLayout);
        //=================================================================

        progressWrapper = (LinearLayout) activityLayout.findViewById(R.id.progress_wrapper_id);
        submit = (Button) activityLayout.findViewById(R.id.submit);
        reject = (Button) activityLayout.findViewById(R.id.reject);
        socialTrendSpn = (Spinner) activityLayout.findViewById(R.id.member_request_social_trend);
        socialNameSpn = (Spinner) activityLayout.findViewById(R.id.member_request_social_name);
        socialCode = (EditText) activityLayout.findViewById(R.id.member_request_social_code);

        socialNameList = new ArrayList<>();
        socialTrendList = new ArrayList<>();
        socialNameAdapter = new PaymentMethodAdapter1(this,socialNameList, R.layout.simple_textview);
        socialTrendAdapter = new PaymentMethodAdapter1(this, socialTrendList, R.layout.simple_textview);

        comboAPI = RetrofitOAuthClient.getOauthClient(getApplicationContext(),findViewById(R.id.masterPage_mother_drawer_layout)).create(GeneralRetrofitAPIInterface.class);
        userAPI = RetrofitOAuthClient.getOauthClient(getApplicationContext(),findViewById(R.id.masterPage_mother_drawer_layout)).create(UserRetrofitAPIInterface.class);

        socialNameSpn.setAdapter(socialNameAdapter);
        socialTrendSpn.setAdapter(socialTrendAdapter);
        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getSocialCombo();

        socialTrendSpn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filterSocial(socialTrendList.get(position).get_id());
                Log.v("socialTrendSelected:",socialTrendList.get(position).get_id());
                Log.v("socialTrendSelected:",socialTrendList.get(position).getName());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        socialNameSpn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                socialCode.setText(socialNameList.get(position).getId());
                socialId = socialNameList.get(position).get_id();
                Log.v("social===:",socialNameList.get(position).getId());
                Log.v("socialID===:",socialId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitMembershipRequest(socialId);
            }
        });
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void getSocialCombo(){
        HashMap<String,Object> cityValue = new HashMap<>();
        cityValue.put("trend",null);
        cityValue.put("city", MBZ_Token_Prefs.getString(MBZ_Token_Prefs.com_example_aalizade_mbazar_base_app_USER_CITY_ID));

        ComboRequestModel socialGroup = new ComboRequestModel("socialGroup","socialGroupOpenWithConfirm",cityValue);
        ComboRequestModel trendComboBox = new ComboRequestModel("trend","","");

        HashMap<String,ComboRequestModel> requestModel = new HashMap<>();
        requestModel.put("trendComboBox",trendComboBox);
        requestModel.put("socialGroup",socialGroup);

        Call<Map<String,ArrayList<ArrayList<String>>>> call = comboAPI.getCombo(requestModel);
        ProgressBarShower.StartMyProgressBar(this,progressWrapper);
        call.enqueue(new CallbackWithRetry<Map<String, ArrayList<ArrayList<String>>>>(call,this,progressWrapper) {
            @Override
            public void onResponse(Call<Map<String, ArrayList<ArrayList<String>>>> call, Response<Map<String, ArrayList<ArrayList<String>>>> response) {
                ProgressBarShower.StopMyProgressBar(MemberRequestActivity.this,progressWrapper);
                Log.v("SocialResponse:",response.code()+"");
                if(response.isSuccessful()){
                    Map<String,ArrayList<ArrayList<String>>> result= response.body();
                    ArrayList<ArrayList<String>> trendSocial = result.get("trendComboBox");
                    ArrayList<ArrayList<String>> socialName = result.get("socialGroup");
                    ArrayList<ComboResponseModel1> trend = new ArrayList<>();
                    ArrayList<ComboResponseModel1> social = new ArrayList<>();
                    socialNameAdapter.notifyDataSetChanged();
                    socialTrendAdapter.notifyDataSetChanged();
                    for (int i = 0 ; i<trendSocial.size();i++){
                        trend.add(new ComboResponseModel1(trendSocial.get(i).get(0),trendSocial.get(i).get(1),trendSocial.get(i).get(2)));
                        socialTrendList.add(trend.get(i));
                    }

                    for (int i = 0 ; i<socialName.size();i++){
                        social.add(new ComboResponseModel1(socialName.get(i).get(0),socialName.get(i).get(1),socialName.get(i).get(2)));
                        socialNameList.add(social.get(i));
                    }
                    socialNameAdapter.notifyDataSetChanged();
                    socialTrendAdapter.notifyDataSetChanged();
                    Log.v("SocialName:",socialName.get(0).get(1));
                    Log.v("TrendSocial:",trendSocial.get(0).get(1));
                }
            }
        });
    }

    public void filterSocial(String id){
        HashMap<String,Object> value = new HashMap<>();
        value.put("trend",id);
        value.put("city", MBZ_Token_Prefs.getString(MBZ_Token_Prefs.com_example_aalizade_mbazar_base_app_USER_CITY_ID));

        ComboRequestModel socialGroup = new ComboRequestModel("socialGroup","socialGroupOpenWithConfirm",value);

        Map<String,ComboRequestModel> request = new HashMap<>();
        request.put("socialGroup",socialGroup);
        Log.v("socialRequest:",request.toString());
        Call<Map<String,ArrayList<ArrayList<String>>>> call = comboAPI.getCombo(request);
        call.enqueue(new CallbackWithRetry<Map<String, ArrayList<ArrayList<String>>>>(call,this,progressWrapper) {
            @Override
            public void onResponse(Call<Map<String, ArrayList<ArrayList<String>>>> call, Response<Map<String, ArrayList<ArrayList<String>>>> response) {
                Log.v("SocialSelectedResponse:",response.code()+"");
                if (response.isSuccessful()){
                    socialNameList.clear();
                    Map<String,ArrayList<ArrayList<String>>> result= response.body();
                    Log.v("resultList",result.toString());
                    ArrayList<ArrayList<String>> socialName = result.get("socialGroup");
                    ArrayList<ComboResponseModel1> social = new ArrayList<>();
                    socialNameAdapter.notifyDataSetChanged();
                    for (int i = 0 ; i<socialName.size();i++){
                        social.add(new ComboResponseModel1(socialName.get(i).get(0),socialName.get(i).get(1),socialName.get(i).get(2)));
                        socialNameList.add(social.get(i));
                    }
                    Log.v("nameList",socialNameList.toString());
                    socialNameAdapter.notifyDataSetChanged();
                }
            }
        });

    }

    public void submitMembershipRequest(String id){
        HashMap<String,String> request = new HashMap<>();
        request.put("socialGroupId",id);
        Call<ResponseBody> call = userAPI.submitMemberRequest(request);
        call.enqueue(new CallbackWithRetry<ResponseBody>(call,this,progressWrapper) {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    Log.v("memberRequestResponse:",response.isSuccessful()+"");
                    finish();
                }else {
                    MySnackBar.snackBarWithNoAction("کاربر قبلا در این کانون ثبت نام کرده است",activityLayout);
                }
            }
        });
    }
}
