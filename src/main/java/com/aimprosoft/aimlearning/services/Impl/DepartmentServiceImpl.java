package com.aimprosoft.aimlearning.services.Impl;

import com.aimprosoft.aimlearning.dao.Impl.HibernateDepartmentDAOImpl;
import com.aimprosoft.aimlearning.exceptions.DBException;
import com.aimprosoft.aimlearning.exceptions.ValidationException;
import com.aimprosoft.aimlearning.models.Department;
import com.aimprosoft.aimlearning.services.DepartmentService;
import com.aimprosoft.aimlearning.validations.ModelValidator;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DepartmentServiceImpl implements DepartmentService {

    private final HibernateDepartmentDAOImpl departmentDAO;
    //private final ModelValidator<Department> modelValidator = new ModelValidator<>();

    @Override
    public List<Department> getAllDepartments() throws DBException {
        return departmentDAO.getAllDepartments();
    }

    @Override
    public Department getDepartmentById(Integer id) throws DBException {
        return departmentDAO.getDepartmentById(id);
    }

    @Override
    public void deleteDepartment(int id) throws DBException {
        departmentDAO.deleteDepartment(id);
    }

    @Override
    public void createOrUpdate(Department department) throws ValidationException, DBException {
       // modelValidator.validate(department);
        departmentDAO.createOrUpdate(department);
    }

    @Override
    public boolean existsByName(Department department) throws DBException {
        return departmentDAO.existsByName(department);
    }

}
