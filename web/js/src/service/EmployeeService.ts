import {Employee} from "../models/Employee";

export class EmployeeService {
    public getEmployees(): JQuery.jqXHR {
        return $.ajax({
            type: "GET",
            url: "http://localhost:8080/employee",
            dataType: "json"
        });
    }

    public deleteEmployee(id: number): void{
        $.ajax({
            url: 'http://localhost:8080/employee?' + $.param({"id": id}),
            type: 'DELETE',
        })
    }

    public saveOrUpdateEmployee(employee: Employee): void{
        $.ajax({
            contentType: 'application/json',
            data: {
               "idEmployee": employee.id,
                "firstName": employee.firstName,
                "lastName": employee.lastName,
                "email": employee.email,
                "salary": employee.salary,
                "hireDate": employee.hireDate,
                "idDepartment": employee.idDepartment
            },
            dataType: 'json',
            success: function(){
                console.log("Employee saved or updated")
            },
            error: function(err){
                console.log("Try later" + err)
            },
            processData: false,
            type: 'POST',
            url: 'http://localhost:8080/employee'
        });
    }



}
