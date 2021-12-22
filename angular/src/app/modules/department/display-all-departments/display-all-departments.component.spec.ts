import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayAllDepartmentsComponent } from './display-all-departments.component';

describe('DisplayAllDepartmentsComponent', () => {
  let component: DisplayAllDepartmentsComponent;
  let fixture: ComponentFixture<DisplayAllDepartmentsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DisplayAllDepartmentsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DisplayAllDepartmentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
