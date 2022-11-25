package com.ideas2it.EmployeeManagementSystem.service.employeeManagement;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.adapter.ThrowsAdviceInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.ideas2it.EmployeeManagementSystem.constant.EmployeeManagementConstant;
import com.ideas2it.EmployeeManagementSystem.dao.Repository.ProjectManagementRepo;
import com.ideas2it.EmployeeManagementSystem.dto.ProjectDTO;
import com.ideas2it.EmployeeManagementSystem.exception.EMSException;
import com.ideas2it.EmployeeManagementSystem.mapper.ProjectMapper;
import com.ideas2it.EmployeeManagementSystem.model.Employee;
import com.ideas2it.EmployeeManagementSystem.model.Project;
import com.ideas2it.EmployeeManagementSystem.service.EmployeeService;
import com.ideas2it.EmployeeManagementSystem.service.ProjectService;

/**
 * This Application used to maintain the Project details. CRUD & Search
 * operations were done in this application.
 *
 * @author ESAKKIRAJA E.
 */
@Service("projectService")
public class ProjectManagementService implements ProjectService {

	@Autowired
	ProjectManagementRepo projectManagementRepo;

	@Autowired
	ApplicationContext context;

	private static final Logger log = LoggerFactory.getLogger(ProjectManagementService.class);

	/**
	 * {@inheritDoc}
	 */
	public ProjectDTO addProject(ProjectDTO projectDTO) {
		Project project = ProjectMapper.toProject(projectDTO);
		ProjectDTO projectDTOs = ProjectMapper.toProjectDTO(projectManagementRepo.save(project));
		log.info(EmployeeManagementConstant.PROJECT_DETAILS_ADDED + "PROJECT ID: " + projectDTO.getProjectId());
		return projectDTOs;

	}

	/**
	 * {@inheritDoc}
	 */
	public List<ProjectDTO> getAllProject() {
		List<Project> projectDetail = projectManagementRepo.findAll();
		List<ProjectDTO> projectDTOs = new ArrayList<ProjectDTO>();

		for (Project project : projectDetail) {
			projectDTOs.add(ProjectMapper.toProjectDTO(project));
			log.info(EmployeeManagementConstant.PROJECT_DETAILS_DISPLAYED);
		}
		return projectDTOs;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @return
	 */
	public ProjectDTO updateProject(ProjectDTO projectDTO) {
		Project project = ProjectMapper.toProject(projectDTO);
		ProjectDTO projectDto = ProjectMapper.toProjectDTO(projectManagementRepo.save(project));
		log.info(EmployeeManagementConstant.PROJECT_DETAILS_NOT_UPDATED);
		return projectDto;
	}

	/**
	 * {@inheritDoc}
	 */
	public void deleteProject(int projectId) throws EMSException {
		if (getProjectIdPresent(projectId) != null) {
			projectManagementRepo.deleteById(projectId);
			log.info(EmployeeManagementConstant.PROJECT_DETAIL_DELETED + "PROJECT ID :" + projectId);
		} else {
			log.info(EmployeeManagementConstant.PROJECT_ID_NOT_FOUND);
			throw new EMSException(EmployeeManagementConstant.ERROR_CODE104,
					EmployeeManagementConstant.PROJECT_ID_NOT_FOUND);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ProjectDTO> searchProject(String name) throws EMSException {
		List<Project> projects = projectManagementRepo.findByName(name);
		List<ProjectDTO> projectDTOs = new ArrayList<ProjectDTO>();

		if (!projects.isEmpty()) {
			for (Project project : projects) {
				projectDTOs.add(ProjectMapper.toProjectDTO(project));
				log.info(EmployeeManagementConstant.PROJECT_DETAILS_DISPLAYED);
			}
		} else {
			throw new EMSException(EmployeeManagementConstant.ERROR_CODE105,
					EmployeeManagementConstant.PROJECT_NOT_FOUND);
		}
		return projectDTOs;
	}

	/**
	 * {@inheritDoc}
	 */
	public Project getProjectIdPresent(int projectId) throws EMSException {
		return projectManagementRepo.findById(projectId)
				.orElseThrow(() -> new EMSException(EmployeeManagementConstant.ERROR_CODE106,
						EmployeeManagementConstant.PROJECT_ID_NOT_FOUND));
	}

	/**
	 * {@inheritDoc}
	 */
	public ProjectDTO assignEmployeeForProject(int employeeId, int projectId) throws EMSException {
		EmployeeService service = (EmployeeManagementService) context.getBean("employeeService");
		Project project = getProjectIdPresent(projectId);
		Employee employee = service.getEmployeeIdPresent(employeeId);
		ProjectDTO projectDTO = null;

		if (employee != null && project != null) {
			project.getEmployee().add(employee);
			projectDTO = ProjectMapper.toProjectDTO(projectManagementRepo.save(project));
		} else {
			throw new EMSException(EmployeeManagementConstant.ERROR_CODE105,
					EmployeeManagementConstant.EMPLOYEE_DETAILS_NOTADDED);
		}
		return projectDTO;
	}

	@Override
	public ProjectDTO assigningEmployeeForProject(int projectId, int employeeId) throws EMSException {
		// TODO Auto-generated method stub
		return null;
	}

}