import { EmployeeByDepartment } from "../../components/employee/EmployeeByDepartment";
import { EmployeeService } from "../../service/EmployeeService";
import {DataNotFoundPage} from "../../components/error/DataNotFoundPage";
import {DepartmentService} from "../../service/DepartmentService";


export class DisplayEmployeesByDepartment {
    private employeeService = new EmployeeService();
    private employeeComponent = new EmployeeByDepartment();
    private dataNotFound = new DataNotFoundPage();
    private departmentService = new DepartmentService();

    render(param: number){
        this.departmentService.getDepartmentById(param).done(()=>{
            this.employeeService.getByDepartment(param).done((data)=>{
                this.employeeComponent.render(data, param);
            })
        }).fail(()=>{
            this.dataNotFound.render();
        });   
    }

}