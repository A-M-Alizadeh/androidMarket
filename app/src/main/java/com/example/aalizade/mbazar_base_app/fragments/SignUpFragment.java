package com.example.aalizade.mbazar_base_app.fragments;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.utility.EmailOrMobileChecker;
import com.example.aalizade.mbazar_base_app.utility.ProgressBarShower;
import com.example.aalizade.mbazar_base_app.network.apiInterface.LoginAPIInterface;
import com.example.aalizade.mbazar_base_app.network.apiInterface.UserRetrofitAPIInterface;
import com.example.aalizade.mbazar_base_app.network.models.customer.CustomerFrontModelCreate;
import com.example.aalizade.mbazar_base_app.network.models.general.ConfirmationCodeFrontModel;
import com.example.aalizade.mbazar_base_app.network.models.user.FindUserModel;
import com.example.aalizade.mbazar_base_app.network.models.user.FullUserFrontModel;
import com.example.aalizade.mbazar_base_app.network.retrofit.CallbackWithRetry;
import com.example.aalizade.mbazar_base_app.network.retrofit.RetrofitClient;
import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by gray on 10/16/17.
 */

public class SignUpFragment extends Fragment {
    Boolean isMobile;
    public static Boolean aferVerificationCode = false;
    TextView rulesTxtview, allRulesTxtv;
    boolean isTextViewClicked, countdownIsFinished = true;
    Button finalVerifyBtn, signupBtn, sendCodeAgainBtn;
    TextInputLayout verificationCodeTietLayout, signInNameTietLayout, signInLastNameTietLayout, emailOrNumberTietLayout,
            passTietLayout, repeatPassTietLayout;

    TextInputEditText verificationCodeTiet, signInNameTiet, signInLastNameTiet, emailOrNumberTiet, passTiet, repeatPassTiet;

    CheckBox isReadRulesChckBx;
    RadioGroup genderRadioGrp, ReceiveSmsRadioGrp;
    RadioButton maleRadioBtn, femaleRadioBtn, yesSmsRadioBtn, noSmsRadioBtn;
    public static LinearLayout gonnaReceiveVerifyCodeLayout, firstPartLayout;
    LinearLayout progressWrapper;
    LoginAPIInterface retroClientApiInterface;
    UserRetrofitAPIInterface userRetrofitAPIInterface;

//    ScrollView outerScrollView,innerScrollView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_signup, container, false);

        initialFindViews(view);
        InitialAPI_RequestService();

        rulesTxtview.setMovementMethod(new ScrollingMovementMethod());

