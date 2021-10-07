package com.aimprosoft.aimlearning.servlets;

import com.aimprosoft.aimlearning.DAO.DepartmentDAOImpl;
import com.aimprosoft.aimlearning.model.Department;
import com.aimprosoft.aimlearning.model.Employee;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import net.sf.oval.context.OValContext;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "DepartmentServlet", value = "/DepartmentServlet")
public class DepartmentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "" : request.getParameter("action");
        switch (action){
            case "delete":
                new DepartmentDAOImpl().deleteDepartment(Integer.parseInt(request.getParameter("id")));
                response.sendRedirect("/aimlearning_war_exploded/");
                break;
            case "update":
                List<Department> departments = new ArrayList<>();
                departments.add(new DepartmentDAOImpl().findDepartmentById(Integer.parseInt(request.getParameter("id"))));
                request.setAttribute("departments", departments);
                request.getRequestDispatcher("updateDepartment.jsp").forward(request, response);
                break;
            default:
                List<Employee> employees = new DepartmentDAOImpl().getById(Integer.parseInt(request.getParameter("id")));
                request.setAttribute("employees", employees);
                request.setAttribute("department", new DepartmentDAOImpl().findDepartmentById(Integer.parseInt(request.getParameter("id"))));
                request.getRequestDispatcher("/departmentByid.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Department department = new Department();
        department.setIdDepartment(Integer.parseInt(request.getParameter("id")));
        department.setName(request.getParameter("name"));
        department.setAddress(request.getParameter("address"));

        Validator validator = new Validator();
        List<ConstraintViolation> violations = validator.validate(department);
        Map<String, String> errors = new HashMap<>();

        for(ConstraintViolation obj: violations){
            String fieldName = null;
            for (OValContext node : obj.getContextPath()) {
                fieldName = node.toStringUnqualified();
            }
            errors.put(fieldName, obj.getMessage());
        }

        if(!errors.isEmpty()){
            request.setAttribute("errors", errors);

            List<Department> departments = new ArrayList<>();
            departments.add(department);
            request.setAttribute("departments", departments);
            request.getRequestDispatcher("updateDepartment.jsp").forward(request, response);
        }else{
            new DepartmentDAOImpl().updateDepartment(department);
            response.sendRedirect("http://localhost:8080/aimlearning_war_exploded/");
        }

    }
}
