import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Employee} from "../../models/Employee";

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  public getAll() {
    return this.httpClient.get("/api/employees");
  }

  public getByDepartment(id: number) {
    return this.httpClient.get(`api/employees/departments/${id}`);
  }

  public getById(id: number) {
    return this.httpClient.get(`api/employees/${id}`);
  }

  public delete(id: number): any {
    return this.httpClient.delete<any>("api/employees", {params: {"id": id}});
  }

  public saveOrUpdate(employee: Employee): any {
    return this.httpClient.post("/api/employees", employee);
  }

  public getByEmail(email: string): any {
    return this.httpClient.get(`/api/employees/email`, {params: {"email": email}});
  }

  constructor(private httpClient: HttpClient) {
  }
}
