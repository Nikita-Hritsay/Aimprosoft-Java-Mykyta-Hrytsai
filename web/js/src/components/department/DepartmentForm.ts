import { Component } from "../Component";
import "../main.css";
import { Department } from "../../models/Department";
import { DepartmentService } from "../../service/DepartmentService";
import { Router } from "../../router/Router";


export class DepartmentForm implements Component{
    render(param: any){
        const main = $("#main");
        main.empty();
        const formDiv = $("<div />").addClass("createOrUpdateForm");
        const formForm = $("<form name=\"createOrUpdateDepartment\"/>").addClass("createOrUpdateDepartment");

        formForm.append("<div />").addClass("createOrUpdateForm");
        formForm.append("<p>Enter your name</p>");
        formForm.append($("<input />", {value: param.name, name:"name",type: "text", class: "input_param", id: "departmentName", maxlength: 75, required: true}));
        formForm.append("<p>Enter your address</p>");
        formForm.append($("<input />", {value: param.address, name: "address", type: "text", class: "input_param", id: "departmentAddress", required: true}));
        formForm.append("<input type=\"submit\" class=\"submit_createOrUpdate\" value=\"submit\">")

        formDiv.append(formForm);
        main.append(formDiv);

        formForm.submit((event)=>{
            event.preventDefault();
            let arr = formForm.serializeArray();
            let deparment = new Department();
            deparment.id = param.id;
            deparment.name = arr[0].value;
            deparment.address = arr[1].value;
            new DepartmentService().saveOrUpdateDepartment(deparment);
            new Router().getUrl("#department").render("main");
        });
         
    }
}