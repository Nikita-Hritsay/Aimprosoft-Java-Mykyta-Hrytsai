import { Department } from "./Department";

export class Employee {
    id: number;
    firstName: string;
    lastName: string;
    email: string;
    salary: number;
    hireDate: Date;
    department: Department;

    constructor(){
    this.id = 0;
    this.firstName = "";
    this.lastName = "";
    this.email = "";
    this.salary = 0;
    this.hireDate = new Date();
    this.department = new Department;

    }
}