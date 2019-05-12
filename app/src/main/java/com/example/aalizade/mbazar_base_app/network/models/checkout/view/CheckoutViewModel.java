package com.example.aalizade.mbazar_base_app.network.models.checkout.view;

/**
 * Created by sbayatani on 4/15/2018.
 */

public class CheckoutViewModel {

    private String id;
    private String code;
    private String dateOfRegister;
    private String dateOfClearing;
    private String status;

    public CheckoutViewModel(String id, String code, String dateOfRegister, String dateOfClearing, String status) {
        this.id = id;
        this.code = code;
        this.dateOfRegister = dateOfRegister;
        this.dateOfClearing = dateOfClearing;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDateOfRegister() {
        return dateOfRegister;
    }

    public void setDateOfRegister(String dateOfRegister) {
        this.dateOfRegister = dateOfRegister;
    }

    public String getDateOfClearing() {
        return dateOfClearing;
    }

    public void setDateOfClearing(String dateOfClearing) {
        this.dateOfClearing = dateOfClearing;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
