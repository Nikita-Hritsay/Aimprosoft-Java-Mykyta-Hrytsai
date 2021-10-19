package com.aimprosoft.aimlearning.commands.employee;

import com.aimprosoft.aimlearning.DAO.Impl.DepartmentDAOImpl;
import com.aimprosoft.aimlearning.DAO.Impl.EmployeeDAOImpl;
import com.aimprosoft.aimlearning.commands.ICommand;
import com.aimprosoft.aimlearning.exceptions.ValidationException;
import com.aimprosoft.aimlearning.models.Employee;
import com.aimprosoft.aimlearning.utils.NumberUtils;

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
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Employee employee = getEmployee(request);
        try {
            employeeDAO.createOrUpdate(employee);
            request.getRequestDispatcher("/WEB-INF/pages/displayAllDepartments").forward(request, response);
        } catch (ValidationException exception) {
            request.setAttribute("errors", exception.getErrors());
            request.setAttribute("employee", employee);
            request.setAttribute("idDepartment", request.getParameter("iddepartment"));
            request.setAttribute("departments", departmentDAO.getAllDepartments());
            request.getRequestDispatcher("/WEB-INF/pages/createOrUpdateEmployee.jsp").forward(request, response);
        }
    }

    private Employee getEmployee(HttpServletRequest request) {
        try {
            Employee employee = new Employee()
                    .withId(NumberUtils.getInt(request.getParameter("id")))
                    .withFirstName(request.getParameter("firstName"))
                    .withLastName(request.getParameter("lastName"))
                    .withEmail(request.getParameter("email"))
                    .withSalary(request.getParameter("salary") == "" ? 0 : NumberUtils.getDouble(request.getParameter("salary")))
                    .withHireDate(request.getParameter("hireDate") == "" ? null : new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("hireDate")))
                    .withIdDepartment(request.getParameter("iddepartment") == "" ? 0 : NumberUtils.getInt(request.getParameter("iddepartment")));
            return employee;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
