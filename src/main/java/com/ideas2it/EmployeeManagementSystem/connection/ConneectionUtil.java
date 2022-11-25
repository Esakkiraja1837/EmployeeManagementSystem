package com.ideas2it.EmployeeManagementSystem.connection;

import org.hibernate.cfg.Configuration;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

/**
 * Makes connection between database and the application .
 * @author ESAKKIRAJA E
 */
public class ConneectionUtil {

    private static SessionFactory factory;

    private ConneectionUtil() {
    }

    /**
     * Creates connection between the database and application.
     */
    public static SessionFactory getSessionFactory() {

        try {

            if(factory == null) {
                factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
            }

        } catch (HibernateException e) {
              e.printStackTrace();
        }
         return factory;
    }

    /**
     * Closes the connection between the database and application.
     */
    public void closeConnection() {

        try {

            if(factory != null) {
                factory.close();
            }

        } catch(HibernateException e) {
            e.printStackTrace();
        }
    }
}