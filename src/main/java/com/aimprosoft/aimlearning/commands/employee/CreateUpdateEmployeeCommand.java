package com.aimprosoft.aimlearning.commands.employee;

import com.aimprosoft.aimlearning.DAO.Impl.DepartmentDAOImpl;
import com.aimprosoft.aimlearning.DAO.Impl.EmployeeDAOImpl;
import com.aimprosoft.aimlearning.commands.ICommand;
import com.aimprosoft.aimlearning.model.Employee;
import com.aimprosoft.aimlearning.utils.Utils;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

public class CreateUpdateEmployeeCommand implements ICommand {

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Employee employee = getEmployee(req);
        Validator validator = new Validator();
        List<ConstraintViolation> violations = validator.validate(employee);

        if(!Utils.getErrors(violations).isEmpty()){
            req.setAttribute("errors", Utils.getErrors(violations));
            req.setAttribute("employee", employee);
            req.setAttribute("idDepartment", req.getParameter("iddepartment"));
            req.setAttribute("departments", new DepartmentDAOImpl().getAllDepartments());
            req.getRequestDispatcher("createOrUpdateEmployee.jsp").forward(req, resp);
        }else{
            new EmployeeDAOImpl().createOrUpdate(employee);
            req.getRequestDispatcher("displayAllDepartments").forward(req, resp);
        }
    }

    private Employee getEmployee(HttpServletRequest req){
        try {
            Employee employee = new Employee();
            employee.setFirstName(req.getParameter("firstName"));
            employee.setLastName(req.getParameter("lastName"));
            employee.setEmail(req.getParameter("email"));
            employee.setSalary(req.getParameter("salary") == "" ? 0 : Utils.GetInt(req.getParameter("salary")));
            employee.setHireDate(req.getParameter("hireDate") == "" ? null : new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("hireDate")));
            employee.setIdDepartment(req.getParameter("iddepartment") == "" ? 0 : Utils.GetInt(req.getParameter("iddepartment")));
            return employee;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
