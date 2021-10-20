package com.aimprosoft.aimlearning.DAO.Impl;

import com.aimprosoft.aimlearning.config.ConnectionFactory;
import com.aimprosoft.aimlearning.DAO.DepartmentDAO;
import com.aimprosoft.aimlearning.exceptions.ValidationException;
import com.aimprosoft.aimlearning.models.Department;
import com.aimprosoft.aimlearning.validations.ModelValidator;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAOImpl implements DepartmentDAO {

    private ConnectionFactory connectionFactory;

    private final String FIND_ALL = "select iddepartment, name, address from department";
    private final String FIND_DEPARTMENT_BY_ID = "select iddepartment, name, address from department where iddepartment = ?";
    private final String ADD_DEPARTMENT = "insert into department(name, address) values(?, ?)";
    private final String UPDATE_DEPARTMENT = "update department set name = ?, address = ? where iddepartment = ?";
    private final String DELETE_DEPARTMENT = "delete from department where iddepartment = ?";
    private final String EXISTS_BY_NAME = "select iddepartment from department where name = ?";

    public DepartmentDAOImpl() {
        this.connectionFactory = new ConnectionFactory();
    }

    @Override
    public List<Department> getAllDepartments() {
        try (Connection connection = connectionFactory.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_ALL)) {
            List<Department> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(new Department().withIdDepartment(resultSet.getInt(1))
                        .withName(resultSet.getString(2))
                        .withAddress(resultSet.getString(3)));
            }
            return result;
        } catch (SQLException sqlException) {
            System.out.println("something went wrong" + sqlException.getSQLState());
        }
        return new ArrayList<>();
    }

    @Override
    public void addDepartment(Department department) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_DEPARTMENT)) {
            preparedStatement.setString(1, department.getName());
            preparedStatement.setString(2, department.getAddress());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            System.out.println("something went wrong" + throwables.getSQLState());
        }
    }

    @Override
    public void deleteDepartment(int id) {
        try ( Connection conn = connectionFactory.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(DELETE_DEPARTMENT)){
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            System.out.println("Something went wrong" + throwables.getSQLState());
        }
    }

    @Override
    public void updateDepartment(Department department) {
        try  ( Connection conn = connectionFactory.getConnection();
               PreparedStatement statement = conn.prepareStatement(UPDATE_DEPARTMENT)){
            statement.setString(1, department.getName());
            statement.setString(2, department.getAddress());
            statement.setInt(3, department.getIdDepartment());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("something went wrong" + e.getSQLState());
        }
    }

    @Override
    public Department getDepartmentById(int id) {
        try ( Connection connection = connectionFactory.getConnection();
              PreparedStatement statement = connection.prepareStatement(FIND_DEPARTMENT_BY_ID)) {
            ResultSet resultSet = null;
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            Department result;
            resultSet.next();
            result = new Department(resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3));
            return result;
        } catch (SQLException sqlException) {
            System.out.println("something went wrong" + sqlException.getSQLState());
        }
        return null;
    }

    @Override
    public boolean existsByName(Department department) {
        try ( Connection connection = connectionFactory.getConnection();
              PreparedStatement statement = connection.prepareStatement(EXISTS_BY_NAME)) {
            ResultSet resultSet = null;
            statement.setString(1, department.getName());
            resultSet = statement.executeQuery();
            resultSet.next();
            int id = resultSet.getInt("iddepartment");
            if (id != 0 && id != department.getIdDepartment()) {
                return true;
            }
            return false;
        } catch (SQLException sqlException) {
            System.out.println("department with such name exists");
            return false;
        }
    }

    @Override
    public void createOrUpdate(Department department) {
        if (department.getIdDepartment() != null) {
            updateDepartment(department);
        } else {
            addDepartment(department);
        }
    }
}
