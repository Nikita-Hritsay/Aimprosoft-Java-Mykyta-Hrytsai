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

        render(param: any, idDepartment: number){
            this.departmentService.getDepartments().done((DepartmentList)=>{
                this.employeeService.getById(param).done((data)=>{
                    const main = $(Constants.main);
                    main.empty();
                    const formDiv = $("<div />").addClass("createOrUpdateForm");
                    const formForm = $("<form />", 
                        {   name: "#createOrUpdateEmploee", id: "createOrUpdateEmployee"}).addClass("createOrUpdateEmployee");
    
                    formForm.append("<div />").addClass("createOrUpdateForm");
    
                    formForm.append($("<p />", 
                        {   text: "Enter your first name"}));
    
                    formForm.append($("<input />", 
                        {   value: data.firstName,
                            name:"firstName",
                            type: "text", 
                            class: "input_param", 
                            id: "firstName"}));
    
                    formForm.append($("<p />", 
                        {   text: "Enter your last name"}));
    
                    formForm.append($("<input />", 
                        {   value: data.lastName, 
                            name: "lastName", 
                            type: "text", 
                            class: "input_param", 
                            id: "lastName"}));
    
                    formForm.append($("<p />", 
                        {   text: "Enter your email"}));
    
                    formForm.append($("<input />", 
                        {   value:  data.email, 
                            name:"email",
                            type: "email", 
                            class: "input_param", 
                            id: "email"}));
                            
                    formForm.append($("<p />", 
                        {   text: "Enter your salary"}));
    
                    formForm.append($("<input />", 
                        {   value:  data.salary, 
                            name: "salary", 
                            type: "number", 
                            class: "input_param", 
                            id: "salary",}));
    
                    formForm.append($("<p />", 
                        {   text: "Enter your hire date"}));
    
                    formForm.append($("<input />", 
                        {   value:  Formatter.getDate(data.hireDate), 
                            name:"hireDate",
                            type: "date", 
                            class: "input_param", 
                            id: "hireDate"}));
    
                    formForm.append($("<p />", 
                        {   text: "Enter your departmnet name"}));
    
                    formForm.append($("<input />", 
                        {   value: data.department == null ? idDepartment == null ? "" : idDepartment : data.department.id, 
                            list:"idDepartments", 
                            name:"idDepartment", 
                            class:"input_param", 
                            size:"15px"}));
    
                    const datalist = $("<datalist />", 
                        {   id: "idDepartments"});

                    DepartmentList.forEach((element: any) => {
                        datalist.append($("<option />", {text: element.name, value: element.id, }));
                    });
                        
                    formForm.append(datalist);
    
                    formForm.append($("<input />", 
                        {   type:"submit", 
                            class: "submit_createOrUpdate", 
                            value: "submit"}))
    
                    formDiv.append(formForm);
                    main.append(formDiv);
    
                    this.employeeValidation.validate("#createOrUpdateEmployee");

                    formForm.submit((event)=>{
                        event.preventDefault();
                        let employee = new Employee();
                        let arr = formForm.serializeArray();
                        employee.id = data.id == 0 ? null : data.id;
                        employee.firstName = arr[0].value;
                        employee.lastName = arr[1].value;
                        employee.email = arr[2].value;
                        employee.salary = Number(arr[3].value);
                        employee.hireDate = new Date(arr[4].value);
                        employee.department.id = Number(arr[5].value);
                        this.employeeService.saveOrUpdateEmployee(employee).done(()=>{
                            location.hash = "#department/" + employee.department.id + "/employee";
                        });
                    });
                })
            })
    }
}