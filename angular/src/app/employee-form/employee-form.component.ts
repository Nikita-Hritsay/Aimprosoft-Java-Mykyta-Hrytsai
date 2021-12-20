import { Component, OnInit } from '@angular/core';
import {EmployeeService} from "../service/employee/employee.service";
import {Department} from "../models/Department";
import {DepartmentService} from "../service/department/department.service";
import {ActivatedRoute, Router} from "@angular/router";
import {FormBuilder, FormControl, FormGroup, NgForm, Validators} from "@angular/forms";
import {Employee} from "../models/Employee";

@Component({
  selector: 'app-employee-form',
  templateUrl: './employee-form.component.html',
  styleUrls: ['../style/main.css']
})
export class EmployeeFormComponent implements OnInit {

  employee?: any;
  departments?: Set<Department>;
  idDepartment?: number;
  employeeForm = new FormGroup({
    firstName: new FormControl("", Validators.required),
    lastName: new FormControl("", Validators.required),
    email: new FormControl("", Validators.required),
    salary: new FormControl("", Validators.required),
    hireDate: new FormControl("", Validators.required),
    idDepartment: new FormControl("", Validators.required),
  });

  constructor(private employeeService: EmployeeService, private departmentService: DepartmentService, private activateRout: ActivatedRoute, private router: Router) {
    if(Number.parseInt(<string>this.activateRout.snapshot.paramMap.get("id")) != 0){
      this.employeeService.getById(Number.parseInt(<string>this.activateRout.snapshot.paramMap.get("id"))).subscribe((data)=>{
        this.employee = data;
      });
    }
    this.departmentService.get().subscribe((data)=>{
      this.departments = data;
    });
    this.employeeForm = new FormBuilder().group({
      id: [this.employee?.id],
      firstName: ["", Validators.required],
      lastName: ["", Validators.required],
      email: ["", Validators.required],
      salary: ["", Validators.required],
      hireDate: ["", Validators.required],
      idDepartment: ["", Validators.required]
    });
    this.idDepartment = Number.parseInt(<string>this.activateRout.snapshot.paramMap.get("idDepartment"));
  }

  ngOnInit(): void {

  }

  onSubmit(){
    console.log("submitting")
    if(this.employeeForm.valid){
      const employeeResult = new Employee(
        this.employee == null ? null: this.employee.id,
          String(this.employeeForm.get('firstName')?.value),
          String(this.employeeForm.get('lastName')?.value),
          String(this.employeeForm.get('email')?.value),
          Number(this.employeeForm.get('salary')?.value),
          new Date(this.employeeForm.get("hireDate")?.value),
          new Department(Number(this.employeeForm.get("idDepartment")?.value), "", "")
      );
      this.employeeService.saveOrUpdate(employeeResult).subscribe(()=>{
        this.router.navigate([`departments/${this.employeeForm.get("idDepartment")?.value}/employees`]);
      })
    }
  }

}
