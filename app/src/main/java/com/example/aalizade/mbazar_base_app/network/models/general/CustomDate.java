package com.example.aalizade.mbazar_base_app.network.models.general;

import com.google.gson.annotations.SerializedName;

/**
 * Created by aalizade on 12/5/2017.
 */

public class CustomDate {
    @SerializedName("year")
    private String year;
    @SerializedName("month")
    private String month;
    @SerializedName("day")
    private String day;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "CustomDate{" +
                "year='" + year + '\'' +
                ", month='" + month + '\'' +
                ", day='" + day + '\'' +
                '}';
    }

    public CustomDate(String year, String month, String day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public CustomDate() {
    }
}
