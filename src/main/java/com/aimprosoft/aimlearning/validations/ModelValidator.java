package com.aimprosoft.aimlearning.validations;

import com.aimprosoft.aimlearning.exceptions.ValidationException;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import net.sf.oval.context.OValContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelValidator<T> {
    private final Validator validator = new Validator();

    public void validator(T obj) throws ValidationException {
        List<ConstraintViolation> violations = validator.validate(obj);
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
