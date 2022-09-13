package com.ideas2it.employee.model;

import java.time.LocalDate;

/**
 * It present employee Address.
 *
 * @version 1.1
 * @author  ESAKKIRAJA E
 * 13-09-2022.
 */
public class Address  {

    private String flatNo;
    private String streetName;
    private String homeTown;
    private String district;
    private String state;
    private int pinCode;

    public Address() { 
    }

    public Address(String flatNo, String streetName, String homeTown,
                   String district, String state, int pinCode ) {
        this.flatNo = flatNo;
        this.streetName = streetName;
        this.homeTown = homeTown;
        this.district= district;
        this.state = state;
        this.pinCode = pinCode;
    }

    public void setFlatNo(String flatNo) {
        this.flatNo = flatNo;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }

    public String  getFlatNo() {
        return flatNo;
    }

    public String  getStreetName() {
        return streetName;
    }

    public String  getHomeTown() {
        return homeTown;
    }

    public String  getDistrict() {
        return district;
    }

    public String  getState() {
        return state;
    }

    public int  getPinCode() {
        return pinCode;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\n\n ADDRESS of Employee: \n")
               .append("\n FlatNo           : ").append(this.getFlatNo())
               .append("\n StreetName       : ").append(this.getStreetName())
               .append("\n Home Town        : ").append(this.getHomeTown())
               .append("\n District         : ").append(this.getDistrict())
               .append("\n State            : ").append(this.getState())
               .append("\n PinCode          : ").append(this.getPinCode());
        return builder.toString();
    }
}