package com.example.aalizade.mbazar_base_app.network.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by aalizade on 12/4/2017.
 */

public class TokenModel {
    @SerializedName("access_token")
    private  String access_token ;
    @SerializedName("expires_in")
    private  String expires_in ;
    @SerializedName("token_type")
    private  String token_type;
    @SerializedName("refresh_token")
    private  String refresh_token;
    @SerializedName("scope")
    private  String scope;
    @SerializedName("jti")
    private  String jti;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getJti() {
        return jti;
    }

    public void setJti(String jti) {
        this.jti = jti;
    }

    @Override
    public String toString() {
        return "TokenModel{" +
                "access_token='" + access_token + '\'' +
                ", expires_in='" + expires_in + '\'' +
                ", token_type='" + token_type + '\'' +
                ", refresh_token=" + refresh_token +
                ", scope='" + scope + '\'' +
                ", jti='" + jti + '\'' +
                '}';
    }

    public TokenModel(String access_token, String expires_in, String token_type, String refresh_token, String scope, String jti) {
        this.access_token = access_token;
        this.expires_in = expires_in;
        this.token_type = token_type;
        this.refresh_token = refresh_token;
        this.scope = scope;
        this.jti = jti;
    }

    public TokenModel() {
    }
}
