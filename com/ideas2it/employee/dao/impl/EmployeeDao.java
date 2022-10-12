package com.ideas2it.employee.dao.impl;

import com.ideas2it.employee.dao.impl.Factory;
import com.ideas2it.employee.model.Address;
import com.ideas2it.employee.dao.Dao;
import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.exception.EMSException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * Save the Employee details, read, serach, update, and delete.
 * @author  Esakkiraja.
 */
public class EmployeeDao implements Dao { 
    Factory factoryConnection = Factory.getFactory();
    private static final Logger logger = LogManager.getLogger(EmployeeDao.class);

    /**
     * Save the employee details.
     * @param employee details.
     * @return if employee details added it returns true/false value.
     */
    @Override
    public boolean addEmployee(Employee employee) throws EMSException {

        Connection connection = factoryConnection.getConnection();
        boolean isAdded = false;
        PreparedStatement preparedStatement = null;
        int count = 0; 
        int employeeId = 0;
        try {
            String query = "insert into employee( first_name, last_name, gender, date_of_birth, mobile_number, email_id, salary, date_of_joining, role) values(?,?,?,?,?,?,?,?,?)"; 
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setString(3, employee.getGender());
            preparedStatement.setDate(4,Date.valueOf(employee.getDateOfBirth()));
            preparedStatement.setLong(5, employee.getMobileNumber());
            preparedStatement.setString(6, employee.getEmailId());
            preparedStatement.setDouble(7, employee.getSalary());
            preparedStatement.setDate(8,Date.valueOf(employee.getJoiningDate()));
            preparedStatement.setString(9, employee.getRole());
            count = preparedStatement.executeUpdate();
            String queries = ( "select employee_id from employee where email_id = ?");

            PreparedStatement statement = connection.prepareStatement(queries);
            statement.setString(1, employee.getEmailId());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                employeeId = resultSet.getInt(1);
            }
            isAdded = addAddress(employee.getAddress(), employeeId);

        } catch (SQLException e) {
            logger.error("Employee Details Not Added");
            throw new EMSException( "ERROR 404", "Error occured in insert data, Try again");    
        } finally {
            factoryConnection.closeConnection();
        }

