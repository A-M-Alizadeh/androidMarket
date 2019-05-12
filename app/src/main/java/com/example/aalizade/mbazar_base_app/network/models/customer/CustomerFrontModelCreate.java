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
public class CustomerFrontModelCreate extends CustomerFrontModel {

    private Integer id;
    private String user_password;
    private String code;

    //====================================//


    public CustomerFrontModelCreate( String user_name, String user_family, String user_password, Boolean user_userAgreement,String user_username, String user_gender_value, Boolean user_newsLetter, String code, String user_defaultUserContact_mobileNo, String user_defaultUserContact_emailAddress) {
        super(user_username, user_name, user_family, user_defaultUserContact_mobileNo, user_defaultUserContact_emailAddress, user_gender_value, user_newsLetter, user_userAgreement);

        this.user_password = user_password;
        this.code = code;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
