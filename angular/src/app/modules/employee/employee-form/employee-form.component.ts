import {Component, OnInit} from '@angular/core';
import {EmployeeService} from "../../../service/employee/employee.service";
import {Department} from "../../../models/Department";
import {DepartmentService} from "../../../service/department/department.service";
import {ActivatedRoute, Router} from "@angular/router";
import {FormBuilder, FormControl, FormGroup, NgForm, Validators} from "@angular/forms";
import {Employee} from "../../../models/Employee";
import {applySourceSpanToExpressionIfNeeded} from "@angular/compiler/src/output/output_ast";
import {RequestUtils} from "../../../utils/RequestUtils";

@Component({
  selector: 'app-employee-form',
  templateUrl: './employee-form.component.html',
  styleUrls: ['../../../style/main.css']
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

    this.departmentService.get().subscribe((data) => {
      this.departments = data;
    });
    this.employeeForm = new FormBuilder().group({
      id: [this.employee?.id],
      firstName: [null, Validators.required],
      lastName: [null, Validators.required],
      email: [null, Validators.required],
      salary: [null, Validators.required],
      hireDate: [null, Validators.required],
      idDepartment: [null, Validators.required]
    });
    if (RequestUtils.getNumber(<string>this.activateRout.snapshot.paramMap.get("id")) != 0) {
      this.employeeService.getById(RequestUtils.getNumber(<string>this.activateRout.snapshot.paramMap.get("id"))).subscribe((data) => {
        this.employee = data;
        this.employeeForm.get('firstName')?.setValue(this.employee?.id);
        this.employeeForm.get('firstName')?.setValue(this.employee?.firstName);
        this.employeeForm.get('lastName')?.setValue(this.employee?.lastName);
        this.employeeForm.get('email')?.setValue(this.employee?.email);
        this.employeeForm.get('salary')?.setValue(this.employee?.salary);
        this.employeeForm.get('hireDate')?.setValue(new Date(this.employee?.hireDate));
        this.employeeForm.get('idDepartment')?.setValue(this.employee?.department?.id);
      });
    }
    this.idDepartment = RequestUtils.getNumber(<string>this.activateRout.snapshot.paramMap.get("idDepartment"));
  }

  ngOnInit(): void {

  }

  onSubmit() {
    console.log(this.employee?.hireDate);
    this.employeeService.getByEmail(this.employeeForm.get('email')?.value).subscribe((data: any) => {
      if (!data || data?.id == this.employee?.id && this.employeeForm.valid) {
        const employeeResult = new Employee(
          this.employee == null ? null : this.employee.id,
          String(this.employeeForm.get('firstName')?.value),
          String(this.employeeForm.get('lastName')?.value),
          String(this.employeeForm.get('email')?.value),
          RequestUtils.getNumber(this.employeeForm.get('salary')?.value),
          new Date(this.employeeForm.get("hireDate")?.value),
          new Department(RequestUtils.getNumber(this.employeeForm.get("idDepartment")?.value), "", "")
        );
        this.employeeService.saveOrUpdate(employeeResult).subscribe(() => {
          this.router.navigate([`web/departments/${employeeResult.department.id}/employees`]);
        })
      } else {
        this.employeeForm.get("email")?.setErrors({notUnique: true});
      }
    });
  }


  getErrors(formControlName: string): boolean {
    const value = this.employeeForm.get(formControlName)!;
    return value?.invalid && (value?.dirty || value?.touched);
  }

}
