package com.aimprosoft.aimlearning.commands.department;

import com.aimprosoft.aimlearning.commands.ICommand;
import com.aimprosoft.aimlearning.exceptions.DBException;
import com.aimprosoft.aimlearning.services.Impl.DepartmentServiceImpl;
import com.aimprosoft.aimlearning.utils.NumberUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DeleteDepartmentCommand implements ICommand {

    private final DepartmentServiceImpl departmentService;

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DBException {
        try {
            departmentService.deleteDepartment(NumberUtils.getInt(request.getParameter("idDepartment")));
            response.sendRedirect("displayAllDepartments");
        } catch (DBException e) {
            response.sendRedirect("displayAllDepartments");
        }

    }
}
