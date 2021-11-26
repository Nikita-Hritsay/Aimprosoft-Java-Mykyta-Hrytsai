import {Employee} from "../models/Employee";
import {Department} from "../models/Department";

export class DepartmentService {
    public getDepartment(): void {
        $.getJSON(
            'http://localhost:8080/department'
        ).then(function (result) {
            console.log(result)
        }).catch(function () {
            console.log("no data")
        })
    }

    public deleteDepartment(id: number): void{
        $.ajax({
            url: 'http://localhost:8080/department?' + $.param({"idDepartment": id}),
            type: 'DELETE',
        })
    }

    public saveOrUpdateDepartment(department: Department): void{
        $.ajax({
            contentType: 'application/json',
            data: {
                "idDepartment": department.id,
                "name": department.name,
                "address": department.address
            },
            dataType: 'json',
            success: function(){
                console.log("Department saved or updated")
            },
            error: function(err){
                console.log("Try later" + err)
            },
            processData: false,
            type: 'POST',
            url: 'http://localhost:8080/department'
        });
    }



}
