package com.ideas2it.employee.controller;

import com.ideas2it.employee.dto.AddressDTO;
import com.ideas2it.employee.dto.EmployeeDTO;
import com.ideas2it.employee.dto.ProjectDTO;
import com.ideas2it.employee.exception.EMSException;
import com.ideas2it.employee.service.EmployeeService;
import com.ideas2it.employee.service.employeeManagement.ProjectManagementService;
import com.ideas2it.employee.service.employeeManagement.EmployeeManagementService;
import com.ideas2it.employee.service.ProjectService;
import com.ideas2it.employee.view.ProjectView;

import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;

/**
 * Transfers employee details.
 *
 * @author  ESAKKIRAJA E.
 */
public class ProjectController {

     ProjectService projectService = new ProjectManagementService();

    /**
     * Get's the value from view and transfer to service section.
     *
     * @return returns value 
     */
    public int addProject(ProjectDTO projectDTO) throws EMSException {
        return projectService.addProject(projectDTO);
    }

    /**
     * Transfers Project details.
     *
     * @return the Project details.
     */
    public List<ProjectDTO> getAllProject() throws EMSException {
        return projectService.getAllProject();
    }

    /**
     * Transfer's Project details to be updated.
     * @param projectDTO
     * @return the Project details.
     */
    public void updateProject(ProjectDTO projectDTO) throws EMSException {
        projectService.updateProject(projectDTO);
    }

    /**
     * Transfer's true if deleting process is complete.
     *
     * @return true if Project is deleted
     */
    public void deleteProject(int projectId) throws EMSException {
        projectService.deleteProject(projectId);
    }

    /**
     * Receives relevent Project details from database.
     *
     * @param Project name
     * @return Returns projectdetails.
     */
    public List<ProjectDTO> searchProject(String projectName) throws EMSException {
        return projectService.searchProject(projectName);
    }

   /**
     * Transfers Project details & validate pattern.
     *
     * @return the value
     */
    public boolean isValid(String regexPatter, String fieldValue) {
        return projectService.isValid(regexPatter, fieldValue);
    }

    /**
     * Check projectId exists or not.
     *
     * @parm project details(projectId).
     * @return Return value.
     *
     */
    public boolean isProjectIdExists(int projectId) throws EMSException {
        return projectService.isProjectIdExists(projectId);
    }

    /**
     * Check ProjectId exists or not.
     *
     * @parm project details(projectId).
     * @return Return value.
     *
     */
    public ProjectDTO getProjectIdPresent(int projectId) throws EMSException {
        return projectService.getProjectIdPresent(projectId);
    }

    /**
     * Used to validate the given employee present in the data or not.
     *
     * @param employee id from the user.
     * @return returns employee.
     *
     */
    public EmployeeDTO getEmployee(int employeeId) throws EMSException{
        EmployeeManagementService employeeManagementService = new EmployeeManagementService();
        EmployeeDTO employeeDto = employeeManagementService.getEmployeeIdPresent(employeeId);
        return employeeDto;
    }
}