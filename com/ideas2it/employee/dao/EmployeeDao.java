package com.ideas2it.employee.dao;

import com.ideas2it.employee.connection.ConneectionUtil;
import com.ideas2it.employee.constant.EmployeeManagementConstant;
import com.ideas2it.employee.exception.EMSException;
import com.ideas2it.employee.model.Address;
import com.ideas2it.employee.model.Employee;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.cfg.Configuration;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;


import java.util.ArrayList;
import java.util.List;


/**
 * Save the Employee details, read, serach, update, and delete.
 * @author  Esakkiraja.
 */
public class EmployeeDao { 

    SessionFactory factory = ConneectionUtil.getSessionFactory();

    private static final Logger logger = LogManager.getLogger(EmployeeDao.class);

    /**
     * Save the employee details.
     * @param employee details.
     * @return if employeeid value.
     */
    public int addEmployee(Employee employee) throws EMSException {
        Transaction transaction = null;
        Session session = factory.openSession();
        int employeeId;

        try {
            transaction = session.beginTransaction();
            employeeId = (Integer) session.save(employee);
            transaction.commit();
        } catch(HibernateException e) {
            logger.error(e.getMessage());
            throw new EMSException( "ERROR 404", "Error occured in insert data, Try again");
        } finally {
            session.close();
        }
        return employeeId;
    }

    /**
     * Employee details were display from the database.
     * @return employee list of value.
     */ 
    public List<Employee> getAllEmployee() throws EMSException {
        List<Employee> employees = new ArrayList();
        Session session = factory.openSession();

        try {
            employees = session.createQuery("FROM Employee").list();
        } catch(HibernateException e) {
            logger.error(e.getMessage());
            throw new EMSException("ERROR 405","Error occured the data, Try again");
        } finally {
            session.close();
        }
        return employees;
    }

    /**
     * The employee details were update by the given employeeid.
     * @param employee details from user
     * @param employee from user
     */ 
    public void updateEmployee(Employee employee) throws EMSException { 
        Transaction transaction = null;
        Session session = factory.openSession();

        try {
            transaction = session.beginTransaction();
            session.update(employee);
            transaction.commit();
        } catch(HibernateException e) {
            logger.error(e.getMessage());
            throw new EMSException
            ("ERROR 406", "Error occured update the  data, Try again");
        } finally {
            session.close();
        }
    }

    /**
     * Used to delete the employee details from the database.
     * Employee id from user used to delete the employee details.
     * @param employeeid from the user.
     */
    public void deleteEmployee(int employeeId) throws EMSException { 
        Transaction transaction = null;
        Session session = factory.openSession();

        try {
            transaction = session.beginTransaction();
            Employee employee = (Employee) session.get(Employee.class, employeeId);
            session.remove(employee);
            transaction.commit();
        } catch (HibernateException e) {
            logger.error(e.getMessage() + "Employee ID :" + employeeId);
            throw new EMSException
            ("ERROR 407", "Error occured in delete the data, Try again");
        } finally {
            session.close();
        }
    }

    /**
     * Find the employee details
     * help of given name from the employee.
     * @param employee name from the user. 
     */
    public List<Employee> searchEmployee(String firstName) throws EMSException {
        List<Employee> employees = new ArrayList();
        Session session = factory.openSession();
        try {
            Criteria criteria = session.createCriteria(Employee.class);
            employees = (List<Employee>) criteria.add(Restrictions.like
                                  ("firstName", (firstName + "%"))).list();
        } catch (HibernateException e) {
             logger.error(e.getMessage());
             throw new EMSException("Error occured the data, Try again", "ERROR 408");
        } finally {
            session.close();
        }
        return employees;
    }
}