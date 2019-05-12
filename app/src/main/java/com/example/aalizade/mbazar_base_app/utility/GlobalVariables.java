package com.example.aalizade.mbazar_base_app.utility;

import android.app.Activity;

import com.example.aalizade.mbazar_base_app.network.models.cart.CartModel;
import com.example.aalizade.mbazar_base_app.network.models.cart.CustomBaghBaghuModel;

/**
 * Created by aalizade on 3/19/2018.
 */

public class GlobalVariables extends Activity {
    public static String selectedCity = null;
    public static String selectedCityNAME = "";
    public static String selectedDepartment;
    public static String selectedProductID;
    public static String selectedType;
    public static CartModel LocalCart;
    public static CartModel LocalUnAthourizedCart;
    public static CustomBaghBaghuModel LocalUnAthourizedSENTCart;
    public static Integer cartItemsCount=0;
    public static Integer cartTotalItemsWithQuantity=0;
    public static String userId;

    public String getSelectedCity() {
        return selectedCity;
    }

    public void setSelectedCity(String selectedCity) {
        this.selectedCity = selectedCity;
    }

    public String getSelectedDepartment() {
        return selectedDepartment;
    }

    public void setSelectedDepartment(String selectedDepartment) {
        this.selectedDepartment = selectedDepartment;
    }

    public String getSelectedType() {
        return selectedType;
    }

    public void setSelectedType(String selectedType) {
        this.selectedType = selectedType;
    }

    @Override
    public String toString() {
        return "GlobalVariables{" +
                "selectedCity='" + selectedCity + '\'' +
                ", selectedDepartment='" + selectedDepartment + '\'' +
                ", selectedType='" + selectedType + '\'' +
                '}';
    }
}
