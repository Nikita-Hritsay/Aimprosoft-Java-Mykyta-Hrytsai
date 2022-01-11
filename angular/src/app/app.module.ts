import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {DepartmentsComponent} from './modules/department/departments/departments.component';
import {HttpClientModule} from '@angular/common/http';
import {
  EmployeesByDepartmentComponent
} from './modules/employee/employees-by-department/employees-by-department.component';
import {EmployeesComponent} from './modules/employee/employees/employees.component';
import {DepartmentFormComponent} from './modules/department/department-form/department-form.component';
import {EmployeeFormComponent} from './modules/employee/employee-form/employee-form.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {ErrorComponent} from './modules/error/error.component';
import {CoreModule} from "./core/core.module";

@NgModule({
  declarations: [
    AppComponent,
    DepartmentsComponent,
    EmployeesByDepartmentComponent,
    EmployeesComponent,
    DepartmentFormComponent,
    EmployeeFormComponent,
    ErrorComponent,
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        FormsModule,
        ReactiveFormsModule,
        CoreModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
