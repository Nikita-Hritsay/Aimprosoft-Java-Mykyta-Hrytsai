package com.aimprosoft.aimlearning.validations.employee;

import com.aimprosoft.aimlearning.dao.Impl.HibernateEmployeeDAOImpl;
import com.aimprosoft.aimlearning.models.Employee;
import lombok.AllArgsConstructor;
import net.sf.oval.Validator;
import net.sf.oval.constraint.CheckWithCheck;
import net.sf.oval.context.OValContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class IsUniqueEmail implements CheckWithCheck.SimpleCheck {
    private final HibernateEmployeeDAOImpl employeeDAO ;

    @Override
    public boolean isSatisfied(Object validatedObject, Object value, OValContext context, Validator validator) {
        try {
            return !employeeDAO.existsByEmail((Employee) validatedObject);
        } catch (Exception e) {
            return false;
        }
    }
}
