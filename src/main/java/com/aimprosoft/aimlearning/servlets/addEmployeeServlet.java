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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "addEmployeeServlet", value = "/addEmployeeServlet")
public class addEmployeeServlet extends HttpServlet {
    private OValContext v;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Department> departments = new DepartmentDAOImpl().getAllDepartments();
        request.setAttribute("departments", departments);
        if(request.getParameter("idDepartment") != null){
            request.setAttribute("idDepartment", request.getParameter("idDepartment"));
        }
        request.getRequestDispatcher("/addEmployee.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Employee employee = new Employee();

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
                request.setAttribute("employee", employee);

                request.setAttribute("errors", errors);
                request.getRequestDispatcher("/addEmployee.jsp").forward(request, response);;
            }else{
                new EmployeeDAOImpl().add(employee);
                response.sendRedirect("http://localhost:8080/aimlearning_war_exploded/");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}



