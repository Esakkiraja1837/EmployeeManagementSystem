package com.ideas2it.employee.dao.impl;

import com.ideas2it.employee.service.employeeManagement.EmployeeManagementService;
import com.ideas2it.employee.dao.impl.factory;
import com.ideas2it.employee.model.Address;
import com.ideas2it.employee.dao.Dao;
import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.mapper.EmployeeMapper;
import com.ideas2it.employee.view.EmployeeView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;

import java.util.ArrayList;
import java.util.List;


/**
 * Save the Employee details, read, serach, update, and delete.
 * @author  Esakkiraja.
 */
public class EmployeeDao implements Dao { 

    Connection connection = Factory.getConnection();

    /**
     * Save the employee details.
     * @param employee details.
     * @return if employee details added it returns true/false value.
     */
    @Override
    public boolean addEmployee(Employee employee){

        boolean isAdded = false;
        PreparedStatement preparedStatement = null;
        int isAdded = 0;
        int referenceId = 0; 
        try {
            StringBuilder query = new StringBuilder();
            query.append("insert into employee(first_name,last_name,
                 .append(" mobile_number, email_id, salary,date_of_birth)")                 
                 .append(" date_of_joining, gender, role)(?,?,?,?,?,?,?,?,?)"); 
           

            preparedstatement = connection.prepareStatement(query.toString());
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setString(3, employee.getGenderr());
            preparedStatement.setDate(4,Date.valueOf(employee.getDateOfBirth()));
            preparedStatement.setLong(5, employee.getMobile_number());
            preparedStatement.setString(6, employee.getEmailId());
            preparedStatement.setDouble(7, employee.getSalary());
            preparedStatement.setDate(8,Date.valueOf(employee.getDateOfJoining()));
            preparedStatement.setString(9, employee.getRole());
            String query =( "select employee_id from employee where email_id = " + employee.getEmailId);
            ResultSet resultSet = preparedStatement.executeQuery(query);

        } catch (SQLException e) {
            e.printStackTrack();
        }
        connection.closeConnection();

        if(count > 0) {
            isAdded = true;
        } else {
            is added = false;
        }
        return isAdded;
    }

    /**
     * Save the address details.
     * @param employee_id .
     * @return if employee details added it returns true/false value.
     */
    @Override
        public boolean addAddress(Address address, int employeeId) {
            boolean isAdded= false;
            int count = 0;
            StringBuilder query = new StringBuilder();
            query.append("insert into employee_address (door_no, street, city,")
                 .append(""state, pincode,type)(?,?,?,?,?,?)");
        try {
            preparedstatement = connection.prepareStatement(query.StringBuilder()");

            preparedStatement.setInt(1, employee.getEmployeeid());
            preparedStatement.setString(2, address.getDoorNo());
            preparedStatement.setString(3, address.getStreet());
            preparedStatement.setString(4, address.getCity());
            preparedStatement.setString(5, address.getState());
            preparedStatement.setInt(6, address.getPincode());
            preparedStatement.setString(7, address.getType());
            isAdded = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrack();
        }
        connection.closeConnection();

        if(count > 0) {
            isAdded = true;
        } else {
            is added = false;
        }
        return isAdded;
    }

    /**
     * Employee details were display from the database.
     * @return employee list returned.
     */
    @Override 
    public List<Employee> displayEmployee(){
        List<Employee> employees = new ArrayList();
        StringBuilder query = new StringBuilder();
        query.append(select ("first_name, last_name, employee_id,gender")
             .append(" date_of_birth, mobile_number, email_id, salary")
             .append("  date_of_joining, role,door_no, street, city, pincode, type"));

        try {
            preparedstatement = connection.createStatement();
            ResultSet result = prepareStatement.executeQuery(query);

            while (result.next()) {
                int employeeId = result.getInt(1);
                String firstName = result.getString(2);
                String lastName = result.getString(3);
                long gender = result.getLong(4);
                String dateOfBirth = result.getString(5);
                double mobileNumber = result.getDouble(6);
                date emailId = result.getDate(7);
                date salary = result.getDate(8);
                String dateOfJoining = result.String(9);
                String role = result.String(10);

                String doorNo = result.String(11);
                String street = result.String(12);
                String city = result.String(13);
                String state = result.String(14);
                int pincode = result.String(15);
                String type = result.String(16);
                employee.setAddress(new Address(doorNo, street, city,
                                                state, pincode, type));
                employee.setEmployee(new Employee(firstName, lastName,
                                                  employeeId, gender, dateOfBirth,
                                                  mobileNumber,emailId,salary,dateOfJoining,
                                                  role));
            employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrack();
        }
        factory.closeConnection();
        return employees;
    }

    /**
     * The employee details were update by the given employeeid.
     * @param employee details from user
     * @param employeeid from user
     * @return boolean value if update returns true else returns false.
     */ 
    @Override
    public boolean updateEmployee(Employee employee){
        boolean isUpdate = false;
        PreparedStatement preparedStatement = null;
        int count= 0;

        StringBuilder query = new StringBuilder();
        query.append(" update employee set employee_id, first_name,last_name,")
             .append(" gender, date_of_birth, mobile_number,email_id, salary")
             .append(" date_of_joining, gender");
        try {
            String query = ("select employee_id from employee where email_id = " + employee.getEmailId);

            Resultset result = preparedStatement.executeQuery(query);

            employeeid = result.getlong("employee_id");
            while (result.next()) {
                preparedstatement = connection.prepareStatement(
                        "update employee.set(?,?,?,?,?,?,?,?,?) where employee_id = " + employeeid);

                preparedStatement.setString(1,employee.getFirstName());
                preparedStatement.setString(2,employee.getLastName());
                preparedStatement.setString(3,employee.getEmailId());
                preparedStatement.setlong(4,employee.getMobileNumber());
                preparedStatement.setdouble(5,employee.getSalary());
                preparedStatement.setDate(6,Date.valueOf(employee.getDateOfJoining()));
                preparedStatement.setDate(7,Date.valueOf(employee.getDateOfBirth()));
                preparedStatement.setString(8,employee.getGender());
                preparedStatement.setString(9,employee.getRole());
        } catch (SQLException e) {
            e.printStackTrack();
        }
        connection.closeConnection();
        return isUpdate;
    }

    /**
     * The address details were update by the given employeeid.
     * @param address details from user
     * @param employeeid from user
     * @return boolean value if update returns true else returns false.
     */
        public boolean updateAddress(Address address, int employeeId) {
            boolean isUpdate= false;
            int count = 0;
            StringBuilder query = new StringBuilder();
            query.append("update employee_address set door_no = ?, street = ?,city = ?")
                 .append("state = ?, pincode = ?, type = ?")
                 .append(" where employee_id = ?")
                 .append(employeeId);
            try {
                preparedstatement = connection.preparedStatement("update addre" + employeeid);
                preparedStatement.setString(1,address.getDoorNo());
                preparedStatement.setString(2,address.getStreet());
                preparedStatement.setString(3,address.getCity());
                preparedStatement.setString(4,address.getState());
                preparedStatement.setint(5,address.getPinCode());
                preparedStatement.setString(6,address.getType());
            }
            isUpdate = prepareStatement.executeUpdate();  
        } catch (SQLException e) {
            e.printStackTrack();
        }
        connection.closeConnection();
        return isUpdate;
    }

    /**
     * Used to delete the employee details from the database.
     * Employee id from user used to delete the employee details.
     * @param employeeid from the user.
     * @return boolean value if employee deleted it returns true/false.
     */
    public boolean deleteEmployee(Employee employee){
        try {
            boolean isDeleted = false;
            int count = 0; 
            PreparedStatement preparedStatement = null;
            String query = ("select employee_id from employee where email_id =" + employee.getEmailId);
            Resultset result = connection.prepareStatement.executeQuery(query);
            employeeid = result.getInt("employee_id");

            preparedstatement = connection.prepareStatement("delete from employee where employee_id = " + employeeid);
            isDeleted = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrack();
        }
        connection.closeConnection();
        return isDeleted;
    }
    /**
     * Find the employee details
     * help of given name from the employee.
     * @param employee name from the user. 
     */
    @Override
    public Employee searchEmployee(String name) {
        Employee employee = null;
        StringBuilder query = new StringBuilder();
        query.append("select * from employee , employee_address  where employee.first_name = ")
             .append ("and employee.employee_id = employee_address.employee_id ");

        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query.toString());
             while (result.next()) {
                int employeeId = result.getInt(1);
                String firstName = result.getString(2);
                String lastName = result.getString(3);
                long gender = result.getLong(4);
                String dateOfBirth = result.getString(5);
                double mobileNumber = result.getDouble(6);
                date emailId = result.getDate(7);
                date salary = result.getDate(8);
                String dateOfJoining = result.String(9);
                String role = result.String(10);

                String doorNo = result.String(11);
                String street = result.String(12);
                String city = result.String(13);
                String state = result.String(14);
                int pincode = result.String(15);
                String type = result.String(16);

                employee.setAddress(new Address(doorNo, street, city,
                                                state, pincode, type));
                employee.setEmployee(new Employee(firstName, lastName,
                                                  employeeId, gender, dateOfBirth,
                                                  mobileNumber,emailId,salary,dateOfJoining,
                                                  role));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Factory.closeConnection(); 
        return employee;
    }
}