package com.aimprosoft.aimlearning.dao.Impl;

import com.aimprosoft.aimlearning.config.ConnectionFactory;
import com.aimprosoft.aimlearning.dao.EmployeeDAO;
import com.aimprosoft.aimlearning.exceptions.DBException;
import com.aimprosoft.aimlearning.exceptions.ValidationException;
import com.aimprosoft.aimlearning.models.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    private final ConnectionFactory connectionFactory = new ConnectionFactory();

    private final String FIND_ALL = "select idemployee, firstName, lastName, email, salary, hireDate, department_iddepartment from employee";
    private final String FIND_BY_ID = "select idemployee, firstName, lastName, email, salary, hireDate, department_iddepartment from employee where idemployee = ?";
    private final String ADD_EMPLOYEE = "insert into employee(firstName, lastName, email, salary, hireDate, department_iddepartment) values(?, ?, ?, ?, ?, ?)";
    private final String DELETE_EMPLOYEE = "delete from employee where idemployee = ?";
    private final String UPDATE_EMPLOYEE = "update employee set firstName = ?, lastName = ?, email = ?, salary = ?, hireDate = ?, department_iddepartment = ? where idemployee = ?";
    private final String GET_ALL_EMAILS = "select idemployee from employee where email = ?";
    private final String FIND_BY_IDDEPARTMENT = "select idemployee, firstName, lastName, email, salary, hireDate, department_iddepartment from employee where department_iddepartment = ?";

    @Override
    public List<Employee> getAllEmployees() throws DBException {
        try (Connection connection = connectionFactory.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_ALL)) {
            return getEmployees(resultSet);
        } catch (SQLException sqlException) {
            throw new DBException("Error in get All Employees: " + sqlException.getMessage());
        }
    }

    @Override
    public List<Employee> getByIdDepartment(int id) throws DBException {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_IDDEPARTMENT)) {
            ResultSet resultSet;
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            return getEmployees(resultSet);
        } catch (SQLException sqlException) {
            throw new DBException("Error in get Employees by department id: " + sqlException.getMessage());
        }
    }

    @Override
    public void updateEmployee(Employee employee) throws DBException {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement statement = setStatement(employee, connection, UPDATE_EMPLOYEE)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DBException("Error in update Employees: " + e.getMessage());
        }
    }

    @Override
    public void deleteEmployee(int id) throws DBException {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EMPLOYEE);) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new DBException("Error in get All Employees: " + throwables.getMessage());
        }
    }

    @Override
    public Employee getById(int id) throws DBException {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {
            ResultSet resultSet;
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            resultSet.next();
            return new Employee(resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getBigDecimal(5),
                    resultSet.getDate(6),
                    resultSet.getInt(7));
        } catch (SQLException sqlException) {
            throw new DBException("Error in get Employee by id: " + sqlException.getMessage());
        }
    }

    @Override
    public boolean existsByEmail(Employee employee) throws DBException {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ALL_EMAILS);) {
            ResultSet resultSet;
            statement.setString(1, employee.getEmail());
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("idemployee");
                if (id != null && id != employee.getId()) {
                    return true;
                }
            }
            return false;
        } catch (SQLException sqlException) {
            throw new DBException("Error in get All Employees: " + sqlException.getMessage());
        }
    }

    @Override
    public void add(Employee employee) throws DBException {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = setStatement(employee, connection, ADD_EMPLOYEE);) {
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
            add(employee);
        }
    }

    private List<Employee> getEmployees(ResultSet resultSet) throws DBException {
        try {
            List<Employee> result = new ArrayList<>();
            while (true) {
                if (!resultSet.next()) break;
                result.add(new Employee(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getBigDecimal(5),
                        resultSet.getDate(6),
                        resultSet.getInt(7)));
            }
            return result;
        } catch (SQLException e) {
            throw new DBException("Error in get All Employees: " + e.getMessage());
        }
    }

    private PreparedStatement setStatement(Employee employee, Connection connection, String query) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, employee.getFirstName());
        statement.setString(2, employee.getLastName());
        statement.setString(3, employee.getEmail());
        statement.setBigDecimal(4, employee.getSalary());
        statement.setDate(5, new Date(employee.getHireDate().getTime()));
        statement.setInt(6, employee.getIdDepartment());
        if (employee.getId() != null) {
            statement.setInt(7, employee.getId());
        }
        return statement;
    }

}
