package com.aimprosoft.aimlearning.controllers.exception;

import com.aimprosoft.aimlearning.exceptions.DBException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(DBException.class)
    public String handleConflict(Model model) {
        return "Try later";
    }

}
