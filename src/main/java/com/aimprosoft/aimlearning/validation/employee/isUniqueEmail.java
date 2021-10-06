package com.aimprosoft.aimlearning.validation.employee;

import com.aimprosoft.aimlearning.DAO.EmployeeDAOImpl;
import com.aimprosoft.aimlearning.model.Employee;
import net.sf.oval.Validator;
import net.sf.oval.constraint.CheckWithCheck;
import net.sf.oval.context.OValContext;


public class isUniqueEmail implements CheckWithCheck.SimpleCheck {
    private EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();

    @Override
    public boolean isSatisfied(Object validatedObject, Object value, OValContext context, Validator validator) {
        try {
            return !employeeDAO.existsByEmail((Employee) validatedObject);
        } catch (Exception e) {
            return false;
        }
    }
}
