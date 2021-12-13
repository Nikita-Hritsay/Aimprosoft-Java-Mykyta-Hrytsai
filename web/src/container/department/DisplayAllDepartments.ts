import { DepartmentList } from "../../components/department/DepartmentList";
import { DepartmentService } from "../../service/DepartmentService";


export class DisplayAllDepartments {
    departmentService = new DepartmentService();
    departmentComponent = new DepartmentList();

    render(){
        this.departmentService.getDepartments().then((data)=>{
            this.departmentComponent.render(data);
        }).fail(()=>{
            location.hash = "#errorPage";
        })
    }

}