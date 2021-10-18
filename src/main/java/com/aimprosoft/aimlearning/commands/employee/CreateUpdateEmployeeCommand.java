package com.aimprosoft.aimlearning.commands.employee;

import com.aimprosoft.aimlearning.DAO.Impl.DepartmentDAOImpl;
import com.aimprosoft.aimlearning.DAO.Impl.EmployeeDAOImpl;
import com.aimprosoft.aimlearning.commands.ICommand;
import com.aimprosoft.aimlearning.exceptions.ValidationException;
import com.aimprosoft.aimlearning.models.Employee;
import com.aimprosoft.aimlearning.utils.GetInt;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class CreateUpdateEmployeeCommand implements ICommand {

    private final EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
    private final DepartmentDAOImpl departmentDAO = new DepartmentDAOImpl();

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Employee employee = getEmployee(req);
        try {
            employeeDAO.createOrUpdate(employee);
            req.getRequestDispatcher("displayAllDepartments").forward(req, resp);
        } catch (ValidationException exception) {
            req.setAttribute("errors", exception.getErrors());
            req.setAttribute("employee", employee);
            req.setAttribute("idDepartment", req.getParameter("iddepartment"));
            req.setAttribute("departments", departmentDAO.getAllDepartments());
            req.getRequestDispatcher("createOrUpdateEmployee.jsp").forward(req, resp);
        }
    }

    private Employee getEmployee(HttpServletRequest req) {
        try {
            Employee employee = new Employee()
                    .withId(GetInt.getInt(req.getParameter("id")))
                    .withFirstName(req.getParameter("firstName"))
                    .withLastName(req.getParameter("lastName"))
                    .withEmail(req.getParameter("email"))
                    .withSalary(req.getParameter("salary") == "" ? 0 : GetInt.getInt(req.getParameter("salary")))
                    .withHireDate(req.getParameter("hireDate") == "" ? null : new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("hireDate")))
                    .withIdDepartment(req.getParameter("iddepartment") == "" ? 0 : GetInt.getInt(req.getParameter("iddepartment")));
            return employee;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
