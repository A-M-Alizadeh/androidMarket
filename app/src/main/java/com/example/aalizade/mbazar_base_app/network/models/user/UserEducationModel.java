/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.example.aalizade.mbazar_base_app.network.models.user;

import com.example.aalizade.mbazar_base_app.network.models.general.GenericModel;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Dev2
 */
public class UserEducationModel extends GenericModel implements Serializable{
    @SerializedName("id")
    private String id;
    @SerializedName("certificateField")
    private String certificateField;
    @SerializedName("certificateLevel_id")
    private String certificateLevel_id;
    @SerializedName("certificateLevel_langKey")
    private String certificateLevel_langKey;
    @SerializedName("defaultItem")
    private String defaultItem;

    public UserEducationModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCertificateField() {
        return certificateField;
    }

    public void setCertificateField(String certificateField) {
        this.certificateField = certificateField;
    }

    public String getCertificateLevel_id() {
        return certificateLevel_id;
    }

    public void setCertificateLevel_id(String certificateLevel_id) {
        this.certificateLevel_id = certificateLevel_id;
    }

    public String getCertificateLevel_langKey() {
        return certificateLevel_langKey;
    }

    public void setCertificateLevel_langKey(String certificateLevel_langKey) {
        this.certificateLevel_langKey = certificateLevel_langKey;
    }

    public String getDefaultItem() {
        return defaultItem;
    }

    public void setDefaultItem(String defaultItem) {
        this.defaultItem = defaultItem;
    }

    @Override
    public String toString() {
        return "UserEducationModel{" +
                "id=" + id +
                ", certificateField='" + certificateField + '\'' +
                ", certificateLevel_id=" + certificateLevel_id +
                ", certificateLevel_langKey='" + certificateLevel_langKey + '\'' +
                ", defaultItem=" + defaultItem +
                '}';
    }
}
