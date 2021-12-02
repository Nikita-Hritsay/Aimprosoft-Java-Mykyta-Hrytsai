import { Component } from "../Component";
import "../main.css";
import { Department } from "../../models/Department";
import { DepartmentService } from "../../service/DepartmentService";
import {Constants} from "../../utils/Constants";


export class DepartmentForm implements Component{
    
    departmentService = new DepartmentService();

    render(param: number){
        this.departmentService.getDepartmentById(param).done((data) => {
            const main = $(Constants.main);
            main.empty();
            const formDiv = $("<div />").addClass("createOrUpdateForm");
            const formForm = $("<form name=\"createOrUpdateDepartment\"/>").addClass("createOrUpdateDepartment");
    
            formForm.append("<div />").addClass("createOrUpdateForm");
            formForm.append($("<p />", {text: "Enter your name"}));
            formForm.append($("<input />", 
                {
                    value: data.name, 
                    name:"name",
                    type: "text", 
                    class: "input_param", 
                    id: "departmentName", 
                    maxlength: 75, 
                    required: true}));
            formForm.append($("<p />", {text: "Enter your address"}));
            formForm.append($("<input />", 
                {   value: data.address, 
                    name: "address", 
                    type: "text", 
                    class: "input_param", 
                    id: "departmentAddress", 
                    required: true}));
            formForm.append($("<input />", {type: "submit", class: "submit_createOrUpdate", value: "submit"}))
    
            formDiv.append(formForm);
            main.append(formDiv);
    
            formForm.submit((event)=>{
                event.preventDefault();
                let arr = formForm.serializeArray();
                let deparment = new Department();
                deparment.id = data.id;
                deparment.name = arr[0].value;
                deparment.address = arr[1].value;
                this.departmentService.saveOrUpdateDepartment(deparment);
                location.href = "#department";
            });
        })
    }
}