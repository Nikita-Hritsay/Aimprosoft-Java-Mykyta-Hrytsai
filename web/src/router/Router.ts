import { Component } from "../components/Component";
import { Formatter } from "../utils/Formatter";
import { DisplayAllDepartments } from "../container/department/DisplayAllDepartments";
import { DisplayDepartmentForm } from "../container/department/DisplayDepartmentForm";
import { DisplayAllEmployees } from "../container/employee/DisplayAllEmployees";
import { DisplayEmployeesByDepartment } from "../container/employee/DisplayEmployeesByDepartment";
import { DisplayEmployeeForm } from "../container/employee/DisplayEmployeeForm";
import { DisplayErrorPage } from "../container/error/DisplayErrorPage";


export class Router {
    
    private urls: Map<string, Component>;

    constructor() {
        this.urls = new Map();
        this.urls.set("#departments", new DisplayAllDepartments())
        this.urls.set("#department" , new DisplayDepartmentForm());
        this.urls.set("#department/employee", new DisplayEmployeesByDepartment());
        this.urls.set("#employees", new DisplayAllEmployees());
        this.urls.set("#employee", new DisplayEmployeeForm());
        this.urls.set("#employee/department", new DisplayEmployeeForm());
        this.urls.set("#errorPage", new DisplayErrorPage());
    }

    public getUrl(url: string) {
        location.hash = url;
        console.log(Formatter.getUrl(url));
        if (this.urls.get(Formatter.getUrl(url))){
            this.urls.get(Formatter.getUrl(url)).render(Formatter.parseUrl(url), Formatter.getIdDepartment(url));
        }else{
            this.urls.get("#errorPage").render();
        }
    }

}