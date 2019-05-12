package com.example.aalizade.mbazar_base_app.entities;

import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by aalizade on 11/20/2017.
 */

public class FilterTypeEntity {
    private LinearLayout clickableLayout;
    private TextView tempTextView;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinearLayout getClickableLayout() {
        return clickableLayout;
    }

    public void setClickableLayout(LinearLayout clickableLayout) {
        this.clickableLayout = clickableLayout;
    }

    public TextView getTempTextView() {
        return tempTextView;
    }

    public void setTempTextView(TextView tempTextView) {
        this.tempTextView = tempTextView;
    }
}
