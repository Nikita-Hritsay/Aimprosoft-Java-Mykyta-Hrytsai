package com.aimprosoft.aimlearning.services;

import com.aimprosoft.aimlearning.exceptions.DBException;
import com.aimprosoft.aimlearning.exceptions.ValidationException;
import com.aimprosoft.aimlearning.models.Employee;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees() throws DBException;

    Employee getById(Integer id) throws DBException;

    void add(Employee employee) throws DBException;

    void deleteEmployee(Employee employee) throws DBException;

    void createOrUpdate(Employee employee) throws ValidationException, DBException;

    boolean existByEmail(Employee employee) throws DBException;

    Employee getByEmail(String email) throws  DBException;

    List<Employee> getByDepartmentId(Integer id) throws DBException;
}
