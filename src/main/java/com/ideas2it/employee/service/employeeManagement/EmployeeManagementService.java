package com.ideas2it.employee.service.employeeManagement;

import com.ideas2it.employee.constant.EmployeeManagementConstant;
import com.ideas2it.employee.dao.EmployeeDao;
import com.ideas2it.employee.dao.ProjectManagementDao;
import com.ideas2it.employee.dto.AddressDTO;
import com.ideas2it.employee.dto.EmployeeDTO;
import com.ideas2it.employee.dto.ProjectDTO;
import com.ideas2it.employee.exception.EMSException;
import com.ideas2it.employee.model.Project;
import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.mapper.EmployeeMapper;
import com.ideas2it.employee.mapper.ProjectMapper;
import com.ideas2it.employee.service.ProjectService;
import com.ideas2it.employee.service.employeeManagement.ProjectManagementService;
import com.ideas2it.employee.service.EmployeeService;
import com.ideas2it.employee.view.EmployeeView;
import com.ideas2it.employee.util.ValidationUtil;

import java.time.LocalDate;
import java.time.Period;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;
import java.util.regex.Pattern;


/**
 * This Application used to maintain the employee details.
 * CRUD & Search operations were done in this application.
 * @author  ESAKKIRAJA E.
 */
public class EmployeeManagementService {  

    EmployeeDao employeeDao = new EmployeeDao();
    EmployeeMapper employeeMapper = new EmployeeMapper();
    ValidationUtil employeeUtil = new ValidationUtil();
    ProjectMapper projectMapper = new ProjectMapper();
    
    /**
     * {@inheritDoc}
     */
    public int addEmployee(EmployeeDTO employeeDTO) throws EMSException {
        return employeeDao.addEmployee(employeeMapper.toEmployee(employeeDTO));
    }

    /**
     * {@inheritDoc}
     */
    public static boolean isValid(String regexPattern, String fieldValue) {
        return Pattern.matches(regexPattern, fieldValue);
    }

    /**
     * {@inheritDoc}
     */
    public List<EmployeeDTO> getAllEmployee() throws EMSException {
        List<Employee> employeeDetails = employeeDao.getAllEmployee();

        List<EmployeeDTO> employeeDTOList = new ArrayList<EmployeeDTO>();
        EmployeeDTO employeeDto;

        for(Employee employee : employeeDetails ){
            employeeDto = employeeMapper.toEmployeeDTO(employee);

            if(null != employee.getProject()) {
                List<ProjectDTO> projectList = new ArrayList<ProjectDTO>();

                for(Project project : employee.getProject()) {
                    projectList.add(projectMapper.toProjectDTO(project));
                    employeeDto.setProject(projectList);
                }
            }
            employeeDTOList.add(employeeDto);
        }
        return employeeDTOList;
    }

    /**
     * {@inheritDoc}
     */
    public void updateEmployee(EmployeeDTO employeeDTO) throws EMSException {
        employeeDao.updateEmployee(employeeMapper.toEmployee(employeeDTO));
    }

    /**
     * {@inheritDoc}
     */
    public void deleteEmployee(int employeeId) throws EMSException {
        employeeDao.deleteEmployee(employeeId);
    }

    /**
     * {@inheritDoc}
     */
    public List<EmployeeDTO> searchEmployee(String firstName) throws EMSException {
        List<Employee> employeeDetails = employeeDao.searchEmployee(firstName);
        List<EmployeeDTO> employeeDto = new ArrayList<EmployeeDTO>();

        for (Employee employee : employeeDetails) {
            employeeDto.add(employeeMapper.toEmployeeDTO(employee));
        }
        return employeeDto;
    }


    /**
     * {@inheritDoc}
     */
    public boolean validateJoiningDate(LocalDate dateOfBirth, LocalDate joiningDate) {
       return employeeUtil.validateJoiningDate(dateOfBirth, joiningDate);
    }

    /**
     * {@inheritDoc}
     */
    public boolean validateDateOfBirth(LocalDate dateOfBirth) {
        return employeeUtil.validateDateOfBirth(dateOfBirth);
    }

    /**
     * {@inheritDoc}
     */
    public boolean validateMobileNumber(long mobileNumber) throws EMSException {
        return (!(getAllEmployee().stream().anyMatch(employeeDTO -> String.valueOf
              (employeeDTO.getMobileNumber()).equals(Long.toString(mobileNumber)))));
    }

    /**
     * {@inheritDoc}
     */
    public boolean validateEmailId(String emailId) throws EMSException {
        return (!(getAllEmployee().stream().anyMatch
                (employeeDTO -> employeeDTO.getEmailId().equals(emailId))));
    }

    /**
     * {@inheritDoc}
     */
    public boolean isEmployeeIdExists(int employeeId) throws EMSException {

        List<Employee> employees = employeeDao.getAllEmployee();
        boolean isValid = false;
        EmployeeDTO employeeDto = null;

        for (Employee employee : employees) {

            if (employee.getEmployeeId() == employeeId) {
                employeeDto = (employeeMapper.toEmployeeDTO(employee));
                isValid = true;
            }
        }
        return isValid;
    }

    /**
     * {@inheritDoc}
     */
    public EmployeeDTO getEmployeeIdPresent(int employeeId) throws EMSException {

        List<Employee> employees = employeeDao.getAllEmployee();
        EmployeeDTO employeeDto = null;

        for (Employee employee : employees) {

            if (employee.getEmployeeId() == employeeId) {
                employeeDto = (employeeMapper.toEmployeeDTO(employee));
            }
        }
        return employeeDto;
    }
}