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

  public delete(id: number){
    this.httpClient.delete<any>("")
  }

  constructor(private httpClient: HttpClient) { }
}
