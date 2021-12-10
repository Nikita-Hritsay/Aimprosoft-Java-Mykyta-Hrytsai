import { Component } from "../components/Component";
import { DepartmentForm } from "../components/department/DepartmentForm";
import { DepartmentList } from "../components/department/DepartmentList";
import { EmployeeForm } from "../components/employee/EmployeeForm";
import { EmployeeList } from "../components/employee/EmployeeList";
import { EmployeeByDepartment } from "../components/employee/EmployeeByDepartment";
import { Formatter } from "../utils/Formatter";


export class Router {
    
    private urls: Map<string, Component>;

    constructor() {
        this.urls = new Map();
        this.urls.set("#departments", new DepartmentList())
        this.urls.set("#departmentForm" , new DepartmentForm());
        this.urls.set("#department/employee", new EmployeeByDepartment());
        this.urls.set("#employees", new EmployeeList());
        this.urls.set("#employeeForm", new EmployeeForm());
        this.urls.set("#employeeForm/department", new EmployeeForm());
    }

    public getUrl(url: string) {
        location.hash = url;
        if (this.urls.get(Formatter.getUrl(url))){
            this.urls.get(Formatter.getUrl(url)).render(Formatter.parseUrl(url), Formatter.getIdDepartment(url));
        }else{
            this.urls.get("#departments").render();
        }
    }

}