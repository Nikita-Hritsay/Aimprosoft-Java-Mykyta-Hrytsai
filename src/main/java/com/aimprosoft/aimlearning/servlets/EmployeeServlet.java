package com.aimprosoft.aimlearning.servlets;

import com.aimprosoft.aimlearning.DAO.EmployeeDAOImpl;
import com.aimprosoft.aimlearning.model.Employee;


import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@WebServlet(name = "EmployeeServlet", value = "/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Boolean sent = false;
        String action = request.getParameter("action") == null ? "" : request.getParameter("action");
        switch (action){
            case "delete":
                new EmployeeDAOImpl().deleteEmployee(Integer.parseInt(request.getParameter("id")));
                List<Employee> employees = new EmployeeDAOImpl().getAllEmployees();
                request.setAttribute("employees", employees);
                response.sendRedirect("http://localhost:8080/aimlearning_war_exploded/DepartmentServlet?id=" + request.getParameter("idDepartment"));
                sent = true;
                break;
            case "update":
                Employee employee = new EmployeeDAOImpl().getById(Integer.parseInt(request.getParameter("id")));
                List<Employee> employeesUpdate = new ArrayList<>();
                employeesUpdate.add(employee);
                request.setAttribute("employees", employeesUpdate);
                request.getRequestDispatcher("updateEmployee.jsp").forward(request, response);
                sent = true;
                break;
        }
        if(!sent) {
            List<Employee> employees = new EmployeeDAOImpl().getAllEmployees();
            request.setAttribute("employees", employees);
            request.getRequestDispatcher("/allEmployees.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = simpleDateFormat.parse(request.getParameter("hireDate"));
            new EmployeeDAOImpl().updateEmployee(new Employee( Integer.parseInt(request.getParameter("id")),
                    request.getParameter("firstName"),
                    request.getParameter("lastName") ,
                    request.getParameter("email"),
                    Integer.parseInt(request.getParameter("salary")),
                    date,
                    Integer.parseInt(request.getParameter("iddepartment"))));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/aimlearning_war_exploded/");
    }
}
