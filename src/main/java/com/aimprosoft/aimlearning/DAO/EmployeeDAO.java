package com.aimprosoft.aimlearning.DAO;

import com.aimprosoft.aimlearning.models.Employee;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> getAllEmployees();
    Employee getById(int id);
    void add(Employee employee);
    void deleteEmployee(int id);
    void updateEmployee(Employee employee);
    boolean existsByEmail(Employee employee);
    void createOrUpdate(Employee employee);
}
