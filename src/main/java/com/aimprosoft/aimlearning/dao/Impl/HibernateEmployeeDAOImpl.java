package com.aimprosoft.aimlearning.dao.Impl;

import com.aimprosoft.aimlearning.config.HibernateSessionFactory;
import com.aimprosoft.aimlearning.dao.EmployeeDAO;
import com.aimprosoft.aimlearning.exceptions.DBException;
import com.aimprosoft.aimlearning.exceptions.ValidationException;
import com.aimprosoft.aimlearning.models.Department;
import com.aimprosoft.aimlearning.models.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class HibernateEmployeeDAOImpl implements EmployeeDAO {

    SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();

    @Override
    public List<Employee> getAllEmployees() throws DBException {
        try (Session session = sessionFactory.openSession();) {
            List<Employee> employees = session.createQuery("FROM Employee", Employee.class).list();
            return employees;
        } catch (Exception e) {
            throw new DBException(e.getMessage());
        }
    }

    @Override
    public Employee getById(int id) throws DBException {
        try (Session session = sessionFactory.openSession();) {
            Employee employee = (Employee) session.createQuery("FROM Employee where id=" + id).uniqueResult();
            return employee;
        } catch (Exception e) {
            throw new DBException(e.getMessage());
        }
    }

    @Override
    public List<Employee> getByIdDepartment(int id) throws DBException {
        try (Session session = sessionFactory.openSession();) {
            List<Employee> employees = session.createQuery("FROM Employee where idDepartment =" + id).list();
            return employees;
        } catch (Exception e) {
            throw new DBException(e.getMessage());
        }
    }

    @Override
    public void saveOrUpdate(Employee employee) throws DBException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession();) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(employee);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new DBException(e.getMessage());
        }
    }

    @Override
    public void deleteEmployee(int id) throws DBException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession();) {
            transaction = session.beginTransaction();
            session.delete(getById(id));
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new DBException(e.getMessage());
        }
    }

    @Override
    public boolean existsByEmail(Employee employee) throws DBException {
        try (Session session = sessionFactory.openSession()) {
            Employee check = (Employee) session.createQuery("FROM Employee where email='" + employee.getEmail() + "'").uniqueResult();
            if (check != null && check.getEmail() != employee.getEmail()) {
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new DBException(e.getMessage());
        }
    }

    @Override
    public void createOrUpdate(Employee employee) throws ValidationException, DBException {
        saveOrUpdate(employee);
    }
}
