import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {DepartmentsComponent} from "./departments/departments.component";
import {DepartmentFormComponent} from "./department-form/department-form.component";
import {EmployeesComponent} from "../employee/employees/employees.component";

const routes: Routes = [
  {path: '', component: DepartmentsComponent},
  {path: 'employees', component: EmployeesComponent, pathMatch: "full"},
  {path: ':id', component: DepartmentFormComponent},
  {
    path: ':id/employees',
    loadChildren: () => import('../employee/employee.module').then(m => m.EmployeeModule)
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DepartmentRoutingModule {
}
