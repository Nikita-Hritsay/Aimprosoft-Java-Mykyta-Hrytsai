import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeesByDepartmentComponent } from './employees-by-department.component';

describe('EmployeesByDepartmentComponent', () => {
  let component: EmployeesByDepartmentComponent;
  let fixture: ComponentFixture<EmployeesByDepartmentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EmployeesByDepartmentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EmployeesByDepartmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
