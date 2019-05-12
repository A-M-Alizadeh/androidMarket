package com.example.aalizade.mbazar_base_app.utility;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.aalizade.mbazar_base_app.activities.login_related.SignInLogInActivity;
import com.example.aalizade.mbazar_base_app.utility.interfaces.UtilInterface;
import com.example.aalizade.mbazar_base_app.network.MBZ_Token_Prefs;

/**
 * Created by aalizade on 5/13/2018.
 */

public class AuthorizationCheck {
    View view;
    Context context;

    public AuthorizationCheck(Context context, View view) {
        MBZ_Token_Prefs.initTokenSharedPrefs(context);
        this.view = view;
        this.context = context;
    }

    public void CheckLoginStatus() {
        if (MBZ_Token_Prefs.isAuthorized()) {
            MySnackBar.snackBarWithNoAction("شما قبلا وارد شده اید", view);
        } else {
            context.startActivity(new Intent(context, SignInLogInActivity.class));
        }
    }

    public void DoIfAuthorized(UtilInterface utilInterface) {           //پیاده سازی اینترفیس درصورت لاگین بودن در غیر این صورت رفتن به صفحه لاگین
        if (MBZ_Token_Prefs.isAuthorized()) {
            utilInterface.HandleAfterResponse();
        } else {
            MySnackBar.snackBarWithAction("لطفا ابتدا وارد شوید", "ورود", view, new UtilInterface() {
                @Override
                public void HandleAfterResponse() {
                    context.startActivity(new Intent(context, SignInLogInActivity.class));
                }
            });
        }
    }

    public void DoIfNotAuthorized(UtilInterface utilInterface) {        //پیاده سازی اینترفیس درصورت لاگین نبودن
        if (MBZ_Token_Prefs.isAuthorized()) {
            MySnackBar.snackBarWithNoAction("شما لاگین کرده اید", view);
        } else {
            utilInterface.HandleAfterResponse();
        }
    }
}