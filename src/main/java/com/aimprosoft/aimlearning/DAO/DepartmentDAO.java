package com.aimprosoft.aimlearning.DAO;

import com.aimprosoft.aimlearning.models.Department;
import com.aimprosoft.aimlearning.models.Employee;

import java.util.List;

public interface DepartmentDAO {
    List<Department> getAllDepartments();
    List<Employee>  getById(int id);
    void addDepartment(Department department);
    Department findDepartmentById(int id);
    void updateDepartment(Department department);
    void deleteDepartment(int id);
    boolean existsByName(Department department);
    void createOrUpdate(Department department);
}
