package com.aimprosoft.aimlearning.commands.employee;

import com.aimprosoft.aimlearning.commands.ICommand;
import com.aimprosoft.aimlearning.exceptions.DBException;
import com.aimprosoft.aimlearning.models.Employee;
import com.aimprosoft.aimlearning.services.Impl.EmployeeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DisplayEmployeeCommand implements ICommand {

    private final EmployeeServiceImpl employeeService = new EmployeeServiceImpl();

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DBException {
        List<Employee> employees = employeeService.getAllEmployees();
        System.out.println(employees.get(1));
        System.out.println(employees.get(1).getDepartment());
        request.setAttribute("employees", employeeService.getAllEmployees());
        request.getRequestDispatcher("/WEB-INF/pages/allEmployees.jsp").forward(request, response);
    }


}
