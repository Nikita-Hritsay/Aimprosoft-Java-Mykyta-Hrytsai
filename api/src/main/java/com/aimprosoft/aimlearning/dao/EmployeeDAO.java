package com.aimprosoft.aimlearning.dao;

import com.aimprosoft.aimlearning.exceptions.DBException;
import com.aimprosoft.aimlearning.exceptions.ValidationException;
import com.aimprosoft.aimlearning.models.Employee;
import org.hibernate.jpa.internal.ExceptionMapperLegacyJpaImpl;

import java.util.List;
import java.util.Map;

public interface EmployeeDAO {
    List<Employee> getAllEmployees() throws DBException;

    Employee getById(Integer id) throws DBException;

    void saveOrUpdate(Employee employee) throws DBException;

    void deleteEmployee(Employee employee) throws DBException;

    boolean existsByEmail(Employee employee) throws DBException;

    void createOrUpdate(Employee employee) throws ValidationException, DBException;

    List<Employee> getByDepartmentId(Integer id) throws DBException;

    Employee getByEmail(String email) throws DBException;

}
