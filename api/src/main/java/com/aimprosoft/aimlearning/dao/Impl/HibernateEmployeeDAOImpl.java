package com.aimprosoft.aimlearning.dao.Impl;

import com.aimprosoft.aimlearning.dao.EmployeeDAO;
import com.aimprosoft.aimlearning.exceptions.DBException;
import com.aimprosoft.aimlearning.exceptions.ValidationException;
import com.aimprosoft.aimlearning.models.Employee;

import lombok.AllArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class HibernateEmployeeDAOImpl implements EmployeeDAO {

    private final SessionFactory sessionFactory;

    @Override
    public List<Employee> getAllEmployees() throws DBException {
        try {
            return sessionFactory.getCurrentSession().createQuery("FROM Employee", Employee.class).list();
        } catch (Exception e) {
            throw new DBException(e.getMessage());
        }
    }

    @Override
    public Employee getById(Integer id) throws DBException {
        if (id != 0) {
            try {
                return sessionFactory.getCurrentSession().get(Employee.class, id);
            } catch (Exception e) {
                throw new DBException(e.getMessage());
            }
        }
        return new Employee();
    }

    @Override
    public Employee saveOrUpdate(Employee employee) {
        sessionFactory.getCurrentSession().saveOrUpdate(employee);
        return employee;
    }

    @Override
    public void deleteEmployee(int id) throws DBException {
        try {
            sessionFactory.getCurrentSession().delete(getById(id));
        } catch (Exception e) {
            throw new DBException(e.getMessage());
        }
    }

    @Override
    public Employee existsByEmail(Employee employee) throws DBException {
        try {
            return (Employee) sessionFactory.getCurrentSession().createQuery("FROM Employee where email='" + employee.getEmail() + "'").uniqueResult();
        } catch (Exception e) {
            throw new DBException(e.getMessage());
        }
    }

    @Override
    public Employee createOrUpdate(Employee employee) throws ValidationException, DBException {
        return saveOrUpdate(employee);
    }

    @Override
    public Employee getByEmail(String email) throws DBException {
        try {
            return (Employee) sessionFactory.getCurrentSession().createQuery("FROM Employee where email='" + email + "'").uniqueResult();
        } catch (Exception e) {
            throw new DBException(e.getMessage());
        }
    }

    @Override
    public List<Employee> getByDepartmentId(Integer id) throws DBException{
        try {
            return sessionFactory.getCurrentSession().createQuery("from Employee e where e.department.id = " + id).list();
        } catch (Exception e) {
            throw new DBException(e.getMessage());
        }
    }




}
