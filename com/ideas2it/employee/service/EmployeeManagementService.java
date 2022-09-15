package com.ideas2it.employee.service;

import com.ideas2it.employee.dao.Dao;
import com.ideas2it.employee.dao.impl.EmployeeDao;
import com.ideas2it.employee.model.AddressDTO;
import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.model.EmployeeDTO;
import com.ideas2it.employee.mapper.ModelMapper;
import com.ideas2it.employee.view.EmployeeView;

import java.util.List;
import java.util.ArrayList;

public class EmployeeManagementService {  
    EmployeeDao employeeDao = new EmployeeDao();
    ModelMapper modelMapper = new ModelMapper();

    /**
     * Get's the value from viewand transfer to service section.
     *
     * @return returns true if employee added
     */
    public boolean addEmployee(EmployeeDTO employeeDTO) {
        return employeeDao.addEmployee(modelMapper.toEmployee(employeeDTO));
    }

    /**
     * Transfers employee details to be displayed.
     *
     * @return the employee details from the service class.
     */
    public List<EmployeeDTO> displayEmployee() {
        List<Employee> employeeDetails = EmployeeDao.displayEmployee();
        List<EmployeeDTO> employeeDTO = new ArrayList<EmployeeDTO>();
        for (int i = 0; i < employeeDetails.size(); i++) {
            Employee employee = employeeDetails.get(i);
            employeeDTO.add(modelMapper.toEmployeeDTO(employee));
        }
        return employeeDTO;
    }

    /**
     * Transfer's employee details to be updated.
     * @param employee
     * @return the employee details from the service class.
     */
    public boolean updateEmployee(EmployeeDTO employeeDTO) {
        return employeeDao.updateEmployee(ModelMapper.toEmployee(employeeDTO));
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
        List<EmployeeDTO> employeesDTO = displayEmployee();
        EmployeeDTO searchEmployeeDto = null;
        for (int i = 0; i < employeesDTO.size(); i++) {
            if (employeesDTO.get(i).getName().equals(name)) {
                searchEmployeeDTO = employeesDTO.get(i);
            }
        }
        return searchEmployeeDTO;

}