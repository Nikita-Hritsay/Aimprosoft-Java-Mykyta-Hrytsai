package com.aimprosoft.aimlearning.commands.department;

import com.aimprosoft.aimlearning.DAO.Impl.DepartmentDAOImpl;
import com.aimprosoft.aimlearning.commands.ICommand;
import com.aimprosoft.aimlearning.model.Department;
import com.aimprosoft.aimlearning.utils.Utils;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class CreateOrUpdateDepartmentCommand implements ICommand {

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Department department = getDepartment(req);
        Validator validator = new Validator();
        List<ConstraintViolation> violations = validator.validate(department);
        if(!Utils.getErrors(violations).isEmpty()){
            req.setAttribute("errors", Utils.getErrors(violations));
            req.setAttribute("department", department);
            req.getRequestDispatcher("createOrUpdateDepartment.jsp").forward(req, resp);
        }else{
            new DepartmentDAOImpl().createOrUpdate(department);
            req.getRequestDispatcher("displayAllDepartments").forward(req, resp);
        }
    }

    private Department getDepartment(HttpServletRequest request){
        Department department = new Department();
        department.setIdDepartment( Utils.GetInt(request.getParameter("id")));
        department.setName(request.getParameter("name"));
        department.setAddress(request.getParameter("address"));
        return department;
    }
}
