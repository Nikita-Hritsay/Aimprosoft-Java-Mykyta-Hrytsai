package com.aimprosoft.aimlearning.servlets;

import com.aimprosoft.aimlearning.DAO.DepartmentDAOImpl;
import com.aimprosoft.aimlearning.model.Department;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import net.sf.oval.context.OValContext;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "addDepartmentServlet", value = "/addDepartmentServlet")
public class addDepartmentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("addDepartment.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Department department = new Department();
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
            request.setAttribute("department", department);
            request.getRequestDispatcher("/addDepartment.jsp").forward(request, response);;
        }else{
            new DepartmentDAOImpl().addDepartment(department);
            response.sendRedirect("http://localhost:8080/aimlearning_war_exploded/");
        }
    }
}
