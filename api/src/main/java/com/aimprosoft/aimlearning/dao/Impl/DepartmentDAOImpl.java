package com.aimprosoft.aimlearning.dao.Impl;

import com.aimprosoft.aimlearning.config.ConnectionFactory;
import com.aimprosoft.aimlearning.dao.DepartmentDAO;
import com.aimprosoft.aimlearning.exceptions.DBException;
import com.aimprosoft.aimlearning.models.Department;
import com.aimprosoft.aimlearning.utils.NumberUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAOImpl implements DepartmentDAO {

    private static final String FIND_ALL = "select iddepartment, name, address from department";
    private static final String FIND_DEPARTMENT_BY_ID = "select iddepartment, name, address from department where iddepartment = ?";
    private static final String ADD_DEPARTMENT = "insert into department(name, address) values(?, ?)";
    private static final String UPDATE_DEPARTMENT = "update department set name = ?, address = ? where iddepartment = ?";
    private static final String DELETE_DEPARTMENT = "delete from department where iddepartment = ?";
    private static final String EXISTS_BY_NAME = "select iddepartment from department where name = ?";
    private static final String FIND_DEPARTMENT_BY_NAME = "select iddepartment, name, address from department where name = ?";
    private static final String FIND_DEPARTMENT_NAME_BY_EMPLOYEE_ID = "select name from department where iddepartment = (select department_iddepartment from employee where idemployee = ? )";

    @Override
    public List<Department> getAllDepartments() throws DBException {
        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_ALL)) {
            List<Department> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(new Department().withId(resultSet.getInt(1))
                        .withName(resultSet.getString(2))
                        .withAddress(resultSet.getString(3)));
            }
            return result;
        } catch (SQLException sqlException) {
            throw new DBException("Error in get All Departments: " + sqlException.getMessage());
        }
    }

    @Override
    public void saveOrUpdate(Department department) throws DBException {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = setupPreparedStatement(department, connection, ADD_DEPARTMENT)) {
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new DBException("Error in get add Department: " + throwables.getMessage());
        }
    }

    @Override
    public void deleteDepartment(Department department) throws DBException {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(DELETE_DEPARTMENT)) {
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new DBException("Error in get delete Department: " + throwables.getMessage());
        }
    }

    public void updateDepartment(Department department) throws DBException {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement statement = setupPreparedStatement(department, conn, UPDATE_DEPARTMENT)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DBException("Error in get update Department: " + e.getMessage());
        }
    }

    @Override
    public Department getDepartmentById(Integer id) throws DBException {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_DEPARTMENT_BY_ID)) {
            ResultSet resultSet;
            statement.setInt(1, id == null ? 0 : id);
            resultSet = statement.executeQuery();
            if(resultSet.next()) {
                return new Department().withId(resultSet.getInt(1)).withName(resultSet.getString(2)).withAddress(resultSet.getString(3));
            }
            return new Department();
        } catch (SQLException sqlException) {
            throw new DBException("Error in get Department by id: " + sqlException.getMessage());
        }
    }

    @Override
    public boolean existsByName(Department department) throws DBException {
        return false;
    }

    @Override
    public Department getDepartmentByName(String name) throws DBException {
        return null;
    }

    @Override
    public void createOrUpdate(Department department) throws DBException {
        if (department.getId() != null) {
            updateDepartment(department);
        } else {
            saveOrUpdate(department);
        }
    }

    private PreparedStatement setupPreparedStatement(Department department, Connection connection, String query) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, department.getName());
        preparedStatement.setString(2, department.getAddress());
        if (department.getId() != null) {
            preparedStatement.setInt(3, department.getId());
        }
        return preparedStatement;
    }
}

