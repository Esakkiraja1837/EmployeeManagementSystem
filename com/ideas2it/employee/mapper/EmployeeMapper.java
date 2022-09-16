package com.ideas2it.employee.mapper;

import com.ideas2it.employee.model.Address;
import com.ideas2it.employee.dto.AddressDTO;
import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.dto.EmployeeDTO;

/*
 * class Convert DTO object to model Object and Model to DTO
 *

 */
public class EmployeeMapper {

    /*
     * Convert Employee model object to EmployeeDTO object
     * 
     * @param employeeDTO 
     * @return employee
     */
    public static EmployeeDTO toEmployeeDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();

        employeeDTO.setName(employee.getName());
        employeeDTO.setId(employee.getId());
        employeeDTO.setAge(employee.getAge());
        employeeDTO.setMobileNumber(employee.getMobileNumber());
        employeeDTO.setEmailId(employee.getEmailId());
        employeeDTO.setSalary(employee.getSalary());
        employeeDTO.setAddress(toAddressDTO(employee.getAddress()));
        employeeDTO.setJoiningDate(employee.getJoiningDate());

        return employeeDTO;
    }

    /*
     * Convert EmployeeDTO object to Employee model object
     *
     * @param employee 
     * @return employeeDTO
     */
    public static Employee toEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();

        employee.setName(employeeDTO.getName());
        employee.setId(employeeDTO.getId());
        employee.setAge(employeeDTO.getAge());
        employee.setMobileNumber(employeeDTO.getMobileNumber());
        employee.setEmailId(employeeDTO.getEmailId());
        employee.setSalary(employeeDTO.getSalary());
        employee.setAddress(toAddress(employeeDTO.getAddress()));
        employee.setJoiningDate(employeeDTO.getJoiningDate());

        return employee;

    }

    /*
     * Convert Address model object to AddressDTO object
     *
     * @param addressDTO 
     * @return address
     */
    public static AddressDTO toAddressDTO(Address address) {
        AddressDTO addressDTO = new AddressDTO();

        addressDTO.setFlatNo(address.getFlatNo());
        addressDTO.setStreetName(address.getStreetName());
        addressDTO.setHomeTown(address.getHomeTown());
        addressDTO.setDistrict(address.getDistrict());
        addressDTO.setState(address.getState());
        addressDTO.setPinCode(address.getPinCode());

        return addressDTO;

    }

    /*
     * Convert AddressDTO object to Address model object
     *
     * @param address
     * @return addressDTO
     */
    public static Address toAddress(AddressDTO addressDTO) {
        Address address = new Address();

        address.setFlatNo(addressDTO.getFlatNo());
        address.setStreetName(addressDTO.getStreetName());
        address.setHomeTown(addressDTO.getHomeTown());
        address.setDistrict(addressDTO.getDistrict());
        address.setState(addressDTO.getState());
        address.setPinCode(addressDTO.getPinCode());

        return address;

    }

}