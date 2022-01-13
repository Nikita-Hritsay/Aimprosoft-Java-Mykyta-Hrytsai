package com.aimprosoft.aimlearning.dao.Impl;

import com.aimprosoft.aimlearning.dao.DepartmentDAO;
import com.aimprosoft.aimlearning.exceptions.DBException;
import com.aimprosoft.aimlearning.exceptions.ValidationException;
import com.aimprosoft.aimlearning.models.Department;
import lombok.AllArgsConstructor;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class HibernateDepartmentDAOImpl implements DepartmentDAO {

    private final SessionFactory sessionFactory;

    @Override
    public List<Department> getAllDepartments() throws DBException {
        try {
            return sessionFactory.getCurrentSession().createQuery("FROM Department ", Department.class).list();
        } catch (Exception e) {
            throw new DBException(e.getMessage());
        }
    }

    @Override
    public Department saveOrUpdate(Department department) throws DBException {
        System.out.println();
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(department);
            return department;
        } catch (Exception e) {
            throw new DBException(e.getMessage());
        }
    }

    @Override
    public Department getDepartmentById(Integer id) throws DBException {
        if (id != 0) {
            try {
                return sessionFactory.getCurrentSession().get(Department.class, id);
            } catch (Exception e) {
                throw new DBException(e.getMessage());
            }
        }
        return new Department();
    }

    @Override
    public void deleteDepartment(int id) throws DBException {
        try {
            sessionFactory.getCurrentSession().delete(getDepartmentById(id));
        } catch (Exception e) {
            throw new DBException(e.getMessage());
        }
    }

    @Override
    public Department getDepartmentByName(String name) throws DBException {
        try {
            return (Department) sessionFactory.getCurrentSession().createQuery("FROM Department where name='" + name + "'").uniqueResult();
        } catch (Exception e) {
            throw new DBException(e.getMessage());
        }
    }

    @Override
    public Department existsByName(Department department) throws DBException {
        try {
            return (Department) sessionFactory.getCurrentSession().createQuery("FROM Department where name='" + department.getName() + "'").uniqueResult();
        } catch (Exception e) {
            throw new DBException(e.getMessage());
        }
    }

    @Override
    public Department createOrUpdate(Department department) throws ValidationException, DBException {
        return saveOrUpdate(department);
    }
}
