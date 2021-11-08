package com.aimprosoft.aimlearning.validations.department;

import com.aimprosoft.aimlearning.models.Department;
import com.aimprosoft.aimlearning.services.Impl.DepartmentServiceImpl;
import net.sf.oval.Validator;
import net.sf.oval.constraint.CheckWithCheck;
import net.sf.oval.context.OValContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IsUniqueName implements CheckWithCheck.SimpleCheck {

    private static DepartmentServiceImpl departmentService;

    @Autowired
    public void setDepartmentService(DepartmentServiceImpl departmentService) {
        IsUniqueName.departmentService = departmentService;
    }

    @Override
    public boolean isSatisfied(Object validatedObject, Object value, OValContext context, Validator validator) {
        try {
            return !departmentService.existsByName((Department) validatedObject);
        } catch (Exception e) {
            return false;
        }
    }
}
