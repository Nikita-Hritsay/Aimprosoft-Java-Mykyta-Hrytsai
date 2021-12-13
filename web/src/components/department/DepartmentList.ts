import {DepartmentService} from "../../service/DepartmentService";
import {mainDiv} from "../../utils/Constants";
import {Component} from "../Component"; 
import "../main.css";

export class DepartmentList implements Component{

    departmentService = new DepartmentService();

    render(departments: any){
        const listDiv =  $(mainDiv).empty();
        const table =  $("<table/>");
        const headerTable = $("<tr/>");
        headerTable.append($("<th/>", {text: "Adress"}));
        headerTable.append($("<th/>", {text: "Name"}));
        headerTable.append($("<th/>", {text: "List"}));
        headerTable.append($("<th/>", {text: "Update"}));
        headerTable.append($("<th/>", {text: "Delete"}));
        table.append(headerTable);
        this.renderTable(table, departments)
        listDiv.append(table);
    }

    private renderTable(table: any, data: any): any{
        data.forEach((department: any) => {
            const tr = $("<tr/>");
            tr.append($("<td/>", {text: department.name})); 
            tr.append($("<td/>", {text: department.address})); 
            const listButton = $("<button />", {text: "Employees", href: `#department/${department.id}/employee`}).addClass("update_button").on("click", ()=>{
                location.hash = `#department/${department.id}/employee`;
            }) ;
            tr.append($("<td/>").append(listButton))

            const updateButton = $("<button />", {text: "update", href: `#department/${department.id}`}).addClass("update_button").addClass("submit_delete").on("click", ()=>{
                location.hash = `#department/${department.id}`;
            });
            tr.append($("<td/>").append(updateButton))

            const deleteButton = $("<button />", {text: "delete"}).addClass("delete_button").addClass("submit_delete");
            tr.append($("<td/>").append(deleteButton.on("click", () => {
                this.departmentService.deleteDepartment(department.id).done(()=>{
                    this.departmentService.getDepartments().done((data: any)=>{
                        this.render(data);
                    });
                })
            })))
            table.append(tr);
        })

    }

}