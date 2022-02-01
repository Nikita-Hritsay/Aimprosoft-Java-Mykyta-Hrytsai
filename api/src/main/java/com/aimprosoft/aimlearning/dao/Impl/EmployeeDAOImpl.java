package com.aimprosoft.aimlearning.dao.Impl;

import com.aimprosoft.aimlearning.config.ConnectionFactory;
import com.aimprosoft.aimlearning.dao.EmployeeDAO;
import com.aimprosoft.aimlearning.exceptions.DBException;
import com.aimprosoft.aimlearning.exceptions.ValidationException;
import com.aimprosoft.aimlearning.models.Department;
import com.aimprosoft.aimlearning.models.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

    private static final String FIND_ALL = "select idemployee, firstName, lastName, email, salary, hireDate, department_iddepartment from employee";
    private static final String FIND_BY_ID = "select idemployee, firstName, lastName, email, salary, hireDate, department_iddepartment from employee where idemployee = ?";
    private static final String ADD_EMPLOYEE = "insert into employee(firstName, lastName, email, salary, hireDate, department_iddepartment) values(?, ?, ?, ?, ?, ?)";
    private static final String DELETE_EMPLOYEE = "delete from employee where idemployee = ?";
    private static final String UPDATE_EMPLOYEE = "update employee set firstName = ?, lastName = ?, email = ?, salary = ?, hireDate = ?, department_iddepartment = ? where idemployee = ?";
    private static final String GET_ALL_EMAILS = "select idemployee from employee where email = ?";
    private static final String FIND_BY_IDDEPARTMENT = "select idemployee, firstName, lastName, email, salary, hireDate, department_iddepartment from employee where department_iddepartment = ?";
    private static final String FIND_DEPARTMENTNAME_BY_EMPLOYEE_ID = "select idemployee, name from employee join department on department.iddepartment = employee.department_iddepartment";

    @Override
    public List<Employee> getAllEmployees() throws DBException {
        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_ALL)) {
            return getEmployees(resultSet);
        } catch (SQLException sqlException) {
            throw new DBException("Error in get All Employees: " + sqlException.getMessage());
        }
    }

    public void updateEmployee(Employee employee) throws DBException {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = setupPreparedStatement(employee, connection, UPDATE_EMPLOYEE)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DBException("Error in update Employees: " + e.getMessage());
        }
    }

    @Override
    public void deleteEmployee(Employee employee) throws DBException {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EMPLOYEE)) {
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new DBException("Error in get All Employees: " + throwables.getMessage());
        }
    }

    @Override
    public Employee getById(Integer id) throws DBException {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {
            ResultSet resultSet;
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            resultSet.next();
            return new Employee().withId(resultSet.getInt(1))
                    .withFirstName(resultSet.getString(2))
                    .withLastName(resultSet.getString(3))
                    .withEmail(resultSet.getString(4))
                    .withSalary(resultSet.getBigDecimal(5))
                    .withHireDate(resultSet.getDate(6))
                    .withDepartment(new Department().withId(resultSet.getInt(7)));
        } catch (SQLException sqlException) {
            throw new DBException("Error in get Employee by id: " + sqlException.getMessage());
        }
    }

    @Override
    public boolean existsByEmail(Employee employee) throws DBException {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ALL_EMAILS)) {
            ResultSet resultSet;
            statement.setString(1, employee.getEmail());
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("idemployee");
                if (!id.equals(employee.getId())) {
                    return true;
                }
            }
            return false;
        } catch (SQLException sqlException) {
            throw new DBException("Error in get All Employees: " + sqlException.getMessage());
        }
    }

    @Override
    public void saveOrUpdate(Employee employee) throws DBException {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = setupPreparedStatement(employee, connection, ADD_EMPLOYEE)) {
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new DBException("Error in get All Employees: " + throwables.getMessage());
        }
    }

    @Override
    public void createOrUpdate(Employee employee) throws ValidationException, DBException {
        if (employee.getId() != null) {
            updateEmployee(employee);
        } else {
            saveOrUpdate(employee);
        }
    }

    @Override
    public List<Employee> getByDepartmentId(Integer id) throws DBException {
        return null;
    }

    @Override
    public Employee getByEmail(String email) throws DBException {
        return null;
    }

    private List<Employee> getEmployees(ResultSet resultSet) throws DBException {
        try {
            List<Employee> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(new Employee().withId(resultSet.getInt(1))
                        .withFirstName(resultSet.getString(2))
                        .withLastName(resultSet.getString(3))
                        .withEmail(resultSet.getString(4))
                        .withSalary(resultSet.getBigDecimal(5))
                        .withHireDate(resultSet.getDate(6))
                        .withDepartment(new Department().withId(resultSet.getInt(7))));
            }
            return result;
        } catch (SQLException e) {
            throw new DBException("Error in get All Employees: " + e.getMessage());
        }
    }

    private PreparedStatement setupPreparedStatement(Employee employee, Connection connection, String query) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, employee.getFirstName());
        statement.setString(2, employee.getLastName());
        statement.setString(3, employee.getEmail());
        statement.setBigDecimal(4, employee.getSalary());
        statement.setDate(5, new Date(employee.getHireDate().getTime()));
        statement.setInt(6, employee.getDepartment().getId());
        if (employee.getId() != null) {
            statement.setInt(7, employee.getId());
        }
        return statement;
    }

}
