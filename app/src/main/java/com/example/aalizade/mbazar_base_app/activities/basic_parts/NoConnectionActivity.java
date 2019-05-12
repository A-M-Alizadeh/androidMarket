package com.example.aalizade.mbazar_base_app.activities.basic_parts;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.utility.GoOffline;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class NoConnectionActivity extends AppCompatActivity { //کلاس برای بررسی ارتباط با اینترنت
    protected void attachBaseContext(Context newBase) {       //درصورت عدم دسترسی به اکتیویتی منتقل میشویم
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    Button goOnlineBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_connection);

        goOnlineBtn = (Button)findViewById(R.id.go_online_btn_id) ;
        goOnlineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                CallbackWithRetry.firstTime = true;
                GoOffline.GoOnline(getApplicationContext(),NoConnectionActivity.this);          //بازگشت به اکتیویتی قبلی درصورت دسترسی به اینترنت
            }
        });


    }
}
