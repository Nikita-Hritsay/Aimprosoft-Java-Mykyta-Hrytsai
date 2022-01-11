import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {EmployeeFormComponent} from "./employee-form/employee-form.component";
import {EmployeesByDepartmentComponent} from "./employees-by-department/employees-by-department.component";

const routes: Routes = [
  {path: '', component: EmployeesByDepartmentComponent},
  {path: ':idEmployee', component: EmployeeFormComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EmployeeRoutingModule {
}
