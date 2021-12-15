import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class DepartmentService {

  public get(): Observable<any>{
    return this.httpClient.get<any>("/api/department");
  }

  public delete(id: number): void{
    this.httpClient.delete("/department?id=" + id);
    console.log(id)
    console.log("/department?id=" + id)
  }

  public getById(id: number){
    return this.httpClient.get<any>(`/api/department/${id}`);
  }

  constructor(private httpClient: HttpClient) { }
}
