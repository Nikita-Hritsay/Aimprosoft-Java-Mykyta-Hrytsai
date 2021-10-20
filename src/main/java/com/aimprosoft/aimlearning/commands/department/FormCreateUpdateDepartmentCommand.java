package com.aimprosoft.aimlearning.commands.department;

import com.aimprosoft.aimlearning.commands.ICommand;
import com.aimprosoft.aimlearning.exceptions.DBException;
import com.aimprosoft.aimlearning.models.Department;
import com.aimprosoft.aimlearning.services.Impl.DepartmentServiceImpl;
import com.aimprosoft.aimlearning.utils.NumberUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FormCreateUpdateDepartmentCommand implements ICommand {

    private final DepartmentServiceImpl departmentService = new DepartmentServiceImpl();

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DBException {
        request.setAttribute("department", getDepartment(request));
        request.setAttribute("idDepartment", request.getParameter("id"));
        request.getRequestDispatcher("/WEB-INF/pages/createOrUpdateDepartment.jsp").forward(request, response);
    }

    private Department getDepartment(HttpServletRequest request) throws DBException {
        if (request.getParameter("id") != null) {
            return departmentService.getDepartmentById(NumberUtils.getInt(request.getParameter("id")));
        }
        return new Department()
                .withName(request.getParameter("name"))
                .withAddress(request.getParameter("address"));
    }

}
