package com.ideas2it.employee.dao.impl;

import com.ideas2it.employee.constant.EmployeeManagementConstant;
import com.ideas2it.employee.connection.ConneectionUtil;
import com.ideas2it.employee.dao.Dao;
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
public class EmployeeDao implements Dao { 

    SessionFactory factory = ConneectionUtil.getSessionFactory();

    private static final Logger logger = LogManager.getLogger(EmployeeDao.class);

    /**
     * Save the employee details.
     * @param employee details.
     * @return if employee details added it returns true/false value.
     */
    @Override
    public int addEmployee(Employee employee) throws EMSException {
        Session session = factory.openSession();
        Transaction transaction = null;
        int employeeId;

        try {
            transaction = session.beginTransaction();
            employeeId = (Integer) session.save(employee);
            transaction.commit();

        } catch(HibernateException e) {
            logger.error("Employee Details Not Added");
            throw new EMSException( "ERROR 404", "Error occured in insert data, Try again");
        }
        return employeeId;
    }

    /**
     * Employee details were display from the database.
     * @return employee list returned.
     */
    @Override 
    public List<Employee> displayEmployee() throws EMSException {
        List<Employee> employees = new ArrayList();
        Transaction transaction = null;

        try {
            Session session = factory.openSession();
            transaction = session.beginTransaction();
            employees = session.createQuery("FROM Employee").list();
            transaction.commit();

        } catch(HibernateException e) {
            logger.error("Employee Details Not displayed");
            throw new EMSException("ERROR 405","Error occured the data, Try again");
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
    public void updateEmployee(Employee employee) throws EMSException { 
        Transaction transaction = null;

        try {
            Session session = factory.openSession();
            transaction = session.beginTransaction();
            session.merge(employee);
            transaction.commit();

        } catch(HibernateException e) {
            logger.error(EmployeeManagementConstant.EMPLOYEE_DETAILS_NOTFOUND);
            throw new EMSException
            ("ERROR 406", "Error occured update the  data, Try again");
        }
    }

    /**
     * Used to delete the employee details from the database.
     * Employee id from user used to delete the employee details.
     * @param employeeid from the user.
     * @return boolean value if employee deleted it returns true/false.
     */
    @Override
    public void deleteEmployee(int employeeId) throws EMSException {
        Session session = factory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Employee employee = (Employee) session.get(Employee.class, employeeId);
            session.remove(employee);
            transaction.commit();

        } catch (HibernateException e) {
            logger.error(EmployeeManagementConstant.EMPLOYEE_DETAILS_NOTFOUND + "Employee ID :" + employeeId);
            throw new EMSException
            ("ERROR 407", "Error occured in delete the data, Try again");
        }
    }

    /**
     * Find the employee details
     * help of given name from the employee.
     * @param employee name from the user. 
     */
    @Override
    public List<Employee> searchEmployee(String firstName) throws EMSException {
        List<Employee> employees = new ArrayList();
        Transaction transaction = null;

        try {
            Session session = factory.openSession();
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Employee.class);
            employees = (List<Employee>) criteria.add(Restrictions.like
                                  ("firstName", (firstName + "%"))).list();
            transaction.commit();

        } catch (HibernateException e) {
             logger.error(EmployeeManagementConstant.EMPLOYEE_DETAILS_NOTFOUND);
             throw new EMSException("Error occured the data, Try again", "ERROR 408");
        }
        return employees;
    }
}