package com.ideas2it.employee.service;

import com.ideas2it.employee.dto.AddressDTO;
import com.ideas2it.employee.dto.EmployeeDTO;
import com.ideas2it.employee.exception.EMSException;

import java.util.regex.Pattern;
import java.util.List;


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
    public boolean addEmployee(EmployeeDTO employeeDTO) throws EMSException;


    public boolean isValid(String regexPattern, String fieldValue);

    /**
     * Returns EmployeeDetail to be displayed.
     *
     * @param employee
     * @return Returns employee
     */
    public List<EmployeeDTO> displayEmployee() throws EMSException ;

    /**
     * Receives relevent employee details from database.
     *
     * @param Employee name
     * @return returns relevent employee details
     */
    public EmployeeDTO searchEmployee(String firstName) throws EMSException ;

    /**
     * Updates the employee detail and returns true if successful.
     *
     * @param employee
     * @return true if employee is updated
     */
    public boolean updateEmployee(EmployeeDTO employeeDTO) throws EMSException ;

    /**
     * Deletes the employee details and returns true if successful.
     *
     * @param employee name
     * @return true if employee details are deleted.
     */
    public boolean deleteEmployee(int employeeId) throws EMSException;

}