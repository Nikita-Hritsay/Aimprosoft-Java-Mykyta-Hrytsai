package com.aimprosoft.aimlearning.commands.employee;

import com.aimprosoft.aimlearning.commands.ICommand;
import com.aimprosoft.aimlearning.exceptions.DBException;
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
        request.setAttribute("departmentEmployeeMap", createEmployeeDepartmentMap());
        createEmployeeDepartmentMap();
        request.getRequestDispatcher("/WEB-INF/pages/allEmployees.jsp").forward(request, response);
    }

    private Map<Integer, String> createEmployeeDepartmentMap() throws DBException {
        Map<Integer, String> result = new HashMap<>();
        List<Integer> ids = employeeService.getAllEmployeesIds();
        List<String> names = departmentService.getDepartmentNameByEmployeeId(ids);
        for(int i = 0 ; i < ids.size(); i ++) {
            result.put(ids.get(i), names.get(i));
        }
        return result;
    }

}
