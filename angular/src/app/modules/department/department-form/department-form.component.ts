import {Component, OnInit} from '@angular/core';
import {Department} from "../../../models/Department";
import {DepartmentService} from "../../../service/department/department.service";
import {ActivatedRoute, Router} from "@angular/router";
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators
} from "@angular/forms";
import {RequestUtils} from "../../../utils/RequestUtils";

@Component({
  selector: 'app-department-form',
  templateUrl: './department-form.component.html',
  styleUrls: ['./department-form.component.css']
})
export class DepartmentFormComponent implements OnInit {

  department?: Department;
  departmentForm = new FormGroup({
    id: new FormControl(""),
    name: new FormControl("", Validators.required),
    address: new FormControl("", Validators.required)
  });

  constructor(private departmentService: DepartmentService, private activateRout: ActivatedRoute,
              private router: Router) {
    this.departmentForm = new FormBuilder().group({
      id: [this.department?.id],
      name: [null, Validators.required],
      address: [null, Validators.required]
    });
    this.departmentService.getById(RequestUtils.getNumber(<string>this.activateRout.snapshot.paramMap.get("id"))).subscribe((data) => {
      this.department = data;
      this.departmentForm.get('name')?.setValue(this.department?.name);
      this.departmentForm.get('address')?.setValue(this.department?.address);
    });
  }

  onSubmit() {
    if (this.departmentForm.valid) {
      this.departmentService.getByName(this.departmentForm.get("name")?.value).subscribe((data: any) => {
        if (!data || data?.id == this.department?.id) {
          const departmentResult = new Department(
            this.department == null ? 0 : this.department?.id,
            String(this.departmentForm.get('name')?.value),
            String(this.departmentForm.get('address')?.value));
          this.departmentService.saveOrUpdate(departmentResult).subscribe(() => {
            this.router.navigate(["/web/departments"]);
          });
        } else {
          this.departmentForm.get("name")?.setErrors({notUnique: true});
        }
      })
    }

  }

  getErrors(formControlName: string): boolean {
    const value = this.departmentForm.get(formControlName)!;
    return value?.invalid && (value?.dirty || value?.touched);
  }


  ngOnInit(): void {
  }


}
