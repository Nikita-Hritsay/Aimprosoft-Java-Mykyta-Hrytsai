import {Component, OnInit} from '@angular/core';
import {EmployeeService} from "../../../service/employee/employee.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-employees',
  templateUrl: './employees.component.html',
  styleUrls: ['./employees.component.css']
})
export class EmployeesComponent implements OnInit {

  employees: any;

  constructor(private employeeService: EmployeeService, private router: Router) {
  }

  ngOnInit(): void {
    this.employeeService.getAll().subscribe((data) => {
      this.employees = data;
    })
  }

  delete(id: number) {
    this.employeeService.delete(id).subscribe(() => {
      this.ngOnInit();
    });
  }

  routeToUpdatePage(item: any){
    this.router.navigate([`web/departments/${item.department.id}/employees/${item.id}`]);
  }

  getDate(date: Date): string {
    return new Date(date).toISOString().slice(0, 10);
  }

}