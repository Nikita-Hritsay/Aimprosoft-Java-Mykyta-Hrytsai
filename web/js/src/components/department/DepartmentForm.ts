import { Component } from "../Component";
import "../department/Department.css";

export class DepartmentForm implements Component{
    render(param: number){
        
        const app = $("#app");
        const formDiv = $("<div />").addClass("createOrUpdateForm");
        formDiv.append("<div />").addClass("createOrUpdateForm");
        formDiv.append("<p>Enter your first Name</p>");
        formDiv.append("<input name=\"firstName\"/ class=\"input_param\">");
        formDiv.append("<p>Enter your last Name</p>");
        formDiv.append("<input name=\"lastName\" class=\"input_param\">");
        formDiv.append("<p>Enter your email</p>");
        formDiv.append("<input name=\"email\" class=\"input_param\">");
        formDiv.append("<p>Enter your salary</p>");
        formDiv.append("<input name=\"salary\"/ class=\"input_param\">");
        formDiv.append("<p>Enter your hire Date</p>");
        formDiv.append("<input name=\"hireDate\" class=\"input_param\">");
        formDiv.append("<p>Enter your Department</p>");
        formDiv.append("<input name=\"idDepartment\" class=\"input_param\">");
        formDiv.append("<input type=\"submit\" class=\"submit_createOrUpdate\" value=\"submit\">")

        app.append(formDiv);
        
    }
}