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
    this.httpClient.delete<any>("/department/" + id);
    console.log(id)
  }

  constructor(private httpClient: HttpClient) { }
}
