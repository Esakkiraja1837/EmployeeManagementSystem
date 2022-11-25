package com.ideas2it.EmployeeManagementSystem.service.employeeManagement;

import java.util.ArrayList;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.ideas2it.EmployeeManagementSystem.constant.EmployeeManagementConstant;
import com.ideas2it.EmployeeManagementSystem.dao.Repository.EmployeeManagementRepo;
import com.ideas2it.EmployeeManagementSystem.dto.EmployeeDTO;
import com.ideas2it.EmployeeManagementSystem.dto.ProjectDTO;
import com.ideas2it.EmployeeManagementSystem.exception.EMSException;
import com.ideas2it.EmployeeManagementSystem.mapper.EmployeeMapper;
import com.ideas2it.EmployeeManagementSystem.mapper.ProjectMapper;
import com.ideas2it.EmployeeManagementSystem.model.Employee;
import com.ideas2it.EmployeeManagementSystem.model.Project;
import com.ideas2it.EmployeeManagementSystem.service.EmployeeService;
import com.ideas2it.EmployeeManagementSystem.service.ProjectService;

/**
 * This Application used to maintain the employee details. CRUD & Search
 * operations were done in this application.
 * 
 * @author ESAKKIRAJA E.
 */
@Service("employeeService")
public class EmployeeManagementService implements EmployeeService {

	private static final Logger log = LoggerFactory.getLogger(EmployeeManagementService.class);

	@Autowired
	EmployeeManagementRepo employeeManagementRepo;

	@Autowired
	ApplicationContext context;

	/**
	 * {@inheritDoc}
	 * 
	 * @throws EMSException
	 */
	public EmployeeDTO addEmployee(EmployeeDTO employeeDTO) throws EMSException {
		Employee employee = EmployeeMapper.toEmployee(employeeDTO);

		if (validateEmployeeDetails(employeeDTO) != false) {
			employeeManagementRepo.save(employee);
			log.info(EmployeeManagementConstant.EMPLOYEE_DETAILS_ADDED);
		} else {

			log.error(EmployeeManagementConstant.EMPLOYEE_DETAILS_NOTADDED);
			throw new EMSException(EmployeeManagementConstant.ERROR_CODE101, EmployeeManagementConstant.DUPLICATE_DATA);
		}
		return EmployeeMapper.toEmployeeDTO(employee);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<EmployeeDTO> getAllEmployee() throws EMSException {
		List<Employee> employeeDetail = employeeManagementRepo.findAll();
		List<EmployeeDTO> employeeDtos = new ArrayList<EmployeeDTO>();
		if (!employeeDetail.isEmpty()) {
			for (Employee employee : employeeDetail) {
				employeeDtos.add(EmployeeMapper.toEmployeeDTO(employee));
				log.info(EmployeeManagementConstant.EMPLOYEE_DETAILS_DISPLAYED);

			}
		} else {
			log.error(EmployeeManagementConstant.EMPLOYEE_DETAILS_NOTFOUND);
			throw new EMSException(EmployeeManagementConstant.ERROR_CODE103,
					EmployeeManagementConstant.EMPLOYEE_DETAILS_NOTFOUND);

		}
		return employeeDtos;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws EMSException
	 */
	public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO) throws EMSException {
		Employee employee = EmployeeMapper.toEmployee(employeeDTO);

		if (validateEmployeeDetails(employeeDTO) != false) {
			employeeManagementRepo.save(employee);
			log.info(EmployeeManagementConstant.EMPLOYEE_DETAILS_UPDATED);
		} else {
			log.error(EmployeeManagementConstant.EMPLOYEE_DETAILS_NOT_UPDATED);
			throw new EMSException(EmployeeManagementConstant.ERROR_CODE101, EmployeeManagementConstant.DUPLICATE_DATA);
		}
		return EmployeeMapper.toEmployeeDTO(employee);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws EMSException
	 */
	public void deleteEmployee(int employeeId) throws EMSException {
		if (getEmployeeIdPresent(employeeId) != null) {
			employeeManagementRepo.deleteById(employeeId);
			log.info("Employee Details Deleted");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public List<EmployeeDTO> searchEmployee(String name) {
		List<Employee> employees = employeeManagementRepo.findByName(name);
		List<EmployeeDTO> employeeDtos = new ArrayList<EmployeeDTO>();

		if (!employees.isEmpty()) {
			for (Employee employee : employees) {
				employeeDtos.add(EmployeeMapper.toEmployeeDTO(employee));
				log.info(EmployeeManagementConstant.EMPLOYEE_DETAILS_ADDED);
			}
		}
		log.error(EmployeeManagementConstant.EMPLOYEE_DETAILS_NOTFOUND);
		return employeeDtos;
	}

	/**
	 * {@inheritDoc}
	 */
	public Employee getEmployeeIdPresent(int employeeId) throws EMSException {
		return employeeManagementRepo.findById(employeeId)
				.orElseThrow(() -> new EMSException(EmployeeManagementConstant.ERROR_CODE106,
						EmployeeManagementConstant.EMPLOYEE_ID_NOT_FOUND));
	}

	public EmployeeDTO assignProjectForEmployee(int employeeId, int projectId) throws EMSException {
		ProjectService service = (ProjectManagementService) context.getBean("projectService");
		Employee employee = getEmployeeIdPresent(employeeId);
		Project project = service.getProjectIdPresent(projectId);
		EmployeeDTO employeeDTO = null;

		if (employee != null && project != null) {
			List<Project> projects = employee.getProject();
			projects.add(project);
			employeeDTO = EmployeeMapper.toEmployeeDTO(employeeManagementRepo.save(employee));
		} else {
			throw new EMSException("Employee Details Not Exists", "103");
		}
		return employeeDTO;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean validateEmployeeDetails(EmployeeDTO employeeDTO) throws EMSException {
		boolean isValidDetail = false;

		if (validateEmailId(employeeDTO.getEmailId()) && (validateMobileNumber(employeeDTO.getMobileNumber()))) {
			isValidDetail = true;
		}
		return isValidDetail;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean validateMobileNumber(long mobileNumber) throws EMSException {
		return (!(getAllEmployee().stream().anyMatch(
				employeeDTO -> String.valueOf(employeeDTO.getMobileNumber()).equals(Long.toString(mobileNumber)))));
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean validateEmailId(String emailId) throws EMSException {
		return (!(getAllEmployee().stream().anyMatch(employeeDTO -> employeeDTO.getEmailId().equals(emailId))));
	}

	@Override
	public EmployeeDTO addProject(EmployeeDTO employeeDTO) {

		return null;
	}

}