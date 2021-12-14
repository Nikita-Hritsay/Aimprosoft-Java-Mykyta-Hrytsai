import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class DepartmentService {

  public get(): Observable<any>{
    return this.httpClient.get<any>("/department");
  }

  constructor(private httpClient: HttpClient) { }
}
