package com.example.aalizade.mbazar_base_app.activities.gift;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.network.apiInterface.CreditAPIInterface;
import com.example.aalizade.mbazar_base_app.network.models.credit.CreditCheckRequestModel;
import com.example.aalizade.mbazar_base_app.network.models.credit.CreditCheckResponseModel;
import com.example.aalizade.mbazar_base_app.network.models.credit.CreditSubmitRequestModel;
import com.example.aalizade.mbazar_base_app.network.retrofit.CallbackWithRetry;
import com.example.aalizade.mbazar_base_app.network.retrofit.RetrofitOAuthClient;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by sbayatani on 4/8/2018.
 */

public class AttachBonActivity extends AppCompatActivity {
    TextView productGroupId,productGroupTitle,remainAmount,expireDate;
    Button check,submit,reject;
    CreditAPIInterface creditAPI;
    LinearLayout progressWrapper;
    EditText bonCode;
    String code;


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attach_bon);

        progressWrapper = (LinearLayout)findViewById(R.id.progress_wrapper_id);
        check = (Button)findViewById(R.id.btn_check_credit);
        creditAPI = RetrofitOAuthClient.getOauthClient(getApplicationContext(),findViewById(R.id.masterPage_mother_drawer_layout)).create(CreditAPIInterface.class);
        bonCode = (EditText)findViewById(R.id.edt_bon_code);
        productGroupId = (TextView) findViewById(R.id.txt_product_group_id);
        productGroupTitle = (TextView) findViewById(R.id.txt_product_group_title);
        remainAmount = (TextView) findViewById(R.id.txt_remain_amount_user);
        expireDate = (TextView) findViewById(R.id.expire_date_credit_card);
        submit = (Button) findViewById(R.id.submit);
        reject = (Button) findViewById(R.id.reject);

         check.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 checkCredit(bonCode.getText().toString());
             }
         });

         submit.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 attachmentCredit(code);
             }
         });

         reject.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 finish();
             }
         });


    }

    public void checkCredit(String code){
        CreditCheckRequestModel checkModel = new CreditCheckRequestModel(code);
        Call<CreditCheckResponseModel> call = creditAPI.checkCredit(checkModel);
        call.enqueue(new CallbackWithRetry<CreditCheckResponseModel>(call,this,progressWrapper) {
            @Override
            public void onResponse(Call<CreditCheckResponseModel> call, Response<CreditCheckResponseModel> response) {
                if(response.isSuccessful()){
                    fillForm(response.body());
                }else {
                    Toast.makeText(getApplicationContext(),"کد معتبر نیست",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void attachmentCredit(String id){
            CreditSubmitRequestModel submitModel = new CreditSubmitRequestModel(id);
            Call<ResponseBody> call = creditAPI.submitAttacmentCredit(submitModel);
            call.enqueue(new CallbackWithRetry<ResponseBody>(call,this,progressWrapper) {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if(response.isSuccessful()){
                        Toast.makeText(getApplicationContext(),"الصاق شد",Toast.LENGTH_SHORT).show();
                        finish();
                    }else {
                        Toast.makeText(getApplicationContext(),"انجام نشد",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    public void fillForm(CreditCheckResponseModel checkResponseModel){
        productGroupId.setText(checkResponseModel.getProductTypeGroup_id());
        productGroupTitle.setText(checkResponseModel.getProductTypeGroup_title());
        remainAmount.setText(checkResponseModel.getRemainAmount());
        expireDate.setText(checkResponseModel.getUserGiftRequest_expireDate().toString());
        code = checkResponseModel.getId();
    }
}
