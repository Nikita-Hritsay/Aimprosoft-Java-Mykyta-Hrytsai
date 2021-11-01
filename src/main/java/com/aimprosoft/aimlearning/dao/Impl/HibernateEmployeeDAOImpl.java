package com.aimprosoft.aimlearning.dao.Impl;

import com.aimprosoft.aimlearning.config.HibernateSessionFactory;
import com.aimprosoft.aimlearning.dao.EmployeeDAO;
import com.aimprosoft.aimlearning.exceptions.DBException;
import com.aimprosoft.aimlearning.exceptions.ValidationException;
import com.aimprosoft.aimlearning.models.Employee;

import com.aimprosoft.aimlearning.utils.NumberUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class HibernateEmployeeDAOImpl implements EmployeeDAO {

    private Transaction transaction = null;

    @Override
    public List<Employee> getAllEmployees() throws DBException {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            return session.createQuery("FROM Employee", Employee.class).list();
        } catch (Exception e) {
            throw new DBException(e.getMessage());
        }
    }

    @Override
    public Employee getById(int id) throws DBException {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            return (Employee) session.createQuery("FROM Employee where id=" + id).uniqueResult();
        } catch (Exception e) {
            throw new DBException(e.getMessage());
        }
    }

    @Override
    public List<Employee> getByIdDepartment(int id) throws DBException {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            return session.createQuery("FROM Employee where department.idDepartment =" + id).list();
        } catch (Exception e) {
            throw new DBException(e.getMessage());
        }
    }

    @Override
    public void saveOrUpdate(Employee employee) throws DBException {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(employee);
            transaction.commit();
        } catch (Exception e) {
            assert transaction != null;
            transaction.rollback();
            throw new DBException(e.getMessage());
        }
    }

    @Override
    public void deleteEmployee(int id) throws DBException {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(getById(id));
            transaction.commit();
        } catch (Exception e) {
            assert transaction != null;
            transaction.rollback();
            throw new DBException(e.getMessage());
        }
    }

    @Override
    public boolean existsByEmail(Employee employee) throws DBException {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Employee check = (Employee) session.createQuery("FROM Employee where email='" + employee.getEmail() + "'").uniqueResult();
            return check != null && !Objects.equals(check.getId(), employee.getId());
        } catch (Exception e) {
            throw new DBException(e.getMessage());
        }
    }

    @Override
    public void createOrUpdate(Employee employee) throws ValidationException, DBException {
        saveOrUpdate(employee);
    }

    @Override
    public Map<Integer, String> getMapEmployeeByDepartmentName() throws DBException {
        try {
            List<Map<Object, Object>> maps = HibernateSessionFactory.getSessionFactory().openSession().createQuery("select new map (emp.id as idemployee, dep.name as name) FROM Employee as emp join emp.department as dep").list();
            Map<Integer, String> result = new HashMap<>();
            for (Map<Object, Object> obj : maps) {
                result.put(NumberUtils.getInt(obj.get("idemployee").toString()), obj.get("name").toString());
            }
            return result;
        } catch (Exception e) {
            throw new DBException(e.getMessage());
        }
    }
}
