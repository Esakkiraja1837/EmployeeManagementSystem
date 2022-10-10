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
    public static final String REGEX_SALARY = "([0-9]{3,}([.][0-9]{2}))|([0-9]{3,}?)";
    public static final String REGEX_CITYSTATE = "([a-zA-Z]{2,20})(([ ]([a-zA-Z]){2,20})?)";
    public static final String REGEX_NAME = "(([A-Za-z]{2,20})((([ ])([a-zA-Z]){2,20})?){1,4})";
    public static final String REGEX_MOBILENUMBER = "[7-9][0-9]{9}";
    public static final String REGEX_EMAILID = "(([a-z0-9]([.][a-z0-9]+)*){4,}[@]{1}[a-z]{2,8}[\\.]([a-z]{3,}([a-z\\.][a-z]{2})*))";
    public static final String REGEX_PINCODE = "([0-9]{6})";
    public static final String REGEX_EMPLOYEEID = "([0-9]{1,9})";
    public static final String REGEX_DOOR_NUMBER = "^[1-9]*(?:[ -]?(?:[a-zA-Z]{1,2}+|[1-9]*))?$";
    public static final String REGEX_ADDRESS = "^[a-zA-Z]{2,15}([ ]?[a-zA-Z]{0,15}){0,}$";
}