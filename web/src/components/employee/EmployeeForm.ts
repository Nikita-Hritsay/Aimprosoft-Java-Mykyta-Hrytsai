import { Component } from "../Component";
import "../main.css";
import { Employee } from "../../models/Employee";
import { EmployeeService } from "../../service/EmployeeService";
import { Formatter } from "../../utils/Formatter";
import {Constants} from "../../utils/Constants";
import { DepartmentService } from "../../service/DepartmentService";
import { EmployeeValidation } from "../../validation/employee/EmployeeValidation";

export class EmployeeForm implements Component{

    employeeService = new EmployeeService();
    departmentService = new DepartmentService();
    employeeValidation = new EmployeeValidation();

        render(employee: any, department: any, param: number){
    
            const main = $(Constants.main);
            main.empty();
            const formDiv = $("<div />").addClass("createOrUpdateForm");
            const formForm = $("<form />", 
                {   id: "createOrUpdateEmployeeForm"}).addClass("createOrUpdateEmployeeForm");

            formForm.append("<div />").addClass("createOrUpdateForm");

            formForm.append($("<input />", {name: "id", type: "hidden", value: employee.id, id: "id", class: "id"}))

            formForm.append($("<p />", 
                {   text: "Enter your first name"}));

            formForm.append($("<input />", 
                {   value: employee.firstName,
                    name:"firstName",
                    type: "text", 
                    class: "input_param firstName", 
                    id: "firstName"}));

            formForm.append($("<p />", 
                {   text: "Enter your last name"}));

            formForm.append($("<input />", 
                {   value: employee.lastName, 
                    name: "lastName", 
                    type: "text", 
                    class: "input_param lastName", 
                    id: "lastName"}));

            formForm.append($("<p />", 
                {   text: "Enter your email"}));

            formForm.append($("<input />", 
                {   value:  employee.email, 
                    name:"email",
                    type: "text", 
                    class: "input_param email", 
                    id: "email"}));
                    
            formForm.append($("<p />", 
                {   text: "Enter your salary"}));

            formForm.append($("<input />", 
                {   value:  employee.salary, 
                    name: "salary", 
                    type: "number", 
                    class: "input_param salary", 
                    id: "salary",}));

            formForm.append($("<p />", 
                {   text: "Enter your hire date"}));

            formForm.append($("<input />", 
                {   value:  Formatter.getDate(employee.hireDate), 
                    name:"hireDate",
                    type: "date", 
                    class: "input_param hireDate", 
                    id: "hireDate"})); 

            formForm.append($("<p />", 
                {   text: "Enter your department name"}));

            formForm.append($("<input />", 
                {   value: employee.department == null ? param == null ? "" : param : employee.department.id, 
                    list:"idDepartments", 
                    name:"idDepartment", 
                    class:"input_param idDepartment",
                    id: "idDepartment",
                    size:"15px"}));

            const datalist = $("<datalist />", 
                {   id: "idDepartments"});

                department.forEach((department: any) => {
                datalist.append($("<option />", {text: department.name, value: department.id, }));
            });
                
            formForm.append(datalist);

            formForm.append($("<input />", 
                {   type:"submit", 
                    class: "submit_createOrUpdate idDepartment", 
                    value: "submit"}))

            formDiv.append(formForm);
            main.append(formDiv);

            const errorList = this.employeeValidation.validate("#createOrUpdateEmployeeForm");

            formForm.on("submit", (event)=>{
                event.preventDefault();
                if(errorList.errorList.length < 1){
                    const employee = new Employee();
                    const arr = formForm.serializeArray();
                    console.log("arr "  + arr)
                    employee.id = Number($("input[name=id]").val()) == 0 ? null : Number($("input[name=id]").val());
                    employee.firstName = String($("input[name=firstName]").val());
                    employee.lastName = String($("input[name=lastName]").val());
                    employee.email = String($("input[name=email]").val());
                    employee.salary = Number($("input[name=salary]").val());
                    employee.hireDate = new Date(String($("input[name=hireDate]").val()));
                    employee.department.id = Number($("input[name=idDepartment]").val());
                    console.log("employee in render " + employee);
                    this.employeeService.saveOrUpdateEmployee(employee).done(()=>{
                        location.hash = "#department/" + employee.department.id + "/employee";
                    });
                }
            });
        
    }
}