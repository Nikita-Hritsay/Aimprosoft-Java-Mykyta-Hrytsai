package com.aimprosoft.aimlearning.controllers.department;

import com.aimprosoft.aimlearning.exceptions.DBException;
import com.aimprosoft.aimlearning.exceptions.ValidationException;
import com.aimprosoft.aimlearning.models.Department;
import com.aimprosoft.aimlearning.services.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/api")
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping(value = "/departments")
    public List<Department> displayAllDepartments() throws DBException {
        return departmentService.getAllDepartments();
    }

    @PostMapping("/departments")
    public Department createOrUpdateDepartment(@RequestBody Department department) throws DBException, ValidationException {
        return departmentService.createOrUpdate(department);
    }

    @GetMapping("/departments/{id}")
    public Department getDepartmentById(@PathVariable Integer id) throws DBException {
        return departmentService.getDepartmentById(id);
    }

    @GetMapping("/departments/name")
    public Department existByName(@RequestParam String name) throws DBException {
        return departmentService.getByName(name);
    }

    @DeleteMapping("/departments")
    public void deleteDepartment(@RequestParam Integer idDepartment) throws DBException {
        departmentService.deleteDepartment(idDepartment);
    }

}
