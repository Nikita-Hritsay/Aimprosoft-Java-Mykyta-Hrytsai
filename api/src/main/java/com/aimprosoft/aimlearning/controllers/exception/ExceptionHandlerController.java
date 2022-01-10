package com.aimprosoft.aimlearning.controllers.exception;

import com.aimprosoft.aimlearning.exceptions.DBException;
import com.aimprosoft.aimlearning.exceptions.ValidationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(DBException.class)
    public String handleDBConflict() {
        return "Try later DB";
    }

    @ExceptionHandler(ValidationException.class)
    public String handleValidationConflict() {
        return "Try later validation";
    }

}
