package com.ideas2it.EmployeeManagementSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ideas2it.EmployeeManagementSystem.dto.EmployeeDTO;
import com.ideas2it.EmployeeManagementSystem.exception.EMSException;
import com.ideas2it.EmployeeManagementSystem.service.employeeManagement.EmployeeManagementService;

/**
 * Transfers employee details.
 *
 * @author ESAKKIRAJA E.
 */
@RestController
@RequestMapping("api/v1/ems/employee")
public class EmployeeController {

	@Autowired
    EmployeeManagementService employeeService;

	/**
	 * user add the EmployeeDetails.
	 * 
	 * @throws EMSException
	 *
	 */
	@PostMapping("/add")
	public ResponseEntity<EmployeeDTO> addEmployee(@RequestBody EmployeeDTO employeeDTO) throws EMSException {
		return new ResponseEntity<EmployeeDTO>(employeeService.addEmployee(employeeDTO), HttpStatus.CREATED);
	}

	/**
	 * Get All employee details.
	 *
	 */
	@GetMapping("/show")
	@ResponseBody
	public ResponseEntity<List<EmployeeDTO>> getAllEmployee() throws EMSException  {
		return new ResponseEntity<List<EmployeeDTO>>(employeeService.getAllEmployee(), HttpStatus.FOUND);
	}

	/**
	 * Update employee details.
	 * 
	 * @throws EMSException
	 */
	@PutMapping("/update")
	public ResponseEntity<EmployeeDTO> updateEmployee(@RequestBody EmployeeDTO employeeDTO) throws EMSException {
		return new ResponseEntity<EmployeeDTO>(employeeService.updateEmployee(employeeDTO), HttpStatus.OK);
	}

	/**
	 * Delete the particular Employee Details.
	 *
	 */
	@DeleteMapping("/delete/{id}")
	@ResponseBody
	public ResponseEntity<String> DeleteEmployee(@PathVariable("id") int employeeId) throws EMSException {
		employeeService.deleteEmployee(employeeId);
		return new ResponseEntity<>("Employee Details Deleted", HttpStatus.OK);
	}

	/**
	 * Show All employee details from database.
	 *
	 */
	@GetMapping("/search/{name}")
	public ResponseEntity<List<EmployeeDTO>> searchEmployee(@PathVariable String name) throws EMSException  {
		return new ResponseEntity<List<EmployeeDTO>>(employeeService.searchEmployee(name), HttpStatus.FOUND);
	}

	/**
	 * Employee Assigning for Project.
	 * 
	 * @throws EMSException
	 *
	 */
	@PutMapping("/assign/{employeeId}/{projectId}")
	public ResponseEntity<EmployeeDTO> assigingProjectForEmployee(@PathVariable int employeeId,
			@PathVariable int projectId) throws EMSException {
		EmployeeDTO employeeDTO = employeeService.assignProjectForEmployee(employeeId, projectId);
		return new ResponseEntity<EmployeeDTO>(employeeDTO, HttpStatus.CREATED);
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

}