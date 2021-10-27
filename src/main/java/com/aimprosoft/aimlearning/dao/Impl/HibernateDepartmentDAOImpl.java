package com.aimprosoft.aimlearning.dao.Impl;

import com.aimprosoft.aimlearning.config.HibernateSessionFactory;
import com.aimprosoft.aimlearning.dao.DepartmentDAO;
import com.aimprosoft.aimlearning.exceptions.DBException;
import com.aimprosoft.aimlearning.exceptions.ValidationException;
import com.aimprosoft.aimlearning.models.Department;
import org.hibernate.*;

import java.util.List;
import java.util.Objects;

public class HibernateDepartmentDAOImpl implements DepartmentDAO {

    //SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();

    @Override
    public List<Department> getAllDepartments() throws DBException {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            return session.createQuery("FROM Department ", Department.class).list();
        } catch (Exception e) {
            throw new DBException(e.getMessage());
        }
    }

    @Override
    public void addDepartment(Department department) throws DBException {
        Transaction transaction = null;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(department);
            transaction.commit();
        } catch (Exception e) {
            assert transaction != null;
            transaction.rollback();
            throw new DBException(e.getMessage());
        }
    }

    @Override
    public Department getDepartmentById(Integer id) throws DBException {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            return (Department) session.createQuery("FROM Department where idDepartment=" + id).uniqueResult();
        } catch (Exception e) {
            throw new DBException(e.getMessage());
        }
    }

    @Override
    public Department getDepartmentByName(String name) throws DBException {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            return  (Department) session.createQuery("FROM Department where name='" + name + "'").uniqueResult();
        } catch (Exception e) {
            throw new DBException(e.getMessage());
        }
    }

    @Override
    public void deleteDepartment(int id) throws DBException {
        Transaction transaction = null;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(getDepartmentById(id));
            transaction.commit();
        } catch (Exception e) {
            assert transaction != null;
            transaction.rollback();
            throw new DBException(e.getMessage());
        }
    }

    @Override
    public boolean existsByName(Department department) throws DBException {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Department check = (Department) session.createQuery("FROM Department where name='" + department.getName() + "'").uniqueResult();
            return check != null && !Objects.equals(check.getIdDepartment(), department.getIdDepartment());
        } catch (Exception e) {
            throw new DBException(e.getMessage());
        }
    }

    @Override
    public void createOrUpdate(Department department) throws ValidationException, DBException {
        addDepartment(department);
    }
}