        if(count > 0) {
            isAdded = true;
            logger.info("Employee Details created Employee Id :" + employeeId);
        } else {
            isAdded = false;
        }
        return isAdded;
    }

    /**
     * Save the address details.
     * @param employee_id, address .
     * @return if employee details added it returns true/false value.
     */
    @Override
    public boolean addAddress(List <Address> address, int employeeId) throws EMSException {
        PreparedStatement preparedStatement = null;
        Connection connection = factoryConnection.getConnection();
        boolean isAdded= false;
        int count = 0;

        try {

            for(Address addresses : address) {
                String query = "insert into address(employee_id, door_no, street, city, state, pincode, type)"
                                                    +" values(?,?,?,?,?,?,?)";

                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1,employeeId);
                preparedStatement.setString(2, addresses.getDoorNo());
                preparedStatement.setString(3, addresses.getStreet());
                preparedStatement.setString(4, addresses.getCity());
                preparedStatement.setString(5, addresses.getState());
                preparedStatement.setInt(6, addresses.getPinCode());
                preparedStatement.setString(7, addresses.getType());
                count = preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            logger.error("Employee address Details not added");
            throw new EMSException
            ("ERROR 404", "Error occured in insert data, Try again");
        } finally {
            factoryConnection.closeConnection();
        }

        if(count > 0) {
            isAdded = true;
        } else {
            isAdded = false;
        }
        return isAdded;
    }

    /**
     * Employee details were display from the database.
     * @return employee list returned.
     */
    @Override 
    public List<Employee> displayEmployee() throws EMSException {
        List<Employee> employees = new ArrayList();
        Connection connection = factoryConnection.getConnection();
        PreparedStatement preparedStatement = null;
        String query ="select * from employee,address where employee.employee_id = address.employee_id ";

        try {
             preparedStatement = connection.prepareStatement(query);
             ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                int employeeId = result.getInt(1);
                String firstName = result.getString(2);
                String lastName = result.getString(3);
                String gender = result.getString(4);
                LocalDate dateOfBirth = result.getDate(5).toLocalDate();
                Long mobileNumber = result.getLong(6);
                String emailId = result.getString(7);
                Double salary = result.getDouble(8);
                LocalDate joiningDate = result.getDate(9).toLocalDate();
                String role = result.getString(10);

                Statement statement = connection.createStatement();
                String addressQuery = "select door_no, street, city, state,"
                                       + "pincode, type from address where "
                                       + "employee_id = " + employeeId; 
                ResultSet resultAddress = statement.executeQuery(addressQuery);
                List<Address> address = new ArrayList();

                while (resultAddress.next()) {
                    Address addresses = new Address(); 

                    String doorNo = result.getString(13);
                    String street = result.getString(14);
                    String city = result.getString(15);
                    String state = result.getString(16);
                    int pincode = result.getInt(17);
                    String type = result.getString(18);

                    addresses = new Address(doorNo, street, city, state, pincode, type);
                    address.add(addresses);
                }

                Employee employee = new Employee(firstName, lastName, employeeId, role,
                                                 mobileNumber, emailId, salary, address,
                                                 joiningDate, dateOfBirth, gender);
                employees.add(employee);
            }
        } catch (SQLException e) {
            logger.error("Employee Details Not displayed");
            throw new EMSException("ERROR 405",
                    "Error occured the data, Try again");
        } finally {
            factoryConnection.closeConnection();
        }
        return employees;
    }

    /**
     * The employee details were update by the given employeeid.
     * @param employee details from user
     * @param employeeid from user
     * @return boolean value if update returns true else returns false.
     */ 
    @Override
    public boolean updateEmployee(Employee employee) throws EMSException { 
        Connection connection = factoryConnection.getConnection();
        boolean isUpdate = false;
        PreparedStatement preparedStatement = null;
        int count= 0;
        int employeeid = employee.getEmployeeId();

        String query = "update employee set first_name=(?),last_name = (?), email_id = (?), mobile_number = (?),salary = (?),date_of_joining = (?),date_of_birth = (?),gender = (?), role = (?) where employee_id = ?";
        try {
            preparedStatement = connection.prepareStatement(query.toString());
            preparedStatement.setString(1,employee.getFirstName());
            preparedStatement.setString(2,employee.getLastName());
            preparedStatement.setString(3,employee.getEmailId());
            preparedStatement.setLong(4,employee.getMobileNumber());
            preparedStatement.setDouble(5,employee.getSalary());
            preparedStatement.setDate(6,Date.valueOf(employee.getJoiningDate()));
            preparedStatement.setDate(7,Date.valueOf(employee.getDateOfBirth()));
            preparedStatement.setString(8,employee.getGender());
            preparedStatement.setString(9,employee.getRole());
            preparedStatement.setInt(10,employeeid);
            count = preparedStatement.executeUpdate();
            isUpdate = updateAddress(employee.getAddress(), employeeid);
        } catch (SQLException e) {
            logger.error("Employee Details Not found" + "EmployeeID :" + employeeid);
            throw new EMSException
            ("ERROR 406", "Error occured update the  data, Try again");
        } finally {
            factoryConnection.closeConnection();
        }
        return isUpdate;
    }

    /**
     * The address details were update by the given employeeid.
     * @param address details from user
     * @param employeeid from user
     * @return boolean value if update returns true else returns false.
     */
    public boolean updateAddress(List<Address> address, int employeeId) throws EMSException {
        Connection connection = factoryConnection.getConnection();
        boolean isUpdate= false;
        int count = 0;
        PreparedStatement preparedStatement = null;
        StringBuilder query = new StringBuilder();
        query.append("update address set door_no = ?, street = ?,city = ?,")
                 .append("state = ?, pincode = ?, type = ?")
                 .append(" where employee_id = ?");

            try {
                for(Address addresses : address) {
                    preparedStatement = connection.prepareStatement(query.toString());
                    preparedStatement.setString(1,addresses.getDoorNo());
                    preparedStatement.setString(2,addresses.getStreet());
                    preparedStatement.setString(3,addresses.getCity());
                    preparedStatement.setString(4,addresses.getState());
                    preparedStatement.setInt(5,addresses.getPinCode());
                    preparedStatement.setString(6,addresses.getType());
                    preparedStatement.setInt(7,employeeId);
                    count = preparedStatement.executeUpdate(); 
                } 
            } catch (SQLException e) {
                logger.error("Employee Details Not found");
                throw new EMSException
                ("ERROR 406", "Error occured in update the data, Try again");
        } finally {
            factoryConnection.closeConnection();
        }
            return isUpdate;
    }

    /**
     * Used to delete the employee details from the database.
     * Employee id from user used to delete the employee details.
     * @param employeeid from the user.
     * @return boolean value if employee deleted it returns true/false.
     */
    @Override
    public boolean deleteEmployee(int employeeId) throws EMSException {
        Connection connection = factoryConnection.getConnection();
        boolean isDeleted = false;
        int count = 0; 
        PreparedStatement preparedStatement = null;
        String query = "delete from employee where employee_id = ?";
        

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,employeeId);
            count = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Employee Details Not found" + "Employee ID :" + employeeId);
            throw new EMSException
            ("ERROR 407", "Error occured in delete the data, Try again");
        } finally {
            factoryConnection.closeConnection();
        }

        if(count > 0) {
            isDeleted = true;
        }
        return isDeleted;
    }
    /**
     * Find the employee details
     * help of given name from the employee.
     * @param employee name from the user. 
     */
    @Override
    public List<Employee> searchEmployee(String firstName) throws EMSException {
        List<Employee> employees = new ArrayList();

        Connection connection = factoryConnection.getConnection();
        Employee employee = null;
        StringBuilder query = new StringBuilder();
        query.append("select employee_id, first_name, last_name, gender,")
             .append(" date_of_birth, mobile_number, email_id,")
             .append(" salary, date_of_joining, role from employee")
             .append(" where first_name like '")
             .append(firstName).append("%'");

        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query.toString());
            while (result.next()) {
                int employeeId = result.getInt(1);
                firstName = result.getString(2);
                String lastName = result.getString(3);
                String gender = result.getString(4);
                LocalDate dateOfBirth = result.getDate(5).toLocalDate();
                long mobileNumber = result.getLong(6);
                String emailId = result.getString(7);
                double salary = result.getDouble(8);
                LocalDate joiningDate = result.getDate(9).toLocalDate();
                String role = result.getString(10);

                Statement statement1 = connection.createStatement();
                String addressQuery = "select door_no, street, city, state," 
                                      + " pincode, type from address where "
                                      + " employee_id = " + employeeId;

                ResultSet resultAddress = statement1.executeQuery(addressQuery);
                List<Address> addresses = new ArrayList();

                while (resultAddress.next()) {
                    Address addresss = new Address();

                    String doorNo = resultAddress.getString(1);
                    String street = resultAddress.getString(2);
                    String city = resultAddress.getString(3);
                    String state = resultAddress.getString(4);
                    int pincode = resultAddress.getInt(5);
                    String type = resultAddress.getString(6);

                    addresss = new Address(doorNo, street, city, 
                                            state, pincode, type);
                    addresses.add(addresss);
                }

                 employee = new Employee(firstName,lastName, employeeId,  role,
                                         mobileNumber, emailId, salary, addresses,
                                         joiningDate,  dateOfBirth, gender);
                 employees.add(employee);
            }
        } catch (SQLException e) {
             logger.error("Employee Details Not found");
             throw new EMSException("Error occured the data, Try again", "ERROR 408");
        } finally {
            factoryConnection.closeConnection();
        }

        return employees;
    }
}