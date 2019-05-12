/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.aalizade.mbazar_base_app.network.models.general;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AutoCompleteModel{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("elementStr")
    @Expose
    private String elementStr;

    public AutoCompleteModel() {
    }

    public AutoCompleteModel(String id, String text, String elementStr) {
        this.id = id;
        this.text = text;
        this.elementStr = elementStr;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getElementStr() {
        return elementStr;
    }

    public void setElementStr(String elementStr) {
        this.elementStr = elementStr;
    }

    @Override
    public String toString() {
        return "AutoCompleteModel{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", elementStr='" + elementStr + '\'' +
                '}';
    }
}
