package com.aimprosoft.aimlearning.DAO.Impl;

import com.aimprosoft.aimlearning.config.ConnectionFactory;
import com.aimprosoft.aimlearning.DAO.DepartmentDAO;
import com.aimprosoft.aimlearning.exceptions.DBException;
import com.aimprosoft.aimlearning.models.Department;
import com.aimprosoft.aimlearning.utils.NumberUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAOImpl implements DepartmentDAO {

    private ConnectionFactory connectionFactory = new ConnectionFactory();

    private final String FIND_ALL = "select iddepartment, name, address from department";
    private final String FIND_DEPARTMENT_BY_ID = "select iddepartment, name, address from department where iddepartment = ?";
    private final String ADD_DEPARTMENT = "insert into department(name, address) values(?, ?)";
    private final String UPDATE_DEPARTMENT = "update department set name = ?, address = ? where iddepartment = ?";
    private final String DELETE_DEPARTMENT = "delete from department where iddepartment = ?";
    private final String EXISTS_BY_NAME = "select iddepartment from department where name = ?";

    @Override
    public List<Department> getAllDepartments() throws DBException {
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
            throw new DBException("Error in get All Departments: " + sqlException.getMessage());
        }
    }

    @Override
    public void addDepartment(Department department) throws DBException {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_DEPARTMENT)) {
            preparedStatement.setString(1, department.getName());
            preparedStatement.setString(2, department.getAddress());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new DBException("Error in get add Department: " + throwables.getMessage());
        }
    }

    @Override
    public void deleteDepartment(int id) throws DBException {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(DELETE_DEPARTMENT)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new DBException("Error in get delete Department: " + throwables.getMessage());
        }
    }

    @Override
    public void updateDepartment(Department department) throws DBException {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement statement = conn.prepareStatement(UPDATE_DEPARTMENT)) {
            statement.setString(1, department.getName());
            statement.setString(2, department.getAddress());
            statement.setInt(3, department.getIdDepartment());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DBException("Error in get update Department: " + e.getMessage());
        }
    }

    @Override
    public Department getDepartmentById(int id) throws DBException {
        try (Connection connection = connectionFactory.getConnection();
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
            throw new DBException("Error in get Department by id: " + sqlException.getMessage());
        }
    }

    @Override
    public boolean existsByName(Department department) throws DBException {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(EXISTS_BY_NAME)) {
            ResultSet resultSet = null;
            statement.setString(1, department.getName());
            resultSet = statement.executeQuery();
            System.out.println("");
            while (resultSet.next()) {
                Integer id = NumberUtils.getInt(resultSet.getString("iddepartment"));
                if (id != null && id != department.getIdDepartment()) {
                    return true;
                }
            }
            return false;
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
            throw new DBException("Error in exists by name Department: " + sqlException.getMessage());
        }
    }

    @Override
    public void createOrUpdate(Department department) throws DBException {
        if (department.getIdDepartment() != null) {
            updateDepartment(department);
        } else {
            addDepartment(department);
        }
    }
}
