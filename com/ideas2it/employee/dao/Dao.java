package com.ideas2it.employee.dao;

import com.ideas2it.employee.model.Address;
import com.ideas2it.employee.model.Employee;
import java.util.List;

/**
 * Interface used to declare the abstract method
 * to employeemanagementservice class.
 * @version 2.0.  13-09-2022.
 * @author  ESAKKITRAJA E.
 */
public interface Dao {  


    /**
     * save the employee Details.
     * @param employee object parameter.
     * @return Return the EmployeeDetails
     */
    boolean addEmployee(Employee employee);

    /**
     * Display the employee Details.
     * @param employee parameter.
     * @return Return the EmployeeDetails
     */
    List<Employee> displayEmployee();

    /**
     * update the employee Details.
     * @param employee object.
     * @return Return the boolean value.
     */ 
    boolean updateEmployee(Employee employee);

    /**
     * Delete the employee Details.
     * @param employee name.
     * @return Return the boolean value.
     */
    boolean deleteEmployee(String name);
}


