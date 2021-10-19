package com.aimprosoft.aimlearning.DAO.Impl;

import com.aimprosoft.aimlearning.config.ConnectionFactory;
import com.aimprosoft.aimlearning.DAO.EmployeeDAO;
import com.aimprosoft.aimlearning.exceptions.ValidationException;
import com.aimprosoft.aimlearning.models.Employee;
import com.aimprosoft.aimlearning.validations.ModelValidator;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    private ConnectionFactory connectionFactory;

    private final String FIND_ALL = "select idemployee, firstName, lastName, email, salary, hireDate, department_iddepartment from employee";
    private final String FIND_BY_ID = "select idemployee, firstName, lastName, email, salary, hireDate, department_iddepartment from employee where idemployee = ?";
    private final String ADD_EMPLOYEE = "insert into employee(firstName, lastName, email, salary, hireDate, department_iddepartment) values(?, ?, ?, ?, ?, ?)";
    private final String DELETE_EMPLOYEE = "delete from employee where idemployee = ?";
    private final String UPDATE_EMPLOYEE = "update employee set firstName = ?, lastName = ?, email = ?, salary = ?, hireDate = ?, department_iddepartment = ? where idemployee = ?";
    private final String GET_ALL_EMAILS = "select idemployee from employee where email = ?";
    private final String FIND_BY_IDDEPARTMENT = "select idemployee, firstName, lastName, email, salary, hireDate, department_iddepartment from employee where department_iddepartment = ?";

    public EmployeeDAOImpl() {
        this.connectionFactory = new ConnectionFactory();
    }

    @Override
    public List<Employee> getAllEmployees() {
        try (Connection connection = connectionFactory.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_ALL)) {

            return getEmployees(resultSet);
        } catch (SQLException sqlException) {
            System.out.println("Something went wrong" + sqlException.getSQLState());
        }
        return null;
    }

    @Override
    public List<Employee> getByIdDepartment(int id) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_IDDEPARTMENT)) {
            ResultSet resultSet;
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            return getEmployees(resultSet);
        } catch (SQLException sqlException) {
            System.out.println("something went wrong" + sqlException.getMessage());
        }
        return null;
    }

    @Override
    public void updateEmployee(Employee employee) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_EMPLOYEE)) {
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getEmail());
            statement.setDouble(4, employee.getSalary());
            statement.setDate(5, new Date(employee.getHireDate().getTime()));
            statement.setInt(6, employee.getIdDepartment());
            statement.setInt(7, employee.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Something went wrong" + e.getSQLState());
        }
    }

    @Override
    public void deleteEmployee(int id) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EMPLOYEE);) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            System.out.println("Something went wrong" + throwables.getSQLState());
        }
    }

    @Override
    public Employee getById(int id) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {
            ResultSet resultSet;
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            Employee result;
            resultSet.next();
            result = new Employee(resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getInt(5),
                    resultSet.getDate(6),
                    resultSet.getInt(7));
            return result;
        } catch (SQLException sqlException) {
            System.out.println("Something went wrong" + sqlException.getSQLState());
        }
        return null;
    }

    @Override
    public boolean existsByEmail(Employee employee) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ALL_EMAILS);) {
            ResultSet resultSet;
            statement.setString(1, employee.getEmail());
            resultSet = statement.executeQuery();
            resultSet.next();
            int id = resultSet.getInt("idemployee");
            if (id != 0 && id != employee.getId()) {
                return true;
            }
            return false;
        } catch (SQLException sqlException) {
            System.out.println("Something went wrong" + sqlException.getSQLState());
            return false;
        }
    }

    @Override
    public void add(Employee employee) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_EMPLOYEE);) {
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setString(3, employee.getEmail());
            preparedStatement.setDouble(4, employee.getSalary());
            preparedStatement.setDate(5, new Date(employee.getHireDate().getTime()));
            preparedStatement.setInt(6, employee.getIdDepartment());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            System.out.println("Something went wrong" + throwables.getSQLState());
        }
    }

    @Override
    public void createOrUpdate(Employee employee) throws ValidationException {
        ModelValidator<Employee> validator = new ModelValidator<>();
        validator.validator(employee);
        if (employee.getId() != null) {
            updateEmployee(employee);
        } else {
            add(employee);
        }
    }

    private List<Employee> getEmployees(ResultSet resultSet) {
        try {
            List<Employee> result = new ArrayList<>();
            while (true) {
                if (!resultSet.next()) break;
                result.add(new Employee(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5),
                        resultSet.getDate(6),
                        resultSet.getInt(7)));
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
