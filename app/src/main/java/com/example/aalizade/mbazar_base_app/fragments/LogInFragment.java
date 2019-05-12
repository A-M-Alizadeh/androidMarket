package com.example.aalizade.mbazar_base_app.fragments;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.activities.login_related.FirstLoginActivity;
import com.example.aalizade.mbazar_base_app.activities.login_related.ForgottenPasswordActivity;
import com.example.aalizade.mbazar_base_app.utility.GlobalVariables;
import com.example.aalizade.mbazar_base_app.utility.MySnackBar;
import com.example.aalizade.mbazar_base_app.utility.ProgressBarShower;
import com.example.aalizade.mbazar_base_app.network.MBZ_Token_Prefs;
import com.example.aalizade.mbazar_base_app.network.apiInterface.CartAPIInterface;
import com.example.aalizade.mbazar_base_app.network.apiInterface.LoginAPIInterface;
import com.example.aalizade.mbazar_base_app.network.models.TokenModel;
import com.example.aalizade.mbazar_base_app.network.models.loggedin.LoggedInModel;
import com.example.aalizade.mbazar_base_app.network.models.loggedin.LoggedInUserModel;
import com.example.aalizade.mbazar_base_app.network.models.loggedin.LoggedInUserPositionRoleUnitModel;
import com.example.aalizade.mbazar_base_app.network.retrofit.CallbackWithRetry;
import com.example.aalizade.mbazar_base_app.network.retrofit.RetrofitClient;
import com.example.aalizade.mbazar_base_app.network.retrofit.RetrofitOAuthClient;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Response;


/**
 * Created by gray on 10/16/17.
 */

public class LogInFragment extends Fragment {
    Button loginBtn;
    TextView forgotPassTxt;
    TextInputEditText usernameTiet, passwordtiet;
    LinearLayout progressWrapper, motherLayout;
    CartAPIInterface cartAPIInterface;
    static Activity activity;
    Boolean isFirstTime = true;

    LoginAPIInterface loginApiInterface;
    LoginAPIInterface ouathedLoginApiInterface;


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_login, container, false);

        activity = getActivity();
        progressWrapper = (LinearLayout) view.findViewById(R.id.progress_wrapper_id);
        motherLayout = (LinearLayout) view.findViewById(R.id.mylayout_id);
        // underline
        forgotPassTxt = (TextView) view.findViewById(R.id.signin_pass_forgot_txt_id);
        forgotPassTxt.setPaintFlags(forgotPassTxt.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        //underline

        //fonts
        Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/B_Yekan.ttf");
        TextInputLayout sign_in_name_layout_id = (TextInputLayout) view.findViewById(R.id.login_name_tiet_layout);
        sign_in_name_layout_id.setTypeface(custom_font);
        TextInputLayout sign_in_pass_layout_id = (TextInputLayout) view.findViewById(R.id.login_pass_tiet_layout);
        sign_in_pass_layout_id.setTypeface(custom_font);
        //fonts

        //getting interfaces
        InitialAPI_RequestService();
        //getting interfaces

        //getting username & password
        usernameTiet = (TextInputEditText) view.findViewById(R.id.login_tietusername_id);
        passwordtiet = (TextInputEditText) view.findViewById(R.id.login_tietpassword_id);
        //getting username & password


        //Login
        loginBtn = (Button) view.findViewById(R.id.login_btn_id);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!usernameTiet.getText().toString().trim().matches("") && !passwordtiet.getText().toString().trim().matches("")) {
                    if (GlobalVariables.LocalCart != null && !MBZ_Token_Prefs.isAuthorized()) {
                        GlobalVariables.LocalUnAthourizedCart = GlobalVariables.LocalCart;
                        GlobalVariables.LocalCart = null;
                    }
                    getToken(loginApiInterface, ouathedLoginApiInterface, view);
                }

            }
