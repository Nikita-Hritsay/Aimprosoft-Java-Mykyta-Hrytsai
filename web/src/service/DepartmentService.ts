import {Department} from "../models/Department";

export class DepartmentService {
    public getDepartments(): JQuery.jqXHR {
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

    public getDepartmentById(id: number): JQuery.jqXHR {
        return $.ajax({
            type: "GET",
            url: "/department/" + id,
            dataType: "json"
        });
    }

    public saveOrUpdateDepartment(department: Department): JQuery.jqXHR{
        return $.ajax({
            contentType: 'application/json',
            data: JSON.stringify(department),
            dataType: 'json',
            success: function(){
                location.hash = "#departments";
            },
            error: function(error){
                location.hash = "#departments"
            },
            processData: false,
            type: 'POST',
            url: '/department'
        });
    }


}
