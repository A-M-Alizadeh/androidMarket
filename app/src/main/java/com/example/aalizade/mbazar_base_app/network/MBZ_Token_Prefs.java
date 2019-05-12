package com.example.aalizade.mbazar_base_app.network;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.aalizade.mbazar_base_app.utility.GlobalVariables;
import com.scottyab.aescrypt.AESCrypt;

import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by aalizade on 12/12/2017.
 */

public class MBZ_Token_Prefs {
    public static final String com_example_aalizade_mbazar_base_app_ACCESS_TOKEN = "access_token";
    public static final String com_example_aalizade_mbazar_base_app_EXPIRES_IN = "expires_in";
    public static final String com_example_aalizade_mbazar_base_app_TOKEN_TYPE = "token_type";
    public static final String com_example_aalizade_mbazar_base_app_REFRESH_TOKEN = "refresh_token";
    public static final String com_example_aalizade_mbazar_base_app_ACCOUNT_USERNAME = "account_username";
    public static final String com_example_aalizade_mbazar_base_app_RECEIVE_TIME = "receive_time";
    public static final String com_example_aalizade_mbazar_base_app_FULL_NAME = "full_name";
    public static final String com_example_aalizade_mbazar_base_app_USER_ID = "user_id";
    public static final String com_example_aalizade_mbazar_base_app_USER_ROLE_ID = "user_role_id";
    public static final String com_example_aalizade_mbazar_base_app_USER_CITY_ID = "user_city_id";
    public static final String com_example_aalizade_mbazar_base_app_USER_IS_MEMBER = "customer";

    private static SharedPreferences sharedOAuthCredentials;

    public static void initTokenSharedPrefs(Context context) {
        sharedOAuthCredentials = context.getSharedPreferences("oauth", Context.MODE_PRIVATE);
    }

    public static SharedPreferences getOAuthCredentials() {
        return sharedOAuthCredentials;
    }

    private static SharedPreferences.Editor getSharedPrefEditor() {
        return sharedOAuthCredentials.edit();
    }

    @Nullable
    public static String getString(String key) {
        return sharedOAuthCredentials.getString(key, "not set");
    }

    @Nullable
    public static Boolean getBoolean(String key) {
        return sharedOAuthCredentials.getBoolean(key, false);
    }

    public static Long getLong(String key) {
        return Long.parseLong(sharedOAuthCredentials.getString(key, "-1"));
    }

    public static void setTokenValues(String key, String value) {
        getSharedPrefEditor().putString(key, value).commit();
    }

    public static void setTokenValues(String key, Boolean value) {
        getSharedPrefEditor().putBoolean(key, value).commit();
    }

    //-----------------------------------------------------------------------------set Encrypted and get Decrypted
    public static void setTokenEncryptedValues(String key, String value) {
        try {
            String encryptedMsg = AESCrypt.encrypt(key, value);
            System.out.println("MBZ TOKEN Encrypted -->key :" + key + " value: " + value + " enc: " + encryptedMsg);
            getSharedPrefEditor().putString(key, encryptedMsg).commit();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            Log.e("MBZ TOKEN", "Error in Encrypting Token Values");
        }
    }

    @Nullable
    public static String getDecryptedString(String key) {
        String messageAfterDecrypt = "not set";
        try {
            messageAfterDecrypt = AESCrypt.decrypt(key, sharedOAuthCredentials.getString(key, "not set"));
            System.out.println("MBZ TOKEN Decrypted -->key :" + key + " dec: " + messageAfterDecrypt);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            Log.e("MBZ TOKEN", "Error in Encrypting Token Values");
        }
        return messageAfterDecrypt;
    }

    //-----------------------------------------------------------------------------set Encrypted and get Decrypted
    public static Boolean isAuthorized() {
        Date expireDate = new Date(getLong(MBZ_Token_Prefs.com_example_aalizade_mbazar_base_app_RECEIVE_TIME) + (getLong(MBZ_Token_Prefs.com_example_aalizade_mbazar_base_app_EXPIRES_IN) * 1000));
        if (new Date().after(expireDate))
            return false;
        return true;
    }

    public static void cleanTokenOnExit() {
        setTokenValues(com_example_aalizade_mbazar_base_app_EXPIRES_IN, "0");
        setTokenEncryptedValues(com_example_aalizade_mbazar_base_app_ACCESS_TOKEN, "");
        setTokenEncryptedValues(com_example_aalizade_mbazar_base_app_REFRESH_TOKEN, "");
    }

}
