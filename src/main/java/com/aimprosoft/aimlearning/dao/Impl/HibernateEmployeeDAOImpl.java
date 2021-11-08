package com.aimprosoft.aimlearning.dao.Impl;

import com.aimprosoft.aimlearning.dao.EmployeeDAO;
import com.aimprosoft.aimlearning.exceptions.DBException;
import com.aimprosoft.aimlearning.exceptions.ValidationException;
import com.aimprosoft.aimlearning.models.Employee;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Repository
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Transactional
public class HibernateEmployeeDAOImpl implements EmployeeDAO {

    private final SessionFactory sessionFactory;

    @Override
    public List<Employee> getAllEmployees() throws DBException {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Employee", Employee.class).list();
        } catch (Exception e) {
            throw new DBException(e.getMessage());
        }
    }

    @Override
    public Employee getById(int id) throws DBException {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Employee.class, id);
        } catch (Exception e) {
            throw new DBException(e.getMessage());
        }
    }

    @Override
    public void saveOrUpdate(Employee employee) throws DBException {
        try (Session session = sessionFactory.openSession()) {
            session.saveOrUpdate(employee);
        } catch (Exception e) {
            throw new DBException(e.getMessage());
        }
    }

    @Override
    public void deleteEmployee(int id) throws DBException {
        try (Session session = sessionFactory.openSession()) {
            session.delete(getById(id));
        } catch (Exception e) {
            throw new DBException(e.getMessage());
        }
    }

    @Override
    public boolean existsByEmail(Employee employee) throws DBException {
        try (Session session = sessionFactory.openSession()) {
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

}
