package com.ideas2it.employee.util;

import java.util.regex.Pattern;
import java.util.Scanner;

/**
 *
 * @version 1.0. 13-09-2022.
 * @author  ESAKKIRAJA E.
 */
public class EmployeeUtil {

    public static boolean isValid(String regexPattern, String fieldValue) {
        return Pattern.matches(regexPattern, fieldValue);
    }
}

    
