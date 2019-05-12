package com.example.aalizade.mbazar_base_app.entities;

import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by aalizade on 11/21/2017.
 */

public class FilterTypeItemEntity {
    private LinearLayout clickableLayout;
    private TextView tempTextView;
    private CheckBox checkBox;
    private String itemName;
    private Boolean isChecked;

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

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }

    public String getITemName() {
        return itemName;
    }

    public void setItemName(String name) {
        this.itemName = name;
    }

    public String getItemName() {
        return itemName;
    }

    public Boolean getChecked() {
        return isChecked;
    }

    public void setChecked(Boolean checked) {
        isChecked = checked;
    }
}
