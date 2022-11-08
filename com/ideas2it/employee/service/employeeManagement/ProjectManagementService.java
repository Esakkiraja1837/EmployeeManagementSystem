package com.ideas2it.employee.service.employeeManagement;

import com.ideas2it.employee.constant.EmployeeManagementConstant;
import com.ideas2it.employee.dao.EmployeeDao;
import com.ideas2it.employee.dao.ProjectManagementDao;
import com.ideas2it.employee.exception.EMSException;

import com.ideas2it.employee.dto.ProjectDTO;
import com.ideas2it.employee.dto.EmployeeDTO;
import com.ideas2it.employee.dto.AddressDTO;
import com.ideas2it.employee.exception.EMSException;
import com.ideas2it.employee.model.Project;
import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.model.Address;
import com.ideas2it.employee.mapper.EmployeeMapper;
import com.ideas2it.employee.mapper.ProjectMapper;
import com.ideas2it.employee.service.EmployeeService;
import com.ideas2it.employee.service.ProjectService;
import com.ideas2it.employee.service.employeeManagement.EmployeeManagementService;
import com.ideas2it.employee.view.ProjectView;

import java.time.LocalDate;
import java.util.regex.Pattern;
import java.util.List;
import java.util.ArrayList;


/**
 * This Application used to maintain the Project details.
 * CRUD & Search operations were done in this application.
 *
 * @author  ESAKKIRAJA E.
 */
public  class ProjectManagementService implements ProjectService {

    ProjectDTO projectDTO = new ProjectDTO();
    ProjectManagementDao projectManagementDao =  new ProjectManagementDao();
    ProjectMapper projectMapper = new ProjectMapper();
    EmployeeManagementService employeeManagementService = new EmployeeManagementService();

    /**
     * {@inheritDoc}
     */
    public int addProject(ProjectDTO projectDTO) throws EMSException{
        return projectManagementDao.addProject(projectMapper.toProject(projectDTO));
    }

    /**
     * {@inheritDoc}
     */
    public List<ProjectDTO> getAllProject() throws EMSException {
        List<Project> projectDetails = projectManagementDao.getAllProject();

        List<ProjectDTO> projectDto = new ArrayList<ProjectDTO>();

        for(Project project : projectDetails) {
            projectDto.add(projectMapper.toProjectDTO(project));
        }
        return projectDto;
    }

    /**
     * {@inheritDoc}
     */
    public void updateProject(ProjectDTO projectDTO) throws EMSException {
        projectManagementDao.updateProject(projectMapper.toProject(projectDTO));
    }

    /**
     * {@inheritDoc}
     */
    public void deleteProject(int projectId)  throws EMSException{
        projectManagementDao.deleteProject(projectId);
    }

    /**
     * {@inheritDoc}
     */
    public List<ProjectDTO> searchProject(String projectName) throws EMSException {
        List<Project> projectDetails = projectManagementDao.searchProject(projectName);
        List<ProjectDTO> projectDto = new ArrayList<ProjectDTO>();

        for (Project project : projectDetails) {
                projectDto.add(projectMapper.toProjectDTO(project));
        }
        return projectDto;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isValid(String regexPatter, String fieldValue) {
        return Pattern.matches(regexPatter, fieldValue);
    }

    /**
     * {@inheritDoc}
     */
    public boolean isProjectIdExists(int projectId) throws EMSException {
        List<Project> projects = projectManagementDao.getAllProject();
        boolean isValid = false;
        ProjectDTO projectDto = null;

        for(Project project : projects) {

            if(project.getProjectId() == projectId) {
                projectDto = (projectMapper.toProjectDTO(project));
                isValid = true;
            }
        }
        return isValid;
    }

    /**
     * {@inheritDoc}
     */
    public ProjectDTO getProjectIdPresent(int projectId) throws EMSException {
        List<Project> projects = projectManagementDao.getAllProject();
        ProjectDTO projectDto = null;

        for(Project project : projects) {

            if(project.getProjectId() == projectId) {
                projectDto = (projectMapper.toProjectDTO(project));
            }
        }
        return projectDto;
    }
}