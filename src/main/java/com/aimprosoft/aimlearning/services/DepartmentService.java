package com.aimprosoft.aimlearning.services;

import com.aimprosoft.aimlearning.exceptions.ValidationException;
import com.aimprosoft.aimlearning.models.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> getAllDepartments();

    Department getDepartmentById(int id);

    void deleteDepartment(int id);

    void createOrUpdate(Department department) throws ValidationException;
}
