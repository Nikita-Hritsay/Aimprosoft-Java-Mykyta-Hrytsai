package com.aimprosoft.aimlearning.dao.Impl;

import com.aimprosoft.aimlearning.config.HibernateSessionFactory;
import com.aimprosoft.aimlearning.dao.DepartmentDAO;
import com.aimprosoft.aimlearning.exceptions.DBException;
import com.aimprosoft.aimlearning.exceptions.ValidationException;
import com.aimprosoft.aimlearning.models.Department;
import org.hibernate.*;

import java.util.List;

public class HibernateDepartmentDAOImpl implements DepartmentDAO {

    SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();

    @Override
    public List<Department> getAllDepartments() throws DBException {
        try (Session session = sessionFactory.openSession();) {
            List<Department> departments = session.createQuery("FROM Department ", Department.class).list();
            return departments;
        } catch (Exception e) {
            throw new DBException(e.getMessage());
        }
    }

    @Override
    public void addDepartment(Department department) throws DBException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession();) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(department);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new DBException(e.getMessage());
        }
    }

    @Override
    public Department getDepartmentById(Integer id) throws DBException {
        try (Session session = sessionFactory.openSession();) {
            Department department = (Department) session.createQuery("FROM Department where idDepartment=" + id).uniqueResult();
            return department;
        } catch (Exception e) {
            throw new DBException(e.getMessage());
        }
    }

    @Override
    public Department getDepartmentByName(String name) throws DBException {
        try (Session session = sessionFactory.openSession();) {
            Department department = (Department) session.createQuery("FROM Department where name='" + name + "'").uniqueResult();
            return department;
        } catch (Exception e) {
            throw new DBException(e.getMessage());
        }
    }

    @Override
    public void deleteDepartment(int id) throws DBException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession();) {
            transaction = session.beginTransaction();
            session.delete(getDepartmentById(id));
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new DBException(e.getMessage());
        }
    }

    @Override
    public boolean existsByName(Department department) throws DBException {
        try (Session session = sessionFactory.openSession();) {
            Department check = (Department) session.createQuery("FROM Department where name='" + department.getName() + "'").uniqueResult();
            if (check != null && check.getIdDepartment() != department.getIdDepartment()) {
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new DBException(e.getMessage());
        }
    }

    @Override
    public void createOrUpdate(Department department) throws ValidationException, DBException {
        addDepartment(department);
    }
}
