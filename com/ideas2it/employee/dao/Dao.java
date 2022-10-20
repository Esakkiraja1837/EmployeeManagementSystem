package com.ideas2it.employee.dao;

import com.ideas2it.employee.exception.EMSException;
import com.ideas2it.employee.model.Address;
import com.ideas2it.employee.model.Employee;

import java.util.List;

/**
 * @interface class.
 * @version 2.0.  13-09-2022.
 * @author  ESAKKITRAJA E.
 */
public interface Dao {  

    /**
     * save the employee Details.
     * @param employee.
     * @return Return the EmployeeDetails
     */
    public int addEmployee(Employee employee) throws EMSException;

    /**
     * Display the employee Details.
     * @param employee.
     * @return Return the EmployeeDetails
     */
    public List<Employee> displayEmployee() throws EMSException;

    /**
     * update the employee Details.
     * @param employee.
     * @return Return the boolean value.
     */ 
    public void updateEmployee(Employee employee) throws EMSException;

    /**
     * Delete the employee Details.
     * @param employee.
     * @return Return the boolean value.
     */
    public void deleteEmployee(int employeeId) throws EMSException;

    /**
     * search the employee Details.
     * @param employee name.
     * @return Return the boolean value.
     */
    public List<Employee> searchEmployee(String firstName) throws EMSException;
}


