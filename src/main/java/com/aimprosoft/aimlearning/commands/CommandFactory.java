package com.aimprosoft.aimlearning.commands;

import com.aimprosoft.aimlearning.commands.department.CreateOrUpdateDepartmentCommand;
import com.aimprosoft.aimlearning.commands.department.DeleteDepartmentCommand;
import com.aimprosoft.aimlearning.commands.department.DisplayAllDepartmentCommand;
import com.aimprosoft.aimlearning.commands.department.FormCreateUpdateDepartmentCommand;
import com.aimprosoft.aimlearning.commands.employee.*;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CommandFactory {

    private Map<String, ICommand> commands;
    private DisplayAllDepartmentCommand displayAllDepartmentCommand;

    @Autowired
    public void setCommands(DisplayEmployeeCommand displayEmployeeCommand,
                                  DisplayAllDepartmentCommand displayAllDepartmentCommand,
                                  EmployeesByDepartmentCommand employeesByDepartmentCommand,
                                  CreateUpdateEmployeeCommand createUpdateEmployeeCommand,
                                  FormCreateOrUpdateEmployeeCommand formCreateOrUpdateEmployeeCommand,
                                  FormCreateUpdateDepartmentCommand formCreateUpdateDepartmentCommand,
                                  CreateOrUpdateDepartmentCommand createOrUpdateDepartmentCommand,
                                  DeleteDepartmentCommand deleteDepartmentCommand,
                                  DeleteEmployeeCommand deleteEmployeeCommand) {
        commands = new HashMap<>();
        commands.put("/displayEmployees", displayEmployeeCommand);
        commands.put("/displayAllDepartments", displayAllDepartmentCommand);
        commands.put("/employeesByDepartment", employeesByDepartmentCommand);
        commands.put("/createOrUpdateEmployee", createUpdateEmployeeCommand);
        commands.put("/createOrUpdateEmployeeForm", formCreateOrUpdateEmployeeCommand);
        commands.put("/createOrUpdateDepartmentForm", formCreateUpdateDepartmentCommand);
        commands.put("/createOrUpdateDepartment", createOrUpdateDepartmentCommand);
        commands.put("/deleteDepartment", deleteDepartmentCommand);
        commands.put("/deleteEmployee", deleteEmployeeCommand);
    }

    @Autowired
    public void setDisplayAllDepartmentCommand(DisplayAllDepartmentCommand displayAllDepartmentCommand) {
        this.displayAllDepartmentCommand = displayAllDepartmentCommand;
    }

    public ICommand getCommand(String operation) {
        return commands.getOrDefault(operation, displayAllDepartmentCommand);
    }
}
