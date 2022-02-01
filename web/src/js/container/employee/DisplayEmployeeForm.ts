import { EmployeeForm } from "../../components/employee/EmployeeForm";
import { DepartmentService } from "../../service/DepartmentService";
import { EmployeeService } from "../../service/EmployeeService";
import {DataNotFoundPage} from "../../components/error/DataNotFoundPage";
import {Container} from "../Container";

export class DisplayEmployeeForm implements Container{
    private employeeService = new EmployeeService();
    private departmentService = new DepartmentService();
    private dataNotFound = new DataNotFoundPage();

    private employeeFormComponent = new EmployeeForm();

    render(param:  Map<string, number>){
        this.employeeService.getById(param.get("employeeId") == null ? 0 : param.get("employeeId")).done((employee)=>{
            this.departmentService.getDepartments().done((departments)=>{
                this.employeeFormComponent.render(employee, departments, param.get("departmentId"));
            }) 
        }).fail(()=>{
            this.dataNotFound.render();
        })
    }

}