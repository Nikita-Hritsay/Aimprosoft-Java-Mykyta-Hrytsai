import { EmployeeList } from "../../components/employee/EmployeeList";
import { EmployeeService } from "../../service/EmployeeService";


export class DisplayAllEmployees {
    employeeService = new EmployeeService();
    employeeComponent = new EmployeeList();

    render(){
        this.employeeService.getEmployees().then((data)=>{
            this.employeeComponent.render(data);
        })
    }

}