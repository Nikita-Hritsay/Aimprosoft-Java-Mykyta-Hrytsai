package com.aimprosoft.aimlearning.commands.department;

import com.aimprosoft.aimlearning.commands.ICommand;
import com.aimprosoft.aimlearning.exceptions.DBException;
import com.aimprosoft.aimlearning.services.Impl.DepartmentServiceImpl;
import com.aimprosoft.aimlearning.utils.NumberUtils;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class DeleteDepartmentCommand implements ICommand {

    private final DepartmentServiceImpl departmentService = new DepartmentServiceImpl();

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DBException {
        departmentService.deleteDepartment(NumberUtils.getInt(((request.getParameter("idDepartment")))));
        response.sendRedirect("displayAllDepartments");
    }
}
