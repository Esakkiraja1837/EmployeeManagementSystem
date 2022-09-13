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
    private String name; 
    private int id;
    private int age;
    private long mobileNumber;
    private String emailId;
    private double salary;
    private Address address;
    private LocalDate joiningDate;

    public Employee() {
    }

    public Employee(String name, int id, int age, long mobileNumber, 
                    String emailId, double salary, Address address,
                    LocalDate joiningDate) {
        this.name = name;
        this.id = id;
        this.age =  age;
        this.mobileNumber = mobileNumber;
        this.emailId = emailId;
        this.salary = salary;
        this.address = address;
        this.joiningDate = joiningDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setMobileNumber(long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public double getSalary() {
        return salary;
    }

    public LocalDate  getJoiningDate() {
        return joiningDate;
    }

    public int  getAge() {
        return age;
    }

    public String  getEmailId() {
        return emailId;
    }

    public Address getAddress() {
        return address;
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
               .append("\n Name            : ").append(this.getName())
               .append("\n ID              : ").append(this.getId())
               .append("\n Age             : ").append(this.getAge())
               .append("\n Mobile Number   : ").append(this.getMobileNumber())
               .append("\n Salary          : ").append(this.getSalary())
               .append("\n Email Id        : ").append(this.getEmailId())
               .append("\n Joining Of Date : ").append(this.getJoiningDate())
               .append(this.getAddress());
        return builder.toString();

    }
}