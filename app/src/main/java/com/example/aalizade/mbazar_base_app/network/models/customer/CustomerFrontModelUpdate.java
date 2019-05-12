/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.aalizade.mbazar_base_app.network.models.customer;


/**
 *
 * @author Administrator
 */
public class CustomerFrontModelUpdate extends CustomerFrontModel {

    private Integer id;

    public CustomerFrontModelUpdate(String user_username, String user_name, String user_family, String user_defaultUserContact_mobileNo, String user_defaultUserContact_emailAddress, String user_gender_value, Boolean user_newsLetter, String sendBy, Boolean user_userAgreement) {
        super(user_username, user_name, user_family, user_defaultUserContact_mobileNo, user_defaultUserContact_emailAddress, user_gender_value, user_newsLetter, sendBy, user_userAgreement);
    }

    public CustomerFrontModelUpdate(String user_username, String user_name, String user_family, String user_defaultUserContact_mobileNo, String user_defaultUserContact_emailAddress, String user_gender_value, Boolean user_newsLetter, Boolean user_userAgreement) {
        super(user_username, user_name, user_family, user_defaultUserContact_mobileNo, user_defaultUserContact_emailAddress, user_gender_value, user_newsLetter, user_userAgreement);
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }



}
