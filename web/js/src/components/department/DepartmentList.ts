import {DepartmentService} from "../../service/DepartmentService";
import {Component} from "../Component"; 
import {Router} from "../../router/Router";
import "../main.css";

export class DepartmentList implements Component{

    department = new DepartmentService();

    render(){
        this.department.getDepartment().done((data) => {
            const listDiv =  $("#main");
            listDiv.empty();
            const table =  $("<table/>");
            const headerTable = $("<tr/>");
            headerTable.append($("<th/>", {text: "Id"}));
            headerTable.append($("<th/>", {text: "Adress"}));
            headerTable.append($("<th/>", {text: "Name"}));
            headerTable.append($("<th/>", {text: "List"}));
            headerTable.append($("<th/>", {text: "Update"}));
            headerTable.append($("<th/>", {text: "Delete"}));
            table.append(headerTable);
            for(let i = 0; i < data.length; i++){
                const td = $("<tr/>");
                td.append($("<td/>", {text: data[i].idDepartment})); 
                td.append($("<td/>", {text: data[i].name})); 
                td.append($("<td/>", {text: data[i].address})); 
                const listButton = $("<button />", {text: "list"}) ;
                td.append($("<td/>").append(listButton.on("click", () => {
                    listDiv.empty();
                    new Router().getUrl("#employee").render(data[i].idDepartment);
                })))

                const updateButton = $("<button />", {text: "update"});
                td.append($("<td/>").append(updateButton.on("click", () => {
                    listDiv.empty();
                    new Router().getUrl("#departmentForm").render(data[i]);
                })))

                const deleteButton = $("<button />", {text: "delete"}).addClass("delete_button").addClass("submit_delete");
                td.append($("<td/>").append(deleteButton.on("click", () => {
                    this.department.deleteDepartment(data[i].idDepartment).done(()=>{
                        new Router().getUrl("#department").render("main");
                    })
                })))
                table.append(td);
            }

            listDiv.append(table);
        });
    }
}