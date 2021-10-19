package com.aimprosoft.aimlearning.exceptions;


import java.util.Map;

public class ValidationException extends Exception {

    private Map<String, String> errors;

    public ValidationException(Map<String, String> errors) {
        this.errors = errors;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}
