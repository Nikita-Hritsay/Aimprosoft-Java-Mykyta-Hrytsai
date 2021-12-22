import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Department} from "../../models/Department";
import {Employee} from "../../models/Employee";

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  public getAll(){
    return this.httpClient.get("/api/employee");
  }

  public getByDepartment(id: number){
    return this.httpClient.get(`api/employee/department/${id}`);
  }

  public getById(id: number){
    return this.httpClient.get(`api/employee/${id}`);
  }

  public delete(id: number): any{
    return this.httpClient.delete<any>("api/employee", {params: {"id": id}});
  }

  public saveOrUpdate(employee: Employee): any {
    console.log("employee " + employee.department.id);
    return this.httpClient.post("/api/employee", employee);
  }

  public getByEmail(email: string): any{
    return this.httpClient.get(`/api/employee/exists`, {params: {"email": email}});
  }

  constructor(private httpClient: HttpClient) { }
}
