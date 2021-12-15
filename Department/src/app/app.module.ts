import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DisplayAllDepartmentsComponent } from './display-all-departments/display-all-departments.component';
import { HttpClientModule } from '@angular/common/http';
import { EmployeesByDepartmentComponent } from './employees-by-department/employees-by-department.component';
import { EmployeesComponent } from './employees/employees.component';
import { HeaderComponent } from './header/header.component';
import { DepartmentFormComponent } from './department-form/department-form.component';
import { EmployeeFormComponent } from './employee-form/employee-form.component';

@NgModule({
  declarations: [
    AppComponent,
    DisplayAllDepartmentsComponent,
    EmployeesByDepartmentComponent,
    EmployeesComponent,
    HeaderComponent,
    DepartmentFormComponent,
    EmployeeFormComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
