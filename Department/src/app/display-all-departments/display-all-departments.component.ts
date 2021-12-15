import { Component, OnInit } from '@angular/core';
import {DepartmentService} from "../service/department/department.service";

@Component({
  selector: 'app-display-all-departments',
  templateUrl: './display-all-departments.component.html',
  styleUrls: ['../style/main.css']
})
export class DisplayAllDepartmentsComponent implements OnInit {

  departments: any;

  constructor(private departmentService: DepartmentService) { }

  ngOnInit(): void {
    this.departmentService.get().subscribe((data: any)=>{
      this.departments = data;
    });
  }

  delete(id: number): void{
    this.departmentService.delete(id);
  }

}
