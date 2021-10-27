package com.aimprosoft.aimlearning.services;

import com.aimprosoft.aimlearning.exceptions.DBException;
import com.aimprosoft.aimlearning.exceptions.ValidationException;
import com.aimprosoft.aimlearning.models.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> getAllDepartments() throws DBException;

    Department getDepartmentById(int id) throws DBException;

    void deleteDepartment(int id) throws DBException;

    Department getDepartmentByName(String name) throws DBException;

    List<String> getDepartmentNameByEmployeeId(List<Integer> id) throws DBException;

    void createOrUpdate(Department department) throws ValidationException, DBException;
}
