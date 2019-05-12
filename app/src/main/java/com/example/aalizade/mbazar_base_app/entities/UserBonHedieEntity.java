package com.example.aalizade.mbazar_base_app.entities;

/**
 * Created by aalizade on 3/14/2018.
 */

public class UserBonHedieEntity {
    String type;
    String bonCode;
    String user_username;
    String user_name;
    String givenPrice;
    String totalPrice;

    public UserBonHedieEntity(String type, String bonCode, String user_username, String user_name, String givenPrice, String totalPrice) {
        this.type = type;
        this.bonCode = bonCode;
        this.user_username = user_username;
        this.user_name = user_name;
        this.givenPrice = givenPrice;
        this.totalPrice = totalPrice;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBonCode() {
        return bonCode;
    }

    public void setBonCode(String bonCode) {
        this.bonCode = bonCode;
    }

    public String getUser_username() {
        return user_username;
    }

    public void setUser_username(String user_username) {
        this.user_username = user_username;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getGivenPrice() {
        return givenPrice;
    }

    public void setGivenPrice(String givenPrice) {
        this.givenPrice = givenPrice;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "UserBonHedieEntity{" +
                "type='" + type + '\'' +
                ", bonCode='" + bonCode + '\'' +
                ", user_username='" + user_username + '\'' +
                ", user_name='" + user_name + '\'' +
                ", givenPrice='" + givenPrice + '\'' +
                ", totalPrice='" + totalPrice + '\'' +
                '}';
    }
}
