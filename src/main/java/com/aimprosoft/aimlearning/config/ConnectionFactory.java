package com.aimprosoft.aimlearning.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public static Properties properties = new Properties();

    static {
        try {
            properties.load(
                    new FileInputStream(Objects.requireNonNull(
                            ConnectionFactory.class.getClassLoader().getResource("application.properties")).getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(properties.getProperty("db.driver.mySql"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(properties.getProperty("db.url"), properties);
    }
}
