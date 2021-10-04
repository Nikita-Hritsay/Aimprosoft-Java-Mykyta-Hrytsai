package com.aimprosoft.aimlearning.DAO;

import com.aimprosoft.aimlearning.model.Employee;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> getAllEmployees();
    Employee getById(int id);
    void add(Employee employee);
    void deleteEmployee(int id);
    void updateEmployee(Employee employee);
}
