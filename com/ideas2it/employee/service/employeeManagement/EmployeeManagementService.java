package com.ideas2it.employee.service.employeeManagement;

import com.ideas2it.employee.dao.Dao;
import com.ideas2it.employee.dao.impl.EmployeeDao;
import com.ideas2it.employee.dto.AddressDTO;
import com.ideas2it.employee.dto.EmployeeDTO;
import com.ideas2it.employee.exception.EMSException;
import com.ideas2it.employee.mapper.EmployeeMapper;
import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.service.EmployeeService;
import com.ideas2it.employee.view.EmployeeView;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


/**
 * This Application used to maintain the employee details.
 * CRUD & Search operations were done in this application.
 * @author  ESAKKIRAJA E.
 */
public class EmployeeManagementService {  

    EmployeeDao employeeDao = new EmployeeDao();

    EmployeeMapper employeeMapper = new EmployeeMapper();

    /**
     * {@inheritDoc}
     */
    public boolean addEmployee(EmployeeDTO employeeDTO) throws EMSException {
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
    public List<EmployeeDTO> displayEmployee() throws EMSException {
        List<Employee> employeeDetails = employeeDao.displayEmployee();

        List<EmployeeDTO> employeeDto = new ArrayList<EmployeeDTO>();

        for (int i = 0; i < employeeDetails.size(); i++) {
            Employee employee = employeeDetails.get(i);
            employeeDto.add(employeeMapper.toEmployeeDTO(employee));
        }
        return employeeDto;
    }

    /**
     * {@inheritDoc}
     */
    public boolean updateEmployee(EmployeeDTO employeeDTO) throws EMSException {
        return employeeDao.updateEmployee(employeeMapper.toEmployee(employeeDTO));
    }

    /**
     * {@inheritDoc}
     */
    public boolean deleteEmployee(int employeeId) throws EMSException {
        return employeeDao.deleteEmployee(employeeId);
    }

    /**
     * {@inheritDoc}
     */
    public EmployeeDTO searchEmployee(String firstName) throws EMSException {
        List<EmployeeDTO> employeesDto = displayEmployee();

        EmployeeDTO searchEmployeeDto = null;

        for (int i = 0; i < employeesDto.size(); i++) {
            if (employeesDto.get(i).getFirstName().equals(firstName)) {
                searchEmployeeDto = employeesDto.get(i);
            }
        }
        return searchEmployeeDto;
    }

}