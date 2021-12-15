import { DepartmentForm } from "../../components/department/DepartmentForm";
import { DepartmentService } from "../../service/DepartmentService";
import {DataNotFoundPage} from "../../components/error/DataNotFoundPage";

export class DisplayDepartmentForm{

    private departmentService = new DepartmentService();
    private departmentForm = new DepartmentForm();
    private dataNotFound = new DataNotFoundPage();

    render(param: number){
        this.departmentService.getDepartmentById(param).done((data: any)=>{
            this.departmentForm.render(data);
        }).fail(()=>{
            this.dataNotFound.render();
        });
    }

}