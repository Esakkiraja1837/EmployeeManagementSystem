package com.ideas2it.employee.dao;

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
    boolean addEmployee(Employee employee);

    /**
     * Display the employee Details.
     * @param employee.
     * @return Return the EmployeeDetails
     */
    List<Employee> displayEmployee();

    /**
     * update the employee Details.
     * @param employee.
     * @return Return the boolean value.
     */ 
    boolean updateEmployee(Employee employee);

    /**
     * Delete the employee Details.
     * @param employee.
     * @return Return the boolean value.
     */
    boolean deleteEmployee(String name);
}


