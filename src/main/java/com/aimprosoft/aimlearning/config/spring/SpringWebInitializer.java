package com.aimprosoft.aimlearning.config.spring;

import com.aimprosoft.aimlearning.controllers.department.DepartmentController;
import com.aimprosoft.aimlearning.controllers.employee.EmployeeController;
import com.aimprosoft.aimlearning.controllers.exception.ExceptionHandlerController;
import org.springframework.stereotype.Controller;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

@RestController
public class SpringWebInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) {
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(ApplicationContextConfig.class);
        servletContext.addListener(new ContextLoaderListener(rootContext));
        AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
        dispatcherContext.register(DepartmentController.class, EmployeeController.class, ExceptionHandlerController.class);
        ServletRegistration.Dynamic dispatcher = servletContext
                .addServlet("MainController", new DispatcherServlet(dispatcherContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }

}
