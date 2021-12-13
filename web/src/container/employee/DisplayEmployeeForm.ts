import { EmployeeForm } from "../../components/employee/EmployeeForm";
import { DepartmentService } from "../../service/DepartmentService";
import { EmployeeService } from "../../service/EmployeeService";

export class DisplayEmployeeForm{
    employeeService = new EmployeeService();
    departmentService = new DepartmentService();

    employeeFormComponent = new EmployeeForm();

    render(param: number, department: number){
        console.log(param + "   " + department)
        this.employeeService.getById(param).done((employee)=>{
            this.departmentService.getDepartments().done((departments)=>{
                this.employeeFormComponent.render(employee, departments, department);
            })
        })
    }

}