package com.aimprosoft.aimlearning.DAO;

import com.aimprosoft.aimlearning.model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO{
    private ConnectionFactory connectionFactory;

    private final String FIND_ALL = "select idemployee, firstName, lastName, salary, hireDate, department_iddepartment from emplyees.employee";
    private final String FIND_BY_ID = "select idemployee, firstName, lastName, salary, hireDate, department_iddepartment from emplyees.employee where idemployee = ?";
    private final String ADD_EMPLOYEE = "insert into employee(firstName, lastName, salary, hireDate, department_iddepartment) values(?, ?, ?, ?, ?)";
    private final String DELETE_EMPLOYEE = "delete from employee where idemployee = ?";
    private final String UPDATE_EMPLOYEE = "update employee set firstName = ?, lastName = ?, salary = ?, hireDate = ?, department_iddepartment = ? where idemployee = ?";

    public EmployeeDAOImpl(){
        this.connectionFactory = new ConnectionFactory();
    }

    @Override
    public List<Employee> getAllEmployees() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            connection = connectionFactory.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(FIND_ALL);
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
            System.out.println("Something went wrong" + sqlException.getSQLState());
        }finally {
            ConnectionFactory.release(connection, statement, resultSet);
        }

        return null;
    }

    @Override
    public void updateEmployee(Employee employee) {
        Connection conn = null;
        PreparedStatement statement = null;
        try{
            conn = connectionFactory.getConnection();
            statement = conn.prepareStatement(UPDATE_EMPLOYEE);
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setInt(3, employee.getSalary());
            statement.setString(4, employee.getHireDate());
            statement.setInt(5, employee.getIdDepartment());
            statement.setInt(6, employee.getId());
            statement.executeUpdate();
        }catch(SQLException e){
            System.out.println("Something went wrong" + e.getSQLState());
        }finally{
            ConnectionFactory.release(conn, statement);
        }
    }

    @Override
    public void deleteEmployee(int id) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try{
            conn = connectionFactory.getConnection();
            preparedStatement = conn.prepareStatement(DELETE_EMPLOYEE);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            System.out.println("Something went wrong" + throwables.getSQLState());
        } finally {
            ConnectionFactory.release(conn, preparedStatement);
        }
    }

    @Override
    public Employee getById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = connectionFactory.getConnection();
            statement = connection.prepareStatement(FIND_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            Employee result;
            resultSet.next();
            result = new Employee(resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getString(5),
                    resultSet.getInt(6));
            return result;
        }catch (SQLException sqlException){
            System.out.println("Something went wrong" + sqlException.getSQLState());
        }finally {
            ConnectionFactory.release(connection, statement, resultSet);
        }
        return null;
    }

    @Override
    public void add(Employee employee) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try{
            conn = connectionFactory.getConnection();
            conn.setAutoCommit(false);
            preparedStatement = conn.prepareStatement(ADD_EMPLOYEE);
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setInt(3, employee.getSalary());
            preparedStatement.setString(4, employee.getHireDate());
            preparedStatement.setInt(5, employee.getIdDepartment());
            preparedStatement.executeUpdate();
            conn.commit();
        } catch (SQLException throwables) {
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("Something went wrong" + throwables.getSQLState());
        } finally {
            ConnectionFactory.release(conn, preparedStatement);
        }
    }
}
