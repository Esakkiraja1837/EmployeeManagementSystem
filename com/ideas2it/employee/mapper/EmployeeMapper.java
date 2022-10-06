package com.ideas2it.employee.mapper;

import com.ideas2it.employee.dto.AddressDTO;
import com.ideas2it.employee.dto.EmployeeDTO;
import com.ideas2it.employee.model.Address;
import com.ideas2it.employee.model.Employee;


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

        employeeDTO.setFirstName(employee.getFirstName());
        employeeDTO.setLastName(employee.getLastName());
        employeeDTO.setEmployeeId(employee.getEmployeeId());
        employeeDTO.setRole(employee.getRole());
        employeeDTO.setMobileNumber(employee.getMobileNumber());
        employeeDTO.setEmailId(employee.getEmailId());
        employeeDTO.setSalary(employee.getSalary());
        employeeDTO.setAddress(toAddressDTO(employee.getAddress()));
        employeeDTO.setJoiningDate(employee.getJoiningDate());
        employeeDTO.setDateOfBirth(employee.getDateOfBirth());
        employeeDTO.setGender(employee.getGender());
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

        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setEmployeeId(employeeDTO.getEmployeeId());
        employee.setRole(employeeDTO.getRole());
        employee.setMobileNumber(employeeDTO.getMobileNumber());
        employee.setEmailId(employeeDTO.getEmailId());
        employee.setSalary(employeeDTO.getSalary());
        employee.setAddress(toAddress(employeeDTO.getAddress()));
        employee.setJoiningDate(employeeDTO.getJoiningDate());
        employee.setDateOfBirth(employeeDTO.getDateOfBirth());
        employee.setGender(employeeDTO.getGender());

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

        addressDTO.setDoorNo(address.getDoorNo());
        addressDTO.setStreet(address.getStreet());
        addressDTO.setCity(address.getCity());
        addressDTO.setState(address.getState());
        addressDTO.setPinCode(address.getPinCode());
        addressDTO.setType(address.getType());

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

        address.setDoorNo(addressDTO.getDoorNo());
        address.setStreet(addressDTO.getStreet());
        address.setCity(addressDTO.getCity());
        address.setState(addressDTO.getState());
        address.setPinCode(addressDTO.getPinCode());
        address.setType(addressDTO.getType());

        return address;

    }

}