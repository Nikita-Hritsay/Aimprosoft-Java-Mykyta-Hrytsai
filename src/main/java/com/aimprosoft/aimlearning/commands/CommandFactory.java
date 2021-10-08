package com.aimprosoft.aimlearning.commands;

import com.aimprosoft.aimlearning.commands.department.CreateOrUpdateDepartmentCommand;
import com.aimprosoft.aimlearning.commands.department.DeleteDepartmentCommand;
import com.aimprosoft.aimlearning.commands.department.DisplayAllDepartmentCommand;
import com.aimprosoft.aimlearning.commands.department.FormCreateUpdateDepartmentCommand;
import com.aimprosoft.aimlearning.commands.employee.DisplayEmployeeCommand;
import com.aimprosoft.aimlearning.commands.employee.EmployeesByDepartmentCommand;
import com.aimprosoft.aimlearning.commands.employee.FormCreateUpdateEmployeeCommand;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {

    private final Map<String, ICommand> commands;
    {
        commands = new HashMap<>();
        commands.put("/displayEmployees", new DisplayEmployeeCommand());
        commands.put("/displayAllDepartments", new DisplayAllDepartmentCommand());
        commands.put("/employeesByDepartment", new EmployeesByDepartmentCommand());
        commands.put("/createOrUpdateEmployee", new FormCreateUpdateEmployeeCommand());
        commands.put("/createOrUpdateDepartmentForm", new FormCreateUpdateDepartmentCommand());
        commands.put("/createOrUpdateDepartment", new CreateOrUpdateDepartmentCommand());
        commands.put("/deleteDepartment", new DeleteDepartmentCommand());
    }

    public ICommand getCommand(String operation) {
        return commands.getOrDefault(operation, new DisplayEmployeeCommand());
    }

}
