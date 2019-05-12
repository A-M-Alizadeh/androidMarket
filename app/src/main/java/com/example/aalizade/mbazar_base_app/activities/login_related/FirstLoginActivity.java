package com.example.aalizade.mbazar_base_app.activities.login_related;

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
import android.widget.Toast;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.utility.NationalCodeValidator;
import com.example.aalizade.mbazar_base_app.utility.ProgressBarShower;
import com.example.aalizade.mbazar_base_app.network.RequestServices.LogOutRequestService;
import com.example.aalizade.mbazar_base_app.network.apiInterface.LoginAPIInterface;
import com.example.aalizade.mbazar_base_app.network.models.general.PrimitiveResponse;
import com.example.aalizade.mbazar_base_app.network.models.user.ChangeFirstPasswordModel;
import com.example.aalizade.mbazar_base_app.network.retrofit.CallbackWithRetry;
import com.example.aalizade.mbazar_base_app.network.retrofit.RetrofitOAuthClient;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class FirstLoginActivity extends AppCompatActivity {
    TextInputLayout currentPassTietLayout, newPassTietLayout, newpassrepeatPassTietLayout, nationalCodePassTietLayout;
    TextInputEditText currentPassTiet, newPassTiet, newpassrepeatPassTiet, nationalCodePassTiet;
    RadioButton activemessagesRadioBtn, notactivemessagesRadioBtn;
    Button submitBtn, cancelBtn;
    LinearLayout progressWrapper, motherLayout;
    String currentPass;
    Boolean sendSms = true;

    NationalCodeValidator nationalCodeValidator;
    LoginAPIInterface loginAPIInterface;
    //request service
    LogOutRequestService logOutRequestService;
    //request service

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_login);

        currentPass = getIntent().getStringExtra("currentpass");
        initialFindViews();
        InitialAPI_RequestService();

        //fonts
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/B_Yekan.ttf");
        currentPassTietLayout.setTypeface(custom_font);
        newPassTietLayout.setTypeface(custom_font);
        newpassrepeatPassTietLayout.setTypeface(custom_font);
        nationalCodePassTietLayout.setTypeface(custom_font);
        //fonts

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), String.valueOf(verifyfields()), Toast.LENGTH_SHORT).show();
                if (verifyfields()) {
                    ChangeFirstPasswordModel changeFirstPasswordModel = new ChangeFirstPasswordModel();
                    changeFirstPasswordModel.setOldPassword(currentPassTiet.getText().toString());
                    changeFirstPasswordModel.setNewPassword(newPassTiet.getText().toString());
                    changeFirstPasswordModel.setRePassword(newpassrepeatPassTiet.getText().toString());
                    changeFirstPasswordModel.setNationalCode(nationalCodePassTiet.getText().toString());
                    changeFirstPasswordModel.setEnableServicesMessage(sendSms);
                    changeFirstPassword(changeFirstPasswordModel);
                }
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public Boolean verifyfields() {
        Boolean notEmpty = true;
        if (currentPassTiet.getText().toString().isEmpty()) {
            currentPassTiet.setError("این فیلد باید پر شود.");
            notEmpty = false;
        }
        if (newPassTiet.getText().toString().isEmpty()) {
            newPassTiet.setError("این فیلد باید پر شود.");
            notEmpty = false;
        }
        if (newpassrepeatPassTiet.getText().toString().isEmpty()) {
            newpassrepeatPassTiet.setError("این فیلد باید پر شود.");
            notEmpty = false;
        }
        if (nationalCodePassTiet.getText().toString().isEmpty()) {
            nationalCodePassTiet.setError("این فیلد باید پر شود.");
            notEmpty = false;
        }
        if (!nationalCodeValidator.validate(nationalCodePassTiet.getText().toString())) {
            nationalCodePassTiet.setError("کد ملی نامعتبر است.");
            notEmpty = false;
        }
        if (!currentPassTiet.getText().toString().isEmpty() && !currentPassTiet.getText().toString().matches(currentPass)) {
            currentPassTiet.setError("رمز عبور وارد شده اشتباه است.");
            notEmpty = false;
        }
        if (!newpassrepeatPassTiet.getText().toString().isEmpty() && !newpassrepeatPassTiet.getText().toString().matches(newPassTiet.getText().toString())) {
            newpassrepeatPassTiet.setError("رمز عبور ها یکسان نیست.");
            notEmpty = false;
        }
        if (activemessagesRadioBtn.isChecked())
            sendSms = true;
        else sendSms = false;

        return notEmpty;
    }

    public void changeFirstPassword(ChangeFirstPasswordModel changeFirstPasswordModel) {
        Call<PrimitiveResponse> call = loginAPIInterface.changeFirstLoginPasswordRequest(changeFirstPasswordModel);
        ProgressBarShower.StartMyProgressBar(FirstLoginActivity.this, progressWrapper);
        call.enqueue(new CallbackWithRetry<PrimitiveResponse>(call, this, progressWrapper) {
            @Override
            public void onResponse(Call<PrimitiveResponse> call, Response<PrimitiveResponse> response) {
                ProgressBarShower.StopMyProgressBar(FirstLoginActivity.this, progressWrapper);
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "رمز عبور با موفقیت تغییر کرد", Toast.LENGTH_LONG).show();
                    logOutRequestService.LogOutRequest(loginAPIInterface);
                } else {
                    try {
                        Log.d("MYERROR", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(getApplicationContext(), "اطلاعات ارسالی اشتباه میباشد", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

//    public boolean validateMelliCode(String melliCode) {
//        String[] identicalDigits = {"0000000000", "1111111111", "2222222222", "3333333333", "4444444444", "5555555555", "6666666666", "7777777777", "8888888888", "9999999999"};
//        if (melliCode.trim().isEmpty()) {
//            nationalCodePassTiet.setError("فیلد کد ملی خالی است");
//            return false; // Melli Code is empty
//        } else if (melliCode.length() != 10) {
//            nationalCodePassTiet.setError("کد ملی باید دقیقا 10 کاراکتر باشد");
//            return false; // Melli Code is less or more than 10 digits
//        } else if (Arrays.asList(identicalDigits).contains(melliCode)) {
//            nationalCodePassTiet.setError("کد ملی نامعتبر است");
//            return false; // Fake Melli Code
//        } else {
//            int sum = 0;
//            for (int i = 0; i < 9; i++) {
//                sum += Character.getNumericValue(melliCode.charAt(i)) * (10 - i);
//            }
//            int lastDigit;
//            int divideRemaining = sum % 11;
//            if (divideRemaining < 2) {
//                lastDigit = divideRemaining;
//            } else {
//                lastDigit = 11 - (divideRemaining);
//            }
//            if (Character.getNumericValue(melliCode.charAt(9)) == lastDigit) {
//                return true;
//            } else {
//                nationalCodePassTiet.setError("کد ملی نامعتبر است");
//                return false; // Invalid MelliCode
//            }
//        }
//    }

    public void InitialAPI_RequestService() {
        loginAPIInterface = RetrofitOAuthClient.getOauthClient(getApplicationContext(), motherLayout).create(LoginAPIInterface.class);

        logOutRequestService = new LogOutRequestService(this, progressWrapper);
    }
    public void initialFindViews(){
        progressWrapper = (LinearLayout) findViewById(R.id.progress_wrapper_id);
        motherLayout = (LinearLayout) findViewById(R.id.first_login_mother_layout_id);
        currentPassTietLayout = (TextInputLayout) findViewById(R.id.current_pass_tiet_layout_id);
        newPassTietLayout = (TextInputLayout) findViewById(R.id.new_pass_tiet_layout);
        newpassrepeatPassTietLayout = (TextInputLayout) findViewById(R.id.new_pass_repeat_tiet_layout);
        nationalCodePassTietLayout = (TextInputLayout) findViewById(R.id.nationalCode_tiet_layout);
        currentPassTiet = (TextInputEditText) findViewById(R.id.current_tietpassword_id);
        newPassTiet = (TextInputEditText) findViewById(R.id.new_tietpassword_id);
        newpassrepeatPassTiet = (TextInputEditText) findViewById(R.id.new_pass_repeat_tiet_id);
        nationalCodePassTiet = (TextInputEditText) findViewById(R.id.nationalCode_tiet_id);
        activemessagesRadioBtn = (RadioButton) findViewById(R.id.receive_sms_radio_btn_id);
        notactivemessagesRadioBtn = (RadioButton) findViewById(R.id.not_receive_sms_radio_btn_id);
        submitBtn = (Button) findViewById(R.id.first_login_pass_change_btn_id);
        cancelBtn = (Button) findViewById(R.id.first_login_cancel_btn_id);
        nationalCodeValidator = new NationalCodeValidator();
    }

}
