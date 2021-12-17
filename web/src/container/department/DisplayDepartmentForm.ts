import { DepartmentForm } from "../../components/department/DepartmentForm";
import { DepartmentService } from "../../service/DepartmentService";
import {DataNotFoundPage} from "../../components/error/DataNotFoundPage";
import {Container} from "../Container";

export class DisplayDepartmentForm implements Container{

    private departmentService = new DepartmentService();
    private departmentForm = new DepartmentForm();
    private dataNotFound = new DataNotFoundPage();

    render(param: any){
        this.departmentService.getDepartmentById(param[0]).done((data: any)=>{
            this.departmentForm.render(data);
        }).fail(()=>{
            this.dataNotFound.render();
        });
    }

}