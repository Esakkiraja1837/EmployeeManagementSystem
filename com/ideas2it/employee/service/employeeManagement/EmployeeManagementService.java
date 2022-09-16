package com.ideas2it.employee.service.employeeManagement;

import com.ideas2it.employee.dao.Dao;
import com.ideas2it.employee.dao.impl.EmployeeDao;
import com.ideas2it.employee.dto.AddressDTO;
import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.service.EmployeeService;
import com.ideas2it.employee.dto.EmployeeDTO;
import com.ideas2it.employee.mapper.EmployeeMapper;
import com.ideas2it.employee.view.EmployeeView;

import java.util.List;
import java.util.ArrayList;

public class EmployeeManagementService {  
    EmployeeDao employeeDao = new EmployeeDao();
    EmployeeMapper employeeMapper = new EmployeeMapper();

    /**
     * Get's the value from viewand transfer to service section.
     *
     * @return returns true if employee added
     */
    public boolean addEmployee(EmployeeDTO employeeDTO) {
        return employeeDao.addEmployee(employeeMapper.toEmployee(employeeDTO));
    }

    /**
     * Transfers employee details to be displayed.
     *
     * @return the employee details from the service class.
     */
    public List<EmployeeDTO> displayEmployee() {
        List<Employee> employeeDetails = employeeDao.displayEmployee();
        List<EmployeeDTO> employeeDto = new ArrayList<EmployeeDTO>();
        for (int i = 0; i < employeeDetails.size(); i++) {
            Employee employee = employeeDetails.get(i);
            employeeDto.add(employeeMapper.toEmployeeDTO(employee));
        }
        return employeeDto;
    }

    /**
     * Transfer's employee details to be updated.
     * @param employee
     * @return the employee details from the service class.
     */
    public boolean updateEmployee(EmployeeDTO employeeDTO) {
        return employeeDao.updateEmployee(employeeMapper.toEmployee(employeeDTO));
    }

    /**
     * Transfer's true if deleting process is complete.
     *
     * @return true if employeeDetails deleted
     */
    public boolean deleteEmployee(String name) {
        return employeeDao.deleteEmployee(name);
    }

    public EmployeeDTO searchEmployee(String name) {
        List<EmployeeDTO> employeesDto = displayEmployee();
        EmployeeDTO searchEmployeeDto = null;
        for (int i = 0; i < employeesDto.size(); i++) {
            if (employeesDto.get(i).getName().equals(name)) {
                searchEmployeeDto = employeesDto.get(i);
            }
        }
        return searchEmployeeDto;
    }

}