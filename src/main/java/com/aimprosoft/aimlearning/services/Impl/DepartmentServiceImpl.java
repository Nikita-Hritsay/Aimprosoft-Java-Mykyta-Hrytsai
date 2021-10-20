package com.aimprosoft.aimlearning.services.Impl;

import com.aimprosoft.aimlearning.DAO.Impl.DepartmentDAOImpl;
import com.aimprosoft.aimlearning.exceptions.ValidationException;
import com.aimprosoft.aimlearning.models.Department;
import com.aimprosoft.aimlearning.services.DepartmentService;
import com.aimprosoft.aimlearning.validations.ModelValidator;

import java.util.List;

public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentDAOImpl departmentDAO = new DepartmentDAOImpl();

    @Override
    public List<Department> getAllDepartments() {
        return departmentDAO.getAllDepartments();
    }

    @Override
    public Department getDepartmentById(int id) {
        return departmentDAO.getDepartmentById(id);
    }

    @Override
    public void deleteDepartment(int id) {
        departmentDAO.deleteDepartment(id);
    }

    @Override
    public void createOrUpdate(Department department) throws ValidationException {
        ModelValidator<Department> validator = new ModelValidator<>();
        validator.validator(department);
        departmentDAO.createOrUpdate(department);
    }
}
