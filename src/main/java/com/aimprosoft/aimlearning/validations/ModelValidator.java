package com.aimprosoft.aimlearning.validations;

import com.aimprosoft.aimlearning.exceptions.ValidationException;
import lombok.AllArgsConstructor;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import net.sf.oval.context.OValContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ModelValidator<T> {

    private final Validator validator;

    public void validate(T obj) throws ValidationException {
        List<ConstraintViolation> violations = validator.validate(obj);
        System.out.println(violations);
        if (!violations.isEmpty()) {
            Map<String, String> errors = new HashMap<>();
            for (ConstraintViolation violation : violations) {
                String fieldName = null;
                for (OValContext node : violation.getContextPath()) {
                    fieldName = node.toStringUnqualified();
                }
                errors.put(fieldName, violation.getMessage());
            }
            throw new ValidationException(errors);
        }
    }

}
