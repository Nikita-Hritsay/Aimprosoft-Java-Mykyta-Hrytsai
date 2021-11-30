import {Employee} from "../models/Employee";

export class EmployeeService {
    public getEmployees(): JQuery.jqXHR {
        return $.ajax({
            type: "GET",
            url: "/employee",
            dataType: "json"
        });
    }

    public deleteEmployee(id: number): JQuery.jqXHR{
        return $.ajax({
            url: '/employee?' + $.param({"id": id}),
            type: 'DELETE',
        })
    }

    public saveOrUpdateEmployee(employee: Employee): void{
        $.ajax({
            contentType: 'application/json',
            data: JSON.stringify(employee),
            dataType: 'json',
            success: function(){
                console.log("Employee saved or updated")
            },
            error: function(err){
                console.log("Try later" + err)
            },
            processData: false,
            type: 'POST',
            url: '/employee'
        });
    }



}
