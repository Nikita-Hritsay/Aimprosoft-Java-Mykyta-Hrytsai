import {Department} from "../models/Department";

export class DepartmentService {
    public getDepartment(): JQuery.jqXHR {
        return $.ajax({
            type: "GET",
            url: "http://localhost:8080/department",
            dataType: "json"
        });
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
