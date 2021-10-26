package com.aimprosoft.aimlearning.commands.employee;

import com.aimprosoft.aimlearning.commands.ICommand;
import com.aimprosoft.aimlearning.exceptions.DBException;
import com.aimprosoft.aimlearning.models.Department;
import com.aimprosoft.aimlearning.models.Employee;
import com.aimprosoft.aimlearning.services.Impl.DepartmentServiceImpl;
import com.aimprosoft.aimlearning.services.Impl.EmployeeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DisplayEmployeeCommand implements ICommand {

    private final EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
    private final DepartmentServiceImpl departmentService = new DepartmentServiceImpl();

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DBException {
        request.setAttribute("employees", employeeService.getAllEmployees());
        request.setAttribute("departmentService", departmentService);
        request.setAttribute("departmentEmployeeMap", createEmployeeDepartmentMap());
        request.getRequestDispatcher("/WEB-INF/pages/allEmployees.jsp").forward(request, response);
    }

    private Map<Department, List<Employee>> createEmployeeDepartmentMap() throws DBException {
        Map<Department, List<Employee>> result = new HashMap<>();
        for(Department department: departmentService.getAllDepartments()){
            result.put(department, employeeService.getByIdDepartment(department.getIdDepartment()));
        }

        for (int i = 0; i < result.size(); i++){
            System.out.println(result.keySet());
        }

        return result;
    }

}
