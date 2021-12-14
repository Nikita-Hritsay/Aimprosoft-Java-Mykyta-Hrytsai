import { Injectable } from '@angular/core';

import { HttpClient }   from '@angular/common/http';
import {Observable} from "rxjs";


@Injectable({
  providedIn: 'root'
})
export class DepartmentService {

  public get(): Observable<any>{
    return this.httpClient.get<any>("department");
  }

  constructor(private httpClient: HttpClient) {
  }
}
