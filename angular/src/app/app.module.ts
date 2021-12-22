import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DisplayAllDepartmentsComponent } from './modules/department/display-all-departments/display-all-departments.component';
import { HttpClientModule } from '@angular/common/http';
import { EmployeesByDepartmentComponent } from './modules/employee/employees-by-department/employees-by-department.component';
import { EmployeesComponent } from './modules/employee/employees/employees.component';
import { HeaderComponent } from './header/header.component';
import { DepartmentFormComponent } from './modules/department/department-form/department-form.component';
import { EmployeeFormComponent } from './modules/employee/employee-form/employee-form.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";

@NgModule({
  declarations: [
    AppComponent,
    DisplayAllDepartmentsComponent,
    EmployeesByDepartmentComponent,
    EmployeesComponent,
    HeaderComponent,
    DepartmentFormComponent,
    EmployeeFormComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
