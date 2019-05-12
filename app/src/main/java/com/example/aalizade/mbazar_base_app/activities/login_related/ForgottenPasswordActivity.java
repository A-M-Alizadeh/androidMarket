package com.example.aalizade.mbazar_base_app.activities.login_related;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.utility.ProgressBarShower;
import com.example.aalizade.mbazar_base_app.network.apiInterface.GeneralRetrofitAPIInterface;
import com.example.aalizade.mbazar_base_app.network.apiInterface.UserRetrofitAPIInterface;
import com.example.aalizade.mbazar_base_app.network.models.general.ConfirmationCodeFrontModel;
import com.example.aalizade.mbazar_base_app.network.models.general.ConfirmationCodeModel;
import com.example.aalizade.mbazar_base_app.network.models.general.PrimitiveResponse;
import com.example.aalizade.mbazar_base_app.network.models.user.UserNotificationModel;
import com.example.aalizade.mbazar_base_app.network.retrofit.CallbackWithRetry;
import com.example.aalizade.mbazar_base_app.network.retrofit.RetrofitClient;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ForgottenPasswordActivity extends AppCompatActivity {
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    Button cancelBtn, probeUsernameBtn, getVerifyCodeBtn, changePassBtn;
    TextInputEditText usernameTiet, verificationTiet;
    TextInputLayout usernameTietLayout, verificationTietLayout;
    UserNotificationModel generalUserNotificationModel;
    RadioButton sendToMobileRadioBtn, sendToEmailRadioBtn;
    RadioGroup sendOptionRadioGroup;
    LinearLayout verificationLayout,progressWrapper,motherLayout;
    String usernameStr;

    //interfaces
    UserRetrofitAPIInterface userRetrofitAPIInterface;
    GeneralRetrofitAPIInterface generalRetrofitAPIInterface;
    //interfaces

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotten_password);
        initialFindViews();
        InitialAPI_RequestService();

        //fonts
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/B_Yekan.ttf");
        usernameTietLayout.setTypeface(custom_font);
        verificationTietLayout.setTypeface(custom_font);
        //fonts

        probeUsernameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendOptionRadioGroup.clearCheck();
                if (changePassBtn.isEnabled()) {
                    changePassBtn.setEnabled(false);
                    verificationTiet.setEnabled(false);
                }
                sendToVerifyUsername(userRetrofitAPIInterface);
            }
        });

        getVerifyCodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((sendToMobileRadioBtn.isChecked() || sendToEmailRadioBtn.isChecked())) {
                    sendVerificationCode(generalRetrofitAPIInterface);
                } else {
                    Toast.makeText(getApplicationContext(), "یکی از روشهای دریافت کد را انتخاب کنید", Toast.LENGTH_LONG).show();
                }
            }
        });

        changePassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (verificationTiet.getText().toString().length() == 4) {
                    resetPassword(generalRetrofitAPIInterface);
                }
            }
        });

        //cancek BTN
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //cancek BTN

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void sendToVerifyUsername(UserRetrofitAPIInterface userRetrofitAPIInterface) {       //آیا کاربری با این نام کاربری قبلا ثبت نام کرده است یا خیر
        HashMap<String, Object> usernames = new HashMap<>();                                    // درصورت وجود داشتن ، سایر آیتم ها نشان داده میشوند
        usernames.put("username", usernameTiet.getText().toString().trim());
        Log.d("forget password: ",new Gson().toJson(usernames));
        Call<UserNotificationModel> call = userRetrofitAPIInterface.sendUsernametoremeberPass(usernames);
        ProgressBarShower.StartMyProgressBar(ForgottenPasswordActivity.this,progressWrapper);
        call.enqueue(new CallbackWithRetry<UserNotificationModel>(call,ForgottenPasswordActivity.this,progressWrapper) {
            @Override
            public void onResponse(Call<UserNotificationModel> call, Response<UserNotificationModel> response) {
                ProgressBarShower.StopMyProgressBar(ForgottenPasswordActivity.this,progressWrapper);
                if (response.isSuccessful()) {
                    usernameStr = usernameTiet.getText().toString().trim();
                    Toast.makeText(getApplicationContext(), "نام کاربری موجود است", Toast.LENGTH_LONG).show();
                    generalUserNotificationModel = response.body();

                    //enabling stuff
                    verificationLayout.setVisibility(View.VISIBLE);
                    getVerifyCodeBtn.setEnabled(true);
                    if (!generalUserNotificationModel.getMobile().matches("")) {
                        sendToMobileRadioBtn.setEnabled(true);
                        sendToMobileRadioBtn.setText(getResources().getString(R.string.mobile_persian, generalUserNotificationModel.getMobile().toString()));
                    } else {
                        sendToMobileRadioBtn.setEnabled(false);
                        sendToMobileRadioBtn.setText(getResources().getString(R.string.mobile_persian, ""));
                    }
                    if (!generalUserNotificationModel.getEmail().matches("")) {
                        sendToEmailRadioBtn.setEnabled(true);
                        sendToEmailRadioBtn.setText(getResources().getString(R.string.email_persian, generalUserNotificationModel.getEmail().toString()));
                    } else {
                        sendToEmailRadioBtn.setText(getResources().getString(R.string.email_persian, ""));
                        sendToEmailRadioBtn.setEnabled(false);
                    }
                    //enabling stuff
                } else {
                    try {
                        Log.d("FORGOTTEN PASSWORD-> ", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(getApplicationContext(), "چنین نام کاربری ثبت نشده است", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    public void sendVerificationCode(GeneralRetrofitAPIInterface generalRetrofitAPIInterface) {         //ارسال کد تایید به ایمیل یا شماره تلفن
        ConfirmationCodeFrontModel confirmationCodeFrontModel = new ConfirmationCodeFrontModel();       //فعال سازی فیلد تغییر پسورد
        confirmationCodeFrontModel.setUsername(usernameStr);
        confirmationCodeFrontModel.setType("resetPassword");
        if (sendToMobileRadioBtn.isChecked()) {
            confirmationCodeFrontModel.setSendBy("mobile");
        } else {
            confirmationCodeFrontModel.setSendBy("email");
        }
        Log.d("forget password verify",new Gson().toJson(confirmationCodeFrontModel));
        Call<PrimitiveResponse> call = generalRetrofitAPIInterface.sendforgetPasswordVerificationCode(confirmationCodeFrontModel);
        ProgressBarShower.StartMyProgressBar(ForgottenPasswordActivity.this,progressWrapper);
        call.enqueue(new CallbackWithRetry<PrimitiveResponse>(call,ForgottenPasswordActivity.this,progressWrapper) {
            @Override
            public void onResponse(Call<PrimitiveResponse> call, Response<PrimitiveResponse> response) {
                ProgressBarShower.StopMyProgressBar(ForgottenPasswordActivity.this,progressWrapper);
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "کد تایید برای شما ارسال شد", Toast.LENGTH_LONG).show();
                    verificationTietLayout.setEnabled(true);
                    changePassBtn.setEnabled(true);
                } else {
                    try {
                        Log.d("CORRECT", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(getApplicationContext(), "مشکل ارتباط با سرور", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    public void resetPassword(GeneralRetrofitAPIInterface generalRetrofitAPIInterface) {
        ConfirmationCodeModel confirmationCodeModel = new ConfirmationCodeModel();
        confirmationCodeModel.setUsername(usernameStr);
        confirmationCodeModel.setConfirmationCode(verificationTiet.getText().toString());
        Call<PrimitiveResponse> call = generalRetrofitAPIInterface.resetforgetPassword(confirmationCodeModel);
        ProgressBarShower.StartMyProgressBar(ForgottenPasswordActivity.this,progressWrapper);
        call.enqueue(new CallbackWithRetry<PrimitiveResponse>(call,ForgottenPasswordActivity.this,progressWrapper) {
            @Override
            public void onResponse(Call<PrimitiveResponse> call, Response<PrimitiveResponse> response) {
                ProgressBarShower.StopMyProgressBar(ForgottenPasswordActivity.this,progressWrapper);
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "رمز شما با موفقیت تغییر کرد", Toast.LENGTH_LONG).show();
                    verificationTietLayout.setEnabled(true);
                    changePassBtn.setEnabled(true);
                    finish();
                } else {
                    try {
                        Log.d("CORRECT", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(getApplicationContext(), "کد تایید اشتباه است", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    public void initialFindViews(){
        motherLayout = (LinearLayout)findViewById(R.id.forgotten_mother_layout_id);
        cancelBtn = (Button) findViewById(R.id.cancel_btn_id);
        probeUsernameBtn = (Button) findViewById(R.id.verify_forgotten_username_tiet_id);
        getVerifyCodeBtn = (Button) findViewById(R.id.send_verificationCode_tiet_id);
        changePassBtn = (Button) findViewById(R.id.pass_change_btn_id);

        usernameTiet = (TextInputEditText) findViewById(R.id.forgotten_username_tiet_id);
        verificationTiet = (TextInputEditText) findViewById(R.id.verify_forgotten_verifyCode_tiet_id);
        usernameTietLayout = (TextInputLayout) findViewById(R.id.forgotten_username_tiet_layout_id);
        verificationTietLayout = (TextInputLayout) findViewById(R.id.verify_forgotten_verifyCode_tiet_layout_id);

        sendToMobileRadioBtn = (RadioButton) findViewById(R.id.sendToMobileRadioBtn_id);
        sendToEmailRadioBtn = (RadioButton) findViewById(R.id.sendToEmailRadioBtn_id);
        sendOptionRadioGroup = (RadioGroup) findViewById(R.id.sendOptions_radio_group_id);

        verificationLayout = (LinearLayout) findViewById(R.id.verification_layout_id);
        progressWrapper = (LinearLayout) findViewById(R.id.progress_wrapper_id);

    }
    public void InitialAPI_RequestService(){
        userRetrofitAPIInterface = RetrofitClient.getclient(motherLayout).create(UserRetrofitAPIInterface.class);
        generalRetrofitAPIInterface = RetrofitClient.getclient(motherLayout).create(GeneralRetrofitAPIInterface.class);
    }

}
