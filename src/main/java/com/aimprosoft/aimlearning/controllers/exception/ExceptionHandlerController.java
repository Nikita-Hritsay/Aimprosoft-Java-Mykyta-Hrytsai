package com.aimprosoft.aimlearning.controllers.exception;

import com.aimprosoft.aimlearning.exceptions.DBException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
class ExceptionHandlerController {

    @ExceptionHandler(DBException.class)
    public String handleConflict(Model model) {
        model.addAttribute("error", "DB exception");
        return "errorPage";
    }

}
