import {Component} from "../components/Component";
import { DepartmentForm } from "../components/department/DepartmentForm";
import {DepartmentList} from "../components/department/DepartmentList";
import { EmployeeForm } from "../components/employee/EmployeeForm";
import { EmployeeList } from "../components/employee/EmployeeList";


export class Router {
    
    private urls: Map<string, Component>;

    constructor() {
        this.urls = new Map();
        this.urls.set("#department", new DepartmentList())
        this.urls.set("#departmentForm", new DepartmentForm());
        this.urls.set("#employee", new EmployeeList());
        this.urls.set("#employeeForm", new EmployeeForm());
    }

    public getUrl(url: string): Component {
        location.hash = url;
        return this.urls.get(url);
    }

}