package com.ideas2it.employee.model;

import java.time.LocalDate;

/**
 * It present employee Address.
 *
 * @version 1.1
 * @author  ESAKKIRAJA E
 * 13-09-2022.
 */
public class Address {

    private String doorNo;
    private String street;
    private String city;
    private String state;
    private int pinCode;
    private String type;

    public Address() { 
    }

    public Address(String doorNo, String street, String city,
                   String state, int pinCode, String type) {
        this.doorNo = doorNo;
        this.street = street;
        this.city = city;
        this.state = state;
        this.pinCode = pinCode;
        this.type = type;
    }

    public void setDoorNo(String doorNo) {
        this.doorNo = doorNo;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getDoorNo() {
        return doorNo;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public int getPinCode() {
        return pinCode;
    }

    public String getType() {
        return type;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\n\n ADDRESS of Employee: \n")
               .append("\n DoorNo           : ").append(this.getDoorNo())
               .append("\n Street           : ").append(this.getStreet())
               .append("\n City             : ").append(this.getCity())
               .append("\n State            : ").append(this.getState())
               .append("\n PinCode          : ").append(this.getPinCode())
               .append("\n Type             : ").append(this.getType());
        return builder.toString();
    }
}