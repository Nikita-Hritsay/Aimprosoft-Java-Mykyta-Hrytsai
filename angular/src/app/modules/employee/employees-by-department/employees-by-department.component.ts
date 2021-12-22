import { Component, OnInit } from '@angular/core';
import {EmployeeService} from "../../../service/employee/employee.service";
import {ActivatedRoute, Router} from "@angular/router";
import {RequestUtils} from "../../../utils/RequestUtils";

@Component({
  selector: 'app-employees-by-department',
  templateUrl: './employees-by-department.component.html',
  styleUrls: ['../../../style/main.css']
})
export class EmployeesByDepartmentComponent implements OnInit {

  employees: any;
  departmentId?: number;

  constructor(private employeeService: EmployeeService, private activateRout: ActivatedRoute,
              private router: Router) { }

  ngOnInit(): void {
    this.departmentId = RequestUtils.getNumber(<string>this.activateRout.snapshot.paramMap.get("id"));
    this.employeeService.getByDepartment(RequestUtils.getNumber(<string>this.activateRout.snapshot.paramMap.get("id"))).subscribe((data)=>{
      this.employees = data;
    });
  }

  delete(id: number){
    this.employeeService.delete(id).subscribe(()=>{
      this.ngOnInit();
    });
  }

  routeToEmployeeForm(item: any){
    if(item == null){
      this.router.navigate([`web/departments/${this.departmentId}/employees/0`]);
      return;
    }
    this.router.navigate([`web/departments/${this.departmentId}/employees/${item.id}`]);
  }

}
