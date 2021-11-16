package com.aimprosoft.aimlearning.controllers.employee;

import com.aimprosoft.aimlearning.exceptions.DBException;
import com.aimprosoft.aimlearning.exceptions.ValidationException;
import com.aimprosoft.aimlearning.models.Employee;
import com.aimprosoft.aimlearning.services.DepartmentService;
import com.aimprosoft.aimlearning.services.EmployeeService;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeController {

    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    @GetMapping("/displayEmployees")
    public String displayEmployees(Model model) throws DBException {
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "allEmployees";
    }

    @GetMapping("/employeesByDepartment")
    public String employeesByDepartment(Model model, @RequestParam(required = false) Integer id) throws DBException {
        model.addAttribute("department", departmentService.getDepartmentById(id));
        return "employeesByDepartment";
    }

    @PostMapping("deleteEmployee")
    public String deleteEmployee(@RequestParam Integer id, @RequestParam Integer idDepartment) throws DBException {
        employeeService.deleteEmployee(id);
        return "redirect:/employeesByDepartment?id=" + idDepartment;
    }

    @GetMapping("/createOrUpdateEmployeeForm")
    public String displayCreateOrUpdateEmployeeForm(Model model, @RequestParam(value = "id", required = false) Integer id) throws DBException {
        model.addAttribute("employee", employeeService.getById(id));
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "createOrUpdateEmployee";
    }

    @PostMapping("/createOrUpdateEmployeeForm")
    public String createOrUpdateEmployee(Model model, @ModelAttribute Employee employee) throws DBException {
        try {
            employeeService.createOrUpdate(employee);
        } catch (ValidationException validationException) {
            model.addAttribute("errors", validationException.getErrors());
            model.addAttribute("employee", employee);
            model.addAttribute("departments", departmentService.getAllDepartments());
            return "createOrUpdateEmployee";
        }
        return "redirect:/employeesByDepartment?id=" + employee.getDepartment().getIdDepartment();
    }

}
