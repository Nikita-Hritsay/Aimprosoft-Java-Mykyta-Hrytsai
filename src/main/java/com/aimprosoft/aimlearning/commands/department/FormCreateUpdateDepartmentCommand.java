package com.aimprosoft.aimlearning.commands.department;

import com.aimprosoft.aimlearning.DAO.Impl.DepartmentDAOImpl;
import com.aimprosoft.aimlearning.commands.ICommand;
import com.aimprosoft.aimlearning.models.Department;
import com.aimprosoft.aimlearning.utils.GetInt;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FormCreateUpdateDepartmentCommand implements ICommand {

    private final DepartmentDAOImpl departmentDAO = new DepartmentDAOImpl();

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("department", getDepartment(req));
        req.getRequestDispatcher("createOrUpdateDepartment.jsp").forward(req, resp);
    }

    private Department getDepartment(HttpServletRequest request) {
        if (request.getParameter("id") != null) {
            return departmentDAO.getDepartmentById(GetInt.getInt(request.getParameter("id")));
        }
        return new Department(request.getParameter("name"), request.getParameter("address"));
    }

}
