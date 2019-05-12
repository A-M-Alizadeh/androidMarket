package com.example.aalizade.mbazar_base_app.utility;

import android.app.Activity;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by aalizade on 1/23/2018.
 */

public class ProgressBarShower {        //نمایش/عدم نمایش لودینگ به صورت کلی

    public static void StartMyProgressBar(Activity activity, View progressLayout){
        progressLayout.setVisibility(View.VISIBLE);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }
    public static void StopMyProgressBar(Activity activity, View progressLayout){
        progressLayout.setVisibility(View.GONE);
        activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }
}
