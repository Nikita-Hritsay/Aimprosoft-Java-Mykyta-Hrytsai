package com.aimprosoft.aimlearning.controllers.department;

import com.aimprosoft.aimlearning.exceptions.DBException;
import com.aimprosoft.aimlearning.exceptions.ValidationException;
import com.aimprosoft.aimlearning.models.Department;
import com.aimprosoft.aimlearning.services.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping(value = {"/displayAllDepartments", "/"})
    public String displayAllDepartments(Model model) throws DBException {
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "homePage";
    }

    @GetMapping("/createOrUpdateDepartmentForm")
    public String displayCreateOrUpdateDepartmentForm(Model model, @RequestParam(value = "idDepartment", required = false) Integer idDepartment) throws DBException {
        model.addAttribute("department", departmentService.getDepartmentById(idDepartment));
        return "createOrUpdateDepartment";
    }

    @PostMapping("/createOrUpdateDepartmentForm")
    public String createOrUpdateDepartment(Model model, @ModelAttribute Department department, @RequestParam(value = "idDepartment", required = false) Integer idDepartment) throws DBException {
        try {
            departmentService.createOrUpdate(department.withIdDepartment(idDepartment));
        } catch (ValidationException validationException) {
            model.addAttribute("errors", validationException.getErrors());
            model.addAttribute("department", department);
            return "createOrUpdateDepartment";
        }
        return "redirect:/";
    }

    @PostMapping("/deleteDepartment")
    public String deleteDepartment(Model model, @RequestParam Integer idDepartment) throws DBException {
        departmentService.deleteDepartment(idDepartment);
        return "redirect:/";
    }

}
