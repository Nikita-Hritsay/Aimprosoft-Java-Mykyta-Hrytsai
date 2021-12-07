package com.aimprosoft.aimlearning.services;

import com.aimprosoft.aimlearning.exceptions.DBException;
import com.aimprosoft.aimlearning.exceptions.ValidationException;
import com.aimprosoft.aimlearning.models.Department;
import com.aimprosoft.aimlearning.models.Employee;

import java.util.List;

public interface DepartmentService {
    List<Department> getAllDepartments() throws DBException;

    Department getDepartmentById(Integer id) throws DBException;

    void deleteDepartment(int id) throws DBException;

    void createOrUpdate(Department department) throws ValidationException, DBException;

    Department getByName(String name) throws DBException;


    boolean existsByName(Department department) throws DBException;
}
