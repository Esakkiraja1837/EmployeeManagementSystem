package com.ideas2it.employee.view;

import com.ideas2it.employee.constant.EmployeeManagementConstant;
import com.ideas2it.employee.controller.EmployeeController;
import com.ideas2it.employee.dto.AddressDTO;
import com.ideas2it.employee.dto.EmployeeDTO;
import com.ideas2it.employee.dao.Dao;
import com.ideas2it.employee.dao.impl.EmployeeDao;
import com.ideas2it.employee.service.EmployeeService;
import com.ideas2it.employee.util.EmployeeUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * This method used to get the details of the employee from the user
 * and set in the pojo class.
 * @version 2.0.  13-09-2022.
 * @author  ESAKKIRAJA E.
 */

public class EmployeeView {

    Scanner scanner = new Scanner(System.in);
    EmployeeController employeeController = new EmployeeController(); 
    EmployeeUtil employeeUtil = new EmployeeUtil();
    EmployeeDTO employeeDTO = new EmployeeDTO();

    /**
     *  method makes the user to choose the CRUD operation.
     */     
    public void chooseOption() {
        int exitOption = 1;
        String option;

        StringBuilder menuOption = new StringBuilder();
        menuOption.append("Employee Management - \n\n")
                     .append("\ta - CREATE \n")
                     .append("\tb - DISPLAY \n\tc - UPDATE \n\td - DELETE \n")
                     .append("\te - SEARCH \n\tf - EXIT \n\n");

        do {
                System.out.println(menuOption.toString());
            System.out.println(EmployeeManagementConstant.VALID_INPUT);
            option = scanner.next();

            switch (option) {
                case "a":
                    this.createEmployee();
                    break;

                case "b":
                    this.displayEmployee();
                    break;

                case "c":
                    this.updateEmployee();
                    break;

                case "d":
                    this.deleteEmployee();
                    break;

                case "e":
                    this.searchEmployee();
                    break;

                case "f":
                    exitOption = 0;
                    break;

                default:
                    System.out.println(EmployeeManagementConstant.INVALID_INPUT);
            }
        } while (exitOption != 0);
    }

    /**
     * Create and add employe details.
     * Get the employee values from the user.
     */
    public void createEmployee() {

        System.out.println(EmployeeManagementConstant.VALID_INPUT);
        try {
            System.out.println(EmployeeManagementConstant.EMPLOYEE_FIRSTNAME);
            String firstName = scanner.next();

            System.out.println(EmployeeManagementConstant.EMPLOYEE_LASTNAME);
            String lastName = scanner.next();

            System.out.println("Enter Employee Mobile Number: ");
            long mobileNumber = scanner.nextLong();

            System.out.println("Enter Employee Email ID: ");
            String emailId = scanner.next();

            System.out.println("Enter the Employee role: ");
            String role = scanner.next();

            System.out.println(EmployeeManagementConstant.EMPLOYEE_ID);
            int employeeId = scanner.nextInt();

            System.out.println(EmployeeManagementConstant.EMPLOYEE_SALARY);
            double salary = scanner.nextDouble();

            System.out.println("Enter date of Joinning (yyyy-mm-dd) ");
            LocalDate dateOfJoining = employeeUtil.getDate();


            System.out.println("Enter date of Birth (yyyy-mm-dd) ");
            LocalDate dateOfBirth = employeeUtil.getDate();

            System.out.println("Enter the Gender :");
            String gender = scanner.next();

            employeeDTO = new EmployeeDTO(firstName, lastName, employeeId,role, mobileNumber, emailId,
                                    salary, addAddress(), dateOfJoining, dateOfBirth, gender);

            if (employeeController.addEmployee(employeeDTO)) {
                System.out.println("Employee details stored.");
            } else {
                System.out.println("Employee details not stored.");
            }
        } catch (InputMismatchException e) {
            System.out.println(EmployeeManagementConstant.INVALID_INPUT);
        }
    }

    private AddressDTO addAddress() {
        System.out.println("Enter Employee Door Number: ");
        String doorNo = scanner.next();

        System.out.println("Enter Employee Street: ");
        String street = scanner.next();

        System.out.println("Enter Employee City: ");
        String city = scanner.next();

        System.out.println("Enter Employee State: ");
        String state = scanner.next();

        System.out.println("Enter Employee Pin Code: ");
        int pinCode = scanner.nextInt();

        System.out.println("Enter Address type: ");
        String type = scanner.next();

        AddressDTO addressDTO = new AddressDTO(doorNo, street, city,
                              state, pinCode, type);
        return addressDTO;
    }

    /**
     * Display the saved employeeDetails.
     */
    public void displayEmployee() {
        List<EmployeeDTO> employeesDetail = employeeController.displayEmployee();
        Iterator<EmployeeDTO> iterator = employeesDetail.iterator(); 
        while (iterator.hasNext()) {
            EmployeeDTO employeeDTO =  iterator.next();
            System.out.println(employeeDTO.toString());
        }
    }

    /**
     * update the employeeDetails
     * It found the given name update tha employee.
     * If the given name not found it shows out put as record not found.
     */
    public void updateEmployee() {

        System.out.println(EmployeeManagementConstant.EMPLOYEE_FIRSTNAME);
        String firstName = scanner.next();

        System.out.println(EmployeeManagementConstant.EMPLOYEE_LASTNAME);
        String lastName = scanner.next();

        System.out.println(EmployeeManagementConstant.EMPLOYEE_ID);
        int id = scanner.nextInt();
        employeeDTO.setEmployeeId(employeeId);

        System.out.println("Enter the employee role: ");
        String role = scanner.next(); 

        System.out.println("Enter Employee Mobile Number: ");
        long mobileNumber = scanner.nextLong();

        System.out.println("Enter Employee Email-ID: ");
        String emailId = scanner.next();

        System.out.println("Enter Employee Salary: ");
        double salary = scanner.nextDouble();

        AddressDTO addressDTO = addAddress();

        System.out.println("Enter date of Joinning (yyyy-mm-dd) ");
        LocalDate dateOfJoining = employeeUtil.getDate();

        System.out.println("Enter date of Birth (yyyy-mm-dd) ");
        LocalDate dateOfBirth = employeeUtil.getDate();

        System.out.println("Enter the Gender :");
        String gender = scanner.next();

        employeeDTO = new EmployeeDTO(firstName,lastName, employeeId,role, mobileNumber, emailId,
                                      salary, addressDTO, dateOfJoining, dateOfBirth, gender);

        if (employeeController.updateEmployee(employeeDTO)) {
            System.out.println("Update the Employee Details");
        } else {
            System.out.println("Not Update the Employee Details");
        }
    }

    /**
     * Deletes employee by employee name,
     * If found delete that employee object.
     * If the given name not found it shows out put as record not found.
     */
    public void deleteEmployee() {
        System.out.println(EmployeeManagementConstant.EMPLOYEE_FIRSTNAME);
        String firstName = scanner.next();

        if (employeeController.deleteEmployee(firstName)) {
            System.out.println("Employee Details Deleted");
        } else {
            System.out.println("Employee Details not deleted");
        }
    }

    /**
     * Deletes employee by employee name,
     * If found delete that employee object.
     * If the given name not found it shows out put as record not found.
     */
    public void searchEmployee() {

        System.out.println(EmployeeManagementConstant.EMPLOYEE_NAME);
        String firstName = scanner.next();

        EmployeeDTO selectEmployee = employeeController.searchEmployee(firstName);
        if (selectEmployee != null) {
            System.out.println(selectEmployee);
        } else {
            System.out.println("The Employee Not found ");
        }
    }
}