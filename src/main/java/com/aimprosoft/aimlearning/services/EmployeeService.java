package com.aimprosoft.aimlearning.services;

import com.aimprosoft.aimlearning.exceptions.ValidationException;
import com.aimprosoft.aimlearning.models.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();

    Employee getById(int id);

    List<Employee> getByIdDepartment(int id);

    void add(Employee employee);

    void deleteEmployee(int id);

    void updateEmployee(Employee employee);

    boolean existsByEmail(Employee employee);

    void createOrUpdate(Employee employee) throws ValidationException;
}
