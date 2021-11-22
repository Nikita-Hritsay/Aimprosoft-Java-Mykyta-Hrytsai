package com.aimprosoft.aimlearning.controllers.department;

import com.aimprosoft.aimlearning.exceptions.DBException;
import com.aimprosoft.aimlearning.exceptions.ValidationException;
import com.aimprosoft.aimlearning.models.Department;
import com.aimprosoft.aimlearning.services.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DepartmentController {

    private final DepartmentService departmentService;

    @ResponseBody
    @GetMapping(value = {"/displayAllDepartments", "/"})
    public List<Department> displayAllDepartments(Model model) throws DBException {
        return departmentService.getAllDepartments();
    }

    @ResponseBody
    @GetMapping("/createOrUpdateDepartmentForm")
    public Department displayCreateOrUpdateDepartmentForm(Model model, @RequestParam(value = "idDepartment", required = false) Integer idDepartment) throws DBException {
        return departmentService.getDepartmentById(idDepartment);
    }

    @ResponseBody
    @PostMapping("/createOrUpdateDepartmentForm")
    public ResponseEntity<HttpStatus> createOrUpdateDepartment(Model model, @ModelAttribute Department department) throws DBException{
        try {
            departmentService.createOrUpdate(department);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity(e.getErrors(), HttpStatus.CONFLICT);
        }
    }

    @ResponseBody
    @PostMapping("/deleteDepartment")
    public void deleteDepartment(Model model, @RequestParam Integer idDepartment) throws DBException {
        departmentService.deleteDepartment(idDepartment);
    }

}
