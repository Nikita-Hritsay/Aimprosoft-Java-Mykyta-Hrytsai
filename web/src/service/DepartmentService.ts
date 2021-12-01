import {Department} from "../models/Department";

export class DepartmentService {
    public getDepartment(): JQuery.jqXHR {
        return $.ajax({
            type: "GET",
            url: "/department",
            dataType: "json"
        });
    }

    public deleteDepartment(id: number): JQuery.jqXHR{
         return $.ajax({
            url: '/department?' + $.param({"idDepartment": id}),
            type: 'DELETE',
        })
    }

    public saveOrUpdateDepartment(department: Department): JQuery.jqXHR{
        return $.ajax({
            contentType: 'application/json',
            data: JSON.stringify(department),
            dataType: 'json',
            success: function(){
                console.log("Department saved or updated")
            },
            error: function(err){
                console.log("Try later" + err)
            },
            processData: false,
            type: 'POST',
            url: '/department'
        });
    }


}
