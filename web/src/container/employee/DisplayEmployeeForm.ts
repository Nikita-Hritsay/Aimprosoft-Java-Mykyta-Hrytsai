import { EmployeeForm } from "../../components/employee/EmployeeForm";
import { DepartmentService } from "../../service/DepartmentService";
import { EmployeeService } from "../../service/EmployeeService";
import {DataNotFoundPage} from "../../components/error/DataNotFoundPage";

export class DisplayEmployeeForm{
    private employeeService = new EmployeeService();
    private departmentService = new DepartmentService();
    private dataNotFound = new DataNotFoundPage();

    private employeeFormComponent = new EmployeeForm();

    render(param: number, employeeId: number){
        this.employeeService.getById(employeeId == null ? param : employeeId).done((employee)=>{
            this.departmentService.getDepartments().done((departments)=>{
                this.employeeFormComponent.render(employee, departments, param);
            }) 
        }).fail(()=>{
            this.dataNotFound.render();
        })
    }

}