import { Component } from "../Component";
import "../main.css";
import { Employee } from "../../models/Employee";
import { EmployeeService } from "../../service/EmployeeService";
import { Router } from "../../router/Router";


export class EmployeeForm implements Component{
    render(param: any){
        const main = $("#main");
        main.empty();
        const formDiv = $("<div />").addClass("createOrUpdateForm");
        const formForm = $("<form name=\"createOrUpdateEmployee\"/>").addClass("createOrUpdateEmployee");

        formForm.append("<div />").addClass("createOrUpdateForm");
        formForm.append("<p>Enter your name</p>");
        formForm.append($("<input />", {value: param.name, name:"name",type: "text", class: "input_param", id: "departmentName", maxlength: 75, required: true}));
        formForm.append("<p>Enter your address</p>");
        formForm.append($("<input />", {value: param.address, name: "address", type: "text", class: "input_param", id: "departmentAddress", required: true}));
        formForm.append("<input type=\"submit\" class=\"submit_createOrUpdate\" value=\"submit\">")

        formDiv.append(formForm);
        main.append(formDiv);

        formForm.submit(()=>{
            //$("form[name='createOrUpdateDepartment']").validate()
            /*let deparment = new Department();
            deparment.idDepartment = param.idDepartment;
            deparment.name = formForm.serializeArray()[0].value;
            deparment.address = formForm.serializeArray()[1].value;
            new DepartmentService().saveOrUpdateDepartment(deparment);*/
            new Router().getUrl("#department").render("main");
        });
         
    }
}