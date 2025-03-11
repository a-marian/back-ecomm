package com.back.ecomm.util;

public class ValidatorUtil {
    private static final ValidatorUtil INSTANCE = new ValidatorUtil();
    private ValidatorUtil(){}
    public static ValidatorUtil getInstance() { return INSTANCE;}

    public static boolean validateString(String phrase){
        return  (phrase == null || "".equals(phrase));
    }


}
