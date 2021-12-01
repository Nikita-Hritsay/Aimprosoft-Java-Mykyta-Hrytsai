import { Department } from "../models/Department";
import { Employee } from "../models/Employee";
import {Router} from "../router/Router";

class Start{
    public static start(){
        window.addEventListener("load", ()=>{
            const app = $("#app");
            const headers = $("<div />").addClass("header_refs");
            headers.append($("<a />", {text: "Home page", class: "header_ref", href: "#department"}).on("click", ()=>{
                new Router().getUrl("#department").render("main");
            }))
            headers.append($("<a />", {text: "All Employees", class: "header_ref"}).on("click", ()=>{
                new Router().getUrl("#employee").render(0);
            }))
            headers.append($("<a />", {text: "Add Department", class: "header_ref"}).on("click", ()=>{
                new Router().getUrl("#departmentForm").render(new Department());
            }))
            headers.append($("<a />", {text: "Add Employee", class: "header_ref"}).on("click", ()=>{
                new Router().getUrl("#employeeForm").render(new Employee());
            }))
            $("#main").empty();
            app.append(headers);
            app.append($("<div >", {id: "main"}))
            new Router().getUrl(location.hash).render("start");
        })
    }
}

Start.start()