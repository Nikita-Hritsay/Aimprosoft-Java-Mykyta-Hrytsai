import {Component} from "../components/Component";
import { DepartmentForm } from "../components/department/DepartmentForm";
import {DepartmentList} from "../components/department/DepartmentList";
import { EmployeeList } from "../components/employee/EmployeeList";


export class Router {
    
    private urls: Map<string, Component>;

    constructor() {
        this.urls = new Map();
        this.urls.set("department", new DepartmentList())
        this.urls.set("departmentForm", new DepartmentForm());
        this.urls.set("employee", new EmployeeList());
    }

    public getUrl(url: string): Component {
        console.log(url);
        return this.urls.get(url);
    }

}