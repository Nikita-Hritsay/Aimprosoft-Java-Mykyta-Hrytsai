package com.aimprosoft.aimlearning.controllers.department;

import com.aimprosoft.aimlearning.exceptions.DBException;
import com.aimprosoft.aimlearning.services.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping("/")
    public String helloPage(Model model) throws DBException {
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "helloWorld";
    }

}
