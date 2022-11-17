package com.ideas2it.employee.service;

import com.ideas2it.employee.dto.ProjectDTO;
import com.ideas2it.employee.dto.AddressDTO;
import com.ideas2it.employee.dto.EmployeeDTO;

import com.ideas2it.employee.exception.EMSException;

import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;


/**
 * Interface is used to declare the abstract method to service class
 *
 */
public interface ProjectService {

    /**
     * Saves the project details in above database
     * and return true if the process is successful.
     * 
     * @param project from controller
     * @return Return the int value.
     */
    public int addProject(ProjectDTO projectDTO) throws EMSException;

    /**
     * Returns ProjectDetail to be displayed.
     *
     * @param project
     * @return Returns project.
     */
    public List<ProjectDTO> getAllProject() throws EMSException;

    /**
     * Updates the project detail and returns true if successful.
     *
     * @param project
     * @return true if project is updated
     */
    public void updateProject(ProjectDTO projectDTO) throws EMSException;

    /**
     * Deletes the project details and returns true if successful.
     *
     * @param projectId
     * @return true if project details are deleted.
     */
    public void deleteProject(int projectId) throws EMSException;

    /**
     * Receives relevent project details from database.
     *
     * @param projectname
     * @return returns relevent project details
     */
    public List<ProjectDTO> searchProject(String projectName) throws EMSException;

    /**
     * Compares if the detail matches the expectation
     * and return true if it matches.
     * 
     * @param project details
     * @return Return the boolean value.
     */
    public boolean isValid(String regexPatter, String fieldValue);

    /**
     * Check ProjectId Exists or Not
     *
     *@return Return value
     */
    public boolean isProjectIdExists(int projectId) throws EMSException;

    /**
     * Check ProjectId Exists or Not
     *
     *@return Return ProjectDetails
     */
    public ProjectDTO getProjectIdPresent(int projectId) throws EMSException;
}