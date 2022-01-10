package com.aimprosoft.aimlearning.validations.department;

import com.aimprosoft.aimlearning.models.Department;
import com.aimprosoft.aimlearning.services.DepartmentService;
import net.sf.oval.Validator;
import net.sf.oval.constraint.CheckWithCheck;
import net.sf.oval.context.OValContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class IsUniqueName implements CheckWithCheck.SimpleCheck {

    private DepartmentService departmentService;

    @Autowired
    public void setDepartmentService(DepartmentService departmentService){
        this.departmentService = departmentService;
    }

    @Override
    public boolean isSatisfied(Object validatedObject, Object value, OValContext context, Validator validator) {
        try {
            Department check = departmentService.existsByName((Department) validatedObject);
            return !(check != null && !Objects.equals(check.getId(), ((Department) validatedObject).getId()) ) ;
        } catch (Exception e) {
            return false;
        }
    }
}
