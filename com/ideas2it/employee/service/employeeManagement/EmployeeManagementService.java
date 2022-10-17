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
import com.ideas2it.employee.constant.EmployeeManagementConstant;
import com.ideas2it.employee.util.ValidationUtil;

import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.util.Iterator;
import java.time.Period;

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
    public List<EmployeeDTO> searchEmployee(String firstName) throws EMSException {
        List<Employee> employeeDetails = employeeDao.searchEmployee(firstName);
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
        return (!(displayEmployee().stream().anyMatch(employeeDTO -> String.valueOf
              (employeeDTO.getMobileNumber()).equals(Long.toString(mobileNumber)))));
    }

    /**
     * {@inheritDoc}
     */
    public boolean validateEmailId(String emailId) throws EMSException {
        return (!(displayEmployee().stream().anyMatch
                (employeeDTO -> employeeDTO.getEmailId().equals(emailId))));
    }
}