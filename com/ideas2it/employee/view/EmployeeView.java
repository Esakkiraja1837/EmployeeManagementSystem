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
    EmployeeUtil employeeUtil = new EmployeeUtil();
    EmployeeDTO employeeDTO = new EmployeeDTO();
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

                if (!isValid) {
                    System.out.println("Invalid MobileNumber format");
                }

                if (isValid) {
                    List<EmployeeDTO> employeeDetails = employeeController.displayEmployee();

                    if(!(employeeDetails.isEmpty())) {
                        Iterator<EmployeeDTO> iterator = employeeDetails.iterator();
                        while (iterator.hasNext()) {
                            EmployeeDTO employeeDTO = iterator.next();

                            if (mobileNumber == employeeDTO.getMobileNumber()) {
                                isValid = false;
                            }
                        }

                        if (!isValid) {
                            System.out.println("This mobileNumber already registered");
                        }
                    }
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
                    List<EmployeeDTO> employees = employeeController.displayEmployee();

                    if(!(employees.isEmpty())) {
                        Iterator<EmployeeDTO> iterator = employees.iterator();
                        while (iterator.hasNext()) {
                            EmployeeDTO employeeDTO = iterator.next();

                            if (emailId.equals(employeeDTO.getEmailId())) {
                                isValid = false;
                            }
                        }

                        if (!(isValid)) {
                            System.out.println("This EmailId Already registered");
                        }
                    }
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
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid salary format");
                isValid = false;
            }
         } while (!isValid);
         return salary;
    }

    public LocalDate getJoiningDate(LocalDate dateOfBirth) {
        boolean isvalid;
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
                 LocalDate currentDate = LocalDate.now();
                 int compareValue = currentDate.compareTo(joiningDate);
                 if (!(compareValue >= 0)) {
                     isValid = false;
                  }

                  if (18 >  Period.between(dateOfBirth, joiningDate).getYears()) {
                       isValid = false;
                  }

                  if (!isValid) {
                      System.out.println("  Invalid date : ");
                   }
              }    
        } while(!isValid);
        return joiningDate;
    }


    public LocalDate getDateOfBirth() {
        LocalDate dateOfBirth= null;
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
                LocalDate currentDate = LocalDate.now();
                isValid = (18 <= Period.between(dateOfBirth, currentDate).getYears()
                           && Period.between(dateOfBirth, currentDate).
                           getYears() <= 60);

                if (!(isValid)) {
                    System.out.println("  Invalid date : ");
                }
            }
        } while(!isValid);  
        return dateOfBirth;
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
     * validate Employee address-pincode.
     * @return Return value.
     */
    public int getPinCode() {
        boolean isValid;
        int pinCode = 0;

        do {    
            System.out.println("Enter Employee PinCode: ");
            try {
                 pinCode = Integer.parseInt(scanner.nextLine());
                 isValid = employeeController.isValid(EmployeeManagementConstant.
                                                      REGEX_PINCODE,
                                                      String.valueOf(pinCode));

                 if (!isValid) {
                    System.out.println("Invalid pincode");
                    isValid = false;
                 }
            } catch (NumberFormatException e) {
                System.out.println("Invalid pincode");
                isValid = false;
            }
         } while (!isValid);
         return pinCode;
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
                System.out.println("Empty data...");
            }
        } catch (EMSException e) {
           System.out.println(e.getMessage() + " " + e.getErrorCode());
        }
    }

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
        int employeeId = getEmployeeId();
        try {
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
                System.out.println("The Employee Detail Not found ");
            }
        } catch (EMSException e) {
           System.out.println(e.getMessage() + " " + e.getErrorCode());
        }
        System.out.println("view"+ firstName);
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
                System.out.println("No second address added");
                isValid = true;
            } else {
                System.out.println("Invalid choice");
                isValid = false;
            }
        } while (!(isValid));
        return address;
    }
                
}