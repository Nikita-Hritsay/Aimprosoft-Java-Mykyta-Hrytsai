import { Component, OnInit } from '@angular/core';
import {DepartmentService} from "../service/DepartmentService";

@Component({
  selector: 'app-display-all-departments',
  templateUrl: './display-all-departments.component.html',
  styleUrls: ['./display-all-departments.component.css']
})
export class DisplayAllDepartmentsComponent implements OnInit {

  departments: any;

  constructor(public departmentService: DepartmentService) { }

  ngOnInit(): void {
    this.departmentService.get().subscribe((data: any)=>{
      this.departments = data;
    })
  }

}
