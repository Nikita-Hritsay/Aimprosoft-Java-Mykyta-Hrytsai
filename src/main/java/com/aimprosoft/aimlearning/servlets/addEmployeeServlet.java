package com.aimprosoft.aimlearning.servlets;

import com.aimprosoft.aimlearning.DAO.EmployeeDAOImpl;
import com.aimprosoft.aimlearning.model.Employee;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "addEmployeeServlet", value = "/addEmployeeServlet")
public class addEmployeeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            if(!request.getParameter("firstName").equals("") && !request.getParameter("lastName").equals("")
                && Integer.parseInt(request.getParameter("salary")) > 0
                && Integer.parseInt(request.getParameter("iddepartment")) > 0){
                    Employee employee = new Employee(request.getParameter("firstName"),
                            request.getParameter("lastName"),
                            request.getParameter("email"),
                            Integer.parseInt(request.getParameter("salary")),
                            request.getParameter("hireDate"),
                            Integer.parseInt(request.getParameter("iddepartment")));
                    new EmployeeDAOImpl().add(employee);
                    //response.sendRedirect("/aimlearning_war_exploded/");
                request.getRequestDispatcher("/").forward(request, response);
                }
            else {
                response.sendRedirect("/aimlearning_war_exploded/addEmployee.jsp?wrong=Wrong");
            }
        }
}



