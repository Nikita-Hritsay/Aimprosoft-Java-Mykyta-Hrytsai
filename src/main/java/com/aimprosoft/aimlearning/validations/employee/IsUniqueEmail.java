package com.aimprosoft.aimlearning.validations.employee;

import com.aimprosoft.aimlearning.models.Employee;
import com.aimprosoft.aimlearning.services.EmployeeService;
import net.sf.oval.Validator;
import net.sf.oval.constraint.CheckWithCheck;
import net.sf.oval.context.OValContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IsUniqueEmail implements CheckWithCheck.SimpleCheck {

    private EmployeeService employeeService;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public boolean isSatisfied(Object validatedObject, Object value, OValContext context, Validator validator) {
        try {
            return !employeeService.existByEmail((Employee) validatedObject);
        } catch (Exception e) {
            return false;
        }
    }
}
