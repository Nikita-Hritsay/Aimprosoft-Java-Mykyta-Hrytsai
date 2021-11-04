package com.aimprosoft.aimlearning.validations.department;

import com.aimprosoft.aimlearning.models.Department;
import com.aimprosoft.aimlearning.services.Impl.DepartmentServiceImpl;
import lombok.AllArgsConstructor;
import net.sf.oval.Validator;
import net.sf.oval.constraint.CheckWithCheck;
import net.sf.oval.context.OValContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class IsUniqueName implements CheckWithCheck.SimpleCheck {

    private DepartmentServiceImpl departmentDAO;

    @Override
    public boolean isSatisfied(Object validatedObject, Object value, OValContext context, Validator validator) {
        try {
            return !departmentDAO.existsByName((Department) validatedObject);
        } catch (Exception e) {
            return false;
        }
    }
}
