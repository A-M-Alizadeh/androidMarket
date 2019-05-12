package com.example.aalizade.mbazar_base_app.network.retrofit;

/**
 * Created by aalizade on 1/24/2018.
 */

//public class RefreshTokenProvider {
//    static Context mContext;
//    static String mUsername, mPassword;
//
//    public static void RefreshTokenProvider(Context Context, String Username, String Password) {
//        mContext = mContext;
//        mUsername = mUsername;
//        mPassword = mPassword;
//    }
//
//    public static void ProvideMeWithToken() {
//        LoginAPIInterface loginApiInterface = RetrofitClient.getclient().create(LoginAPIInterface.class);
//        Call<TokenModel> call = loginApiInterface.refreshToken("refresh_token", mUsername);
//        call.enqueue(new Callback<TokenModel>() {
//            @Override
//            public void onResponse(Call<TokenModel> call, Response<TokenModel> response) {
//                if (response.isSuccessful()) {
//                    Globals.globalToken = response.body().getAccess_token();
//                    MBZ_Token_Prefs.setTokenValues(MBZ_Token_Prefs.com_example_aalizade_mbazar_base_app_REFRESH_TOKEN, Globals.globalToken);
//                    setting_token_to_shared(mContext, response.body().getAccess_token(), response.body().getExpires_in(),
//                            response.body().getToken_type(), response.body().getRefresh_token());
//                } else {
//                    Log.d("Invalide Token", "In Refresh Token");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<TokenModel> call, Throwable t) {
//                t.printStackTrace();
//                Log.d("Invalide Token", t.getMessage());
//            }
//        });
//    }
//
//    public static void setting_token_to_shared(Context context, String accessToken, String expiresIn, String tokenType, String refreshToken) {
//        SharedPreferences sharedPref = context.getSharedPreferences("oauth", Context.MODE_PRIVATE);
//        SharedPreferences.Editor sharedPrefEditor = sharedPref.edit();
//        sharedPrefEditor.putString(MBZ_Token_Prefs.com_example_aalizade_mbazar_base_app_ACCESS_TOKEN, accessToken);
//        sharedPrefEditor.putString(MBZ_Token_Prefs.com_example_aalizade_mbazar_base_app_EXPIRES_IN, "100");//expiresIn
//        sharedPrefEditor.putString(MBZ_Token_Prefs.com_example_aalizade_mbazar_base_app_TOKEN_TYPE, tokenType);
//        sharedPrefEditor.putString(MBZ_Token_Prefs.com_example_aalizade_mbazar_base_app_REFRESH_TOKEN, refreshToken);
//        sharedPrefEditor.putString(MBZ_Token_Prefs.com_example_aalizade_mbazar_base_app_RECEIVE_TIME, String.valueOf(System.currentTimeMillis()));
//        sharedPrefEditor.apply();
//    }
//}
