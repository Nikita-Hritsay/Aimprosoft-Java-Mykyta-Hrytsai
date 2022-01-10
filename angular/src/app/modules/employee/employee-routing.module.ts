import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {EmployeesComponent} from "./employees/employees.component";
import {EmployeeFormComponent} from "./employee-form/employee-form.component";

const routes: Routes = [
  { path: '', component: EmployeesComponent },
  { path: ':idEmployee', component: EmployeeFormComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EmployeeRoutingModule { }
