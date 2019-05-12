package com.example.aalizade.mbazar_base_app.network.retrofit;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.example.aalizade.mbazar_base_app.utility.MySnackBar;
import com.example.aalizade.mbazar_base_app.network.MBZ_Token_Prefs;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by aalizade on 12/5/2017.
 */

public class RetrofitOAuthClient {
    //    private Context context;
//    public void setContext(Context context){
//        this.context = context;
//    }
    public static final String BASE_URL = "http://www.mbazar.net/";
    private static Retrofit retrofit = null;

//    public static Retrofit getFromServerByToken(final Context context) {
//        MBZ_Token_Prefs.initTokenSharedPrefs(context);
//        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new Interceptor() {
//                    @Override
//                    public Response intercept(Chain chain) throws IOException {
//
//                        Request authed = chain.request()
//                                .newBuilder()
//                                .addHeader("Authorization", "Bearer " + MBZ_Token_Prefs.getDecryptedString(MBZ_Token_Prefs.com_example_aalizade_mbazar_base_app_ACCESS_TOKEN))//todo changed to decrypted getString
//                                .build();
//                        Response response = chain.proceed(authed);
//                        if (response.code() == 500) {
//                            Log.d("lol", "loo0000000000000000000oooool code 500");
//                            Toast.makeText(context, "loo0000000000000000000oooool", Toast.LENGTH_LONG);
//                            return response;
//                        }else if (response.code() == 401) {
//                            Log.d("lol", "loo0000000000000000000oooool code 500");
//                            Toast.makeText(context, "loo0000000000000000000oooool", Toast.LENGTH_LONG);
//                            return response;
//                        }
//
//                        return response;
//                    }
//                }).cache(null).build();
//
//        GsonBuilder gb = new GsonBuilder();
////        gb.registerTypeAdapter(Set.class, new CollectionConverter());
////        gb.registerTypeAdapter(List.class, new CollectionConverter());
//        gb.registerTypeAdapterFactory(new MyResultObjectAdapterFactory());
//
//        Gson gson = gb.create();
//
//        return new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .client(okHttpClient)
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .build();
//    }

    public static Retrofit getOauthClient(final Context context, final View view) {
        MBZ_Token_Prefs.initTokenSharedPrefs(context);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {

                        Request authed = chain.request()
                                .newBuilder()
                                .addHeader("Authorization", "Bearer " + MBZ_Token_Prefs.getDecryptedString(MBZ_Token_Prefs.com_example_aalizade_mbazar_base_app_ACCESS_TOKEN))
                                .build();
                        Response response = chain.proceed(authed);

                        if (response.code() == 500) {
                            Log.d("lol", "500 : RetrofitOAuthClient ++++++++++++");
                            MySnackBar.snackBarWithNoAction("خطا در ارتباط با سرور", view);
                            return response;
                        }
                        return response;
                    }
                }).build();

        GsonBuilder gb = new GsonBuilder();
        gb.registerTypeAdapterFactory(new MyResultObjectAdapterFactory());

        Gson gson = gb.create();


        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

    }
}
