package com.aimprosoft.aimlearning.DAO;

import com.aimprosoft.aimlearning.exceptions.DBException;
import com.aimprosoft.aimlearning.exceptions.ValidationException;
import com.aimprosoft.aimlearning.models.Department;

import java.util.List;

public interface DepartmentDAO {
    List<Department> getAllDepartments() throws DBException;

    void addDepartment(Department department) throws DBException;

    Department getDepartmentById(int id) throws DBException;

    void updateDepartment(Department department) throws DBException;

    void deleteDepartment(int id) throws DBException;

    boolean existsByName(Department department) throws DBException;

    void createOrUpdate(Department department) throws ValidationException, DBException;
}
