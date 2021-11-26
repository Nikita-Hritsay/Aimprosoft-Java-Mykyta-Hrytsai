import {DepartmentService} from "../../service/DepartmentService";

export class Department{

    department = new DepartmentService();

    render(){
        console.log("render");
        this.department.getDepartment().done((data) => {
        
            const app = $("#app");
            const table =  $("<table/>");
            const headerTable = $("<tr/>");
            headerTable.append($("<th/>", {text: "Name"}));
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
                td.append($("<td>Delete</td>"));
                const deleteLink =  $("<a href = \"/department?" + data[i].idDepartment + "/>")
                td.append(deleteLink);
                table.append(td);
            }
            
            
            app.append(table);
        });
    }
}