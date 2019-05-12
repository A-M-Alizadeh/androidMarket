package com.example.aalizade.mbazar_base_app.network.models.general;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by aalizade on 12/20/2017.
 */

public class ComboRequestModel {
    @SerializedName("entity")
    @Expose
    public String entity;
    @SerializedName("parametersMode")
    @Expose
    public String parametersMode;
    @SerializedName("value")
    @Expose
    public Object value;
    @SerializedName("param1")
    @Expose
    public String param1;

    public ComboRequestModel(String entity, String parametersMode, Object value) {
        this.entity = entity;
        this.parametersMode = parametersMode;
        this.value = value;
    }

    public ComboRequestModel(String entity, String parametersMode, Object value, String param1) {
        this.entity = entity;
        this.parametersMode = parametersMode;
        this.value = value;
        this.param1 = param1;
    }

    public ComboRequestModel() {
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getParametersMode() {
        return parametersMode;
    }

    public void setParametersMode(String parametersMode) {
        this.parametersMode = parametersMode;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    @Override
    public String toString() {
        return "ComboRequestModel{" +
                "entity='" + entity + '\'' +
                ", parametersMode='" + parametersMode + '\'' +
                ", value=" + value +
                ", param1='" + param1 + '\'' +
                '}';
    }
}
