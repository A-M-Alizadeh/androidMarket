package com.example.aalizade.mbazar_base_app.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by aalizade on 5/21/2018.
 */

public class MobileNumberValidator {
    public Boolean validate(String s) {
        Matcher mobileMatcher;
        String mobileExpression = "09(1[0-9]|3[1-9]|2[1-9])-?[0-9]{3}-?[0-9]{4}";
        Pattern mobilePattern = Pattern.compile(mobileExpression, Pattern.CASE_INSENSITIVE);
        mobileMatcher = mobilePattern.matcher(s);
        if (mobileMatcher.find())
            return true;
        return false;
    }
}
