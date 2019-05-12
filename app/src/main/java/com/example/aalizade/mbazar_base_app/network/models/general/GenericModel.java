package com.example.aalizade.mbazar_base_app.network.models.general;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by aalizade on 1/11/2018.
 */

public class GenericModel {

    @SerializedName("invalid")
    @Expose
    private String invalid;
    @SerializedName("hidden")
    @Expose
    private String hidden;
    @SerializedName("deleted")
    transient private Boolean deleted = false;


    public String getInvalid() {
        return invalid;
    }

    public void setInvalid(String invalid) {
        this.invalid = invalid;
    }



    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }



    public String getHidden() {
        return hidden;
    }

    public void setHidden(String hidden) {
        this.hidden = hidden;
    }

    public GenericModel() {
    }

    @Override
    public String toString() {
        return "GenericModel{" +
                "invalid='" + invalid + '\'' +
                ", hidden='" + hidden + '\'' +
                ", deleted=" + deleted +
                '}';
    }
}
