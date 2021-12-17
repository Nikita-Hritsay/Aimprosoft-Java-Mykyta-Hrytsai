import { Component } from "../Component";
import "../main.css";
import { Department } from "../../models/Department";
import { DepartmentService } from "../../service/DepartmentService";
import { mainDiv} from "../../utils/Constants";
import { DepartmentValidation } from "../../validation/department/DepartmentValidation";


export class DepartmentForm implements Component{

    private departmentValidation = new DepartmentValidation();
    private departmentService = new DepartmentService();

    render(department: any){
        const main = $(mainDiv).empty();
        
        const formDiv = $("<div />").addClass("createOrUpdateForm");
        const formForm = $("<form />", {id: "createOrUpdateDepartmentForm"}).addClass("createOrUpdateDepartment");
    
        formDiv.append(this.renderInputForm(formForm, department));
        main.append(formDiv);

        const errorList = this.departmentValidation.validate("#createOrUpdateDepartmentForm");

        formForm.on("submit", (event)=>{
            event.preventDefault();
            if(errorList.errorList.length < 1){
                const departmentAdd = new Department();
                departmentAdd.id = department.id;
                departmentAdd.name = String($("input[name=name]").val());
                departmentAdd.address = String($("input[name=address]").val());
                this.departmentService.saveOrUpdateDepartment(departmentAdd).then(()=>{
                    location.hash = "#departments";
                }).fail(()=>{
                    location.hash = "#departments";
                });
            }
        });
    }

    private renderInputForm(formForm: any, data: any): any{
        formForm.append($("<div />")).addClass("createOrUpdateForm");
        formForm.append($("<input />", {type: "hidden", name: "id", value: data.id, id: "id", class: "id"}))
        formForm.append($("<p />", {text: "Name"}));
        formForm.append($("<input />", 
            {
                value: data == null ? null : data.name, 
                name:"name",
                type: "text", 
                class: "input_param name", 
                id: "name"}));
        formForm.append($("<p />", {text: "Address"}));
        formForm.append($("<input />", 
            {   value: data == null ? null : data.address,
                name: "address", 
                type: "text", 
                class: "input_param address", 
                id: "address"}));
        formForm.append($("<input />", {type: "submit", class: "submit_createOrUpdate", value: "Add Department"}));
        return formForm;
    }
}