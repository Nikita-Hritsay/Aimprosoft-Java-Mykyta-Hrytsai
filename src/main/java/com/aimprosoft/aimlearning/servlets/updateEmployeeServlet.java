package com.aimprosoft.aimlearning.servlets;

import com.aimprosoft.aimlearning.DAO.EmployeeDAOImpl;
import com.aimprosoft.aimlearning.model.Employee;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "updateEmployeeServlet", value = "/updateEmployeeServlet")
public class updateEmployeeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("updateEmployee.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        new EmployeeDAOImpl().updateEmployee(new Employee( Integer.parseInt(request.getParameter("id")),
                request.getParameter("firstName"),
                request.getParameter("lastName") ,
                Integer.parseInt(request.getParameter("salary")),
                request.getParameter("hireDate"),
                Integer.parseInt(request.getParameter("idDepartment"))));
        response.sendRedirect("/aimlearning_war_exploded/");
    }
}
