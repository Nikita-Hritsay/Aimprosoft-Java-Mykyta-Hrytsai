import {EmployeeService} from "../../service/EmployeeService";
import {Component} from "../Component"; 
import {Router} from "../../router/Router";
import "../main.css";

export class EmployeeList implements Component{

    employeeService = new EmployeeService();

    render(param: number){
        if(param != null){
            this.employeeService.getEmployees().done((data) => {
                let employees = this.getData(param, data);
                if (employees != null){
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
                    for(let i = 0; i < employees.length; i++) {
                        const td = $("<tr/>");
                        td.append($("<td/>", {text: employees[i].firstName})); 
                        td.append($("<td/>", {text: employees[i].lastName})); 
                        td.append($("<td/>", {text: employees[i].email}));
                        td.append($("<td/>", {text: employees[i].salary}));
                        td.append($("<td/>", {text: new Date(employees[i].hireDate).toISOString().slice(0, 10)})); 
                        const updateButton = $("<a />", {text: "update"}).addClass("update_button");
                        td.append($("<td/>").append(updateButton.on("click", () => {
                            new Router().getUrl("#employeeForm").render(employees[i]);
                        })))
                        const deleteButton = $("<button />", {text: "delete"}).addClass("delete_button").addClass("submit_delete");
                        td.append($("<td/>").append(deleteButton.on("click", () => {
                            this.employeeService.deleteEmployee(employees[i].id).done(()=>{
                                new Router().getUrl("#employee").render(param);
                            })
                        })))
                        table.append(td);
                        main.append(table);
                    }
                }else{
                    const main = $("#main");
                    main.empty();
                    main.append($("<div />", {class: "emptyClass"}).append($("<p />", {text: "There is no employees in this Department"})))
                }
            });
        }
        else{
            const main = $("#app");
            main.append($("<p />", {text: "there is no such Deparment"}));
        }
    }

    private getData(param: number, data: any): any{
        let employees = data.filter((el: any) => el.department.id == param);
        if(employees.length > 0)
            return employees;
        if(param == 0)
            return data;
        return null;
    }
}