package com.aimprosoft.aimlearning.services.Impl;

import com.aimprosoft.aimlearning.dao.DepartmentDAO;
import com.aimprosoft.aimlearning.exceptions.DBException;
import com.aimprosoft.aimlearning.exceptions.ValidationException;
import com.aimprosoft.aimlearning.models.Department;
import com.aimprosoft.aimlearning.models.Employee;
import com.aimprosoft.aimlearning.services.DepartmentService;
import com.aimprosoft.aimlearning.validations.ModelValidator;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentDAO departmentDAO;
    private final ModelValidator<Department> modelValidator;

    @Override
    public List<Department> getAllDepartments() throws DBException {
        return departmentDAO.getAllDepartments();
    }

    @Override
    public Department getDepartmentById(Integer id) throws DBException {
        return departmentDAO.getDepartmentById(id);
    }

    @Override
    public void deleteDepartment(Department department) throws DBException {
        departmentDAO.deleteDepartment(department);
    }

    @Override
    public Department getByName(String name) throws DBException {
        return departmentDAO.getDepartmentByName(name);
    }

    @Override
    public void createOrUpdate(Department department) throws ValidationException, DBException {
        modelValidator.validate(department);
        departmentDAO.createOrUpdate(department);
    }



    @Override
    public boolean existsByName(Department department) throws DBException {
        return departmentDAO.existsByName(department);
    }

}
