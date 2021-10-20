package com.aimprosoft.aimlearning.commands.employee;

import com.aimprosoft.aimlearning.commands.ICommand;
import com.aimprosoft.aimlearning.models.Employee;
import com.aimprosoft.aimlearning.services.Impl.DepartmentServiceImpl;
import com.aimprosoft.aimlearning.services.Impl.EmployeeServiceImpl;
import com.aimprosoft.aimlearning.utils.NumberUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FormCreateOrUpdateEmployeeCommand implements ICommand {

    private final EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
    private final DepartmentServiceImpl departmentService = new DepartmentServiceImpl();

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("employee", getEmployee(request));
        request.setAttribute("idDepartment", getEmployee(request).getIdDepartment());
        request.setAttribute("departments", departmentService.getAllDepartments());
        request.getRequestDispatcher("/WEB-INF/pages/createOrUpdateEmployee.jsp").forward(request, response);
    }

    private Employee getEmployee(HttpServletRequest request) {
        if (request.getParameter("id") != null) {
            return employeeService.getById(NumberUtils.getInt(request.getParameter("id")));
        }
        return new Employee();
    }

}
