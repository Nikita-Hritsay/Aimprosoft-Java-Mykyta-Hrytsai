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

    render(param: any){
        this.employeeService.getById(param[1] == null ? 0 : param[1]).done((employee)=>{
            this.departmentService.getDepartments().done((departments)=>{
                this.employeeFormComponent.render(employee, departments, param[0]);
            }) 
        }).fail(()=>{
            this.dataNotFound.render();
        })
    }

}