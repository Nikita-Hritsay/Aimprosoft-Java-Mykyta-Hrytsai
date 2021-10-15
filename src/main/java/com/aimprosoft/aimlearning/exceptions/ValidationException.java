package com.aimprosoft.aimlearning.exceptions;


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
