package com.aimprosoft.aimlearning.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

public class ConnectionFactory {

    public static Properties properties = new Properties();

    static {
        try {
            properties.load(
                    new FileInputStream(Objects.requireNonNull(
                            ConnectionFactory.class.getClassLoader().getResource("application.properties")).getPath()));
            Class.forName(properties.getProperty("db.driver.mySql"));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(properties.getProperty("db.url"), properties);
    }
}
