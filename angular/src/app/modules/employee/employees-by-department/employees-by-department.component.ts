import {Component, OnInit} from '@angular/core';
import {EmployeeService} from "../../../service/employee/employee.service";
import {ActivatedRoute} from "@angular/router";
import {RequestUtils} from "../../../utils/RequestUtils";
import * as moment from "moment";

@Component({
  selector: 'app-employees-by-department',
  templateUrl: './employees-by-department.component.html',
  styleUrls: ['./employees-by-department.component.css']
})
export class EmployeesByDepartmentComponent implements OnInit {

  employees: any;
  departmentId?: number;

  constructor(private employeeService: EmployeeService, private activateRout: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.departmentId = RequestUtils.getNumber(<string>this.activateRout.snapshot.paramMap.get("id"));
    this.employeeService.getByDepartment(RequestUtils.getNumber(<string>this.activateRout.snapshot.paramMap.get("id"))).subscribe((data) => {
      this.employees = data;
    });
  }

  delete(id: number) {
    this.employeeService.delete(id).subscribe(() => {
      this.ngOnInit();
    });
  }

  getDate(date: Date): string {
    return  moment(date).toISOString().slice(0, 10);
  }

}
