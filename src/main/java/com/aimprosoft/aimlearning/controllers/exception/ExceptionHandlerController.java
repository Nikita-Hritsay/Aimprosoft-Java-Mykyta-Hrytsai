package com.aimprosoft.aimlearning.controllers.exception;

import com.aimprosoft.aimlearning.exceptions.DBException;
import com.aimprosoft.aimlearning.exceptions.ValidationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;


@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(DBException.class)
    public String handleConflict(Model model, DBException e) {
        return "Error occurred with DB connection or operation: " + e.getMessage();
    }

}
