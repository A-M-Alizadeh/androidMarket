package com.example.aalizade.mbazar_base_app.utility;

/**
 * Created by sajad on 4/25/2018.
 */

public class DoubleToString {
    public DoubleToString() {
    }

    public String dTos(Double d){
        int temp = d.intValue();
        return String.valueOf(temp);
    }
}
