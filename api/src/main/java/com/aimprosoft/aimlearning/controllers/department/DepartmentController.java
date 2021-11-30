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

    @GetMapping(value = {"/department", "/"})
    public List<Department> displayAllDepartments() throws DBException {
        return departmentService.getAllDepartments();
    }

    @PostMapping("/department")
    public ResponseEntity<HttpStatus> createOrUpdateDepartment(@RequestBody Department department) throws DBException{
        try {
            departmentService.createOrUpdate(department);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity(e.getErrors(), HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/department")
    public void deleteDepartment(@RequestParam Integer idDepartment) throws DBException {
        departmentService.deleteDepartment(idDepartment);
    }

}
