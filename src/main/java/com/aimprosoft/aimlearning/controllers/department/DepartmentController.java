package com.aimprosoft.aimlearning.controllers.department;

import com.aimprosoft.aimlearning.exceptions.DBException;
import com.aimprosoft.aimlearning.exceptions.ValidationException;
import com.aimprosoft.aimlearning.models.Department;
import com.aimprosoft.aimlearning.services.DepartmentService;
import com.aimprosoft.aimlearning.utils.NumberUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.MalformedParameterizedTypeException;


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
    public String createOrUpdateDepartmentForm(Model model) throws DBException {
        return "createOrUpdateDepartment";
    }

    @PostMapping("/createOrUpdateDepartmentForm")
    public String createOrUpdateDepartmentPost(Model model) throws DBException, ValidationException {
        try {
            departmentService.createOrUpdate(getDepartment(model));
        } catch (ValidationException validationException) {
            model.addAttribute("errors", validationException.getErrors());
            model.addAttribute("department", getDepartment(model));
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

    public Department getDepartment(Model model){
        return new Department()
                .withIdDepartment(NumberUtils.getInt(model.getAttribute("idDepartment").toString()))
                .withAddress(model.getAttribute("name").toString())
                .withAddress(model.getAttribute("address").toString());
    }

}
