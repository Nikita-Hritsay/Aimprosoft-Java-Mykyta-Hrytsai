package com.aimprosoft.aimlearning.services.Impl;

import com.aimprosoft.aimlearning.dao.Impl.HibernateEmployeeDAOImpl;
import com.aimprosoft.aimlearning.exceptions.DBException;
import com.aimprosoft.aimlearning.exceptions.ValidationException;
import com.aimprosoft.aimlearning.models.Employee;
import com.aimprosoft.aimlearning.services.EmployeeService;
import com.aimprosoft.aimlearning.validations.ModelValidator;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeServiceImpl implements EmployeeService {

    private final HibernateEmployeeDAOImpl employeeDAO;
    //private final ModelValidator<Employee> modelValidator = new ModelValidator<>();

    @Override
    public List<Employee> getAllEmployees() throws DBException {
        return employeeDAO.getAllEmployees();
    }

    @Override
    public Employee getById(int id) throws DBException {
        return employeeDAO.getById(id);
    }

    @Override
    public void add(Employee employee) throws DBException {
        employeeDAO.saveOrUpdate(employee);
    }

    @Override
    public void deleteEmployee(int id) throws DBException {
        employeeDAO.deleteEmployee(id);
    }

    @Override
    public void createOrUpdate(Employee employee) throws ValidationException, DBException {
        //modelValidator.validate(employee);
        employeeDAO.createOrUpdate(employee);
    }
}
