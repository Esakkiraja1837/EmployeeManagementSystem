package com.ideas2it.EmployeeManagementSystem.mapper;

import java.util.ArrayList;
import java.util.List;

import com.ideas2it.EmployeeManagementSystem.dto.AddressDTO;
import com.ideas2it.EmployeeManagementSystem.dto.EmployeeDTO;
import com.ideas2it.EmployeeManagementSystem.dto.ProjectDTO;
import com.ideas2it.EmployeeManagementSystem.model.Address;
import com.ideas2it.EmployeeManagementSystem.model.Employee;
import com.ideas2it.EmployeeManagementSystem.model.Project;

/**
 * class Convert DTO object to model Object and Model to DTO
 *
 */
public class ProjectMapper {

    /**
     * Convert Project object to ProjectDTO model object
     *
     * @param project
     * @return projectDTO.
     */
    public static ProjectDTO toProjectDTO(Project project) {

        ProjectDTO projectDTO = new ProjectDTO();
        List<EmployeeDTO> employeeDTOS = new ArrayList<EmployeeDTO>();

        if(null != project) {
            projectDTO.setProjectId(project.getProjectId());
            projectDTO.setProjectName(project.getProjectName());
            projectDTO.setDomain(project.getDomain());
            projectDTO.setClientName(project.getClientName());
            projectDTO.setClientEmailId(project.getClientEmailId());
            projectDTO.setStartDate(project.getStartDate());
            projectDTO.setDueDate(project.getDueDate());
            projectDTO.setEndDate(project.getEndDate());   

            if(null != project.getEmployee()) {
                for(Employee employees : project.getEmployee()) {
                    employeeDTOS.add(toEmployeeDTO(employees));
                }
                projectDTO.setEmployee(employeeDTOS);
            }
        }
        return projectDTO;
    }

    /**
     * Convert ProjectDTO object to Project model object
     *
     * @param ProjectDTO
     * @return Project.
     */
    public static Project toProject(ProjectDTO projectDTO) {
        EmployeeMapper employeeMapper = new EmployeeMapper();
        Project project = new Project();
        List<Employee> employee = new ArrayList<Employee>();

        if(null != projectDTO) {
            project.setProjectId(projectDTO.getProjectId());
            project.setProjectName(projectDTO.getProjectName());
            project.setDomain(projectDTO.getDomain());
            project.setClientName(projectDTO.getClientName());
            project.setClientEmailId(projectDTO.getClientEmailId());
            project.setStartDate(projectDTO.getStartDate());
            project.setDueDate(projectDTO.getDueDate());
            project.setEndDate(projectDTO.getEndDate());
            //project.setEmployee(toEmployee(projectDTO.getEmployeeDTO()));

            if(null != projectDTO.getEmployee()) {

                for(EmployeeDTO employees : projectDTO.getEmployee()) {
                    employee.add(toEmployee(employees));
                }
                project.setEmployee(employee);
            }
        }
        return project;
    }

    /**
     * Convert EmployeeDTO object to Employee model object
     *
     * @param employee 
     * @return employeeDTO
     */
    public static Employee toEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        List<Address> addresses = new ArrayList<Address>();
        List<Project> projects = new ArrayList<Project>();
        ProjectMapper projectMapper = new ProjectMapper();

        if (null != employeeDTO) {
            employee.setEmployeeId(employeeDTO.getEmployeeId());
            employee.setFirstName(employeeDTO.getFirstName());
            employee.setLastName(employeeDTO.getLastName());
            employee.setEmployeeId(employeeDTO.getEmployeeId());
            employee.setRole(employeeDTO.getRole());
            employee.setMobileNumber(employeeDTO.getMobileNumber());
            employee.setEmailId(employeeDTO.getEmailId());
            employee.setSalary(employeeDTO.getSalary());

            if (null != employeeDTO.getAddress()) {

                for(AddressDTO addresses1 : employeeDTO.getAddress()) {
                     addresses.add(toAddress(addresses1));
                }
                employee.setAddress(addresses);
            }

            employee.setJoiningDate(employeeDTO.getJoiningDate());
            employee.setDateOfBirth(employeeDTO.getDateOfBirth());
            employee.setGender(employeeDTO.getGender());
        }
        return employee;
    }

    /**
     * Convert Address model object to AddressDTO object
     *
     * @param addressDTO 
     * @return address
     */
    public static AddressDTO toAddressDTO(Address address) {
        AddressDTO addressDTO = new AddressDTO();

        addressDTO.setId(address.getId());
        addressDTO.setDoorNo(address.getDoorNo());
        addressDTO.setStreet(address.getStreet());
        addressDTO.setCity(address.getCity());
        addressDTO.setState(address.getState());
        addressDTO.setPinCode(address.getPinCode());
        addressDTO.setType(address.getType());

        return addressDTO;
    }

    /**
     * Convert AddressDTO object to Address model object
     *
     * @param address
     * @return addressDTO
     */
    public static Address toAddress(AddressDTO addressDTO) {
        Address address = new Address();

        address.setId(addressDTO.getId());
        address.setDoorNo(addressDTO.getDoorNo());
        address.setStreet(addressDTO.getStreet());
        address.setCity(addressDTO.getCity());
        address.setState(addressDTO.getState());
        address.setPinCode(addressDTO.getPinCode());
        address.setType(addressDTO.getType());

        return address;
    }

    /**
     * Convert Employee model object to EmployeeDTO object
     * 
     * @param employeeDTO 
     * @return employee
     */
    public static EmployeeDTO toEmployeeDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        List<AddressDTO> addressDTOs = new ArrayList<AddressDTO>();
        List<ProjectDTO> projectDTOs = new ArrayList<ProjectDTO>();
        ProjectMapper projectMapper = new ProjectMapper();

        if (null != employee) {
            employeeDTO.setEmployeeId(employee.getEmployeeId());
            employeeDTO.setFirstName(employee.getFirstName());
            employeeDTO.setLastName(employee.getLastName());
            employeeDTO.setEmployeeId(employee.getEmployeeId());
            employeeDTO.setRole(employee.getRole());
            employeeDTO.setMobileNumber(employee.getMobileNumber());
            employeeDTO.setEmailId(employee.getEmailId());
            employeeDTO.setSalary(employee.getSalary());

            if(null != employee.getAddress()) {
                for (Address addresses : employee.getAddress()) {
                    addressDTOs.add(toAddressDTO(addresses));
                 }
                 employeeDTO.setAddress(addressDTOs);
            }
            employeeDTO.setJoiningDate(employee.getJoiningDate());
            employeeDTO.setDateOfBirth(employee.getDateOfBirth());
            employeeDTO.setGender(employee.getGender());
        }
        return employeeDTO;
    }
}
