package com.aimprosoft.aimlearning.commands.department;

import com.aimprosoft.aimlearning.DAO.Impl.DepartmentDAOImpl;
import com.aimprosoft.aimlearning.commands.ICommand;
import com.aimprosoft.aimlearning.utils.GetInt;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteDepartmentCommand implements ICommand {

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        new DepartmentDAOImpl().deleteDepartment(new GetInt().GetInt((req.getParameter("idDepartment"))));
        System.out.println("deleting " + new GetInt().GetInt((req.getParameter("idDepartment"))));
        resp.sendRedirect("/displayAllDepartments");
    }
}
