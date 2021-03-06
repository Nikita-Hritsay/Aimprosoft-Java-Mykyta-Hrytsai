import {EmployeeService} from "../../service/EmployeeService";
import { mainDiv } from "../../utils/Constants";
import { Formatter } from "../../utils/Formatter";
import {Component} from "../Component"; 
import "../../../style/main.css";
import {Employee} from "../../models/Employee";

export class EmployeeList implements Component{

    private employeeService = new EmployeeService();

    render(data: any){
        
            if (data.length > 0){
                const main = $(mainDiv);
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
                
                this.renderTable(table, data);
                main.append(table);
            }else{
                const main = $(mainDiv);
                main.empty();
                main.append($("<div />", {class: "emptyClass"}).append($("<h3 />", {text: "There is no employees in this Department"})))
            }
        
    }

    private renderTable(table: JQuery<HTMLElement>, data: any){
        data.forEach((employee: Employee)=>{
            const tr = $("<tr/>");
            tr.append($("<td/>", {text: employee.firstName})); 
            tr.append($("<td/>", {text: employee.lastName})); 
            tr.append($("<td/>", {text: employee.email}));
            tr.append($("<td/>", {text: employee.salary}));
            tr.append($("<td/>", {text: Formatter.getDate(employee.hireDate)})); 
            tr.append($("<td/>", {text: employee.department.name}));
            const updateButton = $("<button />", {text: "Update"}).addClass("update_button").on("click", ()=>{
                location.hash = `#departments/${employee.department.id}/employees/${employee.id}`;
            });
            tr.append($("<td/>").append(updateButton));
            const deleteButton = $("<button />", {text: "Delete"}).addClass("delete_button").addClass("submit_delete");
            tr.append($("<td/>").append(deleteButton.on("click", () => {
                this.employeeService.deleteEmployee(employee.id).done(()=>{
                    this.employeeService.getEmployees().done((data: any)=>{
                        this.render(data);
                    });
                })
            })))
            table.append(tr);
        })
    }

}