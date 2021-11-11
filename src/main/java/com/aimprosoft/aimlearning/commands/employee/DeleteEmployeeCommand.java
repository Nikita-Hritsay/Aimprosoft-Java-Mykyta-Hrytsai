package com.aimprosoft.aimlearning.commands.employee;

import com.aimprosoft.aimlearning.commands.ICommand;
import com.aimprosoft.aimlearning.exceptions.DBException;
import com.aimprosoft.aimlearning.services.Impl.EmployeeServiceImpl;
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
public class DeleteEmployeeCommand implements ICommand {

    private final EmployeeServiceImpl employeeService;

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DBException {
        try {
            employeeService.deleteEmployee(NumberUtils.getInt(request.getParameter("id")));
            response.sendRedirect("displayAllDepartments");
        } catch (DBException e) {
            response.sendRedirect("displayAllDepartments");
        }
    }
}
