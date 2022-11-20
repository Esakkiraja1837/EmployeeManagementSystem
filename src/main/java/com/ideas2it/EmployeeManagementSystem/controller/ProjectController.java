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
import com.ideas2it.EmployeeManagementSystem.dto.ProjectDTO;
import com.ideas2it.EmployeeManagementSystem.exception.EMSException;
import com.ideas2it.EmployeeManagementSystem.service.employeeManagement.EmployeeManagementService;
import com.ideas2it.EmployeeManagementSystem.service.employeeManagement.ProjectManagementService;

/**
 * Transfers employee details.
 *
 * @author ESAKKIRAJA E.
 */
@RestController
@RequestMapping("api/v1/ems/project")
public class ProjectController {

	@Autowired
	ProjectManagementService projectService;

	/**
	 * Add ProjectDetails.
	 * 
	 */
	@PostMapping("/add")
	public ResponseEntity<ProjectDTO> addProject(@RequestBody ProjectDTO projectDTO) throws EMSException {
		return new ResponseEntity<ProjectDTO>(projectService.addProject(projectDTO), HttpStatus.CREATED);
	}

	/**
	 * Get All Project details.
	 *
	 */
	@GetMapping("/show")
	public ResponseEntity<List<ProjectDTO>> getAllProject() throws EMSException {
		return new ResponseEntity<List<ProjectDTO>>(projectService.getAllProject(), HttpStatus.FOUND);
	}

	/**
	 * Update Project Details.
	 */
	@PutMapping("/update")
	@ResponseBody
	public ResponseEntity<ProjectDTO> updateProject(@RequestBody ProjectDTO projectDTO) throws EMSException {
		return new ResponseEntity<ProjectDTO>(projectService.updateProject(projectDTO), HttpStatus.OK);
	}

	/**
	 * Delete Project Details(ProjectId).
	 *
	 */
	@DeleteMapping("/delete/{id}")
	@ResponseBody
	public ResponseEntity<String> DeleteProject(@PathVariable("id") int projectId) throws EMSException {
		projectService.deleteProject(projectId);
		return new ResponseEntity<String>("Project Details Deleted", HttpStatus.OK);
	}

	/**
	 * Search particular PorjectDetails(id).
	 * 
	 * @throws EMSException
	 *
	 */
	@GetMapping("/search/{name}")
	public ResponseEntity<List<ProjectDTO>> searchProject(@PathVariable String name) throws EMSException {
		return new ResponseEntity<List<ProjectDTO>>(projectService.searchProject(name), HttpStatus.FOUND);
	}

	/**
	 * Employee Assigning for employee.
	 * 
	 * @throws EMSException
	 *
	 */
	@PutMapping("/assign/{employeeId}/{projectId}")
	public ResponseEntity<ProjectDTO> assigningEmployeeForProject(@PathVariable int projectId,
			@PathVariable int employeeId) throws EMSException {
		ProjectDTO projectDTO = projectService.assignEmployeeForProject(projectId, employeeId);
		return new ResponseEntity<ProjectDTO>(projectDTO, HttpStatus.CREATED);
	}

}