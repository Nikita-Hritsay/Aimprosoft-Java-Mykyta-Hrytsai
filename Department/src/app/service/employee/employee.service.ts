import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  public getAll(){
    return this.httpClient.get("/api/employee");
  }

  constructor(private httpClient: HttpClient) { }
}
