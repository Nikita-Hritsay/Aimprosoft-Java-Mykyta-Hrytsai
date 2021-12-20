import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

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

  constructor(private httpClient: HttpClient) { }
}
