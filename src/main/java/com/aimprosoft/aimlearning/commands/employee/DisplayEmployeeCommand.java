package com.aimprosoft.aimlearning.commands.employee;

import com.aimprosoft.aimlearning.DAO.Impl.EmployeeDAOImpl;
import com.aimprosoft.aimlearning.commands.ICommand;
import com.aimprosoft.aimlearning.models.Employee;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DisplayEmployeeCommand implements ICommand {

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Employee> employees = new EmployeeDAOImpl().getAllEmployees();
        req.setAttribute("employees", employees);
        req.getRequestDispatcher("allEmployees.jsp").forward(req, resp);
    }
}
