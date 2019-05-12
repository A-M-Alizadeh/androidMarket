package com.example.aalizade.mbazar_base_app.utility;

import java.util.Arrays;

/**
 * Created by aalizade on 1/29/2018.
 */

public class NationalCodeValidator {
    public Boolean validate(String melliCode) {
        String[] identicalDigits = {"0000000000", "1111111111", "2222222222", "3333333333", "4444444444", "5555555555", "6666666666", "7777777777", "8888888888", "9999999999"};
        if (melliCode.trim().isEmpty()) {
            return false; // Melli Code is empty
        } else if (melliCode.length() != 10) {
            return false; // Melli Code is less or more than 10 digits
        } else if (Arrays.asList(identicalDigits).contains(melliCode)) {
            return false; // Fake Melli Code
        } else {
            int sum = 0;
            for (int i = 0; i < 9; i++) {
                sum += Character.getNumericValue(melliCode.charAt(i)) * (10 - i);
            }
            int lastDigit;
            int divideRemaining = sum % 11;
            if (divideRemaining < 2) {
                lastDigit = divideRemaining;
            } else {
                lastDigit = 11 - (divideRemaining);
            }
            if (Character.getNumericValue(melliCode.charAt(9)) == lastDigit) {
                return true;
            } else {
                return false; // Invalid MelliCode
            }
        }

    }
}
