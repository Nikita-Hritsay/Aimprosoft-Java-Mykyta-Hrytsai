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
                        {   id: "createOrUpdateEmployeeForm"}).addClass("createOrUpdateEmployeeForm");
    
                    formForm.append("<div />").addClass("createOrUpdateForm");
    
                    formForm.append($("<input />", {name: "id", type: "hidden", value: data.id, id: "id", class: "id"}))

                    formForm.append($("<p />", 
                        {   text: "Enter your first name"}));
    
                    formForm.append($("<input />", 
                        {   value: data.firstName,
                            name:"firstName",
                            type: "text", 
                            class: "input_param firstName", 
                            id: "firstName"}));
    
                    formForm.append($("<p />", 
                        {   text: "Enter your last name"}));
    
                    formForm.append($("<input />", 
                        {   value: data.lastName, 
                            name: "lastName", 
                            type: "text", 
                            class: "input_param lastName", 
                            id: "lastName"}));
    
                    formForm.append($("<p />", 
                        {   text: "Enter your email"}));
    
                    formForm.append($("<input />", 
                        {   value:  data.email, 
                            name:"email",
                            type: "text", 
                            class: "input_param email", 
                            id: "email"}));
                            
                    formForm.append($("<p />", 
                        {   text: "Enter your salary"}));
    
                    formForm.append($("<input />", 
                        {   value:  data.salary, 
                            name: "salary", 
                            type: "number", 
                            class: "input_param salary", 
                            id: "salary",}));
    
                    formForm.append($("<p />", 
                        {   text: "Enter your hire date"}));
    
                    formForm.append($("<input />", 
                        {   value:  Formatter.getDate(data.hireDate), 
                            name:"hireDate",
                            type: "date", 
                            class: "input_param hireDate", 
                            id: "hireDate"})); 
    
                    formForm.append($("<p />", 
                        {   text: "Enter your department name"}));
    
                    formForm.append($("<input />", 
                        {   value: data.department == null ? idDepartment == null ? "" : idDepartment : data.department.id, 
                            list:"idDepartments", 
                            name:"idDepartment", 
                            class:"input_param idDepartment",
                            id: "idDepartment",
                            size:"15px"}));
    
                    const datalist = $("<datalist />", 
                        {   id: "idDepartment"});

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
    
                    let errorList = this.employeeValidation.validate("#createOrUpdateEmployeeForm");

                    formForm.submit((event)=>{
                        event.preventDefault();
                        if(errorList.errorList.length < 1){
                            let employee = new Employee();
                            let arr = formForm.serializeArray();
                            employee.id = data.id == 0 ? null : data.id;
                            employee.firstName = arr[1].value;
                            employee.lastName = arr[2].value;
                            employee.email = arr[3].value;
                            employee.salary = Number(arr[4].value);
                            employee.hireDate = new Date(arr[5].value);
                            employee.department.id = Number(arr[6].value);
                            this.employeeService.saveOrUpdateEmployee(employee).done(()=>{
                                location.hash = "#department/" + employee.department.id + "/employee";
                            });
                        }
                    });
                })
            })
    }
}