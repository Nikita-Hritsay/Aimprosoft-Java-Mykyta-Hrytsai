import { Component } from "../Component";
import "../main.css";
import { Employee } from "../../models/Employee";
import { EmployeeService } from "../../service/EmployeeService";
import { Router } from "../../router/Router";
import { DepartmentService } from "../../service/DepartmentService";


export class EmployeeForm implements Component{
    departments = new DepartmentService();
        render(param: any){
            this.departments.getDepartment().done((data)=>{

            
            const main = $("#main");
            main.empty();
            const formDiv = $("<div />").addClass("createOrUpdateForm");
            const formForm = $("<form name=\"createOrUpdateEmployee\"/>").addClass("createOrUpdateEmployee");

            formForm.append("<div />").addClass("createOrUpdateForm");
            formForm.append("<p>Enter your first name</p>");
            formForm.append($("<input />", {value: param.firstName, name:"firstName",type: "text", class: "input_param", id: "firstName", maxlength: 75, required: true}));
            formForm.append("<p>Enter your last name</p>");
            formForm.append($("<input />", {value: param.lastName, name: "lastName", type: "text", class: "input_param", id: "lastName", required: true}));
            formForm.append("<p>Enter your email</p>");
            formForm.append($("<input />", {value: param.email, name:"email",type: "email", class: "input_param", id: "email", maxlength: 75, required: true}));
            formForm.append("<p>Enter your salary</p>");
            formForm.append($("<input />", {value: param.salary, name: "salary", type: "number", class: "input_param", id: "salary", required: true}));
            formForm.append("<p>Enter your hire date</p>");
            formForm.append($("<input />", {value: new Date(param.hireDate).getDate(), name:"hireDate",type: "date", class: "input_param", id: "hireDate", maxlength: 75, required: true}));
            formForm.append("<p>Enter your department name</p>");
            formForm.append($("<input />", {value: param.department.id, list:"idDepartments", name:"idDepartment", class:"input_param", size:"15px"}));
            const datalist = $("<datelist />", {id: "idDepartments"});
                
            console.log("data  " + data)
            for(let i = 0; i < data.length; i++){
                datalist.append($("<option />", {value: data[i].id}))
                console.log("data  in " + data[i].id)
            }
            console.log("data after " + data)
            
            formForm.append(datalist);

            formForm.append("<input type=\"submit\" class=\"submit_createOrUpdate\" value=\"submit\">")

            formDiv.append(formForm);
            main.append(formDiv);

            formForm.submit((event)=>{
                event.preventDefault();
                
                let employee = new Employee();
                let arr = formForm.serializeArray();
                employee.id = param.id;
                employee.firstName = arr[0].value;
                employee.lastName = arr[1].value;
                employee.email = arr[2].value;
                employee.salary = Number(arr[3].value);
                employee.hireDate = new Date(arr[4].value);
                console.log(employee);
                employee.department.id = Number(arr[5].value);
                console.log(employee);
                new EmployeeService().saveOrUpdateEmployee(employee);
                new Router().getUrl("#department").render("main");
            });

        })
         
    }
}