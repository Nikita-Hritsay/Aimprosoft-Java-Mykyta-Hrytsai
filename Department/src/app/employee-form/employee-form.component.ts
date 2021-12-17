import { Component, OnInit } from '@angular/core';
import {EmployeeService} from "../service/employee/employee.service";
import {Department} from "../models/Department";
import {DepartmentService} from "../service/department/department.service";
import {ActivatedRoute} from "@angular/router";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'app-employee-form',
  templateUrl: './employee-form.component.html',
  styleUrls: ['../style/main.css']
})
export class EmployeeFormComponent implements OnInit {

  employee?: any;
  departments?: Set<Department>;
  idDepartment?: number;

  constructor(private employeeService: EmployeeService, private departmentService: DepartmentService, private activateRout: ActivatedRoute) {
    if(Number.parseInt(<string>this.activateRout.snapshot.paramMap.get("id")) != 0){
      this.employeeService.getById(Number.parseInt(<string>this.activateRout.snapshot.paramMap.get("id"))).subscribe((data)=>{
        this.employee = data;
        console.log("employee " + this.employee);
      });
    }
    this.departmentService.get().subscribe((data)=>{
      this.departments = data;
      console.log("departments " + this.departments);
    });
    this.idDepartment = Number.parseInt(<string>this.activateRout.snapshot.paramMap.get("idDepartment"));
  }

  ngOnInit(): void {

  }

  onSubmit(form: NgForm){

  }

}
