import {Injectable} from '@angular/core';
import {catchError, Observable, tap} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {Department} from "../../models/Department";

@Injectable({
  providedIn: 'root'
})
export class DepartmentService {

  public get(): Observable<any> {
    return this.httpClient.get<any>("/api/department");
  }

  public delete(id: number): any {
    return this.httpClient.delete<any>("api/department", {params: {"idDepartment": id}});
  }

  public getById(id: number): Observable<any> {
    return this.httpClient.get<any>(`/api/department/${id}`);
  }

  public getByName(name: string): Observable<any> {
    return this.httpClient.get<any>('/api/department/exists', {params: {"name": name}});
  }

  public saveOrUpdate(department: Department): any {
    return this.httpClient.post("/api/department", department);
  }


  constructor(private httpClient: HttpClient) {
  }
}
