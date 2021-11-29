import {EmployeeService} from "../../service/EmployeeService";
import {Component} from "../Component"; 
import {Router} from "../../router/Router";
import "../department/Department.css";

export class EmployeeList implements Component{

    employeeService = new EmployeeService();

    render(param: number){
        if(param != 0){
            this.employeeService.getEmployees().done((data) => {
                const app = $("#app");
                const table =  $("<table/>");
                const headerTable = $("<tr/>");
                headerTable.append($("<th/>", {text: "First name"}));
                headerTable.append($("<th/>", {text: "Last name"}));
                headerTable.append($("<th/>", {text: "Email"}));
                headerTable.append($("<th/>", {text: "Salary"}));
                headerTable.append($("<th/>", {text: "Hire Date"}));
                headerTable.append($("<th/>", {text: "Update"}));
                headerTable.append($("<th/>", {text: "Delete"}));
                table.append(headerTable);
                for(let i = 0; i < data.length; i++){
                    if(data[i].department.idDepartment == param){
                        const td = $("<tr/>");
                        td.append($("<td/>", {text: data[i].firstName})); 
                        td.append($("<td/>", {text: data[i].lastName})); 
                        td.append($("<td/>", {text: data[i].email}));
                        td.append($("<td/>", {text: data[i].salary}));
                        td.append($("<td/>", {text: data[i].hireDate})); 

                        const updateButton = $("<button />", {text: "update"});
                        td.append($("<td/>").append(updateButton.on("click", () => {
                            new Router().getUrl("employeeForm").render(data[i].id);
                        })))

                        const deleteButton = $("<button />", {text: "delete"}).addClass("delete_button").addClass("submit_delete");
                        td.append($("<td/>").append(deleteButton.on("click", () => {
                            this.employeeService.deleteEmployee(data[i].id)
                            window.location.reload();
                        })))
                        table.append(td);
                        app.append(table);
                    }
                    
                }
            });
        }
        else{
            const app = $("#app");
            app.append($("<p />", {text: "there is no such Deparment"}));
        }
    }
}