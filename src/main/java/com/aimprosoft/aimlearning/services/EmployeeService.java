package com.aimprosoft.aimlearning.services;

import com.aimprosoft.aimlearning.exceptions.DBException;
import com.aimprosoft.aimlearning.exceptions.ValidationException;
import com.aimprosoft.aimlearning.models.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees() throws DBException;

    Employee getById(int id) throws DBException;

    List<Employee> getByIdDepartment(int id) throws DBException;

    void add(Employee employee) throws DBException;

    void deleteEmployee(int id) throws DBException;

    void createOrUpdate(Employee employee) throws ValidationException, DBException;

    List<Integer> getAllEmployeesIds() throws DBException;
}
