package com.ideas2it.EmployeeManagementSystem.service;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import com.ideas2it.EmployeeManagementSystem.dto.ProjectDTO;
import com.ideas2it.EmployeeManagementSystem.exception.EMSException;
import com.ideas2it.EmployeeManagementSystem.model.Project;

/**
 * Interface is used to declare the abstract method to service class
 *
 */
public interface ProjectService {

	/**
	 * Saves the project details
	 *
	 * @return return the value.
	 * 
	 */
	public ProjectDTO addProject(ProjectDTO projectDTO) throws EMSException;

	/**
	 * ProjectDetail to be displayed.
	 *
	 * @param project
	 * @return Returns projectDetails.
	 */
	public List<ProjectDTO> getAllProject() throws EMSException;

	/**
	 * Updates the project detail.
	 *
	 * @return true if project is updated
	 */
	public ProjectDTO updateProject(ProjectDTO projectDTO) throws EMSException;

	/**
	 * Deletes the project details.
	 *
	 * @param projectId
	 */
	public void deleteProject(int projectId) throws EMSException;

	/**
	 * Receives relevent project details.
	 *
	 * @param projectName.
	 * @return returns relevent project details
	 */
	public List<ProjectDTO> searchProject(String name) throws EMSException;

	/**
	 * Check ProjectId Exists or Not
	 *
	 * @return Return ProjectDetails
	 */
	public Project getProjectIdPresent(int projectId) throws EMSException;

	/*
	 * Employee assigning for project
	 * 
	 * @param projectId
	 * 
	 * @return Return ProjectDetails
	 */
	public ProjectDTO assigningEmployeeForProject(@PathVariable int projectId, @PathVariable int employeeId)
			throws EMSException;


}