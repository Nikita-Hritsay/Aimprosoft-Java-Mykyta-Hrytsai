package com.aimprosoft.aimlearning.validations.department;

import com.aimprosoft.aimlearning.dao.Impl.HibernateDepartmentDAOImpl;
import com.aimprosoft.aimlearning.models.Department;
import net.sf.oval.Validator;
import net.sf.oval.constraint.CheckWithCheck;
import net.sf.oval.context.OValContext;

public class IsUniqueName implements CheckWithCheck.SimpleCheck {
    private final HibernateDepartmentDAOImpl departmentDAO = new HibernateDepartmentDAOImpl();

    @Override
    public boolean isSatisfied(Object validatedObject, Object value, OValContext context, Validator validator) {
        try {
            return !departmentDAO.existsByName((Department) validatedObject);
        } catch (Exception e) {
            return false;
        }
    }
}
