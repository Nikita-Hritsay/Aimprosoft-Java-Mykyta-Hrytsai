package com.aimprosoft.aimlearning.config.spring;

import com.aimprosoft.aimlearning.validations.ModelValidator;
import lombok.AllArgsConstructor;
import net.sf.oval.Validator;
import net.sf.oval.configuration.annotation.AnnotationsConfigurer;
import net.sf.oval.integration.spring.SpringCheckInitializationListener;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.handler.SimpleServletHandlerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@ComponentScan({"com.aimprosoft.aimlearning", "net.sf.oval.integration.spring"})
@EnableTransactionManagement
@PropertySource("classpath:hibernate.properties")
@AllArgsConstructor(onConstructor = @__(@Autowired))
@EnableWebMvc
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

    // DO NOT DELETE IT PLEASE
    @Bean
    public SimpleServletHandlerAdapter simpleServletHandlerAdapter() {
        return new SimpleServletHandlerAdapter();
    }

    @Autowired
    @Bean
    public PlatformTransactionManager hibernateTransactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);
        return txManager;
    }

    @Bean
    public InternalResourceViewResolver jspViewResolver() {
        InternalResourceViewResolver bean = new InternalResourceViewResolver();
        bean.setPrefix("/WEB-INF/pages/");
        bean.setSuffix(".jsp");
        return bean;
    }

    @Bean
    public ModelValidator<?> modelValidator() {
        return new ModelValidator<>(validator());
    }

    @Bean
    public Validator validator() {
        AnnotationsConfigurer annotationsConfigurer = new AnnotationsConfigurer();
        annotationsConfigurer.addCheckInitializationListener(SpringCheckInitializationListener.INSTANCE);
        return new Validator(annotationsConfigurer);
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

