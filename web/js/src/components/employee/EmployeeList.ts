import {EmployeeService} from "../../service/EmployeeService";
import {Component} from "../Component"; 
import {Router} from "../../router/Router";
import "../main.css";

export class EmployeeList implements Component{

    employeeService = new EmployeeService();

    render(param: number){
        if(param != null){
            this.employeeService.getEmployees().done((data) => {
                if (data != null){
                    const main = $("#main");
                    main.empty();
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
                        if(data[i].department.id == param || param == 0){
                            const td = $("<tr/>");
                            td.append($("<td/>", {text: data[i].firstName})); 
                            td.append($("<td/>", {text: data[i].lastName})); 
                            td.append($("<td/>", {text: data[i].email}));
                            td.append($("<td/>", {text: data[i].salary}));
                            td.append($("<td/>", {text: new Date(data[i].hireDate).toISOString().slice(0, 10)})); 
                            const updateButton = $("<a />", {text: "update"}).addClass("update_button");
                            td.append($("<td/>").append(updateButton.on("click", () => {
                                new Router().getUrl("#employeeForm").render(data[i]);
                            })))

                            const deleteButton = $("<button />", {text: "delete"}).addClass("delete_button").addClass("submit_delete");
                            td.append($("<td/>").append(deleteButton.on("click", () => {
                                this.employeeService.deleteEmployee(data[i].id).done(()=>{
                                    new Router().getUrl("#employee").render(param);
                                })
                            })))
                            table.append(td);
                            main.append(table);
                        }
                        
                    }
                }else{
                    const main = $("#main");
                    main.empty();
                    main.append($("<p />", {text: "There is no employees"}))
                }
            });
        }
        else{
            const main = $("#app");
            main.append($("<p />", {text: "there is no such Deparment"}));
        }
    }
}