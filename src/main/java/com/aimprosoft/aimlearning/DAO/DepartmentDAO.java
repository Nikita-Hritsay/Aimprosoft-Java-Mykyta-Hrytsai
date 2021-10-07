package com.aimprosoft.aimlearning.DAO;

import com.aimprosoft.aimlearning.model.Department;
import com.aimprosoft.aimlearning.model.Employee;

import java.util.List;

public interface DepartmentDAO {
    List<Department> getAllDepartments();
    List<Employee>  getById(int id);
    void addDepartment(Department department);
    Department findDepartmentById(int id);
    void updateDepartment(Department department);
    void deleteDepartment(int id);
    boolean existsByName(Department department);
}
