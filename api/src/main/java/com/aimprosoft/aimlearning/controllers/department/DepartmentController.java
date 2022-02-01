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
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping(value = "/department")
    public List<Department> displayAllDepartments() throws DBException {
        return departmentService.getAllDepartments();
    }

    @PostMapping("/department")
    public void createOrUpdateDepartment(@RequestBody Department department) throws DBException{
        try {
            departmentService.createOrUpdate(department);
        } catch (ValidationException e) {

        }
    }

    @GetMapping("/department/{id}")
    public Department getDepartmentById(@PathVariable Integer id) throws DBException {
        return departmentService.getDepartmentById(id);
    }

    @GetMapping("/department/exists")
    public Department existByName(@RequestParam String name) throws DBException {
        return departmentService.getByName(name);
    }

    @DeleteMapping("/department")
    public void deleteDepartment(@RequestParam Integer idDepartment) throws DBException {
        departmentService.deleteDepartment(departmentService.getDepartmentById(idDepartment));
    }

}
