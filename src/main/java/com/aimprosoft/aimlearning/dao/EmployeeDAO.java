package com.aimprosoft.aimlearning.dao;

import com.aimprosoft.aimlearning.exceptions.DBException;
import com.aimprosoft.aimlearning.exceptions.ValidationException;
import com.aimprosoft.aimlearning.models.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeDAO {
    List<Employee> getAllEmployees() throws DBException;

    Employee getById(int id) throws DBException;

    void saveOrUpdate(Employee employee) throws DBException;

    void deleteEmployee(int id) throws DBException;

    boolean existsByEmail(Employee employee) throws DBException;

    void createOrUpdate(Employee employee) throws ValidationException, DBException;

<<<<<<< HEAD
    Map<Integer, String> getMapDepartmentIdByEmployeeName() throws DBException;
=======
>>>>>>> hibernate
}