        isTextViewClicked = false;
        rulesTxtview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTextViewClicked) {
                    //This will shrink textview to 2 lines if it is expanded.
                    rulesTxtview.setMaxLines(4);
                    isTextViewClicked = false;
                } else {
                    //This will expand the textview if it is of 2 lines
                    rulesTxtview.setMaxLines(Integer.MAX_VALUE);
                    isTextViewClicked = true;
                }
            }
        });
        allRulesTxtv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTextViewClicked) {
                    //This will shrink textview to 2 lines if it is expanded.
                    rulesTxtview.setMaxLines(4);
                    isTextViewClicked = false;
                } else {
                    //This will expand the textview if it is of 2 lines
                    rulesTxtview.setMaxLines(Integer.MAX_VALUE);
                    isTextViewClicked = true;
                }
            }
        });

        //btn clicks
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (formIsFull(signInNameTiet, signInLastNameTiet, emailOrNumberTiet, passTiet, repeatPassTiet)) {
//                if(true){
                    ifUserExists(userRetrofitAPIInterface, view);
                }

            }
        });
        //btn clicks

        //check if rule is read
        isReadRulesChckBx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                signupBtn.setEnabled(isChecked);
            }
        });

        //ooppss
        //check if rule is read

        finalVerifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aferVerificationCode) {
                    if (verificationCodeTiet.getText().toString().length() == 4) {
                        mbazarUserCreate(retroClientApiInterface, view);
                    } else {
                        verificationCodeTiet.setError("کد تایید نا معتبر است");
                    }
                }
            }
        });

        //count down
        final CountDownTimer countDownTimer = new CountDownTimer(300000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String hms = String.format("%02d:%02d",
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
//                ((TextView) view.findViewById(R.id.countDownTv_id)).setText(hms);
                sendCodeAgainBtn.setText(hms);

            }

            @Override
            public void onFinish() {
                sendCodeAgainBtn.setEnabled(true);
                countdownIsFinished = true;
                sendCodeAgainBtn.setText("ارسال مجدد کد");
            }
        };
        //count down

        //send code again
        sendCodeAgainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (formIsFull(signInNameTiet, signInLastNameTiet, emailOrNumberTiet, passTiet, repeatPassTiet)) {
                    ifUserExists(userRetrofitAPIInterface, view);
                    if (countdownIsFinished) {
                        countDownTimer.start();
                        sendCodeAgainBtn.setEnabled(false);
                        countdownIsFinished = false;
                    }
                }
            }
        });
        //send code again

        return view;
    }

    public Boolean formIsFull(TextInputEditText signInNameTiet, TextInputEditText signInLastNameTiet, TextInputEditText emailOrNumberTiet,
                              TextInputEditText passTiet, TextInputEditText repeatPassTiet) {
        Boolean isComplete = true;
        String signInNameTietStr, signInLastNameTietStr, emailOrNumberTietStr, passTietStr, repeatPassTietStr;
        signInNameTietStr = signInNameTiet.getText().toString().trim();
        signInLastNameTietStr = signInLastNameTiet.getText().toString().trim();
        emailOrNumberTietStr = emailOrNumberTiet.getText().toString().trim();
        passTietStr = passTiet.getText().toString().trim();
        repeatPassTietStr = repeatPassTiet.getText().toString().trim();

        if (signInNameTietStr.matches("")) {
            isComplete = false;
            signInNameTiet.setError("این فیلد را پر کنید");
        }
        if (signInLastNameTietStr.matches("")) {
            isComplete = false;
            signInLastNameTiet.setError("این فیلد را پر کنید");
        }
        if (emailOrNumberTietStr.matches("")) {
            isComplete = false;
            emailOrNumberTiet.setError("این فیلد را پر کنید");
        }
        if (passTietStr.matches("")) {
            isComplete = false;
            passTiet.setError("این فیلد را پر کنید");
        }
        if (repeatPassTietStr.matches("")) {
            isComplete = false;
            repeatPassTiet.setError("این فیلد را پر کنید");
        }
        if (EmailOrMobileChecker.isEmailOrMobile(emailOrNumberTietStr) == 0) {
            isComplete = false;
            emailOrNumberTiet.setError("نام کاربری باید ایمیل یا شماره تلفن باشد");
        }
        if (EmailOrMobileChecker.isEmailOrMobile(emailOrNumberTietStr) != 0 && (!passTietStr.isEmpty()) && passTietStr.length() < 8) {
            isComplete = false;
            passTiet.setError("رمز عبور باید حداقل 8 کاراکتر باشد");
        }
        if (!repeatPassTietStr.matches(passTietStr)) {
            isComplete = false;
            repeatPassTiet.setError("با رمز عبور همخوانی ندارد");
        }
        return isComplete;
    }

    public void ifUserExists(final UserRetrofitAPIInterface userRetrofitAPIInterface, final View view) {
        FindUserModel findUserModel = new FindUserModel();
        findUserModel.setParam(emailOrNumberTiet.getText().toString().trim());
        findUserModel.setUpDiscriminator("");

        Call<FullUserFrontModel> call = userRetrofitAPIInterface.findUserInfoByUsername(findUserModel);
        ProgressBarShower.StartMyProgressBar(getActivity(), progressWrapper);
        call.enqueue(new CallbackWithRetry<FullUserFrontModel>(call, getActivity(), progressWrapper) {
            @Override
            public void onResponse(Call<FullUserFrontModel> call, Response<FullUserFrontModel> response) {
                ProgressBarShower.StopMyProgressBar(getActivity(), progressWrapper);
                if (response.isSuccessful()) {
                    emailOrNumberTiet.setError("نام کاربری قبلا ثبت شده است");
                } else if (response.code() == 400) {
                    Log.d("Hashtag", "Here1");
                    finalVerifyBtn.setEnabled(true);
                    verificationCodeTietLayout.setEnabled(true);
                    verificationCodeTiet.setEnabled(true);
                    sendVerifyModel(retroClientApiInterface, view);
                }
            }

        });
    }

    public void sendVerifyModel(LoginAPIInterface apiInterface, final View view) {
        String emailormobileStr = null;
        switch (EmailOrMobileChecker.isEmailOrMobile(emailOrNumberTiet.getText().toString())) {
            case 1:
                emailormobileStr = "email";
                isMobile = false;
                break;
            case 2:
                emailormobileStr = "mobile";
                isMobile = true;
                break;
        }
        ConfirmationCodeFrontModel sendVerifyMessageModel = new ConfirmationCodeFrontModel(emailormobileStr, emailOrNumberTiet.getText().toString().trim(), "signup");
        Log.d("RESP SignUp USER", new Gson().toJson(sendVerifyMessageModel));
        Call<ResponseBody> call = apiInterface.sendVerifyMessage(sendVerifyMessageModel);
        ProgressBarShower.StartMyProgressBar(getActivity(), progressWrapper);
        call.enqueue(new CallbackWithRetry<ResponseBody>(call, getActivity(), progressWrapper) {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ProgressBarShower.StopMyProgressBar(getActivity(), progressWrapper);
                if (response.code() == 200) {
                    Toast.makeText(view.getContext(), "کد دوعاملی بازار اعضا برای شما ارسال شد", Toast.LENGTH_SHORT).show();

                    aferVerificationCode = true;

                    firstPartLayout.setVisibility(View.GONE);
                    gonnaReceiveVerifyCodeLayout.setVisibility(View.VISIBLE);//new
                    //animate
                    Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
                    gonnaReceiveVerifyCodeLayout.startAnimation(animation);
                    Animation animation2 = AnimationUtils.loadAnimation(getContext(), R.anim.fade_out);
                    firstPartLayout.startAnimation(animation2);
                    //animate
                } else {
                    Toast.makeText(view.getContext(), "شماره شما برای مدتی مسدود شده است", Toast.LENGTH_SHORT).show();
                    Log.d("RESP", String.valueOf(response.body()));
                }
            }

        });
    }

    public void mbazarUserCreate(LoginAPIInterface apiInterface, final View view) {
        String gender = "";
        Boolean newsLetter = true;
        String user_defaultUserContact_mobileNo = "";
        String user_defaultUserContact_emailAddress = "";
        Boolean agreement = false;
        if (isReadRulesChckBx.isChecked())
            agreement = true;

        if (maleRadioBtn.isChecked())
            gender = "MALE";
        else gender = "FEMALE";

        if (yesSmsRadioBtn.isChecked())
            newsLetter = true;
        else newsLetter = false;
        if (isMobile) {
            user_defaultUserContact_mobileNo = emailOrNumberTiet.getText().toString();
        } else {
            user_defaultUserContact_emailAddress = emailOrNumberTiet.getText().toString();
        }

        CustomerFrontModelCreate MbazarUserCreateModel = new CustomerFrontModelCreate(
                signInNameTiet.getText().toString(),
                signInLastNameTiet.getText().toString(),
                passTiet.getText().toString(),
                agreement,
                emailOrNumberTiet.getText().toString(),
                gender,
                newsLetter,
                verificationCodeTiet.getText().toString(),
                user_defaultUserContact_mobileNo,
                user_defaultUserContact_emailAddress);

        Gson gsonObj = new Gson();
        // converts object to json string
        String jsonStr = gsonObj.toJson(MbazarUserCreateModel);
        System.out.println(jsonStr);
        Log.d("Create--->", jsonStr);

        Call<ResponseBody> call = apiInterface.mbazarUserCreateRequest(MbazarUserCreateModel);
        // show it
        ProgressBarShower.StartMyProgressBar(getActivity(), progressWrapper);
        call.enqueue(new CallbackWithRetry<ResponseBody>(call, getActivity(), progressWrapper) {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ProgressBarShower.StopMyProgressBar(getActivity(), progressWrapper);
                if (response.isSuccessful()) {
                    Toast.makeText(view.getContext(), "ثبت نام شما با موفقیت انجام شد", Toast.LENGTH_SHORT).show();
                    getActivity().finish();
                } else {
                    Toast.makeText(view.getContext(), "ارتباط با خطا مواجه شد", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    public void initialFindViews(final View view){
        progressWrapper = firstPartLayout = view.findViewById(R.id.progress_wrapper_id);

        verificationCodeTietLayout = view.findViewById(R.id.signin_name_tiet_layout_id);
        signInNameTietLayout = view.findViewById(R.id.signin_lastname_tiet_layout_id);
        signInLastNameTietLayout = view.findViewById(R.id.signin_username_tiet_layout_id);
        emailOrNumberTietLayout = view.findViewById(R.id.signin_pass_tiet_layout_id);
        passTietLayout = view.findViewById(R.id.signin_repeatpass_tiet_layout_id);
        repeatPassTietLayout = view.findViewById(R.id.signin_verificationcode_tiet_layout_id);

        signInNameTiet = view.findViewById(R.id.signin_tietname_id);
        signInLastNameTiet = view.findViewById(R.id.signin_tietlastname_id);
        emailOrNumberTiet = view.findViewById(R.id.signin_tietusername_id);
        passTiet = view.findViewById(R.id.signin_tietpassword_id);
        repeatPassTiet = view.findViewById(R.id.signin_tietrepeatpass_id);
        verificationCodeTiet = view.findViewById(R.id.signin_verificationcodeTiet_id);

        finalVerifyBtn = view.findViewById(R.id.final_signup_verify_btn_id);
        sendCodeAgainBtn = view.findViewById(R.id.signup_sendCodeAgain_btn_id);
        signupBtn = view.findViewById(R.id.sign_up__btn_id);
        rulesTxtview = view.findViewById(R.id.rules_txt_id);
        allRulesTxtv = view.findViewById(R.id.all_rules_Tv_id);
        isReadRulesChckBx = view.findViewById(R.id.read_rules_checkbox_id);
        gonnaReceiveVerifyCodeLayout = view.findViewById(R.id.gonna_receive_verify_code_layout_id);
        firstPartLayout = view.findViewById(R.id.firsPartLayout_id);

        genderRadioGrp = view.findViewById(R.id.gender_radio_group_id);
        ReceiveSmsRadioGrp = view.findViewById(R.id.receive_sms_radio_group_id);

        maleRadioBtn = view.findViewById(R.id.male_gender_radio_btn_id);
        femaleRadioBtn = view.findViewById(R.id.female_gender_radio_btn_id);
        yesSmsRadioBtn = view.findViewById(R.id.receive_sms_radio_btn_id);
        noSmsRadioBtn = view.findViewById(R.id.not_receive_sms_radio_btn_id);


    }

    public void InitialAPI_RequestService(){
        retroClientApiInterface = RetrofitClient.getclient(progressWrapper).create(LoginAPIInterface.class);
        userRetrofitAPIInterface = RetrofitClient.getclient(progressWrapper).create(UserRetrofitAPIInterface.class);
    }

}
