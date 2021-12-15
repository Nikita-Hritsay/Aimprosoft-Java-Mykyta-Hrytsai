import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {DisplayAllDepartmentsComponent} from "./display-all-departments/display-all-departments.component";
import {EmployeesComponent} from "./employees/employees.component";

const routes: Routes = [
  {path: 'web/departments', component: DisplayAllDepartmentsComponent},
  {path: 'web/employees', component: EmployeesComponent},
  {path: '**', redirectTo: 'web/departments'},
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, {onSameUrlNavigation: "reload"})],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
