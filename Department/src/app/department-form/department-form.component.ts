import { Component, OnInit } from '@angular/core';
import {Department} from "../models/Department";
import {DepartmentService} from "../service/department/department.service";
import {ActivatedRoute} from "@angular/router";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'app-department-form',
  templateUrl: './department-form.component.html',
  styleUrls: ['../style/main.css']
})
export class DepartmentFormComponent implements OnInit {

  department?: Department;

  constructor(private departmentService: DepartmentService, private activateRout: ActivatedRoute) {
    this.departmentService.getById(Number.parseInt(<string>this.activateRout.snapshot.paramMap.get("id"))).subscribe((data)=>{
      console.log(data);
      this.department = data;
    });
  }

  onSubmit(form: NgForm){
    console.log(form);
    console.log(this.department);
  }

  ngOnInit(): void {
  }

}
