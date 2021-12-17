import { EmployeeList } from "../../components/employee/EmployeeList";
import { EmployeeService } from "../../service/EmployeeService";
import {Container} from "../Container";


export class DisplayAllEmployees implements Container{
    private employeeService = new EmployeeService();
    private employeeComponent = new EmployeeList();

    render(){
        this.employeeService.getEmployees().then((data)=>{
            this.employeeComponent.render(data);
        })
    }

}