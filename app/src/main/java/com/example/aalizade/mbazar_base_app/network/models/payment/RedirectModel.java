package com.example.aalizade.mbazar_base_app.network.models.payment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by sbayatani on 4/18/2018.
 */

public class RedirectModel implements Serializable {
    @SerializedName("vitrinId")
    @Expose
    private String vitrinId;

    @SerializedName("clientRevision")
    @Expose
    private String clientRevision;

    @SerializedName("paymentSystemHashMap")
    @Expose
    private HashMap<String,String> paymentSystemHashMap;

    @SerializedName("paymentSystemIdForCarrier")
    @Expose
    private String paymentSystemIdForCarrier;

    public RedirectModel(String vitrinId, String clientRevision, HashMap<String, String> paymentSystemHashMap, String paymentSystemIdForCarrier) {
        this.vitrinId = vitrinId;
        this.clientRevision = clientRevision;
        this.paymentSystemHashMap = paymentSystemHashMap;
        this.paymentSystemIdForCarrier = paymentSystemIdForCarrier;
    }

    public String getVitrinId() {
        return vitrinId;
    }

    public void setVitrinId(String vitrinId) {
        this.vitrinId = vitrinId;
    }

    public String getClientRevision() {
        return clientRevision;
    }

    public void setClientRevision(String clientRevision) {
        this.clientRevision = clientRevision;
    }

    public HashMap<String, String> getPaymentSystemHashMap() {
        return paymentSystemHashMap;
    }

    public void setPaymentSystemHashMap(HashMap<String, String> paymentSystemHashMap) {
        this.paymentSystemHashMap = paymentSystemHashMap;
    }

    public String getPaymentSystemIdForCarrier() {
        return paymentSystemIdForCarrier;
    }

    public void setPaymentSystemIdForCarrier(String paymentSystemIdForCarrier) {
        this.paymentSystemIdForCarrier = paymentSystemIdForCarrier;
    }

    @Override
    public String toString() {
        return "RedirectModel{" +
                "vitrinId='" + vitrinId + '\'' +
                ", clientRevision='" + clientRevision + '\'' +
                ", paymentSystemHashMap=" + paymentSystemHashMap +
                ", paymentSystemIdForCarrier='" + paymentSystemIdForCarrier + '\'' +
                '}';
    }
}
