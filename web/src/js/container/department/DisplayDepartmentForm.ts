import { DepartmentForm } from "../../components/department/DepartmentForm";
import { DepartmentService } from "../../service/DepartmentService";
import {DataNotFoundPage} from "../../components/error/DataNotFoundPage";
import {Container} from "../Container";
import {Department} from "../../models/Department";

export class DisplayDepartmentForm implements Container{

    private departmentService = new DepartmentService();
    private departmentForm = new DepartmentForm();
    private dataNotFound = new DataNotFoundPage();

    render(param:  Map<string, number>){
        this.departmentService.getDepartmentById(param.get("departmentId")).done((data: Department)=>{
            this.departmentForm.render(data);
        }).fail(()=>{
            this.dataNotFound.render();
        });
    }

}