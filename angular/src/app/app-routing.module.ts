import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {DisplayAllDepartmentsComponent} from "./display-all-departments/display-all-departments.component";
import {EmployeesComponent} from "./employees/employees.component";
import {EmployeesByDepartmentComponent} from "./employees-by-department/employees-by-department.component";
import {DepartmentFormComponent} from "./department-form/department-form.component";
import {EmployeeFormComponent} from "./employee-form/employee-form.component";

const routes: Routes = [
  {path: 'web/departments', component: DisplayAllDepartmentsComponent},
  {path: 'web/employees', component: EmployeesComponent},
  {path: 'web/departments/:id/employees', component: EmployeesByDepartmentComponent},
  {path: 'web/departments/:id', component: DepartmentFormComponent},
  {path: 'web/departments/:idDepartment/employees/:id', component: EmployeeFormComponent},
  {path: '**', redirectTo: 'web/departments'},
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, {onSameUrlNavigation: "reload"})],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
