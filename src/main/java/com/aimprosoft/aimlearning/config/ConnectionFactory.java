package com.aimprosoft.aimlearning.config;

import java.sql.*;

public class ConnectionFactory {

    public static final String URL = "jdbc:mysql://localhost:3306/employees?useUnicode=true&serverTimezone=UTC";
    public static final String USER = "root";
    public static final String PASSWORD = "nikitahr";

    static{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        } catch (Exception e){
            System.out.println("Connection failed, " + e);
        }
    }

    public Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        return conn;
    }

    public static void release(Connection conn, PreparedStatement statement){
        closeConn(conn);
        closePs(statement);
    }

    public static void release(Connection conn, PreparedStatement statement, ResultSet resultSet){
        closeConn(conn);
        closePs(statement);
        closeRS(resultSet);
    }

    public static void release(Connection conn, Statement statement, ResultSet resultSet){
        closeConn(conn);
        closeSt(statement);
        closeRS(resultSet);
    }

    public static void release(Connection conn, Statement statement){
        closeConn(conn);
        closeSt(statement);
    }

    public static void closeConn(Connection connection){
        try {
            if(connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closePs(PreparedStatement statement){
        try {
            if(statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeSt(Statement statement){
        try {
            if(statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeRS(ResultSet resultSet){
        try {
            if(resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
