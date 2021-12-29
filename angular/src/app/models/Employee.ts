import {Department} from "./Department";

export class Employee {
  id: number;
  firstName: string;
  lastName: string;
  email: string;
  salary: number;
  hireDate: Date;
  department: Department;

  constructor(id: number, firstName: string, lastName: string, email: string, salary: number, hireDate: Date, department: Department) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.salary = salary;
    this.hireDate = hireDate;
    this.department = department;
  }
}
