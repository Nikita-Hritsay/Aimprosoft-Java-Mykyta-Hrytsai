import { DepartmentForm } from "../../components/department/DepartmentForm";
import { DepartmentService } from "../../service/DepartmentService";

export class DisplayDepartmentForm{

    departmentService = new DepartmentService();
    departmentForm = new DepartmentForm();

    render(param: number){
        this.departmentService.getDepartmentById(param).done((data: any)=>{
            this.departmentForm.render(data);
        }).fail(()=>{
            this.departmentForm.render(null);
        });
    }

}