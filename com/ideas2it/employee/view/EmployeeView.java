package com.ideas2it.employee.view;

import com.ideas2it.employee.constant.EmployeeManagementConstant;
import com.ideas2it.employee.controller.EmployeeController;
import com.ideas2it.employee.dto.AddressDTO;
import com.ideas2it.employee.dto.EmployeeDTO;
import com.ideas2it.employee.dao.Dao;
import com.ideas2it.employee.dao.impl.EmployeeDao;
import com.ideas2it.employee.exception.EMSException;
import com.ideas2it.employee.service.EmployeeService;
import com.ideas2it.employee.util.EmployeeUtil;

import java.text.ParseException;
import java.time.format.DateTimeParseException;
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
    boolean isValid;

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
                    System.out.println(EmployeeManagementConstant.
                                       INVALID_INPUT);
            }
        } while (exitOption != 0);
    }

    /**
     * Create and add employe details.
     * Get the employee values from the user.
     */
    public void createEmployee(){
        System.out.println(EmployeeManagementConstant.VALID_INPUT);
        try {
            String firstName = getFirstName();
            String lastName = getLastName();
            long mobileNumber = getMobileNumber();
            String emailId = getEmailId();
            double salary = getSalary();
            System.out.println("Enter the Employee role: ");
            String role = scanner.next();

            int employeeId = 0;
            boolean isEmployeeId = false;

            do {
                System.out.println(EmployeeManagementConstant.EMPLOYEE_ID);
                try {
                    Scanner scanner = new Scanner(System.in);
                    employeeId = scanner.nextInt();
                    isEmployeeId = true;
                } catch (InputMismatchException e) {
                    System.out.println(EmployeeManagementConstant.
                                       INVALID_INPUT);
                }
            } while(!isEmployeeId);

            LocalDate joiningDate = null;

            do {
                boolean isvalid;
                System.out.println("Enter date of Joinning (yyyy-mm-dd): ");
                try {
                    joiningDate = LocalDate.parse(scanner.next());
                    isValid = true;
                } catch (DateTimeParseException e) {
                    System.out.println(EmployeeManagementConstant.
                                       INVALID_INPUT);
                    isValid = false;
                }
             } while(!isValid);

            LocalDate dateOfBirth= null;

            do {
                boolean isValid;
                System.out.println("Enter date of Birth (yyyy-mm-dd) ");
                try {
                    dateOfBirth = LocalDate.parse(scanner.next());
                    isValid = true;
                } catch (DateTimeParseException e) {
                    System.out.println(EmployeeManagementConstant.
                                       INVALID_INPUT);
                    isValid = false;
                }
            } while(!isValid);    

            AddressDTO addressDTO = addAddress();

            System.out.println("Enter the Gender :");
            String gender = scanner.next();

            employeeDTO = new EmployeeDTO(firstName, lastName, employeeId,
                    role, mobileNumber, emailId,salary, addressDTO,
                    joiningDate,  dateOfBirth, gender);

            if (employeeController.addEmployee(employeeDTO)) {
                System.out.println("Employee details stored.");
            } else {
                System.out.println("Employee details not stored.");
            }
        } catch (EMSException e) {
           System.out.println(e.getMessage() + " " +e.getErrorCode());
        }


    }

    /**
     * validate Employee name.
     * @return Return firstName.
     */
    public String getFirstName() {
        boolean isValid;
        String firstName;

        do {
            System.out.println(EmployeeManagementConstant.EMPLOYEE_FIRSTNAME);
            firstName = scanner.next();
            isValid = employeeController.isValid(EmployeeManagementConstant.
                                                 REGEX_NAME, firstName);

            if(!(isValid)) {
                System.out.println("Invalid Name");
            }
         } while (!isValid);
         return firstName;
    }

    /**
     * validate Employee name.
     * @return Return LastNameName.
     */
    public String getLastName() {
        boolean isValid;
        String lastName;

        do {
            System.out.println(EmployeeManagementConstant.EMPLOYEE_LASTNAME);
            lastName = scanner.next();
            isValid = employeeController.isValid(EmployeeManagementConstant.
                                                 REGEX_NAME, lastName);

            if (!(isValid)) {
                System.out.println("Invalid Name");
            }
         } while (!isValid);
         return lastName;
    }

    /**
     * validate Employee Mobile Number.
     * @return Return value.
     */
    public long getMobileNumber() {
        boolean isValid;
        long mobileNumber = 0;

        do {
            System.out.println("Enter Employee Mobile Number: ");
            try {
                mobileNumber = Long.parseLong(scanner.next());
                isValid = employeeController.isValid(EmployeeManagementConstant.
                                                     REGEX_MOBILENUMBER,
                                                     Long.toString(mobileNumber));

                if (!(isValid)) {
                    System.out.println("Invalid MobileNumber format");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid MobileNumber format");
                isValid = false;
            }
         } while (!isValid);
         return mobileNumber;
    }

    /**
     * validate Employee EmailID.
     * @return Return value.
     */
    public String getEmailId() {
        boolean isValid;
        String emailId;

        do {
            System.out.println("Enter Employee Email Id: ");
            emailId = scanner.next();
            isValid = employeeController.isValid(EmployeeManagementConstant
                                                 .REGEX_EMAILID, emailId);

            if(!(isValid)) {
                System.out.println("Invalid EmailId format");
            }
         } while (!isValid);
         return emailId;
    }

    /**
     * validate Employee salary.
     * @return Return value.
     */
    public double getSalary() {
        boolean isValid;
        double salary = 0;

        do {
            System.out.println("Enter Employee Salary (0.00): ");
            try {
                salary = Double.parseDouble(scanner.next());
                isValid = employeeController.isValid(EmployeeManagementConstant.
                                                     REGEX_SALARY,
                                                     Double.toString(salary));

                if (!(isValid)) {
                    System.out.println("Invalid salary format");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid salary format");
                isValid = false;
            }
         } while (!(isValid));
         return salary;
    }

    /**
     * Create and add employe address details.
     * Get the employee address values from the user.
     */
    private AddressDTO addAddress() {
        System.out.println("Enter Employee Door Number: ");
        String doorNo = scanner.next();
        System.out.println("Enter Employee Street: ");
        String street = scanner.nextLine();
        System.out.println("Enter Employee City: ");
        String city = scanner.nextLine();
        System.out.println("Enter Employee State: ");
        String state = scanner.nextLine();
        int pinCode = getPinCode();
        System.out.println("Enter Address type: ");
        String type = scanner.nextLine();
        AddressDTO addressDTO = new AddressDTO(doorNo, street, city,
                                               state, pinCode, type);
        return addressDTO;
    }

    /**
     * validate Employee address-pincode.
     * @return Return value.
     */
    public int getPinCode() {
        boolean isValid;
        int pinCode = 0;

        do {    
            System.out.println("Enter Employee Pin Code: ");
            try {
                 pinCode = scanner.nextInt();
                 isValid = employeeController.isValid(EmployeeManagementConstant.
                                                      REGEX_PINCODE,
                                                      String.valueOf(pinCode));

                 if (!(isValid)) {
                    System.out.println("Invalid pincode");
                 }
            } catch (InputMismatchException e) {
                System.out.println("Invalid pincode");
                isValid = false;
            }
         } while (!(isValid));
         return pinCode;
    }

    /**
     * Display the saved employeeDetails.
     */
    public void displayEmployee() {
        try {
            List<EmployeeDTO> employeesDetail = employeeController.
                                                displayEmployee();

            if(employeesDetail.size() > 0) {
                Iterator<EmployeeDTO> iterator = employeesDetail.iterator(); 
                while (iterator.hasNext()) {
                    EmployeeDTO employeeDTO =  iterator.next();
                    System.out.println(employeeDTO.toString());
                }
            } else {
                System.out.println("Empty data...");
            }
        } catch (EMSException e) {
           System.out.println(e.getMessage() + " " + e.getErrorCode());
        }
    }

    /**
     * update the employeeDetails
     * It found the given name update tha employee.
     * If the given name not found it shows out put as record not found.
     */
    public void updateEmployee() {
        try {
            boolean isValid;       
            System.out.println("Enter the Employee Id :");
            int employeeId = scanner.nextInt();
            String firstName = getFirstName();
            String lastName = getLastName();
            long mobileNumber = getMobileNumber();
            String emailId = getEmailId();
            System.out.println("Enter the employee role: ");
            String role = scanner.next();
            double salary = getSalary();
            AddressDTO addressDTO = addAddress();
            System.out.println("Enter date of Joinning (yyyy-mm-dd) ");
            LocalDate joiningDate = LocalDate.parse(scanner.next());
            System.out.println("Enter date of Birth (yyyy-mm-dd) ");
            LocalDate dateOfBirth = LocalDate.parse(scanner.next());
            System.out.println("Enter the Gender :");
            String gender = scanner.next();

            EmployeeDTO employeeDTO = new EmployeeDTO(firstName, lastName,
                    employeeId,role, mobileNumber, emailId,salary, addressDTO,
                    joiningDate,  dateOfBirth, gender);

            if (employeeController.updateEmployee(employeeDTO)) {
                System.out.println("Update the Employee Details");
            } else {
                System.out.println("Not Update the Employee Details");
            }
        } catch (EMSException e) {
           System.out.println(e.getMessage() + " " + e.getErrorCode());
        }
    }

    /**
     * Deletes employee by employee name,
     * If found delete that employee object.
     * If the given name not found it shows out put as record not found.
     */
    public void deleteEmployee() {
        System.out.println("Enter the Employee ID :");
        try {
            int employeeId = scanner.nextInt();

            if (employeeController.removeEmployee(employeeId)) {
                System.out.println("Employee Details Deleted");
            } else {
                System.out.println("Employee Details not deleted");
            } 
        } catch (EMSException e) {
           System.out.println(e.getMessage() + " " + e.getErrorCode());
        }
    }

    /**
     * Deletes employee by employee name,
     * If foundt employee object.
     * If the given name not found it shows out put as record not found.
     */
    public void searchEmployee() {
        System.out.println(EmployeeManagementConstant.EMPLOYEE_FIRSTNAME);
        String firstName = scanner.next();
        try {
            EmployeeDTO selectEmployee = employeeController.
                                         searchEmployee(firstName);

            if (selectEmployee != null) {
                System.out.println(selectEmployee);
            } else {
                System.out.println("The Employee Not found ");
            }
        } catch (EMSException e) {
           System.out.println(e.getMessage() + " " + e.getErrorCode());
        }
    }
}