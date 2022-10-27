package com.ideas2it.employee.service;

import com.ideas2it.employee.dto.AddressDTO;
import com.ideas2it.employee.dto.EmployeeDTO;
import com.ideas2it.employee.exception.EMSException;

import java.util.regex.Pattern;
import java.util.List;
import java.time.LocalDate;
import java.time.Period;


/**
 * Interface is used to declare the abstract method to service class
 *
 */
public interface EmployeeService {

    /**
     * Saves the employee details in above database
     * and return true if the process is successful.
     * 
     * @param employee from controller
     * @return Return the boolean value.
     */
    public int addEmployee(EmployeeDTO employeeDTO) throws EMSException;

    /**
     * Compares if the detail matches the expectation
     * and return true if it matches.
     * 
     * @param employee details
     * @return Return the boolean value.
     */
    public boolean isValid(String regexPattern, String fieldValue);

    /**
     * Returns EmployeeDetail to be displayed.
     *
     * @param employee
     * @return Returns employee
     */
    public List<EmployeeDTO> displayEmployee() throws EMSException;

    /**
     * Receives relevent employee details from database.
     *
     * @param Employee name
     * @return returns relevent employee details
     */
    public List<EmployeeDTO> searchEmployee(String firstName) throws EMSException;

    /**
     * Updates the employee detail and returns true if successful.
     *
     * @param employee
     * @return true if employee is updated
     */
    public boolean updateEmployee(EmployeeDTO employeeDTO) throws EMSException;

    /**
     * Deletes the employee details and returns true if successful.
     *
     * @param employee name
     * @return true if employee details are deleted.
     */
    public void deleteEmployee(int employeeId) throws EMSException;

    /**
     * validate the employee joiningDate
     * and return true if it matches.
     * 
     * @return Return the boolean value.
     */
    public boolean validateJoiningDate(LocalDate dateOfBirth, LocalDate joiningDate);



    /**
     * validate the employee dateOfBirth.
     * and return true if it matches.
     * 
     * @return Return the boolean value.
     */
    public boolean validateDateOfBirth(LocalDate dateOfBirth);

    /**
     * validate the employee mobileNumber.
     * and return true if it matches.
     * 
     * @return Return the boolean value.
     */
    public boolean validateMobileNumber(long mobileNumber) throws EMSException;


    /**
     * validate the employee emailId.
     * and return true if it matches.
     * 
     * @return Return the boolean value.
     */
    public boolean validateEmailId(String emailId) throws EMSException;

    /**
     * Check EmployeeId Exists or Not
     *
     *@return Return value
     */
    public boolean isEmployeeIdExists(int employeeId) throws EMSException;

    /**
     * Check EmployeeId Exists or Not
     *
     *@return Return EmployeeDetail
     */
    public EmployeeDTO getEmployeeIdPresent(int employeeId) throws EMSException;
}