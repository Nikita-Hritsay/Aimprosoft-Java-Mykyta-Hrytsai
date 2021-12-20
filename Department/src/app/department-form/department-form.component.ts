import { Component, OnInit } from '@angular/core';
import {Department} from "../models/Department";
import {DepartmentService} from "../service/department/department.service";
import {ActivatedRoute, Router} from "@angular/router";
import {
  AbstractControl,
  AsyncValidatorFn,
  FormBuilder,
  FormControl,
  FormGroup,
  ValidatorFn,
  Validators
} from "@angular/forms";

@Component({
  selector: 'app-department-form',
  templateUrl: './department-form.component.html',
  styleUrls: ['../style/main.css']
})
export class DepartmentFormComponent implements OnInit {

  department?: Department;
  departmentForm = new FormGroup({
    name: new FormControl("", Validators.required),
    address: new FormControl("", Validators.required)
  });

  constructor(private departmentService: DepartmentService, private activateRout: ActivatedRoute,
              private router: Router) {
    this.departmentService.getById(Number.parseInt(<string>this.activateRout.snapshot.paramMap.get("id"))).subscribe((data)=>{
      this.department = data;
    });
    this.departmentForm = new FormBuilder().group({
      id: [this.department?.id],
      name: ["", Validators.required],
      address: ["", Validators.required]
    });
  }

  onSubmit(){
    if(this.departmentForm.valid){
      const departmentResult = new Department(
        this.department == null ? 0: this.department?.id,
        String(this.departmentForm.get('name')?.value),
        String(this.departmentForm.get('address')?.value));
      this.departmentService.saveOrUpdate(departmentResult).subscribe(()=>{
        this.router.navigate(["/departments"]);
      });
    }
  }



  ngOnInit(): void {

  }

  /*nameValidator(control: AbstractControl): ValidatorFn {
    console.log(control);
    console.log(control.value);
    console.log(this.departmentService.getByName(control.value));

    if(this.departmentService.getByName(control.value) != null){
      return ;
    }
    return null;
  }*/

}
