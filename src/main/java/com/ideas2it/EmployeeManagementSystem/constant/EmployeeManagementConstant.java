package com.ideas2it.EmployeeManagementSystem.constant;

/**
 * declaring constants
 * 
 * @version 1.0
 * @author ESAKKIRAJA E. 13-09-2022.
 */

public class EmployeeManagementConstant {

	public static final String EMPLOYEE_FIRSTNAME = "Enter the Employee First Name :";
	public static final String EMPLOYEE_LASTNAME = "Enter the Employee Last Name :";
	public static final String INVALID_INPUT = "Invalid input... Try again";
	public static final String VALID_INPUT = "Enter valid input";
	public static final String EMPLOYEE_SALARY = "Enter Employee Salary (0.00) : ";
	public static final String EMPLOYEE_ID = "Enter Employee ID: ";
	public static final String INVALID_DATA = "Invalid data format please try again...";

	public static final String EMPLOYEE_DETAILS_NOTFOUND = "Employee Details Not Found";
	public static final String ERROR_OCCUR_TRYAGAIN = "Error occured the data, Try again";
	public static final String REGEX_SALARY = "([0-9]{3,}([.][0-9]{2}))|([0-9]{3,}?)";
	public static final String REGEX_CITYSTATE = "([a-zA-Z]{2,20})(([ ]([a-zA-Z]){2,20})?)";
	public static final String REGEX_NAME = "(([A-Za-z]{2,20})((([ ])([a-zA-Z]){2,20})?){1,4})";
	public static final String REGEX_MOBILENUMBER = "[7-9][0-9]{9}";
	public static final String REGEX_EMAILID = "(([a-z0-9]([.][a-z0-9]+)*){4,}[@]{1}[a-z]{2,8}[\\.]([a-z]{3,}([a-z\\.][a-z]{2})*))";
	public static final String REGEX_PINCODE = "([0-9]{6})";
	public static final String REGEX_EMPLOYEEID = "([0-9]{1,9})";
	public static final String REGEX_DOOR_NUMBER = "(([1-9]{1,3})(([a-zA-Z]{1,2})?))";
	public static final String REGEX_ADDRESS = "^[a-zA-Z]{2,15}([ ]?[a-zA-Z]{0,15}){0,}$";

	public static final String ERROR_CODE101 = "Error code 101 :";
	public static final String ERROR_CODE102 = "Error code 102 :";
	public static final String ERROR_CODE103 = "Error code 103 :";
	public static final String ERROR_CODE104 = "Error code 104 :";
	public static final String ERROR_CODE105 = "Error code 105 :";
	public static final String ERROR_CODE106 = "Error code 106 :";
	public static final String ERROR_CODE107 = "Error code 107 :";

	public static final String DUPLICATE_DATA = "Duplicate data occure, Enter the Validate data ";
	public static final String EMPLOYEE_NOT_FOUND = "Employee Details not found";
	public static final String PROJECT_NOT_FOUND = "PROJECT DETAILS NOT FOUND";
	public static final String PROJECT_ID_NOT_FOUND = "Project Id Not Found";
	public static final String EMPLOYEE_ID_NOT_FOUND = "Employee Id Not Found";
	
	public static final  String EMPLOYEE_DETAILS_ADDED = "Employee Details is Added";
	public static final  String EMPLOYEE_DETAILS_NOTADDED = "Employee Details can not Added";
	public static final  String EMPLOYEE_DETAILS_DISPLAYED = "Employee Details Displayed";
	public static final  String EMPLOYEE_DETAILS_UPDATED = "Employee Details ";
	public static final  String EMPLOYEE_DETAILS_NOT_UPDATED = "Employee DEtails Not Updated";
	
	public static final  String PROJECT_DETAILS_ADDED = "Project Details is Added";
	public static final  String PROJECT_DETAILS_NOTADDED = "Project Details can not Added";
	public static final  String PROJECT_DETAILS_DISPLAYED = "Project Details Displayed";
	public static final  String PROJECT_DETAILS_UPDATED = "Project Details ";
	public static final  String PROJECT_DETAILS_NOT_UPDATED = "Project DEtails Not Updated";
	public static final  String PROJECT_DETAIL_DELETED = "Project details Deleted";
}