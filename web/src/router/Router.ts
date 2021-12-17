import { Formatter } from "../utils/Formatter";
import { DisplayAllDepartments } from "../container/department/DisplayAllDepartments";
import { DisplayDepartmentForm } from "../container/department/DisplayDepartmentForm";
import { DisplayAllEmployees } from "../container/employee/DisplayAllEmployees";
import { DisplayEmployeesByDepartment } from "../container/employee/DisplayEmployeesByDepartment";
import { DisplayEmployeeForm } from "../container/employee/DisplayEmployeeForm";
import { DisplayErrorPage } from "../container/error/DisplayErrorPage";
import {Container} from "../container/Container";


export class Router {
    
    private urls: Map<string, Container>;

    constructor() {
        this.urls = new Map();
        this.urls.set("#departments", new DisplayAllDepartments());
        this.urls.set("#departments/{id}" , new DisplayDepartmentForm());
        this.urls.set("#departments/{id}/employees", new DisplayEmployeesByDepartment());
        this.urls.set("#employees", new DisplayAllEmployees());
        this.urls.set("#departments/{id}/employees/{id}", new DisplayEmployeeForm());
        this.urls.set("#errorPage", new DisplayErrorPage());
    }

    public getUrl(url: string) {
        location.hash = url;
        console.log("params " + Formatter.getParams(url));
        console.log("get " + Formatter.get(url));
        if (this.urls.get(Formatter.get(url))){
            this.urls.get(Formatter.get(url)).render(Formatter.getParams(url));
        }else{
            console.log("not found");
            this.urls.get("#errorPage").render();
        }
    }

}