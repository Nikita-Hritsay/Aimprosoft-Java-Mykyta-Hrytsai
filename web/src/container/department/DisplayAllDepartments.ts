import { DepartmentList } from "../../components/department/DepartmentList";
import { DepartmentService } from "../../service/DepartmentService";


export class DisplayAllDepartments {
    private departmentService = new DepartmentService();
    private departmentComponent = new DepartmentList();

    render(){
        this.departmentService.getDepartments().then((data)=>{
            this.departmentComponent.render(data);
        }).fail(()=>{
            location.hash = "#errorPage";
        })
    }

}