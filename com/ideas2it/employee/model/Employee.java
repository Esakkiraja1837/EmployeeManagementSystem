package com.ideas2it.employee.model;


import java.time.LocalDate;

/**
 * It present employee details.
 *
 * @version 1.0
 * @author  ESAKKIRAJA E
 * 13-09-2022.
 */
public class Employee {
    private String firstName;
    private String lastName;
    private int employeeId;
    private String role;
    private long mobileNumber;
    private String emailId;
    private double salary;
    private Address address;
    private LocalDate joiningDate;
    private LocalDate dateOfBirth;
    private String gender;

    public Employee() {
    }

    public Employee(String firstName, String lastName, int employeeId, String role,
                    long mobileNumber, String emailId, double salary, Address address,
                    LocalDate joiningDate, LocalDate dateOfBirth, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.employeeId = employeeId;
        this.role = role;
        this.mobileNumber = mobileNumber;
        this.emailId = emailId;
        this.salary = salary;
        this.address = address;
        this.joiningDate = joiningDate;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMobileNumber(long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setJoiningDate(LocalDate  joiningDate) {
        this.joiningDate = joiningDate;
    }

    public void setDateOfBirth(LocalDate  dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setGender(String Gender) {
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public double getSalary() {
        return salary;
    }

    public LocalDate  getJoiningDate() {
        return joiningDate;
    }

    public LocalDate  getDateOfBirth() {
        return dateOfBirth;
    }


    public String  getEmailId() {
        return emailId;
    }

    public String  getRole() {
        return role;
    }

    public Address getAddress() {
        return address;
    }

    public String  getGender() {
        return gender;
    }
    public long  getMobileNumber() {
        return mobileNumber;
    }

    /**
     * overiding toString() Method.
     * inside toString Method it uses String Builder to Concat 
     * the Variables and returning it. 
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\n Detail of Employee: \n")
               .append("\n First Name      : ").append(this.getFirstName())
               .append("\n Last Name       : ").append(this.getLastName())
               .append("\n Employee ID     : ").append(this.getEmployeeId())
               .append("\n Employee Role   : ").append(this.getRole())
               .append("\n Mobile Number   : ").append(this.getMobileNumber())
               .append("\n Salary          : ").append(this.getSalary())
               .append("\n Email Id        : ").append(this.getEmailId())
               .append("\n Joining Of Date : ").append(this.getJoiningDate())
               .append("\n Date Of Birth   : ").append(this.getDateOfBirth())
               .append("\n Gender          : ").append(this.getGender())
               .append(this.getAddress());
        return builder.toString();

    }
}