//            }
        });
        //Login

        forgotPassTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ForgottenPasswordActivity.class));
            }
        });


        return view;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public void getToken(LoginAPIInterface loginApiInterface, final LoginAPIInterface loginApiInterface2, final View view) {
        Call<TokenModel> call = loginApiInterface.requestForToken("password", usernameTiet.getText().toString().trim(), passwordtiet.getText().toString().trim());
        ProgressBarShower.StartMyProgressBar(getActivity(), progressWrapper);
        call.enqueue(new CallbackWithRetry<TokenModel>(call, getActivity(), progressWrapper) {
            @Override
            public void onResponse(Call<TokenModel> call, Response<TokenModel> response) {
                ProgressBarShower.StopMyProgressBar(getActivity(), progressWrapper);
                if (response.isSuccessful()) {
                    setting_token_to_shared(response.body().getAccess_token(), response.body().getExpires_in(),
                            response.body().getToken_type(), response.body().getRefresh_token());
                    reqMbazarUserModel(loginApiInterface2, view);
                } else if (response.code() == 401) {
                    MySnackBar.snackBarWithNoAction("نام کاربری یا رمز عبور اشتباه میباشد", progressWrapper);
                } else {
                    Toast.makeText(view.getContext(), "نام کاربری اشتباه است", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    public void reqMbazarUserModel(LoginAPIInterface loginApiInterface, final View view) {
        //simple
        Call<LoggedInModel> call = loginApiInterface.reqforUserModel();
        // Set up progress before call
        ProgressBarShower.StartMyProgressBar(getActivity(), progressWrapper);

        call.enqueue(new CallbackWithRetry<LoggedInModel>(call, getActivity(), progressWrapper) {
            @Override
            public void onResponse(Call<LoggedInModel> call, Response<LoggedInModel> response) {
                ProgressBarShower.StopMyProgressBar(getActivity(), progressWrapper);
                if (response.isSuccessful()) {
                    LoggedInUserModel testUser = response.body().getUser();//fmbg
                    //TODO======sajad=============
                    HashMap<String, LoggedInUserPositionRoleUnitModel> loggedInUserUnitList = response.body().getLoggedInUserPositionRoleUnitList();
                    for (String key : loggedInUserUnitList.keySet()) {
                        if (isFirstTime) {
                            if (loggedInUserUnitList.get(key).getLoggedInRole().getType().equals("MEMBER")) {//todo needs to be done once
                                Log.v("CUSTOMERORMEMBER", "HERE IS MEMBER");
//                            GlobalVariables.userId = loggedInUserUnitList.get(key).getId();
                                MBZ_Token_Prefs.setTokenValues(MBZ_Token_Prefs.com_example_aalizade_mbazar_base_app_USER_ROLE_ID, loggedInUserUnitList.get(key).getId());
                                MBZ_Token_Prefs.setTokenValues(MBZ_Token_Prefs.com_example_aalizade_mbazar_base_app_USER_CITY_ID, testUser.getCity_id());
                                MBZ_Token_Prefs.setTokenValues(MBZ_Token_Prefs.com_example_aalizade_mbazar_base_app_USER_IS_MEMBER, Boolean.TRUE);
                                isFirstTime = false;
                            } else {
                                Log.v("CUSTOMERORMEMBER", "HERE IS CUSTOMER");
                                MBZ_Token_Prefs.setTokenValues(MBZ_Token_Prefs.com_example_aalizade_mbazar_base_app_USER_IS_MEMBER, Boolean.FALSE);//todo added on 10/2/97
                                isFirstTime = false;
                            }
                        }

                    }
                    //==========sajad===========

                    //start activity
                    if (testUser.getFirstLogin()) {
                        MBZ_Token_Prefs.setTokenValues(MBZ_Token_Prefs.com_example_aalizade_mbazar_base_app_EXPIRES_IN, "0");
                        Intent intent = new Intent(getActivity(), FirstLoginActivity.class);
                        intent.putExtra("currentpass", passwordtiet.getText().toString());
                        startActivity(intent);
                        getActivity().overridePendingTransition(R.anim.filter_enter, R.anim.filter_exit);
                        getActivity().finish();//todo can't go out
                    } else {
//                        Log.d("LOG MODEL",response.body().toString());
                        MBZ_Token_Prefs.setTokenValues(MBZ_Token_Prefs.com_example_aalizade_mbazar_base_app_FULL_NAME, testUser.getName() + " " + testUser.getFamily());
                        MBZ_Token_Prefs.setTokenValues(MBZ_Token_Prefs.com_example_aalizade_mbazar_base_app_ACCOUNT_USERNAME, testUser.getUsername());
                        MBZ_Token_Prefs.setTokenValues(MBZ_Token_Prefs.com_example_aalizade_mbazar_base_app_USER_ID, String.valueOf(testUser.getId()));//new

//                        checck type of login and basket
                        //todo uncomment cartmodel changed
//                        if (MBZ_Token_Prefs.isAuthorized() && GlobalVariables.LocalUnAthourizedCart != null) {
//                            Globals.updateBasketItems(cartAPIInterface, getActivity(), progressWrapper, new IBadgeUpdate() {
//                                @Override
//                                public void doUpdate() {
//                                    Globals.mergeCarts(GlobalVariables.LocalUnAthourizedCart, cartAPIInterface, getActivity(), motherLayout, new IBadgeUpdate() {
//                                        @Override
//                                        public void doUpdate() {
//                                            activity.overridePendingTransition(R.anim.filter_enter, R.anim.filter_exit);
//                                            activity.finish();
//                                        }
//                                    });
//                                }
//                            });
//
//                        } else {//todo login with empty local basket
//                            Globals.updateBasketItems(cartAPIInterface, getActivity(), motherLayout, new IBadgeUpdate() {
//                                @Override
//                                public void doUpdate() {
//                                    activity.overridePendingTransition(R.anim.filter_enter, R.anim.filter_exit);
//                                    activity.finish();
//                                }
//                            });
//                        }
                        activity.finish();//todo can't go out
                    }

                    //start activity
                } else if (response.code() == 401) {
                    MySnackBar.snackBarWithNoAction("نام کاربری یا رمز عبور اشتباه میباشد", progressWrapper);
                } else {
                    Toast.makeText(view.getContext(), "مشکل ارتباط با سرور", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //simple
    }

    public void setting_token_to_shared(String accessToken, String expiresIn, String tokenType, String refreshToken) {
        SharedPreferences sharedPref = getActivity().getSharedPreferences("oauth", Context.MODE_PRIVATE);
        SharedPreferences.Editor sharedPrefEditor = sharedPref.edit();
        MBZ_Token_Prefs.setTokenEncryptedValues(MBZ_Token_Prefs.com_example_aalizade_mbazar_base_app_ACCESS_TOKEN, accessToken);
        MBZ_Token_Prefs.setTokenEncryptedValues(MBZ_Token_Prefs.com_example_aalizade_mbazar_base_app_REFRESH_TOKEN, refreshToken);//todo uses new encrypted setString
        MBZ_Token_Prefs.setTokenValues(MBZ_Token_Prefs.com_example_aalizade_mbazar_base_app_EXPIRES_IN, expiresIn);//"100" bood //todo uses new encrypted setString
        MBZ_Token_Prefs.setTokenValues(MBZ_Token_Prefs.com_example_aalizade_mbazar_base_app_TOKEN_TYPE, tokenType);//todo uses new encrypted setString
        MBZ_Token_Prefs.setTokenValues(MBZ_Token_Prefs.com_example_aalizade_mbazar_base_app_RECEIVE_TIME, String.valueOf(System.currentTimeMillis()));//todo uses new encrypted setString
    }

    public void InitialAPI_RequestService(){
        loginApiInterface = RetrofitClient.getclient(motherLayout).create(LoginAPIInterface.class);
        ouathedLoginApiInterface = RetrofitOAuthClient.getOauthClient(getContext(), motherLayout).create(LoginAPIInterface.class);
        cartAPIInterface = RetrofitOAuthClient.getOauthClient(getContext(), motherLayout).create(CartAPIInterface.class);
    }

}

