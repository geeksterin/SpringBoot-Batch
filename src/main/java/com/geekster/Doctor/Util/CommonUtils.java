package com.geekster.Doctor.Util;

import java.util.regex.*;

public class CommonUtils {

    public static boolean validatePhoneNumber (String phoneNumber) {
        Pattern p = Pattern.compile("^\\d{10}$");
        Matcher m = p.matcher(phoneNumber);
        return (m.matches());
    }
}
