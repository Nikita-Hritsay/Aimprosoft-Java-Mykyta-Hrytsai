package com.aimprosoft.aimlearning.DAO;

import com.aimprosoft.aimlearning.model.Department;
import com.aimprosoft.aimlearning.model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAOImpl implements DepartmentDAO{

    private ConnectionFactory connectionFactory;

    private final String FIND_ALL = "select iddepartment, name, address from emplyees.department";
    private final String FIND_DEPARTMENT_BY_ID = "select iddepartment, name, address from emplyees.department where iddepartment = ?";
    private final String FIND_BY_ID = "select idemployee, firstName, lastName, salary, hireDate, department_iddepartment from emplyees.employee where department_iddepartment = ?";
    private final String ADD_DEPARTMENT = "insert into department(name, address) values(?, ?)";
    private final String UPDATE_DEPARTMENT = "update department set name = ?, address = ? where iddepartment = ?";
    private final String DELETE_DEPARTMENT = "delete from department where iddepartment = ?";

    public DepartmentDAOImpl(){
        this.connectionFactory = new ConnectionFactory();
    }

    @Override
    public List<Department> getAllDepartments() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            connection = connectionFactory.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(FIND_ALL);
            List<Department> result = new ArrayList<>();
            while(resultSet.next()){
                result.add(new Department(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3)));
            }
            return result;
        }catch (SQLException sqlException){
            System.out.println("something went wrong" + sqlException.getSQLState());
        }finally {
            ConnectionFactory.release(connection, statement, resultSet);
        }

        return null;
    }

    @Override
    public List<Employee> getById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = connectionFactory.getConnection();
            statement = connection.prepareStatement(FIND_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            List<Employee> result = new ArrayList<>();
            while(resultSet.next()){
                result.add(new Employee(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4),
                        resultSet.getString(5),
                        resultSet.getInt(6)));
            }
            return result;
        }catch (SQLException sqlException){
            System.out.println("something went wrong" + sqlException.getSQLState());
        }finally {
            ConnectionFactory.release(connection, statement, resultSet);
        }

        return null;
    }

    @Override
    public void addDepartment(Department department) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try{
            conn = connectionFactory.getConnection();
            conn.setAutoCommit(false);
            preparedStatement = conn.prepareStatement(ADD_DEPARTMENT);
            preparedStatement.setString(1, department.getName());
            preparedStatement.setString(2, department.getAddress());
            preparedStatement.executeUpdate();
            conn.commit();
        } catch (SQLException throwables) {
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("something went wrong" + throwables.getSQLState());
        } finally {
            ConnectionFactory.release(conn, preparedStatement);
        }
    }

    @Override
    public void deleteDepartment(int id){
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try{
            conn = connectionFactory.getConnection();
            preparedStatement = conn.prepareStatement(DELETE_DEPARTMENT);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            System.out.println("Something went wrong" + throwables.getSQLState());
        } finally {
            ConnectionFactory.release(conn, preparedStatement);
        }
    }

    @Override
    public void updateDepartment(Department department) {
        Connection conn = null;
        PreparedStatement statement = null;
        try{
            conn = connectionFactory.getConnection();
            statement = conn.prepareStatement(UPDATE_DEPARTMENT);
            statement.setString(1, department.getName());
            statement.setString(2, department.getAddress());
            statement.setInt(3, department.getIdDepartment());
            statement.executeUpdate();
        }catch(SQLException e){
            System.out.println("something went wrong" + e.getSQLState());
        }finally{
            ConnectionFactory.release(conn, statement);
        }
    }

    @Override
    public Department findDepartmentById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = connectionFactory.getConnection();
            statement = connection.prepareStatement(FIND_DEPARTMENT_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            Department result;
            resultSet.next();
            result = new Department(resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3));
            return result;
        }catch (SQLException sqlException){
            System.out.println("something went wrong" + sqlException.getSQLState());
        }finally {
            ConnectionFactory.release(connection, statement, resultSet);
        }
        return null;
    }
}
