package com.aimprosoft.aimlearning.servlets;

import com.aimprosoft.aimlearning.DAO.DepartmentDAOImpl;
import com.aimprosoft.aimlearning.DAO.EmployeeDAOImpl;
import com.aimprosoft.aimlearning.model.Department;
import com.aimprosoft.aimlearning.model.Employee;
import com.aimprosoft.aimlearning.validation.employee.isUniqueEmail;
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
        /*
        if(!request.getParameter("firstName").equals("") && !request.getParameter("lastName").equals("") && !request.getParameter("salary").equals("") &&
                    Integer.parseInt(request.getParameter("salary")) > 0 &&
                    !request.getParameter("email").equals("") &&
                    !request.getParameter("hireDate").equals("")){
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = null;

                try {
                    Employee employee = new Employee("", "", "", 1, simpleDateFormat.parse("0000-00-00"), 1);
                    Validator validator = new Validator();
                    List<ConstraintViolation> violations = validator.validate(employee);
                    if(violations.size() > 0){
                        System.out.println(violations);
                    }

                }
                catch (ParseException e) {
                    e.printStackTrace();
                }


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
            }*/
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



