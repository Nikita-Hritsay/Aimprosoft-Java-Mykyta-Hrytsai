import { Component, OnInit } from '@angular/core';
import {EmployeeService} from "../service/employee/employee.service";

@Component({
  selector: 'app-employees',
  templateUrl: './employees.component.html',
  styleUrls: ['../style/main.css']
})
export class EmployeesComponent implements OnInit {

  employees: any;

  constructor(private employeeService: EmployeeService) { }

  ngOnInit(): void {
    this.employeeService.getAll().subscribe((data) =>{
      this.employees = data;
      console.log(data)
    })

  }

  delete(id: number){

  }

}
