/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.aalizade.mbazar_base_app.network.models.membershiprequest;

import com.example.aalizade.mbazar_base_app.network.models.general.CustomDateTime;
import com.example.aalizade.mbazar_base_app.network.models.general.GenericModel;

/**
 *
 * @author Administrator()
 */
public class RequestStatusHistoryModel extends GenericModel {

    private String status_langKey;
    private String status_value;
    private CustomDateTime statusDateTime;

    //getter-setter:
    public String getStatus_langKey() {
        return status_langKey;
    }

    public void setStatus_langKey(String status_langKey) {
        this.status_langKey = status_langKey;
    }

    public String getStatus_value() {
        return status_value;
    }

    public void setStatus_value(String status_value) {
        this.status_value = status_value;
    }

    public CustomDateTime getStatusDateTime() {
        return statusDateTime;
    }

    public void setStatusDateTime(CustomDateTime statusDateTime) {
        this.statusDateTime = statusDateTime;
    }

    public RequestStatusHistoryModel() {
    }

    public RequestStatusHistoryModel(String status_langKey, String status_value, CustomDateTime statusDate) {
        this.status_langKey = status_langKey;
        this.statusDateTime = statusDate;
    }

}
