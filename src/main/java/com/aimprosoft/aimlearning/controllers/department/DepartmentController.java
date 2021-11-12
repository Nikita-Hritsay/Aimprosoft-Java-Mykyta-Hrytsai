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
@RequestMapping("/")
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping(value = {"/displayAllDepartments", "/"})
    public String helloPage(Model model) throws DBException {
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "homePage";
    }

    @GetMapping("/createOrUpdateDepartmentForm")
    public String createOrUpdateDepartmentForm(Model model, @RequestParam(value = "idDepartment", required = false) Integer idDepartment) throws DBException {
        model.addAttribute("department", departmentService.getDepartmentById(idDepartment));
        return "createOrUpdateDepartment";
    }

    @PostMapping("/createOrUpdateDepartmentForm")
    public String createOrUpdateDepartmentPost(Model model, @ModelAttribute Department department, @RequestParam(value = "idDepartment", required = false) Integer idDepartment) throws DBException, ValidationException {
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
    public String deleteDepartmentPost(Model model, @RequestParam Integer idDepartment) {
        try {
            departmentService.deleteDepartment(idDepartment);
        } catch (DBException e) {
            return "redirect:/";
        }
        return "redirect:/";
    }

}
