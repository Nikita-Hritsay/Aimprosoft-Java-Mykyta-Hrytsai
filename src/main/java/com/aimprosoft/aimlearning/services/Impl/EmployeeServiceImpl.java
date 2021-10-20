package com.aimprosoft.aimlearning.services.Impl;

import com.aimprosoft.aimlearning.DAO.Impl.EmployeeDAOImpl;
import com.aimprosoft.aimlearning.exceptions.ValidationException;
import com.aimprosoft.aimlearning.models.Employee;
import com.aimprosoft.aimlearning.services.EmployeeService;
import com.aimprosoft.aimlearning.validations.ModelValidator;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
    private final ModelValidator modelValidator = new ModelValidator();

    @Override
    public List<Employee> getAllEmployees() {
        return employeeDAO.getAllEmployees();
    }

    @Override
    public Employee getById(int id) {
        return employeeDAO.getById(id);
    }

    @Override
    public List<Employee> getByIdDepartment(int id) {
        return employeeDAO.getByIdDepartment(id);
    }

    @Override
    public void add(Employee employee) {
        employeeDAO.add(employee);
    }

    @Override
    public void deleteEmployee(int id) {
        employeeDAO.deleteEmployee(id);
    }

    @Override
    public void updateEmployee(Employee employee) {
        employeeDAO.updateEmployee(employee);
    }

    @Override
    public boolean existsByEmail(Employee employee) {
        return employeeDAO.existsByEmail(employee);
    }

    @Override
    public void createOrUpdate(Employee employee) throws ValidationException {
        modelValidator.validator(employee);
        employeeDAO.createOrUpdate(employee);
    }
}
