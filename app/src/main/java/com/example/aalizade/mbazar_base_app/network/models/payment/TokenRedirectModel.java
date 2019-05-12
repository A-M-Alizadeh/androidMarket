package com.example.aalizade.mbazar_base_app.network.models.payment;

/**
 * Created by sbayatani on 4/18/2018.
 */

public class TokenRedirectModel {

    private String token;
    private String redirectURL;

    public TokenRedirectModel(String token, String redirectURL) {
        this.token = token;
        this.redirectURL = redirectURL;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRedirectURL() {
        return redirectURL;
    }

    public void setRedirectURL(String redirectURL) {
        this.redirectURL = redirectURL;
    }

    @Override
    public String toString() {
        return "TokenRedirectModel{" +
                "token='" + token + '\'' +
                ", redirectURL='" + redirectURL + '\'' +
                '}';
    }
}
