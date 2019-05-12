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
import android.widget.Toast;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.utility.ProgressBarShower;
import com.example.aalizade.mbazar_base_app.network.MBZ_Token_Prefs;
import com.example.aalizade.mbazar_base_app.network.apiInterface.UserRetrofitAPIInterface;
import com.example.aalizade.mbazar_base_app.network.models.user.UserChangePasswordFrontModel;
import com.example.aalizade.mbazar_base_app.network.retrofit.CallbackWithRetry;
import com.example.aalizade.mbazar_base_app.network.retrofit.RetrofitOAuthClient;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class PassChangingActivity extends AppCompatActivity {

    TextInputLayout current_pass_tiet_layout, new_pass_tiet_layout, new_pass_repeat_tiet_layout;
    TextInputEditText currentPassTiet, newPassTiet, repeatNewPassTiet;
    Button cancelBtn, submitBtn;

    LinearLayout passChaningMotherLayout, progressWrapper;
    UserRetrofitAPIInterface userRetrofitAPIInterface;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_changing);
        MBZ_Token_Prefs.initTokenSharedPrefs(getApplicationContext());
        initialFindViews();
        InitialAPI_RequestService();
        //fonts
        Typeface custom_font = Typeface.createFromAsset(this.getAssets(), "fonts/B_Yekan.ttf");
        current_pass_tiet_layout.setTypeface(custom_font);
        new_pass_tiet_layout.setTypeface(custom_font);
        new_pass_repeat_tiet_layout.setTypeface(custom_font);
        //fonts

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (verifyfields()) {
                    UserChangePasswordFrontModel userChangePasswordFrontModel = new UserChangePasswordFrontModel();
//                    userChangePasswordFrontModel.setId(MBZ_Token_Prefs.getString(MBZ_Token_Prefs.com_example_aalizade_mbazar_base_app_USER_ID));
                    userChangePasswordFrontModel.setPassword(currentPassTiet.getText().toString());
                    userChangePasswordFrontModel.setNewPassword(newPassTiet.getText().toString());
                    userChangePasswordFrontModel.setRenewPassword(repeatNewPassTiet.getText().toString());
                    changeUserPassword(userRetrofitAPIInterface, userChangePasswordFrontModel);
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
        this.finish();
    }

    public Boolean verifyfields() {
        Boolean notEmpty = true;
        if (currentPassTiet.getText().toString().isEmpty()) {
            currentPassTiet.setError("این فیلد باید پر شود");
            notEmpty = false;
        }
        if (newPassTiet.getText().toString().isEmpty()) {
            newPassTiet.setError("این فیلد باید پر شود");
            notEmpty = false;
        }
        if (repeatNewPassTiet.getText().toString().isEmpty()) {
            repeatNewPassTiet.setError("این فیلد باید پر شود");
            notEmpty = false;
        }
        if (!repeatNewPassTiet.getText().toString().isEmpty() && !repeatNewPassTiet.getText().toString().matches(newPassTiet.getText().toString())) {
            repeatNewPassTiet.setError("رمز عبور ها یکسان نیست");
            notEmpty = false;
        }

        return notEmpty;
    }

    public void changeUserPassword(UserRetrofitAPIInterface loginApiInterface, UserChangePasswordFrontModel userChangePasswordFrontModel) {
        Call<ResponseBody> call = loginApiInterface.changePassword(userChangePasswordFrontModel);
        ProgressBarShower.StartMyProgressBar(PassChangingActivity.this, progressWrapper);
        Log.d("change pass log", userChangePasswordFrontModel.toString());
        call.enqueue(new CallbackWithRetry<ResponseBody>(call, PassChangingActivity.this, progressWrapper) {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ProgressBarShower.StopMyProgressBar(PassChangingActivity.this, progressWrapper);
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "رمز عبور با موفقیت تغییر کرد", Toast.LENGTH_LONG).show();
                    finish();
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

    public void initialFindViews() {
        passChaningMotherLayout = (LinearLayout) findViewById(R.id.pass_changing_mother_layout_id);
        progressWrapper = (LinearLayout) findViewById(R.id.progress_wrapper_id);
        current_pass_tiet_layout = (TextInputLayout) findViewById(R.id.current_pass_tiet_layout_id);
        new_pass_tiet_layout = (TextInputLayout) findViewById(R.id.new_pass_tiet_layout_id);
        new_pass_repeat_tiet_layout = (TextInputLayout) findViewById(R.id.new_pass_repeat_tiet_layout_id);
        currentPassTiet = (TextInputEditText) findViewById(R.id.current_tietpassword_id);
        newPassTiet = (TextInputEditText) findViewById(R.id.new_tietpassword_id);
        repeatNewPassTiet = (TextInputEditText) findViewById(R.id.new_pass_repeat_tiet_id);
        cancelBtn = (Button) findViewById(R.id.cancel_btn_id);
        submitBtn = (Button) findViewById(R.id.pass_change_submit_btn_id);
    }

    public void InitialAPI_RequestService() {
        userRetrofitAPIInterface = RetrofitOAuthClient.getOauthClient(getApplicationContext(), passChaningMotherLayout).create(UserRetrofitAPIInterface.class);
    }
}
