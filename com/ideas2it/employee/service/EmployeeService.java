package com.ideas2it.employee.service;

import com.ideas2it.employee.dto.AddressDTO;
import com.ideas2it.employee.dto.EmployeeDTO;
import java.util.List;

/**
 * Interface is used to declare the abstract method to service class
 *
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
    boolean addEmployee(EmployeeDTO employeeDTO);

    /**
     * Returns EmployeeDetail to be displayed.
     *
     * @param employee
     * @return Returns employee
     */
    List<EmployeeDTO> displayEmployee();

    /**
     * Receives relevent employee details from database.
     *
     * @param Employee name
     * @return returns relevent employee details
     */
    EmployeeDTO searchEmployee(String firstName);

    /**
     * Updates the employee detail and returns true if successful.
     *
     * @param employee
     * @return true if employee is updated
     */
    boolean updateEmployee(EmployeeDTO employeeDTO);

    /**
     * Deletes the employee details and returns true if successful.
     *
     * @param employee name
     * @return true if employee details are deleted.
     */
    boolean deleteEmployee(String firstName);

}