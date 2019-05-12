package com.example.aalizade.mbazar_base_app.network.retrofit;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.example.aalizade.mbazar_base_app.activities.basic_parts.NoConnectionActivity;
import com.example.aalizade.mbazar_base_app.utility.ProgressBarShower;

import retrofit2.Call;
import retrofit2.Callback;


/**
 * Created by aalizade on 1/24/2018.
 */
public abstract class CallbackWithRetry<T> implements Callback<T> {

    private static final int TOTAL_RETRIES = 1;
    private static final String TAG = CallbackWithRetry.class.getSimpleName();
    private final Call<T> call;
    private int retryCount = 0;
    private final Activity activity;
    private final View progressWrapper;
    public static Boolean firstTime = true;

    public CallbackWithRetry(Call<T> call, Activity activity, final View progressWrapper) {
        this.call = call;
        this.activity = activity;
        this.progressWrapper = progressWrapper;
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        if (TAG != null && t.getLocalizedMessage() != null) {
            Log.d("ssssssssssssssssss=" + TAG, t.getLocalizedMessage());        //TODO new error -> numberformayexception : empty string
        }
        if (retryCount++ < TOTAL_RETRIES) {
            if (!firstTime) {
                firstTime = true;
            }
            Log.d(TAG, "##### Retrying... (" + retryCount + " out of " + TOTAL_RETRIES + ")");
            retry();
        } else {
            if (retryCount == 2 && firstTime) {
                firstTime = false;
                ProgressBarShower.StopMyProgressBar(activity, progressWrapper);
                System.out.println("***** retryCount: " + retryCount + " TOTALCOUNT: " + TOTAL_RETRIES);
                activity.startActivity(new Intent(activity, NoConnectionActivity.class));
            }
        }
    }

    private void retry() {
        call.clone().enqueue(this);
    }
}