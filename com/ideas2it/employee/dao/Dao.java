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
     * Save the address details.
     * @param address details.
     * @param address details.
     */
    boolean addAddress(Address address, int employeeId);


    /**
     * Display the employee Details.
     * @param employee.
     * @return Return the EmployeeDetails
     */
    List<Employee> displayEmployee();

    /**
     * Update address details by employee name,
     * If name found it update address details else it doesn't.
     * @param address details.
     */
    boolean updateAddress(Address address, int employeeId);

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
    boolean deleteEmployee(String firstName);

    /**
     * search the employee Details.
     * @param employee name.
     * @return Return the boolean value.
     */
    public Employee searchEmployee(String name);
}


