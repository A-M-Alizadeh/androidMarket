package com.example.aalizade.mbazar_base_app.utility;

import android.app.Application;

import com.example.aalizade.mbazar_base_app.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by aalizade on 10/24/2017.
 */

public class MyCaligraphyFont extends Application {             // وظیفه ست کردن فونت روی آیتم های صفحه را بر عهده دار
    @Override                                                   // برای تب لایوت ها و .. باید به صورت دستی عمل کرد
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/B_Yekan.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
