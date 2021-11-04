package com.aimprosoft.aimlearning.config.spring;

import com.aimprosoft.aimlearning.commands.CommandFactory;
import com.aimprosoft.aimlearning.commands.ICommand;
import com.aimprosoft.aimlearning.commands.department.CreateOrUpdateDepartmentCommand;
import com.aimprosoft.aimlearning.commands.department.DeleteDepartmentCommand;
import com.aimprosoft.aimlearning.commands.department.DisplayAllDepartmentCommand;
import com.aimprosoft.aimlearning.commands.department.FormCreateUpdateDepartmentCommand;
import com.aimprosoft.aimlearning.commands.employee.*;
import com.aimprosoft.aimlearning.controllers.MainController;
import lombok.AllArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.handler.SimpleServletHandlerAdapter;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:hibernate.properties")
@AllArgsConstructor(onConstructor = @__(@Autowired))
@ComponentScan("com.aimprosoft.aimlearning")
public class ApplicationContextConfig {

    private final Environment environment;

    @Autowired
    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan("com.aimprosoft.aimlearning.models");
        return sessionFactory;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource source = new DriverManagerDataSource();
        source.setDriverClassName(Objects.requireNonNull(environment.getProperty("hibernate.connection.driver_class")));
        source.setUrl(environment.getProperty("hibernate.connection.url"));
        source.setUsername(environment.getProperty("hibernate.connection.username"));
        source.setPassword(environment.getProperty("hibernate.connection.password"));
        return source;
    }

}
