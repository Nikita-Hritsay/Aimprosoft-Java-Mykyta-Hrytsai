package com.aimprosoft.aimlearning.exceptions;

import com.aimprosoft.aimlearning.models.Employee;
import com.aimprosoft.aimlearning.utils.Utils;
import net.sf.oval.Validator;

import java.util.Map;

public class ValidationException extends Exception{

    private Map<String, String> errors;

    public ValidationException(String message) {
        super(message);
    }

    public Map<String, String> getErrors(){
        return errors;
    }
}
