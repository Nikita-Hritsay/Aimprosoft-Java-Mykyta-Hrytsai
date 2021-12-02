import {EmployeeService} from "../../service/EmployeeService";
import {Constants} from "../../utils/Constants";
import { Formatter } from "../../utils/Formatter";
import {Component} from "../Component"; 
import "../main.css";

export class EmployeeList implements Component{

    employeeService = new EmployeeService();

    render(param: number){
        
        this.employeeService.getEmployees().done((data) => {
            if (data != []){
                const main = $(Constants.main);
                main.empty();
                const table =  $("<table/>");
                const headerTable = $("<tr/>");
                headerTable.append($("<th/>", {text: "First name"}));
                headerTable.append($("<th/>", {text: "Last name"}));
                headerTable.append($("<th/>", {text: "Email"}));
                headerTable.append($("<th/>", {text: "Salary"}));
                headerTable.append($("<th/>", {text: "Hire Date"}));
                headerTable.append($("<th/>", {text: "Department name"}));
                headerTable.append($("<th/>", {text: "Update"}));
                headerTable.append($("<th/>", {text: "Delete"}));
                table.append(headerTable);
                for(let i = 0; i < data.length; i++) {
                    const tr = $("<tr/>");
                    tr.append($("<td/>", {text: data[i].firstName})); 
                    tr.append($("<td/>", {text: data[i].lastName})); 
                    tr.append($("<td/>", {text: data[i].email}));
                    tr.append($("<td/>", {text: data[i].salary}));
                    tr.append($("<td/>", {text: Formatter.getDate(data[i].hireDate)})); 
                    tr.append($("<td/>", {text: data[i].department.name}));
                    const updateButton = $("<a />", {text: "update"}).addClass("update_button");
                    tr.append($("<td/>").append(updateButton.on("click", () => {
                    })))
                    const deleteButton = $("<button />", {text: "delete"}).addClass("delete_button").addClass("submit_delete");
                    tr.append($("<td/>").append(deleteButton.on("click", () => {
                        this.employeeService.deleteEmployee(data[i].id).done(()=>{
                            this.render(param);
                        })
                    })))
                    table.append(tr);
                }
                main.append(table);
            }else{
                const main = $(Constants.main);
                main.empty();
                main.append($("<div />", {class: "emptyClass"}).append($("<p />", {text: "There is no employees in this Department"})))
            }
        });
    }
}