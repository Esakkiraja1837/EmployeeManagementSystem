package com.ideas2it.employee.dao;

import com.ideas2it.employee.constant.EmployeeManagementConstant;
import com.ideas2it.employee.connection.ConneectionUtil;
import com.ideas2it.employee.dao.ProjectManagementDao;
import com.ideas2it.employee.exception.EMSException;
import com.ideas2it.employee.model.Address;
import com.ideas2it.employee.model.Employee;
import com.ideas2it.employee.model.Project;

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
public class ProjectManagementDao {

    private static final Logger logger = LogManager.getLogger(ProjectManagementDao.class);
    SessionFactory factory = ConneectionUtil.getSessionFactory();

    /**
     * Save the Project details.
     * @param Project details.
     * @return  ProjectId value.
     */
    public int addProject(Project project) throws EMSException {  
        Session session = factory.openSession(); 
        Transaction transaction = null;
        int projectId = 0;

        try {
            transaction = session.beginTransaction();
            projectId = (Integer) session.save(project);
            transaction.commit();
        } catch(HibernateException e) {
            logger.error(e.getMessage());
            throw new EMSException( "ERROR 404", "Error occured in insert data, Try again");
        } finally {
            session.close();
        }
        return projectId;
    }

    /**
     * Project details were display from the database.
     * @return Project list of values returned.
     */
    public List<Project> getAllProject() throws EMSException {
        List<Project> projects = new ArrayList();
        Session session = factory.openSession();
        Transaction transaction = null;

        try {
            projects = session.createQuery("FROM Project").list();
        } catch(HibernateException e) {
            logger.error(e.getMessage());
            throw new EMSException("ERROR 405","Error occured the data, Try again");
        } finally {
            session.close();
        }
        return projects;
    }

    /**
     * The Project details were update by the given employeeid.
     * @param Project details from user
     * @param Project from user
     */ 
    public void updateProject(Project project) throws EMSException {
        Transaction transaction = null;
        Session session = factory.openSession();

        try {
            transaction = session.beginTransaction();
            session.update(project);
            transaction.commit();
        } catch(HibernateException e) {
            logger.error(e.getMessage());
            throw new EMSException("ERROR 406", "Error occured update the  data, Try again");
        } finally {
            session.close();
        }
    }

    /**
     * Used to delete the Project details from the database.
     * Project id from user used to delete the Project details.
     * @param Projectid from the user.
     */
    public void deleteProject(int projectId) throws EMSException {
        Session session = factory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Project project = (Project) session.get(Project.class, projectId);
            session.remove(project);
            transaction.commit();
        } catch (HibernateException e) {
            logger.error(e.getMessage() + "Project ID :" + projectId);
            throw new EMSException("ERROR 407", "Error occured in delete the data, Try again");
        } finally {
            session.close();
        }
    }

    /**
     * Find the Project details
     * help of given name from the Project.
     * @param Project name from the user. 
     */
    public List<Project> searchProject(String projectName) throws EMSException {
        List<Project> projects = new ArrayList();
        Session session = factory.openSession();
        Transaction transaction = null;

        try {
            Criteria criteria = session.createCriteria(Project.class);
            projects = (List<Project>) criteria.add(Restrictions.like
                                  ("projectName", (projectName + "%"))).list();
        } catch (HibernateException e) {
             logger.error(e.getMessage() + "Project Name :" + projectName);
             throw new EMSException("Error occured the data, Try again", "ERROR 408");
        } finally {
            session.close();
        }
        return projects;
    }
} 