/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.aalizade.mbazar_base_app.network.models.general;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.logging.Logger;

/**
 *
 * @author administrator
 */
public class CustomDateTime{

//    private static final Logger logger = LoggerFactory.getLogger(CustomDateTime.class);
    private String year;
    private String month;
    private String day;
    private String hour;
    private String minute;
    private String second;

    public CustomDateTime() {

    }

    public CustomDateTime(String year,  String month, String day,
                          String hour,  String minute,  String second) throws Exception {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public CustomDateTime(Date date) throws Exception {
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
            this.setYear(String.valueOf(calendar.get(Calendar.YEAR)));
            this.setMonth(String.valueOf(calendar.get(Calendar.MONTH) + 1));
            this.setDay(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
            this.setHour(String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)));
            this.setMinute(String.valueOf(calendar.get(Calendar.MINUTE)));
            this.setSecond(String.valueOf(calendar.get(Calendar.SECOND)));
        }
    }

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

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }


    @Override
    public String toString() {
        return "CustomDateTime{" + "year=" + year + ", month=" + month + ", day=" + day + ", hour=" + hour + ", minute=" + minute + ", second=" + second + '}';
    }


}
