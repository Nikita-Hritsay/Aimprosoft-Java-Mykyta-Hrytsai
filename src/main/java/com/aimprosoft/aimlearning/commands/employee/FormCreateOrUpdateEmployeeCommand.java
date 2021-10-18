package com.aimprosoft.aimlearning.commands.employee;

import com.aimprosoft.aimlearning.DAO.Impl.DepartmentDAOImpl;
import com.aimprosoft.aimlearning.DAO.Impl.EmployeeDAOImpl;
import com.aimprosoft.aimlearning.commands.ICommand;
import com.aimprosoft.aimlearning.models.Employee;
import com.aimprosoft.aimlearning.utils.GetInt;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FormCreateOrUpdateEmployeeCommand implements ICommand {

    private final EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
    private final DepartmentDAOImpl departmentDAO = new DepartmentDAOImpl();

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("employee", getEmployee(req));
        req.setAttribute("idDepartment", getEmployee(req).getIdDepartment());
        req.setAttribute("departments", departmentDAO.getAllDepartments());
        req.getRequestDispatcher("createOrUpdateEmployee.jsp").forward(req, resp);
    }

    private Employee getEmployee(HttpServletRequest request) {
        if (request.getParameter("id") != null) {
            return employeeDAO.getById(GetInt.getInt(request.getParameter("id")));
        }
        return new Employee();
    }

}
