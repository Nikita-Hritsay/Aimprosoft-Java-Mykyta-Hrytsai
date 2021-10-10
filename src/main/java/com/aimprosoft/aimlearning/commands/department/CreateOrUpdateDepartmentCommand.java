package com.aimprosoft.aimlearning.commands.department;

import com.aimprosoft.aimlearning.DAO.Impl.DepartmentDAOImpl;
import com.aimprosoft.aimlearning.commands.ICommand;
import com.aimprosoft.aimlearning.model.Department;
import com.aimprosoft.aimlearning.utils.GetErrors;
import com.aimprosoft.aimlearning.utils.GetInt;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import net.sf.oval.context.OValContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateOrUpdateDepartmentCommand implements ICommand {

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Department department = new Department();
        department.setIdDepartment(new GetInt().GetInt(req.getParameter("id")));
        department.setName(req.getParameter("name"));
        department.setAddress(req.getParameter("address"));
        Validator validator = new Validator();
        List<ConstraintViolation> violations = validator.validate(department);
        System.out.println(violations);
        if(!new GetErrors().getErrors(violations).isEmpty()){
            req.setAttribute("errors", new GetErrors().getErrors(violations));
            req.setAttribute("department", department);
            req.getRequestDispatcher("createOrUpdateDepartment.jsp").forward(req, resp);
        }else{
            new DepartmentDAOImpl().createOrUpdate(department);
            req.getRequestDispatcher("displayAllDepartments").forward(req, resp);
        }
    }
}
