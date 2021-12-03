import {EmployeeService} from "../../service/EmployeeService";
import {Constants} from "../../utils/Constants";
import { Formatter } from "../../utils/Formatter";
import {Component} from "../Component"; 
import "../main.css";

export class EmployeeByDepartment implements Component{

    employeeService = new EmployeeService();

    render(param: number){
        this.employeeService.getByDepartment(param).done((data) => {
            const addToThisDepartmentButton = $("<a />", {text: "add to this department", href: "#employeeForm/0/department/" + param}).addClass("header_ref addToThisDepartment");
            if (data.length > 0){
                const main = $(Constants.main).empty();
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
                data.forEach((element : any) => {
                    const tr = $("<tr/>");
                    tr.append($("<td/>", {text: element.firstName})); 
                    tr.append($("<td/>", {text: element.lastName})); 
                    tr.append($("<td/>", {text: element.email}));
                    tr.append($("<td/>", {text: element.salary}));
                    tr.append($("<td/>", {text: Formatter.getDate(element.hireDate)})); 
                    tr.append($("<td/>", {text: element.department.name})); 
                    const updateButton = $("<a />", {text: "update", href: "#employeeForm/" + element.id}).addClass("update_button");
                    tr.append($("<td/>").append(updateButton))
                    const deleteButton = $("<button />", {text: "delete"}).addClass("delete_button").addClass("submit_delete");
                    tr.append($("<td/>").append(deleteButton.on("click", () => {
                        this.employeeService.deleteEmployee(element.id).done(()=>{
                            this.render(param);
                        })
                    })))
                    table.append(tr);
                });
                
                main.append(addToThisDepartmentButton);
                main.append(table);
            }else{
                const main = $(Constants.main).empty();
                main.append($("<div />", {class: "emptyClass"}).append($("<h3 />", {text: "There is no employees in this Department"})))
            }
        });
        
       
    }
}