package com.aimprosoft.aimlearning.services.Impl;

import com.aimprosoft.aimlearning.dao.Impl.HibernateEmployeeDAOImpl;
import com.aimprosoft.aimlearning.exceptions.DBException;
import com.aimprosoft.aimlearning.exceptions.ValidationException;
import com.aimprosoft.aimlearning.models.Employee;
import com.aimprosoft.aimlearning.services.EmployeeService;
import com.aimprosoft.aimlearning.validations.ModelValidator;

import java.util.List;
import java.util.Map;

public class EmployeeServiceImpl implements EmployeeService {

    private final HibernateEmployeeDAOImpl employeeDAO = new HibernateEmployeeDAOImpl();
    private final ModelValidator<Employee> modelValidator = new ModelValidator<>();

    @Override
    public List<Employee> getAllEmployees() throws DBException {
        return employeeDAO.getAllEmployees();
    }

    @Override
    public Employee getById(int id) throws DBException {
        return employeeDAO.getById(id);
    }

    @Override
    public List<Employee> getByIdDepartment(int id) throws DBException {
        return employeeDAO.getByIdDepartment(id);
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
    public Map<Integer, String> getMapEmployeeByDepartmentName() throws DBException {
        return employeeDAO.getMapEmployeeByDepartmentName();
    }

    @Override
    public void createOrUpdate(Employee employee) throws ValidationException, DBException {
        modelValidator.validate(employee);
        employeeDAO.createOrUpdate(employee);
    }
}
