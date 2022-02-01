import {Formatter} from "../utils/Formatter";
import {DisplayAllDepartments} from "../container/department/DisplayAllDepartments";
import {DisplayDepartmentForm} from "../container/department/DisplayDepartmentForm";
import {DisplayAllEmployees} from "../container/employee/DisplayAllEmployees";
import {DisplayEmployeesByDepartment} from "../container/employee/DisplayEmployeesByDepartment";
import {DisplayEmployeeForm} from "../container/employee/DisplayEmployeeForm";
import {DisplayErrorPage} from "../container/error/DisplayErrorPage";
import {Container} from "../container/Container";


export class Router {

    private containersByRoutes: Map<string, Container>;

    constructor() {
        this.containersByRoutes = new Map();
        this.containersByRoutes.set("#departments", new DisplayAllDepartments());
        this.containersByRoutes.set("#departments/{id}", new DisplayDepartmentForm());
        this.containersByRoutes.set("#departments/{id}/employees", new DisplayEmployeesByDepartment());
        this.containersByRoutes.set("#employees", new DisplayAllEmployees());
        this.containersByRoutes.set("#departments/{id}/employees/{id}", new DisplayEmployeeForm());
        this.containersByRoutes.set("#errorPage", new DisplayErrorPage());
    }

    public renderPage(url: string) {
        location.hash = url;
        const page = this.containersByRoutes.get(Formatter.getRoute(url));
        if (page) {
            page.render(Formatter.getParams(url));
        } else {
            this.containersByRoutes.get("#errorPage").render(null);
        }
    }

}