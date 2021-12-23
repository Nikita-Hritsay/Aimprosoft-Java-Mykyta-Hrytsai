import { appDiv } from "../../utils/Constants";

export class Header{
    render(){
        const app = $(appDiv);
        const headers = $("<div />").addClass("header_refs");
        headers.append($("<a />", {text: "Home page", class: "header_ref", href: "#departments"}))
        headers.append($("<a />", {text: "All Employees", class: "header_ref", href: "#employees"}))
        headers.append($("<a />", {text: "Add Department", class: "header_ref", href: "#departments/0"}))
        headers.append($("<a />", {text: "Add Employee", class: "header_ref", href: "#departments/0/employees/0"}))
        app.append(headers);
    }
}