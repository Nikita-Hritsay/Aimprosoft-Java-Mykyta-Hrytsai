package com.aimprosoft.aimlearning.controllers.employee;

import com.aimprosoft.aimlearning.exceptions.DBException;
import com.aimprosoft.aimlearning.exceptions.ValidationException;
import com.aimprosoft.aimlearning.models.Employee;
import com.aimprosoft.aimlearning.services.EmployeeService;
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
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/employee")
    public List<Employee> displayEmployees() throws DBException {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employee/department/{id}")
    public List<Employee> displayEmployeesByDepartment(@PathVariable Integer id) throws DBException {
        return employeeService.getByDepartmentId(id);
    }

    @DeleteMapping("/employee")
    public void deleteEmployee(@RequestParam Integer id) throws DBException {
        employeeService.deleteEmployee(id);
    }

    @GetMapping("/employee/exists")
    public Employee existByEmail(@RequestParam String email) throws DBException {
        return employeeService.getByEmail(email);
    }

    @GetMapping("/employee/{id}")
    public Employee getEmployeeById(@PathVariable Integer id) throws DBException {
        return employeeService.getById(id);
    }

    @PostMapping("/employee")
    public ResponseEntity<Map<String, String>> createOrUpdateEmployee(@RequestBody Employee employee) throws DBException {
        System.out.println(employee);
        try {
            employeeService.createOrUpdate(employee);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity<>(e.getErrors(), HttpStatus.CONFLICT);
        }
    }

}
