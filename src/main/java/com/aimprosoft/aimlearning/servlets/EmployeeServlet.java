package com.aimprosoft.aimlearning.servlets;

import com.aimprosoft.aimlearning.DAO.DepartmentDAOImpl;
import com.aimprosoft.aimlearning.DAO.EmployeeDAOImpl;
import com.aimprosoft.aimlearning.model.Department;
import com.aimprosoft.aimlearning.model.Employee;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import net.sf.oval.context.OValContext;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
        try {
            Employee employee = new Employee();

            employee.setId(Integer.parseInt(request.getParameter("id")));
            employee.setFirstName(request.getParameter("firstName"));
            employee.setLastName(request.getParameter("lastName"));
            employee.setEmail(request.getParameter("email"));
            employee.setSalary(request.getParameter("salary") == "" ? 0 : Integer.parseInt(request.getParameter("salary")));
            employee.setHireDate(request.getParameter("hireDate") == "" ? new Date(2000,1,1) : new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("hireDate")));
            employee.setIdDepartment(request.getParameter("iddepartment") == "" ? 0 : Integer.parseInt(request.getParameter("iddepartment")));

            Validator validator = new Validator();
            List<ConstraintViolation> violations = validator.validate(employee);
            Map<String, String> errors = new HashMap<>();

            for(ConstraintViolation obj: violations){
                String fieldName = null;
                for (OValContext node : obj.getContextPath()) {
                    fieldName = node.toStringUnqualified();
                }
                errors.put(fieldName, obj.getMessage());
            }

            if(!violations.isEmpty()){
                List<Department> departments = new DepartmentDAOImpl().getAllDepartments();
                request.setAttribute("departments", departments);
                if(request.getParameter("idDepartment") != null){
                    request.setAttribute("departments", request.getParameter("idDepartment"));
                }
                List<Employee> employeesUpdate = new ArrayList<>();
                employeesUpdate.add(employee);
                request.setAttribute("employees", employeesUpdate);
                request.setAttribute("errors", errors);
                request.getRequestDispatcher("/updateEmployee.jsp").forward(request, response);;
            }else{
                new EmployeeDAOImpl().updateEmployee(employee);
                response.sendRedirect("http://localhost:8080/aimlearning_war_exploded/");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }
}
