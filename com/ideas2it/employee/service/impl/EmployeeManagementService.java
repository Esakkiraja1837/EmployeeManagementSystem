package com.ideas2it.employee.service.impl;

import com.ideas2it.employee.controller.EmployeeController;
import com.ideas2it.employee.model.Address;
import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.service.EmployeeManagement;
import com.ideas2it.employee.view.EmployeeView;

import java.util.ArrayList;
import java.util.List;

public class EmployeeManagementService implements EmployeeManagement {
    List<Employee> employeeDetail = new ArrayList<Employee>();

    /**
     * {@inheritDoc}
     */
    @Override   
    public boolean addEmployee(Employee employee) {
        return employeeDetail.add(employee);    
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Employee> displayEmployee() {
        return employeeDetail;
    }

    /**
     * {@inheritDoc}
     */ 
    @Override
    public boolean updateEmployee(Employee employee) {
        boolean isUpdate = false;
        for (int i = 0; i < employeeDetail.size(); i++) {
            if(employeeDetail.get(i).getName().equals(employee.getName())) {
                employeeDetail.set(i,employee);
                isUpdate = true;
            }
        }
        return isUpdate;
    }

    /**
     * {@inheritDoc}
     */ 
    @Override
    public boolean deleteEmployee(String name) {
        boolean isDelete = false;
        for(int i = 0; i < employeeDetail.size(); i++) {
            if(employeeDetail.get(i).getName().equals(name)) {
                employeeDetail.remove(i);
                isDelete = true;
            }
        }
        return isDelete;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Employee searchEmployee(String name) {
        Employee searchEmployee = null;
        for(int i = 0; i < employeeDetail.size(); i++) {
            if(employeeDetail.get(i).getName().equals(name)) {
                searchEmployee = employeeDetail.get(i);
            }
        }
        return searchEmployee;
    }
}