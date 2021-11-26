import {DepartmentService} from "../../service/DepartmentService";

export class Department{

    department = new DepartmentService();

    render(){
        console.log("render");
        this.department.getDepartment().done((data) => {
        
            const app = $("#app");
            const table =  $("<table/>");
            const td = $("<td/>");
            for(let i = 0; i < data.length; i++){
                td.append($("<th/>", {text: data[i].idDepartment})); 
            }
            table.append(td);
            app.append(table);
        });
    }
}