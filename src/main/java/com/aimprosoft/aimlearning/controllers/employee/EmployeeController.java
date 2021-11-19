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

@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeController {

    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    @ResponseBody
    @GetMapping("/displayEmployees")
    public List<Employee> displayEmployees(Model model) throws DBException {
        return employeeService.getAllEmployees();
    }

    @ResponseBody
    @GetMapping("/employeesByDepartment")
    public Department employeesByDepartment(Model model, @RequestParam(required = false) Integer id) throws DBException {
        return departmentService.getDepartmentById(id);
    }

    @ResponseBody
    @PostMapping("deleteEmployee")
    public void deleteEmployee(@RequestParam Integer id, @RequestParam Integer idDepartment) throws DBException {
        employeeService.deleteEmployee(id);
    }

    @ResponseBody
    @GetMapping("/createOrUpdateEmployeeForm")
    public Employee displayCreateOrUpdateEmployeeForm(Model model, @RequestParam(value = "id", required = false) Integer id) throws DBException {
        return employeeService.getById(id);
    }

    @ResponseBody
    @PostMapping("/createOrUpdateEmployeeForm")
    public ResponseEntity<HttpStatus> createOrUpdateEmployee(Model model, @ModelAttribute Employee employee) throws DBException {
        try {
            employeeService.createOrUpdate(employee);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity(e.getErrors(), HttpStatus.CONFLICT);
        }
    }

}
