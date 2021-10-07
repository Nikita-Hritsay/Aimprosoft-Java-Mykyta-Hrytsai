package com.aimprosoft.aimlearning.validation.department;

import com.aimprosoft.aimlearning.DAO.DepartmentDAOImpl;
import com.aimprosoft.aimlearning.DAO.EmployeeDAOImpl;
import com.aimprosoft.aimlearning.model.Department;
import com.aimprosoft.aimlearning.model.Employee;
import net.sf.oval.Validator;
import net.sf.oval.constraint.CheckWithCheck;
import net.sf.oval.context.OValContext;

public class isUniqueName implements CheckWithCheck.SimpleCheck {
    private DepartmentDAOImpl departmentDAO = new DepartmentDAOImpl();

    @Override
    public boolean isSatisfied(Object validatedObject, Object value, OValContext context, Validator validator) {
        try {
            return !departmentDAO.existsByName((Department) validatedObject);
        } catch (Exception e) {
            return false;
        }
    }
}