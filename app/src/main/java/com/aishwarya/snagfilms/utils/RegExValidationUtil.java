package com.aishwarya.snagfilms.utils;

/**
 * Created by Srinivas Rupani on 12/7/2017.
 * Copyright (c) 2017 Promantra Inc, All rights reserved.
 */
public class RegExValidationUtil {
    public static final String PAN = "^[A-Z]{5}\\d{4}[A-Z]{1}$";

    //public static final String NUMBER = "^[0-9]+$";
    //Mobile number can be 10 or 11 digits and should start with number or zero
    public static final String MOBILE_NO_REG_EXPRESSION = "([1-9]{1}+[0-9]{9})|(0{1}+[1-9]{1}+\\d{9})";

    //Alphanumeric characters
    public static final String LOGIN_NAME_PATTERN = "^[A-Za-z0-9_-]+$";

    public static final String NAME = "^[a-zA-Z\\s]+$";

    public static final String DECIMAL = "^[-+]?[0-9]*\\.?[0-9]+$";
    //Email with min and max of 2,5 domain(com,org.in....) name
    public static final String EMAIL = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,5}$";

    //public static final String NAME_SELECTED_SPECIAL_CHARACTER = "^[a-zA-Z\\s._+,/?+ ]+$";
    //Address expression pattern will allow Alpha numeric and Special characters,./#;: )(/
    public static final String NAME_SELECTED_SPECIAL_CHARACTER = "^[a-zA-Z0-9\\s#\\;\\:\\)(,.-[/]{}]*$";

}
