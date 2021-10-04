package com.aimprosoft.aimlearning.servlets;

import com.aimprosoft.aimlearning.DAO.DepartmentDAOImpl;
import com.aimprosoft.aimlearning.DAO.EmployeeDAOImpl;
import com.aimprosoft.aimlearning.model.Department;
import com.aimprosoft.aimlearning.model.Employee;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "addDepartmentServlet", value = "/addDepartmentServlet")
public class addDepartmentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!request.getParameter("name").equals("") && !request.getParameter("address").equals("")){
            Department department = new Department(request.getParameter("name"), request.getParameter("address"));
            new DepartmentDAOImpl().addDepartment(department);
            response.sendRedirect("/aimlearning_war_exploded/");

        }
        else {
            response.sendRedirect("/aimlearning_war_exploded/addDepartment.jsp?wrong=Wrong");
        }
    }
}
