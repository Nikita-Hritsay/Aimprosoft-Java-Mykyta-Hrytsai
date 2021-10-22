package com.aimprosoft.aimlearning.commands.employee;

import com.aimprosoft.aimlearning.commands.ICommand;
import com.aimprosoft.aimlearning.exceptions.DBException;
import com.aimprosoft.aimlearning.exceptions.ValidationException;
import com.aimprosoft.aimlearning.models.Employee;
import com.aimprosoft.aimlearning.services.Impl.DepartmentServiceImpl;
import com.aimprosoft.aimlearning.services.Impl.EmployeeServiceImpl;
import com.aimprosoft.aimlearning.utils.NumberUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class CreateUpdateEmployeeCommand implements ICommand {

    private final EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
    private final DepartmentServiceImpl departmentService = new DepartmentServiceImpl();

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DBException {
        Employee employee = getEmployee(request);
        try {
            employeeService.createOrUpdate(employee);
            response.sendRedirect("/WEB-INF/pages/displayAllDepartments");
        } catch (ValidationException exception) {
            request.setAttribute("errors", exception.getErrors());
            request.setAttribute("employee", employee);
            request.setAttribute("idDepartment", request.getParameter("iddepartment"));
            request.setAttribute("departments", departmentService.getAllDepartments());
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
