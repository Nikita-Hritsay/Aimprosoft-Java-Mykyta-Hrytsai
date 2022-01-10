package com.aimprosoft.aimlearning.controllers.employee;

import com.aimprosoft.aimlearning.exceptions.DBException;
import com.aimprosoft.aimlearning.exceptions.ValidationException;
import com.aimprosoft.aimlearning.models.Employee;
import com.aimprosoft.aimlearning.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/api")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> displayEmployees() throws DBException {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employees/departments/{id}")
    public List<Employee> displayEmployeesByDepartment(@PathVariable Integer id) throws DBException {
        return employeeService.getByDepartmentId(id);
    }

    @DeleteMapping("/employees")
    public void deleteEmployee(@RequestParam Integer id) throws DBException {
        employeeService.deleteEmployee(id);
    }

    @GetMapping("/employees/email")
    public Employee existByEmail(@RequestParam String email) throws DBException {
        return employeeService.getByEmail(email);
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployeeById(@PathVariable Integer id) throws DBException {
        return employeeService.getById(id);
    }

    @PostMapping("/employees")
    public Employee createOrUpdateEmployee(@RequestBody Employee employee) throws DBException, ValidationException {
        return employeeService.createOrUpdate(employee);
    }

}
