package com.aimprosoft.aimlearning.commands.department;

import com.aimprosoft.aimlearning.DAO.Impl.DepartmentDAOImpl;
import com.aimprosoft.aimlearning.DAO.Impl.EmployeeDAOImpl;
import com.aimprosoft.aimlearning.commands.ICommand;
import com.aimprosoft.aimlearning.model.Department;
import com.aimprosoft.aimlearning.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FormCreateUpdateDepartmentCommand implements ICommand {

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("department", getDepartment(req));
        req.getRequestDispatcher("createOrUpdateDepartment.jsp").forward(req, resp);
    }

    private Department getDepartment(HttpServletRequest request) {
        if(request.getParameter("id") != null){
            return new DepartmentDAOImpl().findDepartmentById(Integer.parseInt(request.getParameter("id")));
        }
        return new Department(request.getParameter("name"), request.getParameter("address"));
    }

}
