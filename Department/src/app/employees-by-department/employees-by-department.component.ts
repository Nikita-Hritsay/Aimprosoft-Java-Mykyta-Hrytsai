import { Component, OnInit } from '@angular/core';
import {EmployeeService} from "../service/employee/employee.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-employees-by-department',
  templateUrl: './employees-by-department.component.html',
  styleUrls: ['../style/main.css']
})
export class EmployeesByDepartmentComponent implements OnInit {

  employees: any;

  constructor(private employeeService: EmployeeService, private activateRout: ActivatedRoute) { }

  ngOnInit(): void {
    this.employeeService.getByDepartment(Number.parseInt(<string>this.activateRout.snapshot.paramMap.get("id"))).subscribe((data)=>{
      this.employees = data;
    });
  }

  delete(id: number){

  }

}
