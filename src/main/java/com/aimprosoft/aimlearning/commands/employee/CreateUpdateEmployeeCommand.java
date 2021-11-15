package com.aimprosoft.aimlearning.commands.employee;

import com.aimprosoft.aimlearning.commands.ICommand;
import com.aimprosoft.aimlearning.exceptions.DBException;
import com.aimprosoft.aimlearning.exceptions.ValidationException;
import com.aimprosoft.aimlearning.models.Department;
import com.aimprosoft.aimlearning.models.Employee;
import com.aimprosoft.aimlearning.services.Impl.DepartmentServiceImpl;
import com.aimprosoft.aimlearning.services.Impl.EmployeeServiceImpl;
import com.aimprosoft.aimlearning.utils.NumberUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CreateUpdateEmployeeCommand implements ICommand {

    private final EmployeeServiceImpl employeeService;
    private final DepartmentServiceImpl departmentService;

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DBException {
        Employee employee = null;
        try {
            employee = getEmployee(request);
            employeeService.createOrUpdate(employee);
            response.sendRedirect("/displayAllDepartments");
        } catch (ValidationException exception) {
            request.setAttribute("errors", exception.getErrors());
            request.setAttribute("employee", employee);
            request.setAttribute("idDepartment", request.getParameter("iddepartment"));
            request.setAttribute("departments", departmentService.getAllDepartments());
            request.getRequestDispatcher("/WEB-INF/pages/createOrUpdateEmployee.jsp").forward(request, response);
        }
    }

    private Department getDepartment(HttpServletRequest request) throws DBException {
        try {
            return departmentService.getDepartmentById(NumberUtils.getInt(request.getParameter("idDepartment")));
        } catch (DBException e) {
            throw new DBException(e.getMessage());
        }
    }

    private Employee getEmployee(HttpServletRequest request) throws DBException {
        try {
            return new Employee()
                    .withId(NumberUtils.getInt(request.getParameter("id")))
                    .withFirstName(request.getParameter("firstName"))
                    .withLastName(request.getParameter("lastName"))
                    .withEmail(request.getParameter("email"))
                    .withSalary(request.getParameter("salary").isEmpty() ? new BigDecimal("0") : NumberUtils.getBigDecimal(request.getParameter("salary")))
                    .withHireDate(request.getParameter("hireDate").isEmpty() ? null : new Date(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("hireDate")).getTime()))
                    .withDepartment(getDepartment(request));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
