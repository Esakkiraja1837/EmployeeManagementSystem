package com.ideas2it.employee.constant;

/**
 * declaring constants
 * @version 1.0
 * @author  ESAKKIRAJA E.
 * 13-09-2022. 
 */

public class EmployeeManagementConstant {

    public static final String EMPLOYEE_FIRSTNAME = "Enter the Employee First Name :";
    public static final String EMPLOYEE_LASTNAME = "Enter the Employee Last Name :";
    public static final String INVALID_INPUT = "Invalid input... Try again";
    public static final String VALID_INPUT = "Enter valid input";
    public static final String EMPLOYEE_SALARY = "Enter Employee Salary (0.00) : ";
    public static final String EMPLOYEE_ID = "Enter Employee ID: ";
    public static final String REGEX_SALARY = "([1-9]{1})([0-9]{1,8})((([.])([0-9]{1,2}))?)";
    public static final String REGEX_NAME = "^[a-zA-Z]{2,20}";
    public static final String REGEX_MOBILENUMBER = "[7-9][0-9]{9}";
    public static final String REGEX_EMAILID = "(^[a-zA-Z]{1,20}[.\\-_]?[a-zA-Z0-9]{1,20}[@][a-z0-9]{1,20}[a-z0-9]{0,10}[.][a-z]?[a-z]?[.]?[a-z]{1,3}$)";
    public static final String REGEX_PINCODE = "([0-9]{6})";
}