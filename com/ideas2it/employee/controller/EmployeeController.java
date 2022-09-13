package com.ideas2it.employee.controller;

import com.ideas2it.employee.model.Address;
import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.service.EmployeeManagement;
import com.ideas2it.employee.service.impl.EmployeeManagementService;
import com.ideas2it.employee.view.EmployeeView;

import java.util.List;
import java.util.ArrayList;

public class EmployeeController {  
    EmployeeManagementService employeeManagementService = new EmployeeManagementService();

    /**
     * This method used to get the value from view and transfer the value
     * to service section.
     * @param employee object parameter is passed by controller
     * @return the employeeDeatil from the service class.
     * 13-09-2022.
     */
    public boolean addEmployee(Employee employee) {
        return employeeManagementService.addEmployee(employee);
    }

    /**
     * This method used to return the value.
     * @param employee object parameter is passed by controller.
     * @return the employeeDeatil from the service class.
     * 
     */
    public List<Employee> displayEmployee() {
        return employeeManagementService.displayEmployee();
    }

    /**
     * This method used to return the value.
     * @param employee object parameter is passed by controller
     * @return the employeeDeatil from the service class.
     */
    public boolean updateEmployee(Employee employee) {
        return employeeManagementService.updateEmployee(employee);
    }

    /**
     * This method used to return the value.
     * @param employee name parameter is passed by controller.
     * @return the employeeDeatil from the service class.
     */
    public boolean deleteEmployee(String name) {
        return employeeManagementService.deleteEmployee(name);
    }

    public Employee searchEmployee(String name) {
        return employeeManagementService.searchEmployee(name);
    }

}