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

    render(param: any){
        this.departmentService.getDepartmentById(param[0]).done(()=>{
            this.employeeService.getByDepartment(param[0]).done((data)=>{
                this.employeeComponent.render(data, param[0]);
            })
        }).fail(()=>{
            this.dataNotFound.render();
        });   
    }

}