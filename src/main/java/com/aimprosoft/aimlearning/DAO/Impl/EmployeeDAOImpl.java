package com.aimprosoft.aimlearning.DAO.Impl;

import com.aimprosoft.aimlearning.DAO.ConnectionFactory;
import com.aimprosoft.aimlearning.DAO.EmployeeDAO;
import com.aimprosoft.aimlearning.model.Employee;

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
                        resultSet.getString(4),
                        resultSet.getInt(5),
                        resultSet.getDate(6),
                        resultSet.getInt(7)));
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
            statement.setString(3, employee.getEmail());
            statement.setInt(4, employee.getSalary());
            statement.setDate(5, new Date(employee.getHireDate().getTime()));
            statement.setInt(6, employee.getIdDepartment());
            statement.setInt(7, employee.getId());
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
                    resultSet.getString(4),
                    resultSet.getInt(5),
                    resultSet.getDate(6),
                    resultSet.getInt(7));
            return result;
        }catch (SQLException sqlException){
            System.out.println("Something went wrong" + sqlException.getSQLState());
        }finally {
            ConnectionFactory.release(connection, statement, resultSet);
        }
        return null;
    }

    @Override
    public boolean existsByEmail(Employee employee) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = connectionFactory.getConnection();
            statement = connection.prepareStatement(GET_ALL_EMAILS);
            statement.setString(1, employee.getEmail());
            resultSet = statement.executeQuery();
            resultSet.next();
            int id = resultSet.getInt("idemployee");
            if(id != 0 && id != employee.getId()){
                return true;
            }
        }catch (SQLException sqlException){
            System.out.println("Something went wrong" + sqlException.getSQLState());
            return false;
        }finally {
            ConnectionFactory.release(connection, statement, resultSet);
        }
        return false;
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
            preparedStatement.setString(3, employee.getEmail());
            preparedStatement.setInt(4, employee.getSalary());
            preparedStatement.setDate(5, new Date(employee.getHireDate().getTime()));
            preparedStatement.setInt(6, employee.getIdDepartment());
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
