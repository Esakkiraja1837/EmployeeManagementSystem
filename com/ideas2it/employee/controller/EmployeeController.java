package com.ideas2it.employee.controller;

import com.ideas2it.employee.model.AddressDTO;
import com.ideas2it.employee.model.EmployeeDTO;
import com.ideas2it.employee.service.EmployeeManagementService;
import com.ideas2it.employee.view.EmployeeView;

import java.util.List;
import java.util.ArrayList;

public class EmployeeController {  
    EmployeeManagementService employeeService = new EmployeeManagementService();

    /**
     * Get's the value from view and transfer to service section.
     *
     * @return returns true 
     */
    public boolean addEmployee(EmployeeDTO employeeDTO) {
        return employeeService.addEmployee(employeeDTO);
    }

    /**
     * Transfers employee details.
     *
     * @return the employee details.
     */
    public List<EmployeeDTO> displayEmployee() {
        return employeeService.displayEmployee();
    }

    /**
     * Transfer's employee details to be updated.
     * @param employeeDTO
     * @return the employee details.
     */
    public boolean updateEmployee(EmployeeDTO employeeDTO) {
        return employeeService.updateEmployee(employeeDTO);
    }

    /**
     * Transfer's true if deleting process is complete.
     *
     * @return true if employee is deleted
     *
    public boolean deleteEmployee(String name) {
        return employeeService.deleteEmployee(name);
    }

    /**
     * Receives relevent employee details from database.
     *
     * @param Employee name
     * @return Returns employeedetails.
     */
    public EmployeeDTO searchEmployee(String name) {
        return employeeService.searchEmployee(name);
    }

}