package com.aimprosoft.aimlearning.commands;

import com.aimprosoft.aimlearning.commands.department.CreateOrUpdateDepartmentCommand;
import com.aimprosoft.aimlearning.commands.department.DeleteDepartmentCommand;
import com.aimprosoft.aimlearning.commands.department.DisplayAllDepartmentCommand;
import com.aimprosoft.aimlearning.commands.department.FormCreateUpdateDepartmentCommand;
import com.aimprosoft.aimlearning.commands.employee.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CommandFactory {

    private final Map<String, ICommand> commands;

    {
        commands = new HashMap<>();
        commands.put("/displayEmployees", new DisplayEmployeeCommand());
        commands.put("/displayAllDepartments", new DisplayAllDepartmentCommand());
        commands.put("/employeesByDepartment", new EmployeesByDepartmentCommand());
        commands.put("/createOrUpdateEmployee", new CreateUpdateEmployeeCommand());
        commands.put("/createOrUpdateEmployeeForm", new FormCreateOrUpdateEmployeeCommand());
        commands.put("/createOrUpdateDepartmentForm", new FormCreateUpdateDepartmentCommand());
        commands.put("/createOrUpdateDepartment", new CreateOrUpdateDepartmentCommand());
        commands.put("/deleteDepartment", new DeleteDepartmentCommand());
        commands.put("/deleteEmployee", new DeleteEmployeeCommand());
    }

    public ICommand getCommand(String operation) {
        return commands.getOrDefault(operation, new DisplayAllDepartmentCommand());
    }

}
