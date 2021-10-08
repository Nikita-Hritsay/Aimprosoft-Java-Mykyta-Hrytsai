package com.aimprosoft.aimlearning.servlets;

import com.aimprosoft.aimlearning.DAO.Impl.DepartmentDAOImpl;
import com.aimprosoft.aimlearning.model.Department;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class HomePageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Department> departments = new DepartmentDAOImpl().getAllDepartments();
        request.setAttribute("departments", departments);
        request.getRequestDispatcher("/homePage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
