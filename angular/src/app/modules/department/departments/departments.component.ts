import {Component, OnInit} from '@angular/core';
import {DepartmentService} from "../../../service/department/department.service";

@Component({
  selector: 'app-departments',
  templateUrl: './departments.component.html',
  styleUrls: ['./departments.component.css']
})
export class DepartmentsComponent implements OnInit {

  departments: any;

  constructor(private departmentService: DepartmentService) {
  }

  ngOnInit(): void {
    this.departmentService.get().subscribe((data: any) => {
      this.departments = data;
    });
  }

  delete(id: number): void {
    this.departmentService.delete(id).subscribe(() => {
      this.ngOnInit();
    });
  }

}
