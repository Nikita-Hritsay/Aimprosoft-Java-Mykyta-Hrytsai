package com.aimprosoft.aimlearning.config.spring;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.servlet.handler.SimpleServletHandlerAdapter;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SpringBeans {

    private final Environment environment;

    @Autowired
    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan("com.aimprosoft.models");
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
