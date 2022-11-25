package com.ideas2it.EmployeeManagementSystem.service;

import java.util.List;

import com.ideas2it.EmployeeManagementSystem.dto.EmployeeDTO;
import com.ideas2it.EmployeeManagementSystem.exception.EMSException;
import com.ideas2it.EmployeeManagementSystem.model.Employee;

/**
 * Interface is used to declare the abstract method to service class
 *
 */
public interface EmployeeService {

	/**
	 * Saves the employee details
	 * 
	 * @param employee
	 * @return Return the EmployeeDetails.
	 */
	public EmployeeDTO addProject(EmployeeDTO employeeDTO) throws EMSException;

	/**
	 * Returns EmployeeDetail to be displayed.
	 *
	 * @param employee
	 * @return Returns employee
	 */
	public List<EmployeeDTO> getAllEmployee() throws EMSException;

	/**
	 * Receives relevent employee details from database.
	 *
	 * @param Employee id
	 * @return returns relevent employee details
	 */
	public List<EmployeeDTO> searchEmployee(String name) throws EMSException;

	/**
	 * Updates the employee detail and returns true if successful.
	 *
	 * @param employee
	 * @return the updated employeeDetails.
	 */
	public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO) throws EMSException;

	/**
	 * Deletes the employee details and returns true if successful.
	 *
	 * @param employee id.
	 */
	public void deleteEmployee(int employeeId) throws EMSException;

	/**
	 * Check EmployeeId Exists or Not
	 *
	 * @return Return EmployeeDetail
	 */
	public Employee getEmployeeIdPresent(int employeeId) throws EMSException;

	/**
	 * validate the employee mobileNumber. and return true if it matches.
	 * 
	 * @return Return the boolean value.
	 */
	public boolean validateMobileNumber(long mobileNumber) throws EMSException;

	/**
	 * validate the employee emailId. and return true if it matches.
	 * 
	 * @return Return the boolean value.
	 */
	public boolean validateEmailId(String emailId) throws EMSException;

	/*
	 * Validate Employee MobileNumber & EmailId
	 * 
	 * @return Return the value(true/false)
	 */
	public boolean validateEmployeeDetails(EmployeeDTO employeeDTO) throws EMSException;

	/*
	 * Employee Assigning for project
	 */
	public EmployeeDTO assignProjectForEmployee(int employeeId, int projectId) throws EMSException;
}