import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {DisplayAllDepartmentsComponent} from "./modules/department/display-all-departments/display-all-departments.component";
import {EmployeesComponent} from "./modules/employee/employees/employees.component";
import {EmployeesByDepartmentComponent} from "./modules/employee/employees-by-department/employees-by-department.component";
import {DepartmentFormComponent} from "./modules/department/department-form/department-form.component";
import {EmployeeFormComponent} from "./modules/employee/employee-form/employee-form.component";
import {ErrorComponent} from "./modules/error/error.component";

const routes: Routes = [
  {path: 'web/departments', component: DisplayAllDepartmentsComponent},
  {path: 'web/employees', component: EmployeesComponent},
  {path: 'web/departments/:id/employees', component: EmployeesByDepartmentComponent},
  {path: 'web/departments/:id', component: DepartmentFormComponent},
  {path: 'web/departments/:idDepartment/employees/:id', component: EmployeeFormComponent},
  {path: '**', component: ErrorComponent},
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, {onSameUrlNavigation: "reload"})],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
