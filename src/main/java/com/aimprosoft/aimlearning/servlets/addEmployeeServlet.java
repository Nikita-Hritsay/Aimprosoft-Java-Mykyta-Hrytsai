package com.aimprosoft.aimlearning.servlets;

import com.aimprosoft.aimlearning.DAO.EmployeeDAOImpl;
import com.aimprosoft.aimlearning.model.Employee;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "addEmployeeServlet", value = "/addEmployeeServlet")
public class addEmployeeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/aimlearning_war_exploded/addEmployee.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            if(!request.getParameter("firstName").equals("") && !request.getParameter("lastName").equals("") && !request.getParameter("salary").equals("") &&
                    Integer.parseInt(request.getParameter("salary")) > 0 &&
                    !request.getParameter("email").equals("") &&
                    !request.getParameter("hireDate").equals("")){
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = null;
                    try {
                        date = simpleDateFormat.parse(request.getParameter("hireDate"));
                        Employee employee = new Employee(request.getParameter("firstName"),
                                request.getParameter("lastName"),
                                request.getParameter("email"),
                                Integer.parseInt(request.getParameter("salary")),
                                date,
                                Integer.parseInt(request.getParameter("iddepartment")));
                        new EmployeeDAOImpl().add(employee);
                        response.sendRedirect("http://localhost:8080/aimlearning_war_exploded/");
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            else {
                request.setAttribute("wrong", true);
                request.setAttribute("firstName", request.getParameter("firstName"));
                request.setAttribute("lastName",  request.getParameter("lastName"));
                request.setAttribute("email",  request.getParameter("email"));
                request.setAttribute("salary",  request.getParameter("salary"));
                request.setAttribute("hireDate",  request.getParameter("hireDate"));
                request.setAttribute("iddepartment",  request.getParameter("iddepartment"));
                request.getRequestDispatcher("/addEmployee.jsp").forward(request, response);
            }
        }
}



