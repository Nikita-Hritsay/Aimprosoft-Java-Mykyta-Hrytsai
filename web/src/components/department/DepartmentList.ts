import {DepartmentService} from "../../service/DepartmentService";
import {Constants} from "../../utils/Constants";
import {Component} from "../Component"; 
import "../main.css";

export class DepartmentList implements Component{

    department = new DepartmentService();

    render(){
        this.department.getDepartments().done((data: any) => {
            const listDiv =  $(Constants.main).empty();
            const table =  $("<table/>");
            const headerTable = $("<tr/>");
            headerTable.append($("<th/>", {text: "Adress"}));
            headerTable.append($("<th/>", {text: "Name"}));
            headerTable.append($("<th/>", {text: "List"}));
            headerTable.append($("<th/>", {text: "Update"}));
            headerTable.append($("<th/>", {text: "Delete"}));
            table.append(headerTable);
            data.forEach((element: any) => {
                const tr = $("<tr/>");
                tr.append($("<td/>", {text: element.name})); 
                tr.append($("<td/>", {text: element.address})); 
                const listButton = $("<a />", {text: "Employees", href: "#department/" + element.id + "/employee"}) ;
                tr.append($("<td/>").append(listButton))

                const updateButton = $("<a />", {text: "update", href: "#departmentForm/" + element.id}).addClass("update_button");
                tr.append($("<td/>").append(updateButton))

                const deleteButton = $("<button />", {text: "delete"}).addClass("delete_button").addClass("submit_delete");
                tr.append($("<td/>").append(deleteButton.on("click", () => {
                    this.department.deleteDepartment(element.id).done(()=>{
                        this.render();
                    })
                })))
                table.append(tr);
            })
            
            listDiv.append(table);
            
        });
    
    }
}