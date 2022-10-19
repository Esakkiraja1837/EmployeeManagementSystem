package com.ideas2it.employee.dao.impl;

import com.ideas2it.employee.dao.impl.Factory;
import com.ideas2it.employee.model.Address;
import com.ideas2it.employee.dao.Dao;
import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.connection.HibernateUtil;
import com.ideas2it.employee.exception.EMSException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.hibernate.cfg.Configuration;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.Transaction;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * Save the Employee details, read, serach, update, and delete.
 * @author  Esakkiraja.
 */
public class EmployeeDao implements Dao {

    SessionFactory factory = HibernateUtil.getSessionFactory();

    private static final Logger logger = LogManager.getLogger(EmployeeDao.class);

    /**
     * Save the employee details.
     * @param employee details.
     * @return if employee details added it returns true/false value.
     */
    @Override
    public boolean addEmployee(Employee employee) throws EMSException {
        Session session = factory.openSession();
        Transaction transaction = null;
        int id;
        boolean isAdded = false;
        try {
            transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
        } catch(HibernateException e) {
            logger.error("Employee Details not Added");
            throw new EMSException
             ( "ERROR 404", "Error occured in insert data, Try again");
        } finally {
            session.close();
        }

        if(transaction != null) {
            isAdded = true;
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
        Session session = factory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Employee");
            employees = query.list();
            transaction.commit();
        } catch(HibernateException e) {
            logger.error("Employee Details Not displayed");
            throw new EMSException("ERROR 405",
                    "Error occured the data, Try again");
        } finally {
            session.close();
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
        boolean isUpdate = false;
        Session session = factory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Employee employees = (Employee)session.merge(employee);
            transaction.commit();
            if(employee != null) {
                isUpdate = true;
             }
        } catch(HibernateException e) {
            logger.error("Employee Details Not found");
            throw new EMSException
                   ("ERROR 406", "Error occured update the  data, Try again");
        } finally {
            session.close();
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
        boolean isDeleted = false;
        Session session = factory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Employee employee = (Employee)session.get(Employee.class, employeeId);
            session.delete(employee);
            transaction.commit();
        } catch(HibernateException e) {
                logger.error("Employee Details Not found" + "Employee ID :" + employeeId);
                throw new EMSException
                ("ERROR 407", "Error occured in update the data, Try again");
        } finally {
            session.close();
        }

        if(transaction != null) {
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
        Session session = factory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Employee.class);
            employees = (List<Employee>) criteria.add(Restrictions.like("firstName", (firstName + "%"))).list();
            transaction.commit();
        } catch(HibernateException e) {
             logger.error("Employee Details Not found");
             throw new EMSException("Error occured the data, Try again", "ERROR 408");
        } finally {
            session.close();
        }
        return employees;
    }
}