package com.ideas2it.employee.dao.impl;

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

    //private Factory() {}
    /**
     * Connection between the database and java application.
     */
    public static Connection getConnection() {
        
        try {
            if (connection == null || connection.isClosed())
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(databaseURL, user, password);
        } catch (ClassNotFoundException e) {
            System.out.println("Could not find database driver class");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("An error occurred. Maybe user/password is invalid");
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * Close the opened connection between the database
     * and java application
     */
    public static void closeConnection() {

       try {
           if (connection != null) {
               connection.close();
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }
    }
}