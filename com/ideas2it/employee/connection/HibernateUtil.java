package com.ideas2it.employee.connection;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * This makes the connection between the database and our application.
 * From this connection we can manipulate the data in database.
 * @author  ESAKKIRAJA E.
 */
public class HibernateUtil {

    private static SessionFactory factory = null;

    private HibernateUtil() {
    }

    /**
     * Connection between the database and java application.
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
     * Close the opened connection between the database
     * and java application
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
