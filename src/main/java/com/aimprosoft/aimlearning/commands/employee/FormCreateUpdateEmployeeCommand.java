package com.aimprosoft.aimlearning.commands.employee;

import com.aimprosoft.aimlearning.DAO.Impl.EmployeeDAOImpl;
import com.aimprosoft.aimlearning.commands.ICommand;
import com.aimprosoft.aimlearning.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FormCreateUpdateEmployeeCommand implements ICommand {

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("employee", getEmployee(req));
        req.getRequestDispatcher("createOrUpdateEmployee.jsp").forward(req, resp);
    }

    private Employee getEmployee(HttpServletRequest request) {
        if(request.getParameter("id") != null){
            return new EmployeeDAOImpl().getById(Integer.parseInt(request.getParameter("id")));
        }
        return new Employee();
    }


}
