package com.example.aalizade.mbazar_base_app.entities;

/**
 * Created by aalizade on 2/13/2018.
 */

public class BarginTitlePercent {
    String title;
    String percent;

    public BarginTitlePercent(String title, String percent) {
        this.title = title;
        this.percent = percent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    @Override
    public String toString() {
        return "BarginTitlePercent{" +
                "title='" + title + '\'' +
                ", percent='" + percent + '\'' +
                '}';
    }
}
