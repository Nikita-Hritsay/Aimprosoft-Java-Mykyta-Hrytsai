package com.aimprosoft.aimlearning.dao.Impl;

import com.aimprosoft.aimlearning.dao.DepartmentDAO;
import com.aimprosoft.aimlearning.exceptions.DBException;
import com.aimprosoft.aimlearning.exceptions.ValidationException;
import com.aimprosoft.aimlearning.models.Department;
import lombok.AllArgsConstructor;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class HibernateDepartmentDAOImpl implements DepartmentDAO {

    private final SessionFactory sessionFactory;

    @Override
    public List<Department> getAllDepartments() throws DBException {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Department ", Department.class).list();
        } catch (Exception e) {
            throw new DBException(e.getMessage());
        }
    }

    @Override
    public void saveOrUpdate(Department department) throws DBException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(department);
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null){
                transaction.rollback();
            }
            throw new DBException(e.getMessage());
        }
    }

    @Override
    public Department getDepartmentById(Integer id) throws DBException {
        if (id != null) {
            try (Session session = sessionFactory.openSession()) {
                return session.get(Department.class, id);
            } catch (Exception e) {
                throw new DBException(e.getMessage());
            }
        }
        return null;
    }

    @Override
    public void deleteDepartment(int id) throws DBException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(getDepartmentById(id));
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null){
                transaction.rollback();
            }
            throw new DBException(e.getMessage());
        }
    }

    @Override
    public boolean existsByName(Department department) throws DBException {
        try (Session session = sessionFactory.openSession()) {
            Department check = (Department) session.createQuery("FROM Department where name='" + department.getName() + "'").uniqueResult();
            return check != null && !Objects.equals(check.getIdDepartment(), department.getIdDepartment());
        } catch (Exception e) {
            throw new DBException(e.getMessage());
        }
    }

    @Override
    public void createOrUpdate(Department department) throws ValidationException, DBException {
        saveOrUpdate(department);
    }
}
