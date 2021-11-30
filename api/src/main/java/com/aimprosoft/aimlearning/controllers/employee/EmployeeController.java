package com.aimprosoft.aimlearning.controllers.employee;

import com.aimprosoft.aimlearning.exceptions.DBException;
import com.aimprosoft.aimlearning.exceptions.ValidationException;
import com.aimprosoft.aimlearning.models.Department;
import com.aimprosoft.aimlearning.models.Employee;
import com.aimprosoft.aimlearning.services.DepartmentService;
import com.aimprosoft.aimlearning.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/employee")
    public List<Employee> displayEmployees() throws DBException {
        return employeeService.getAllEmployees();
    }

    @DeleteMapping("/employee")
    public void deleteEmployee(@RequestParam Integer id) throws DBException {
        employeeService.deleteEmployee(id);
    }

    @PostMapping("/employee")
    public ResponseEntity<Map<String, String>> createOrUpdateEmployee(@RequestBody Employee employee) throws DBException {
        try {
            employeeService.createOrUpdate(employee);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ValidationException e) {
            System.out.println(e.getErrors());
            return new ResponseEntity<>(e.getErrors(), HttpStatus.CONFLICT);
        }
    }

}
