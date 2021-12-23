import { EmployeeByDepartment } from "../../components/employee/EmployeeByDepartment";
import { EmployeeService } from "../../service/EmployeeService";
import {DataNotFoundPage} from "../../components/error/DataNotFoundPage";
import {DepartmentService} from "../../service/DepartmentService";
import {Container} from "../Container";


export class DisplayEmployeesByDepartment implements Container{
    private employeeService = new EmployeeService();
    private employeeComponent = new EmployeeByDepartment();
    private dataNotFound = new DataNotFoundPage();
    private departmentService = new DepartmentService();

    render(param:  Map<string, number>){
        this.departmentService.getDepartmentById(param.get("departmentId")).done(()=>{
            this.employeeService.getByDepartment(param.get("departmentId")).done((data)=>{
                console.log(param.get("departmentId"))
                console.log(param.get("employeeId"))
                this.employeeComponent.render(data, param.get("departmentId"));
            })
        }).fail(()=>{
            this.dataNotFound.render();
        });   
    }

}