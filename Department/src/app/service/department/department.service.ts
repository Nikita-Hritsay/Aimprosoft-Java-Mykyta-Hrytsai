import { Injectable } from '@angular/core';
import {catchError, Observable, tap} from "rxjs";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class DepartmentService {

  public get(): Observable<any>{
    return this.httpClient.get<any>("/api/department");
  }

  public delete(id: number): void{
    this.httpClient.delete<any>("api/department", {body: {"idDepartment": id}}).subscribe();

    console.log(id)
    console.log("/department?id=" + id)
  }

  public getById(id: number): Observable<any>{
    return this.httpClient.get<any>(`/api/department/${id}`);
  }

  constructor(private httpClient: HttpClient) { }
}
