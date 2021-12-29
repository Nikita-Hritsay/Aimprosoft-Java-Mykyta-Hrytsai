package com.aimprosoft.aimlearning.controllers.department;

import com.aimprosoft.aimlearning.exceptions.DBException;
import com.aimprosoft.aimlearning.exceptions.ValidationException;
import com.aimprosoft.aimlearning.models.Department;
import com.aimprosoft.aimlearning.services.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/api")
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping(value = "/department")
    public List<Department> displayAllDepartments() throws DBException {
        return departmentService.getAllDepartments();
    }

    @PostMapping("/department")
    public ResponseEntity<Map<String, String>> createOrUpdateDepartment(@RequestBody Department department) throws DBException{
        try {
            departmentService.createOrUpdate(department);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity<>(e.getErrors(), HttpStatus.CONFLICT);
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
        departmentService.deleteDepartment(idDepartment);
    }

}
