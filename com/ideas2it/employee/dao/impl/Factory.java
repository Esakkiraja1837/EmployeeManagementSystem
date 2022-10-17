package com.ideas2it.employee.dao.impl;

import com.ideas2it.employee.exception.EMSException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.PreparedStatement;  
import java.sql.ResultSet;  
import java.sql.SQLException;

/**
 * This makes the connection between the database and our application.
 * From this connection we can manipulate the data in database.
 * @author  ESAKKIRAJA E.
 */
public class Factory {
    private static String databaseURL = "jdbc:mysql://localhost:3306/employee_management_system";
    private static String user = "root";
    private static String password = "1234@";
    private static Connection connection = null;
    private static Factory factoryConnection = null;
    private static final Logger logger = LogManager.getLogger(Factory.class);

    private Factory() {}

    /**
     * This used to call the connection between the database and application
     * @return customconnection
     */
    public static Factory getFactory() {
        if (factoryConnection == null) {
            factoryConnection = new Factory();
        }
        return factoryConnection;
    }

    /**
     * Connection between the database and java application.
     */
    public Connection getConnection() throws EMSException {
        
        try {

            if (connection == null || connection.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(databaseURL, user, password);
            }
        } catch (ClassNotFoundException e) {
            logger.fatal("No connection found");
            throw new EMSException
            ("ErrorCode 107", "Error occured can not find connection, Try again");
        } catch (SQLException e) {
            logger.fatal("Connection can not found");
            throw new EMSException
            ("ErrorCode 107", "Error occured can not find connection, Try again");
        }
        return connection;
    }

    /**
     * Close the opened connection between the database
     * and java application
     */
    public void closeConnection() throws EMSException {

       try {
           if (connection != null) {
               connection.close();
           }
       } catch (SQLException e) {
            logger.fatal("Connection can not Disconnected");
            throw new EMSException
            ("ErrorCode 107", "Error occured can not find connection, Try again");
       }
    }
}