package com.example.aalizade.mbazar_base_app.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by aalizade on 1/29/2018.
 */

public class EmailOrMobileChecker {
    public static int isEmailOrMobile(String username){
        Matcher emailMatcher, mobileMatcher;
        String emailExpression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern emailPattern = Pattern.compile(emailExpression, Pattern.CASE_INSENSITIVE);

        String mobileExpression = "09(1[0-9]|3[1-9]|2[1-9])-?[0-9]{3}-?[0-9]{4}";
        Pattern mobilePattern = Pattern.compile(mobileExpression, Pattern.CASE_INSENSITIVE);

        emailMatcher = emailPattern.matcher(username);
        mobileMatcher = mobilePattern.matcher(username);
        if (emailMatcher.find()) {
            return 1;
        }
        if (mobileMatcher.find()) {
            return 2;
        }
        return 0;
    }
}
