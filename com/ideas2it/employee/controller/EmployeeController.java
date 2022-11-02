package com.ideas2it.employee.controller;

import com.ideas2it.employee.dto.AddressDTO;
import com.ideas2it.employee.dto.EmployeeDTO;
import com.ideas2it.employee.dto.ProjectDTO;
import com.ideas2it.employee.exception.EMSException;
import com.ideas2it.employee.service.EmployeeService;
import com.ideas2it.employee.service.employeeManagement.EmployeeManagementService;
import com.ideas2it.employee.service.employeeManagement.ProjectManagementService;
import com.ideas2it.employee.service.ProjectService;
import com.ideas2it.employee.view.EmployeeView;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.time.LocalDate;
import java.time.Period;


/**
 * Transfers employee details.
 *
 * @author  ESAKKIRAJA E.
 */
public class EmployeeController { 
 
    EmployeeManagementService employeeService = new EmployeeManagementService();

    /**
     * Get's the value from view and transfer to service section.
     *
     * @return returns true 
     */
    public int addEmployee(EmployeeDTO employeeDTO) throws EMSException{
        return employeeService.addEmployee(employeeDTO);
    }

   /**
     * Transfers employee details & validate pattern.
     *
     * @return the value
     */
    public boolean isValid(String regexPattern, String fieldValue) {
        return employeeService.isValid(regexPattern, fieldValue);
    }

    /**
     * Transfers employee details.
     *
     * @return the employee details.
     */
    public List<EmployeeDTO> getAllEmployee() throws EMSException {
        return employeeService.getAllEmployee();
    }

    /**
     * Transfer's employee details to be updated.
     * @param employeeDTO
     * @return the employee details.
     */
    public void updateEmployee(EmployeeDTO employeeDTO) throws EMSException {
        employeeService.updateEmployee(employeeDTO);
    }

    /**
     * Transfer's true if deleting process is complete.
     *
     * @return true if employee is deleted
     */
    public void removeEmployee(int employeeId) throws EMSException {
        employeeService.deleteEmployee(employeeId);
    }

    /**
     * Receives relevent employee details from database.
     *
     * @param Employee name
     * @return Returns employeedetails.
     */
    public List<EmployeeDTO> searchEmployee(String firstName) throws EMSException {
        return employeeService.searchEmployee(firstName);  
    }

    /**
     * validate Employee joiningDate.
     *
     * @parm employee dateOfBirth,joiningDate.
     * @return Return value.
     *
     */
    public boolean validateJoiningDate(LocalDate dateOfBirth, LocalDate joiningDate) {
        return employeeService.validateJoiningDate(dateOfBirth, joiningDate);
    }

    /**
     * validate Employee DateOfBirth.
     *
     * @parm employee dateOfBirth.
     * @return Return value.
     *
     */
    public boolean validateDateOfBirth(LocalDate dateOfBirth) {
        return employeeService.validateDateOfBirth(dateOfBirth);
    }
    
    /**
     * validate Employee MobileNumber.
     *
     * @parm employee details(mobileNumber).
     * @return Return value.
     *
     */
    public boolean validateMobileNumber(long mobileNumber) throws EMSException {
        return employeeService.validateMobileNumber(mobileNumber);
    }

    /**
     * validate Employee emailId.
     *
     * @parm employee details(emailId).
     * @return Return value.
     *
     */
    public boolean validateEmailId(String emailId) throws EMSException {
        return employeeService.validateEmailId(emailId);
    }

    /**
     * Check EmployeeId exists or not.
     *
     * @parm employee details(employeeId).
     * @return Return value.
     *
     */
    public boolean isEmployeeIdExists(int employeeId) throws EMSException {
        return employeeService.isEmployeeIdExists(employeeId);
    }

    /**
     * Check EmployeeId exists or not.
     *
     * @parm employee details(employeeId).
     * @return Return value.
     *
     */
    public EmployeeDTO getEmployeeIdPresent(int employeeId) throws EMSException {
        return employeeService.getEmployeeIdPresent(employeeId);
    } 

    public ProjectDTO getProject(int projectId) throws EMSException {
        ProjectManagementService projectManagementService = new ProjectManagementService();
        ProjectDTO projectDto = projectManagementService.getProjectIdPresent(projectId);
        return projectDto;
    }
}