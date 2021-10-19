package com.aimprosoft.aimlearning.DAO;

import com.aimprosoft.aimlearning.exceptions.ValidationException;
import com.aimprosoft.aimlearning.models.Department;

import java.util.List;

public interface DepartmentDAO {
    List<Department> getAllDepartments();
    void addDepartment(Department department);
    Department getDepartmentById(int id);
    void updateDepartment(Department department);
    void deleteDepartment(int id);
    boolean existsByName(Department department);
    void createOrUpdate(Department department) throws ValidationException;
}
