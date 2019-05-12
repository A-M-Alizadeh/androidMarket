package com.example.aalizade.mbazar_base_app.network.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by aalizade on 12/19/2017.
 */
public class Value {

    @SerializedName("trend")
    @Expose
    public Object trend;
    @SerializedName("city")
    @Expose
    public Object city;

    public Object getTrend() {
        return trend;
    }

    public void setTrend(Object trend) {
        this.trend = trend;
    }

    public Object getCity() {
        return city;
    }

    public void setCity(Object city) {
        this.city = city;
    }

    public Value(Integer trend, Integer city) {
        this.trend = trend;
        this.city = city;
    }

    public Value() {
    }

    public Value(Object trend, Object city) {
        this.trend = trend;
        this.city = city;
    }

    @Override
    public String toString() {
        return "Value{" +
                "trend=" + trend +
                ", city=" + city +
                '}';
    }
}
