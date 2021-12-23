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

    public getById(id: number): JQuery.jqXHR{
        return $.ajax({
            type: "GET",
            url: "/employee/" + id,
            dataType: "json"
        })
    }

    public getByDepartment(id: number): JQuery.jqXHR{
        return $.ajax({
            type: "GET",
            url: "/employee/department/" + id,
            dataType: "json"
        });
    }

    public saveOrUpdateEmployee(employee: Employee): JQuery.jqXHR{
        return $.ajax({
            contentType: 'application/json',
            data: JSON.stringify(employee),
            dataType: 'json',
            processData: false,
            type: 'POST',
            url: '/employee'
        });
    }



}
