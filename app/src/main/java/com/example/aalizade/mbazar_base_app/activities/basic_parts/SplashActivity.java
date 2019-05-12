package com.example.aalizade.mbazar_base_app.activities.basic_parts;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.network.MBZ_Token_Prefs;
import com.example.aalizade.mbazar_base_app.utility.GlobalVariables;

public class SplashActivity extends AppCompatActivity {

    LinearLayout motherLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        motherLayout = findViewById(R.id.splash_activity_mother_layout_id);
        MBZ_Token_Prefs.initTokenSharedPrefs(getApplicationContext());

        Thread t = new Thread() {
            @Override
            public void run() {                 //اجرای ترد و ارسال برادکست هر 1 دقیقه
                try {                        // زمان از بین رفتن ترد معلوم نیست - باید بررسی شود !!!
                    while (!isInterrupted()) {
                        Thread.sleep(60000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                sendBroadcast(new Intent("com.example.aalizade.mbazar_base_app.ACTION_ISAUTHORIZED"));
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        t.start();

        Handler handler = new Handler();        // انتقال به صفحه مسترپیج بعد از 2 ثانیه
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, AnewerMasterPageActivity.class);
                startActivity(i);
                SplashActivity.this.finish();
            }
        }, 2000);


    }
}
