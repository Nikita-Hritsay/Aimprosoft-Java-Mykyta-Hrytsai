import { EmployeeByDepartment } from "../../components/employee/EmployeeByDepartment";
import { EmployeeService } from "../../service/EmployeeService";


export class DisplayEmployeesByDepartment {
    employeeService = new EmployeeService();
    employeeComponent = new EmployeeByDepartment();

    render(param: number){
        this.employeeService.getByDepartment(param).done((data)=>{
            this.employeeComponent.render(data, param);
        }).fail(()=>{
            this.employeeComponent.render(null, null);
        });   
    }

}