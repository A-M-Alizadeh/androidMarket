package com.example.aalizade.mbazar_base_app.utility;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.androidadvance.topsnackbar.TSnackbar;
import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.utility.interfaces.UtilInterface;

/**
 * Created by aalizade on 1/23/2018.
 */

public class MySnackBar {
    private static TSnackbar snackbar;

    public static void snackBarWithNoAction(String message, View view) {            //برای نمایش پیغام در بالای صفحه استفاده میشود
        snackbar = TSnackbar.make(view, message, TSnackbar.LENGTH_LONG);
        snackbar.setIconPadding(2);
        snackbar.setMaxWidth(3000); //if you want fullsize on tablets
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(Color.parseColor("#212121"));
        TextView textView = (TextView) snackbarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        snackbar.show();
    }

    public static void snackBarWithAction(String message,String actionTitle, View view,final UtilInterface utilInterface) {
        snackbar = TSnackbar.make(view, message, TSnackbar.LENGTH_LONG);                        //برای نمایش پیام و انجام یک اکشن هنگام کلیک روی آن استفاده میشود
        snackbar.setIconLeft(R.drawable.account_white_ic, 32); //Resize to bigger dp
        snackbar.setIconPadding(2);

        snackbar.setAction(actionTitle, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                utilInterface.HandleAfterResponse();
            }
        }).setActionTextColor(Color.YELLOW);

        snackbar.setMaxWidth(3000); //if you want fullsize on tablets
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(Color.parseColor("#212121"));
        TextView textView = (TextView) snackbarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        snackbar.show();
    }

    public static void snackBarWithAction(String message, View view,final UtilInterface utilInterface) {
        snackbar = TSnackbar.make(view, message, TSnackbar.LENGTH_LONG);
        snackbar.setMaxWidth(3000); //if you want fullsize on tablets
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(Color.parseColor("#212121"));
        TextView textView = (TextView) snackbarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        snackbar.show();
    }
}
