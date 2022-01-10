import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {Department} from "../../models/Department";

@Injectable({
  providedIn: 'root'
})
export class DepartmentService {

  public get(): Observable<any> {
    return this.httpClient.get<any>("/api/departments");
  }

  public delete(id: number): any {
    return this.httpClient.delete<any>("api/departments", {params: {"idDepartment": id}});
  }

  public getById(id: number): Observable<any> {
    return this.httpClient.get<any>(`/api/departments/${id}`);
  }

  public getByName(name: string): Observable<any> {
    return this.httpClient.get<any>('/api/departments/name', {params: {"name": name}});
  }

  public saveOrUpdate(department: Department): any {
    return this.httpClient.post("/api/departments", department);
  }


  constructor(private httpClient: HttpClient) {
  }
}
