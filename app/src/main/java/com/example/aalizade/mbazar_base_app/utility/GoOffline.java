package com.example.aalizade.mbazar_base_app.utility;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.aalizade.mbazar_base_app.activities.basic_parts.NoConnectionActivity;

/**
 * Created by aalizade on 1/23/2018.
 */

public class GoOffline {
    public static void GoOffline(Context context, final Activity thisActivty) {         //درصورت عدم دسترسی به اینترنت به صفحه آفلاین منتقل میشود
        if (!isNetworkAvailable(context)) {
            thisActivty.startActivity(new Intent(thisActivty, NoConnectionActivity.class));
//            thisActivty.finish();
        }

    }

    private static boolean isNetworkAvailable(Context context) {            //بررسی دسترسی به اینترنت از طریق وای فای یا سیمکارت
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    public static void GoOnline(Context context,final Activity thisActivty) {           //درصورت دسترسی به اینترنت به اکتیویتی قبلی باز میگردد
        if (isNetworkAvailable(context)) {
            thisActivty.finish();
        }

    }


}
