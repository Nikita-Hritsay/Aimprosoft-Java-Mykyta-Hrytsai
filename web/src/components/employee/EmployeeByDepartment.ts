import {EmployeeService} from "../../service/EmployeeService";
import {mainDiv} from "../../utils/Constants";
import { Formatter } from "../../utils/Formatter";
import {Component} from "../Component"; 
import "../main.css";

export class EmployeeByDepartment implements Component{

    private employeeService = new EmployeeService();

    render(data: any, param: number){
        const main = $(mainDiv).empty();
        const addToThisDepartmentButton = $("<a />", {text: "Add employee to this department", href: `#departments/${param}/employee/0`}).addClass("header_ref addToThisDepartment");
        main.append(addToThisDepartmentButton);
       
        if (data != null && data.length > 0){
            const table =  $("<table/>");
            const headerTable = $("<tr/>");
            headerTable.append($("<th/>", {text: "First name"}));
            headerTable.append($("<th/>", {text: "Last name"}));
            headerTable.append($("<th/>", {text: "Email"}));
            headerTable.append($("<th/>", {text: "Salary"}));
            headerTable.append($("<th/>", {text: "Hire Date"}));
            headerTable.append($("<th/>", {text: "Department Name"}));
            headerTable.append($("<th/>", {text: "Update"}));
            headerTable.append($("<th/>", {text: "Delete"}));
            table.append(headerTable);
            this.renderTable(table, data, param);
            main.append(table);
        }else{
            main.append($("<div />", {class: "emptyClass"}).append($("<h3 />", {text: "There is no employees in that Department"})));
        }
        
    }

    private renderTable(table: any, data: any, param: number) {
        data.forEach((employee : any) => {
            const tr = $("<tr/>");
            tr.append($("<td/>", {text: employee.firstName})); 
            tr.append($("<td/>", {text: employee.lastName})); 
            tr.append($("<td/>", {text: employee.email}));
            tr.append($("<td/>", {text: employee.salary}));
            tr.append($("<td/>", {text: Formatter.getDate(employee.hireDate)})); 
            tr.append($("<td/>", {text: employee.department.name})); 
            const updateButton = $("<button />", {text: "Update", href: `#employee/${employee.id}`}).addClass("update_button").on("click", ()=>{
                location.hash = `#employee/${employee.id}`;
            });
            tr.append($("<td/>").append(updateButton))
            const deleteButton = $("<button />", {text: "Delete"}).addClass("delete_button").addClass("submit_delete");
            tr.append($("<td/>").append(deleteButton.on("click", () => {
                this.employeeService.deleteEmployee(employee.id).done(()=>{
                    this.employeeService.getByDepartment(param).done((data: any)=>{
                        this.render(data, param);
                    });
                })
            })))
            table.append(tr);
        });
    }

}