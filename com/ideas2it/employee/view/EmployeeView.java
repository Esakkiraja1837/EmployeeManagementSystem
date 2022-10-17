package com.ideas2it.employee.view;

import com.ideas2it.employee.constant.EmployeeManagementConstant;
import com.ideas2it.employee.controller.EmployeeController;
import com.ideas2it.employee.dto.AddressDTO;
import com.ideas2it.employee.dto.EmployeeDTO;
import com.ideas2it.employee.dao.Dao;
import com.ideas2it.employee.dao.impl.EmployeeDao;
import com.ideas2it.employee.exception.EMSException;
import com.ideas2it.employee.service.EmployeeService;
import com.ideas2it.employee.util.ValidationUtil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Period;
import java.text.ParseException;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.time.DateTimeException;
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
    ValidationUtil employeeUtil = new ValidationUtil();
    EmployeeDTO employeeDTO = new EmployeeDTO();

    private static final Logger logger = LogManager.getLogger(EmployeeView.class);

    boolean isValid;

    /**
     *  method makes the user to choose the CRUD operation.
     */     
    public void chooseOption() {
        String option;
        int exitOption = 1;

        StringBuilder menuOption = new StringBuilder();
        menuOption.append("Employee Management - \n\n")
                  .append("\ta - CREATE \n")
                  .append("\tb - DISPLAY \n\tc - UPDATE \n\td - DELETE \n")
                  .append("\te - SEARCH \n\tf - EXIT \n\n");

        do {
            System.out.println(menuOption.toString());
            System.out.println(EmployeeManagementConstant.VALID_INPUT);
            option = scanner.nextLine();

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
                    logger.warn(EmployeeManagementConstant.
                                       INVALID_INPUT);
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
        List<AddressDTO> addressDTOs = new ArrayList<>();
        System.out.println(EmployeeManagementConstant.VALID_INPUT);
        try {
            int employeeId = 0;
            String firstName = getFirstName();
            String lastName = getLastName();
            long mobileNumber = getMobileNumber();
            String emailId = getEmailId();
            double salary = getSalary();
            String role = getRole();
            String gender = getGender();
            LocalDate dateOfBirth = getDateOfBirth();
            LocalDate joiningDate = getJoiningDate(dateOfBirth);

            AddressDTO addresses = addAddress();
            addressDTOs.add(addresses);
            addresses = addAnotherAddress();

            if (addresses != null)   {
                addressDTOs.add(addresses);
            }
            employeeDTO = new EmployeeDTO(firstName, lastName, employeeId,
                    role, mobileNumber, emailId, salary, addressDTOs,
                    joiningDate,  dateOfBirth, gender);

            if (employeeController.addEmployee(employeeDTO)) {
                logger.info("Employee details stored");
                System.out.println("Employee details stored.");
            } else {
                logger.info("Employee details can not stored");
                System.out.println("Employee details can not stored.");
            }
        } catch (EMSException e) {
           logger.error("Employee Deatils not Created");
           System.out.println(e.getMessage() + " " + e.getErrorCode());
        }


    }


    /**
     * Create and add employe address details.
     * Get the employee address values from the user.
     */
    private AddressDTO addAddress() {

        String type = getType();
        String doorNo = getDoorNo();
        String street = getStreet();
        String city = getCity();
        String state = getState();
        int pinCode = getPinCode();

        AddressDTO address = new AddressDTO(doorNo, street, city,
                                            state, pinCode,type);
        return address;
    }

    /**
     * Display the saved employeeDetails.
     */
    public void displayEmployee() {

        try {
            List<EmployeeDTO> employeesDetail = employeeController.
                                                displayEmployee();

            if (employeesDetail.size() > 0) {
                Iterator<EmployeeDTO> iterator = employeesDetail.iterator(); 

                while (iterator.hasNext()) {
                    EmployeeDTO employeeDTO =  iterator.next();
                    System.out.println(employeeDTO.toString());
                }
            } else {
                logger.info("Can not found the employeeDetails");
                System.out.println("Empty data...");
            }
        } catch (EMSException e) {
              logger.error("Can not found the employeeDetails");
              System.out.println(e.getMessage() + " " + e.getErrorCode());
        }
    }



    /**
     * update the employeeDetails
     * It found the given name update tha employee.
     * If the given name not found it shows out put as record not found.
     */
    public void updateEmployee() {
        List<AddressDTO> addressDTOs = new ArrayList<>();

        try {
            boolean isValid;       
            
            int employeeId = getEmployeeId();
            String firstName = getFirstName();
            String lastName = getLastName();
            long mobileNumber = getMobileNumber();
            String emailId = getEmailId();
            String role = getRole();
            double salary = getSalary();
            String gender = getGender();
            AddressDTO addressDTO = addAddress();
            System.out.println("Enter date of Joinning (yyyy-mm-dd) ");
            LocalDate joiningDate = LocalDate.parse(scanner.next());
            System.out.println("Enter date of Birth (yyyy-mm-dd) ");
            LocalDate dateOfBirth = LocalDate.parse(scanner.next());

            EmployeeDTO employeeDTO = new EmployeeDTO(firstName, lastName,
                    employeeId,role, mobileNumber, emailId, salary, addressDTOs,
                    joiningDate,  dateOfBirth, gender);

            if (employeeController.updateEmployee(employeeDTO)) {
                logger.info("Employee Details Updated");
                System.out.println("Employee Details Updated");
            } else {
                logger.info("Employee Details not Updated");
                System.out.println("Not Update the Employee Details");
            }
        } catch (EMSException e) {
           logger.error("Employee Detail can not updated");
           System.out.println(e.getMessage() + " " + e.getErrorCode());
        }
    }

    /**
     * Deletes employee by employee name,
     * If found delete that employee object.
     * If the given name not found it shows out put as record not found.
     */
    public void deleteEmployee() {
        int employeeId = getEmployeeId();
        try {

            if (employeeController.removeEmployee(employeeId)) {
                logger.info("Employee Details Deleted");
                System.out.println("Employee Details Deleted");
            } else {
                logger.info("Employee Details Not Deleted");
                System.out.println("Employee Details not deleted");
            }
        } catch (EMSException e) {
           logger.error("Employee Details Not found" + "Employee ID :" + employeeId);
           System.out.println(e.getMessage() + " " + e.getErrorCode());
        }
    }

    /**
     * Search employee by employee name,
     * If found employee object.
     * If the given name not found it shows out put as record not found.
     */
    public void searchEmployee() {
        String firstName = getFirstName();

        try {
            List<EmployeeDTO> employee = employeeController.searchEmployee(firstName);

            if(!employee.isEmpty()) {
                Iterator<EmployeeDTO> iterator = employee.iterator();

                while (iterator.hasNext()) {
                    EmployeeDTO employeeDTO = iterator.next();
                    System.out.println(employeeDTO);
                }
            } else {
                logger.info("Employee Details Not found" + "First Name :" + firstName); 
                System.out.println("The Employee Detail Not found " + firstName);
            }
        } catch (EMSException e) {
           logger.info("The Employee Detail Not found" + "First Name :" + firstName);
           System.out.println(e.getMessage() + " " + e.getErrorCode());
        }
    }

    public AddressDTO addAnotherAddress() {
        boolean isValid = false;
        AddressDTO address = null;

        do {
            System.out.println("Do you wish to add another Address (Y or N) ?");
            String choice = scanner.nextLine();

            if (choice.equals("Y") || choice.equals("y")) {
                address = addAddress();
                isValid = true;

            } else if (choice .equals("N") || choice.equals("n")) {
                System.out.println("Employee can't Second address");
                isValid = true;
            } else {
                System.out.println("Invalid choice");
                isValid = false;
            }
        } while (!(isValid));
        return address;
    }

    /**
     * validate EmployeeId.
     * @return Return value.
     */
    public int getEmployeeId() {
        int employeeId = 0;
        boolean isValid;

        do {
            System.out.println(EmployeeManagementConstant.EMPLOYEE_ID);
            try {
                 employeeId = Integer.parseInt(scanner.nextLine());
                 isValid = employeeController.isValid(EmployeeManagementConstant.
                         REGEX_EMPLOYEEID,String.valueOf(employeeId));

                 if(!isValid) {
                     System.out.println(EmployeeManagementConstant.VALID_INPUT);
                 }
            } catch (NumberFormatException e) {
                System.out.println("Invalid EmployeeID");
                isValid = false;
            }
         } while (!isValid);
         return employeeId;
    }

    /**
     * validate Employee address-pincode.
     * @return Return value.
     */
    public int getPinCode() {
        boolean isValid;
        String pinCode;

        do {    
            System.out.println("Enter Employee PinCode: ");
            pinCode = scanner.nextLine();
            isValid = employeeController.isValid(EmployeeManagementConstant.
                                                 REGEX_PINCODE, pinCode);

            if (!isValid) {
                System.out.println("Invalid pincode");
                isValid = false;
            }
         } while (!isValid);
         return Integer.parseInt(pinCode);
    }

    /**
     * validate Employee address doorNo.
     * @return Return doorNo.
     */
    public String getDoorNo() {
        boolean isValid;
        String doorNo;

        do {
            System.out.println("Enter Employee Door Number: ");
            doorNo = scanner.nextLine();
            isValid = employeeController.isValid(EmployeeManagementConstant
                                                 .REGEX_DOOR_NUMBER, doorNo);

            if(!isValid) {
                System.out.println("Invalid DoorNo format");
            }
         } while (!isValid);
         return doorNo;
    }

    /**
     * validate Employee address street.
     * @return Return street.
     */
    public String getStreet() {
        boolean isValid;
        String street;

        do {
            System.out.println("Enter the Employee Street: ");
            street = scanner.nextLine();
            isValid = employeeController.isValid(EmployeeManagementConstant
                                                 .REGEX_CITYSTATE, street);

            if(!isValid) {
                System.out.println("Invalid format");
            }
         } while (!isValid);
         return street;
    }

    /**
     * validate Employee address City.
     * @return Return city.
     */
    public String getCity() {
        boolean isValid;
        String city;

        do {
            System.out.println("Enter the Employee City: ");
            city = scanner.nextLine();
            isValid = employeeController.isValid(EmployeeManagementConstant
                                                 .REGEX_CITYSTATE, city);

            if(!isValid) {
                System.out.println("Invalid format");
            }
         } while (!isValid);
         return city;
    }

    /**
     * validate Employee address city.
     * @return Return city.
     */
    public String getState() {
        boolean isValid;
        String state;

        do {
            System.out.println("Enter the Employee State: ");
            state = scanner.nextLine();
            isValid = employeeController.isValid(EmployeeManagementConstant
                                                 .REGEX_CITYSTATE, state);

            if(!isValid) {
                System.out.println("Invalid format");
            }
         } while (!isValid);
         return state;
    }

    /**
     * validate Employee address type.
     * @return Return type.
     */
    public String getType() {
        boolean isValid;
        String type;

        do {
            System.out.println("Enter the Employee Address Type: ");
            type = scanner.nextLine();
            isValid = employeeController.isValid(EmployeeManagementConstant
                                                 .REGEX_CITYSTATE, type);

            if(!isValid) {
                System.out.println("Invalid format");
            }
         } while (!isValid);
         return type;
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
            firstName = scanner.nextLine();
            isValid = employeeController.isValid(EmployeeManagementConstant.
                                                 REGEX_NAME, firstName);

            if(!(isValid)) {
                System.out.println("Invalid Name");
            }
         } while (!isValid);
         return firstName;
    }

    /**
     * validate Employee Gender.
     * @return Return gender.
     */
    public String getGender() {
        boolean isValid;
        String gender;

        do {
            System.out.println("Enter the Employee Gender: ");
            gender = scanner.nextLine();
            isValid = employeeController.isValid(EmployeeManagementConstant.
                                                 REGEX_NAME, gender);

            if(!(isValid)) {
                System.out.println("Invalid Data");
            }
         } while (!isValid);
         return gender;
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
            lastName = scanner.nextLine();
            isValid = employeeController.isValid(EmployeeManagementConstant.
                                                 REGEX_NAME, lastName);

            if (!(isValid)) {
                System.out.println("Invalid Name");
            }
         } while (!isValid);
         return lastName;
    }
    /**
     * validate Employee role.
     * @return Return role.
     */
    public String getRole() {
        boolean isValid;
        String role;

        do {
            System.out.println("Enter the Employee role :");
            role = scanner.nextLine();
            isValid = employeeController.isValid(EmployeeManagementConstant.
                                                 REGEX_NAME, role);

            if (!(isValid)) {
                System.out.println("Invalid Format");
            }
         } while (!isValid);
         return role;
    }

    /**
     * validate Employee Mobile Number.
     * @return Return value.
     */
    public long getMobileNumber() {
        boolean isValid;
        long mobileNumber = 0;

        do {
            try {
                System.out.println("Enter Employee Mobile Number: ");

                mobileNumber = Long.parseLong(scanner.nextLine());
                isValid = employeeController.isValid(EmployeeManagementConstant.
                                                     REGEX_MOBILENUMBER,
                                                     Long.toString(mobileNumber));

                if (isValid) {
                    isValid = employeeController.validateMobileNumber(mobileNumber);
                }

                if (!isValid) {
                    System.out.println("This mobileNumber already registered");
                }
            } catch (EMSException e) {
                System.out.println(e.getMessage() + " " + e.getErrorCode());
                isValid = false;
            }

             catch (NumberFormatException e) {
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
        boolean isValid = false;
        String emailId = null;

        do {
            try {
                System.out.println("Enter Employee Email Id: ");
                emailId = scanner.nextLine();
                isValid = employeeController.isValid(EmployeeManagementConstant
                                                     .REGEX_EMAILID, emailId);
                if(!isValid) {
                    System.out.println("Invalid EmailId format");
                }

                if (isValid) {
                    isValid = employeeController.validateEmailId(emailId);
                }

                if (!(isValid)) {
                    System.out.println("This EmailId Already registered");
                }
            } catch (EMSException e) {
                System.out.println(e.getErrorCode() + " " + e.getMessage());
            }

         } while (!(isValid));
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
            System.out.println("Enter Employee Salary (Rupess.PP): ");

            try {
                salary = Double.parseDouble(scanner.nextLine());
                isValid = employeeController.isValid(EmployeeManagementConstant.
                                                     REGEX_SALARY,
                                                     Double.toString(salary));

                if (!isValid) {
                    System.out.println("Invalid salary format");
                    logger.warn("Invalid salary format");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid salary format");
                isValid = false;
            }
         } while (!isValid);
         return salary;
    }

    /**
     * validate Employee JoiningDate.
     * @return Return value.
     */
    public LocalDate getJoiningDate(LocalDate dateOfBirth) {
        boolean isValid;
        LocalDate joiningDate = null;

        do {
            System.out.println("Enter date of Joinning (yyyy-mm-dd): ");

            try {
                joiningDate = LocalDate.parse(scanner.nextLine());
                isValid = true;
            } catch (DateTimeParseException e) {
                System.out.println(EmployeeManagementConstant.
                                       INVALID_INPUT);
                isValid = false;
            }

            if (isValid) {
                isValid = employeeController.validateJoiningDate(dateOfBirth, joiningDate);
            } 

            if (!isValid) {
                System.out.println("  Invalid date : ");
            }   
        } while(!(isValid));
        return joiningDate;
    }

    /**
     * validate Employee DateOfBirth.
     * @return Return value.
     */
    public LocalDate getDateOfBirth() {
        LocalDate dateOfBirth = null;
        boolean isValid;

        do {
            System.out.println("Enter date of Birth (yyyy-mm-dd) ");
            try {
                dateOfBirth = LocalDate.parse(scanner.nextLine());
                isValid = true;
            } catch (DateTimeException e) {
                System.out.println(EmployeeManagementConstant.
                                   INVALID_INPUT);
                isValid = false;
            }

            if (isValid) {
                isValid = employeeController.validateDateOfBirth(dateOfBirth);
            }

            if (!(isValid)) {
                System.out.println("  Invalid date : ");
            }
            
        } while(!isValid);  
        return dateOfBirth;
    }             
}