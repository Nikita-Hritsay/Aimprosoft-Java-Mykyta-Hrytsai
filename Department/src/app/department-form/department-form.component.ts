import { Component, OnInit } from '@angular/core';
import {Department} from "../models/Department";
import {DepartmentService} from "../service/department/department.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-department-form',
  templateUrl: './department-form.component.html',
  styleUrls: ['../style/main.css']
})
export class DepartmentFormComponent implements OnInit {

  department: any;

  constructor(private departmentService: DepartmentService, private activateRout: ActivatedRoute) { }

  ngOnInit(): void {
    this.departmentService.getById(Number.parseInt(<string>this.activateRout.snapshot.paramMap.get("id"))).subscribe((data)=>{
      this.department = !!data ? {} : data;
    });
  }

}
