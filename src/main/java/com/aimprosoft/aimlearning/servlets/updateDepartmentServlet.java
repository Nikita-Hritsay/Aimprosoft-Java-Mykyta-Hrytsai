package com.aimprosoft.aimlearning.servlets;

import com.aimprosoft.aimlearning.DAO.DepartmentDAOImpl;
import com.aimprosoft.aimlearning.model.Department;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "updateDepartmentServlet", value = "/updateDepartmentServlet")
public class updateDepartmentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/aimlearning_war_exploded/updateDepartment.jsp?name=" + request.getParameter("name") +
                "&address=" + request.getParameter("address") +
                "&id=" + request.getParameter("id"));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        new DepartmentDAOImpl().updateDepartment(new Department(Integer.parseInt(request.getParameter("id")),
                request.getParameter("name"),
                request.getParameter("address")));
        response.sendRedirect("/aimlearning_war_exploded/");
    }
}
