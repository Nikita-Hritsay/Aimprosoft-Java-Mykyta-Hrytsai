package com.aimprosoft.aimlearning.controllers.exception;

import com.aimprosoft.aimlearning.exceptions.DBException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
class ExceptionHandlerController {

    @ExceptionHandler(DBException.class)
    public String handleConflict(Model model, Exception e) {
        model.addAttribute("error", "Error occurred with DB connection or operation:\n" + e.getMessage());
        return "errorPage";
    }

}
