package com.example.aalizade.mbazar_base_app.network.retrofit;

import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by aalizade on 12/5/2017.
 */

public class RetrofitClient {
    public static final String BASE_URL = "http://www.mbazar.net/";

    private static Retrofit retrofit = null;

//    private static Gson gson = new Gson();
//    public static Retrofit getclient(){
//        GsonBuilder gb = new GsonBuilder();
//        gb.registerTypeAdapter(Set.class, new CollectionConverter());
//        gb.registerTypeAdapter(List.class, new CollectionConverter());
//        Gson gson = gb.create();
//        if (retrofit == null) {
//            retrofit = new Retrofit.Builder()
//                    .baseUrl(BASE_URL)
//                    .addConverterFactory(GsonConverterFactory.create(gson))
//                    .build();
//        }
//        return retrofit;
//    }

    public static Retrofit getclient(final View view) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        okhttp3.Response response = chain.proceed(request);

                        if (response.code() == 500 || response.code() == 400) {
                            String rawJson = response.body().string();
                            Log.d("lol", response.code() + " : RetrofitClient ++++++++++++" + rawJson);
//                            MySnackBar.snackBarWithNoAction(String.valueOf(response.code()),view); //removed this because od error

                            // Re-create the response before returning it because body can be read only once
                            return response.newBuilder()
                                    .body(ResponseBody.create(response.body().contentType(), rawJson)).build();
                        }
                        return response;
                    }
                }).build();

        GsonBuilder gb = new GsonBuilder();
        gb.registerTypeAdapter(Set.class, new CollectionConverter());
        gb.registerTypeAdapter(List.class, new CollectionConverter());
        Gson gson = gb.create();
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }

}
