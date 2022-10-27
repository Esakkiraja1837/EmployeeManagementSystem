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

            employeeId = employeeController.addEmployee(employeeDTO);
            logger.info("Employee details stored");
            System.out.println("Employee details stored.");
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
            boolean isValid = false;       
            int employeeId;
            int updateMenuOptions = 0;
            EmployeeDTO employeeDto = null;

            try {

               do {
                   employeeId = getEmployeeId(); 
               } while(!(employeeController.isEmployeeIdExists(employeeId)));

               employeeDto = employeeController.getEmployeeIdPresent(employeeId);

               if (null != employeeDto) {

                   do {
                       displayUpdateMenu();

                       try{
                            System.out.println("Enter the Employee update option : ");
                            updateMenuOptions = Integer.valueOf(scanner.nextLine());

                            switch (updateMenuOptions) {
                                case 1:
                                    employeeDto.setFirstName(getFirstName());
                                    break;

                                case 2:
                                    employeeDto.setLastName(getLastName());
                                    break;

                                case 3:
                                    employeeDto.setMobileNumber(getMobileNumber());
                                    break;

                                case 4:
                                    employeeDto.setEmailId(getEmailId());
                                    break;

                                case 5:
                                    employeeDto.setRole(getRole());
                                    break;

                                case 6:
                                    employeeDto.setSalary(getSalary());
                                    break;

                                case 7:
                                    employeeDto.setGender(getGender());
                                    break;

                                case 8:
                                    employeeDto.setJoiningDate(getJoiningDate(getDateOfBirth()));
                                    break;

                                case 9:
                                    employeeDto.setDateOfBirth(getDateOfBirth());
                                    break;

                                case 10:
                                    employeeDto.setAddress(updateAddress(employeeDto));
                                    break;

                                case 11:
                                    isValid = true;
                                    break;

                                default:
                                    System.out.println(EmployeeManagementConstant.
                                       INVALID_INPUT);
                            }

                       } catch (NumberFormatException e) {
                           logger.error(EmployeeManagementConstant.
                                        INVALID_INPUT);
                           System.out.println(EmployeeManagementConstant.
                                              INVALID_INPUT);
                       }
                   } while (!isValid);
                   employeeController.updateEmployee(employeeDto);
                   logger.info("Employee Details Updated" + "EmployeeID :" + employeeId);
                   System.out.println("Employee Details Updated");
               } else {
                    System.out.println("EmployeeId not exists");
               }

            } catch (EMSException e) {
               logger.error("Employee Detail can not updated");
               System.out.println(e.getMessage() + " " + e.getErrorCode());
            }
    }

    /**
     * Display the EmployeeUpdateMenu
     */
    public void displayUpdateMenu() {

        StringBuilder updateMenuOption = new StringBuilder();
        updateMenuOption.append("Employee Update - \n\n")
                        .append("\t1 - FIRSTNAME \n\t2 - LASTNAME \n\t3 - MOBILENUMBER \n")
                        .append("\t4 - EMAILID \n\t5 - ROLE \n\t6 - SALARY \n")
                        .append("\t7 - GENDER \n\t8 - JOININGDATE \n")
                        .append("\t9 - DATEOFBIRTH \n\t10 - ADDRESS \n\t11 - EXIT");
       System.out.println(updateMenuOption.toString());
    }

    /**
     * Display the AddressUpdateMenu
     */
    public void displayupdateAddressmenu() {

        StringBuilder updateAddressOption = new StringBuilder();
        updateAddressOption.append("EmployeeAddress Update - \n\n")
                        .append("\t1 - DOORNO \n\t2 - STREET \n\t3 - CITY")
                        .append("\n\t4 - STATE \n\t5 - PINCODE")
                        .append("\n\t6 - EXIT");
       System.out.println(updateAddressOption.toString());
    }

    /**
     * update the employee AddressDetails
     * It found update tha employee Address.
     * If the given name not found it shows out put as record not found.
     */
    public List<AddressDTO> updateAddress(EmployeeDTO employeeDto) {
        boolean isValid = false;
        int updateAddressoption = 0;
        List <AddressDTO> employeeAddress = employeeDto.getAddress();

        for (AddressDTO addressDto : employeeAddress) {

            if (getType().equals(addressDto.getType())) {

                do {
                    displayupdateAddressmenu();

                    try {
                         System.out.println("Enter the Employee address update option : ");
                         updateAddressoption = Integer.valueOf(scanner.nextLine());

                         switch (updateAddressoption) {
                             case 1:
                                 addressDto.setDoorNo(getDoorNo());
                                 break;

                             case 2:
                                 addressDto.setStreet(getStreet());
                                 break;

                             case 3:
                                 addressDto.setCity(getCity());
                                 break;

                             case 4:
                                 addressDto.setState(getState());
                                 break;

                             case 5:
                                 addressDto.setPinCode(getPinCode());
                                 break;

                             case 6:
                                 isValid = true;
                                 break;

                             default:
                                 System.out.println(EmployeeManagementConstant.
                                       INVALID_INPUT);
                         }
                    } catch (NumberFormatException e) {
                        logger.error(EmployeeManagementConstant.
                                       INVALID_INPUT);
                        System.out.println(EmployeeManagementConstant.
                                       INVALID_INPUT);
                    }
                } while (!isValid);
            }
        }
        return employeeAddress;
    }

    /**
     * Deletes employee by employee name,
     * If found delete that employee object.
     * If the given name not found it shows out put as record not found.
     */
    public void deleteEmployee() {
        int employeeId = getEmployeeId();

        try {

            if (employeeController.isEmployeeIdExists(employeeId)) {
                 employeeController.removeEmployee(employeeId);
                 logger.info("Employee Details Deleted");
                 System.out.println("Employee Details Deleted");
             } else {
                 System.out.println("EmployeeId does not exists");
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

            if (!employee.isEmpty()) {
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

    /**
     * employee choose address Type.
     * @return return address.
     */
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

                 if (!isValid) {
                     System.out.println(EmployeeManagementConstant.VALID_INPUT);
                 }
            } catch (NumberFormatException e) {
                System.out.println(EmployeeManagementConstant.INVALID_DATA);
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
                System.out.println(EmployeeManagementConstant.INVALID_DATA);
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

            if (!isValid) {
                System.out.println(EmployeeManagementConstant.INVALID_DATA);
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

            if (!isValid) {
                System.out.println(EmployeeManagementConstant.INVALID_DATA);
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

            if (!isValid) {
                System.out.println(EmployeeManagementConstant.INVALID_DATA);
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

            if (!isValid) {
                System.out.println(EmployeeManagementConstant.INVALID_DATA);
            }

         } while (!isValid);
         return state;
    }

    /**
     * Display Employee address typess.
     * @return Return type.
     */
    public String getType() {
        boolean isValid;
        String type = null;
        int options = 0;

        try {
            System.out.println("\n1 - Temporary - Address" + "\t\n2 - Permanent - Address");
            System.out.println("Enter the update Address Type : ");
            options = Integer.valueOf(scanner.nextLine());

            switch(options) {
                    case 1:
                        type = "Temporary - Address";
                        break;

                    case 2:
                        type = "Permanent - Address";
                        break;

                    default:
                        System.out.println("INVALID INPUT");
                }
            } catch(NumberFormatException e) {
                System.out.println(EmployeeManagementConstant.
                                   INVALID_INPUT);
            }
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

            if (!(isValid)) {
                System.out.println(EmployeeManagementConstant.INVALID_DATA);
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

            if (!(isValid)) {
                System.out.println(EmployeeManagementConstant.INVALID_DATA);
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
                System.out.println(EmployeeManagementConstant.INVALID_DATA);
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
                System.out.println(EmployeeManagementConstant.INVALID_DATA);
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
                System.out.println(EmployeeManagementConstant.INVALID_DATA);
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
                    System.out.println(EmployeeManagementConstant.INVALID_DATA);
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
                    System.out.println(EmployeeManagementConstant.INVALID_DATA);
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
                System.out.println(EmployeeManagementConstant.INVALID_DATA);
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
                System.out.println(EmployeeManagementConstant.INVALID_DATA);
            }
            
        } while(!isValid);  
        return dateOfBirth;
    }             
}