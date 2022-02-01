import {DepartmentService} from "../../service/DepartmentService";
import {mainDiv} from "../../utils/Constants";
import {Component} from "../Component"; 
import "../../../style/main.css";
import {Department} from "../../models/Department";

export class DepartmentList implements Component{

    private departmentService = new DepartmentService();

    render(departments: any){
        const listDiv =  $(mainDiv).empty();
        const table =  $("<table/>");
        const headerTable = $("<tr/>");
        headerTable.append($("<th/>", {text: "Address"}));
        headerTable.append($("<th/>", {text: "Name"}));
        headerTable.append($("<th/>", {text: "List"}));
        headerTable.append($("<th/>", {text: "Update"}));
        headerTable.append($("<th/>", {text: "Delete"}));
        table.append(headerTable);
        this.renderTable(table, departments)
        listDiv.append(table);
    }

    private renderTable(table: JQuery<HTMLElement>, data: any){
        data.forEach((department: Department) => {
            const tr = $("<tr/>");
            tr.append($("<td/>", {text: department.name})); 
            tr.append($("<td/>", {text: department.address})); 
            const listButton = $("<button />", {text: "Employees", href: `#departments/${department.id}/employees`}).addClass("update_button").on("click", ()=>{
                location.hash = `#departments/${department.id}/employees`;
            }) ;
            tr.append($("<td/>").append(listButton))

            const updateButton = $("<button />", {text: "Update", href: `#departments/${department.id}`}).addClass("update_button").addClass("submit_delete").on("click", ()=>{
                location.hash = `#departments/${department.id}`;
            });
            tr.append($("<td/>").append(updateButton))

            const deleteButton = $("<button />", {text: "Delete"}).addClass("delete_button").addClass("submit_delete");
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