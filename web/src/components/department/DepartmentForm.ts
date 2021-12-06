import { Component } from "../Component";
import "../main.css";
import { Department } from "../../models/Department";
import { DepartmentService } from "../../service/DepartmentService";
import {Constants} from "../../utils/Constants";
import { EmployeeService } from "../../service/EmployeeService";
import { DepartmentValidation } from "../../validation/department/DepartmentValidation";
import { error } from "jquery";


export class DepartmentForm implements Component{
    
    deparmentValidation = new DepartmentValidation();
    departmentService = new DepartmentService();

    render(param: number){
        this.departmentService.getDepartmentById(param).done((data) => {
            const main = $(Constants.main).empty();
            const formDiv = $("<div />").addClass("createOrUpdateForm");
            const formForm = $("<form />", {id: "createOrUpdateDepartmentForm"}).addClass("createOrUpdateDepartment");
    
            formForm.append("<div />").addClass("createOrUpdateForm");
            formForm.append($("<input />", {name: "id", value: data.id, id: "id", class: "id"}))
            formForm.append($("<p />", {text: "Enter your name"}));
            formForm.append($("<input />", 
                {
                    value: data.name, 
                    name:"name",
                    type: "text", 
                    class: "input_param name", 
                    required: true,
                    id: "name"}));
            formForm.append($("<p />", {text: "Enter your address"}));
            formForm.append($("<input />", 
                {   value: data.address, 
                    name: "departmentAddress", 
                    type: "text", 
                    class: "input_param departmentAddress", 
                    required: true,
                    id: "departmentAddress"}));
            formForm.append($("<input />", {type: "submit", class: "submit_createOrUpdate", value: "submit"}))
    
            formDiv.append(formForm);
            main.append(formDiv);
    
            
            let errorList = this.deparmentValidation.validate("#createOrUpdateDepartmentForm");
            formForm.submit((event)=>{
                event.preventDefault();
                if(errorList.errorList.length < 1){
                    let arr = formForm.serializeArray();
                    let department = new Department();
                    department.id = data.id;
                    department.name = arr[1].value;
                    department.address = arr[2].value;
                    console.log(department)
                    this.departmentService.saveOrUpdateDepartment(department).done(()=>{
                        location.hash = "#departments";
                    });
                }
            });
        })
    }
}