import { Department } from "./Department";
var Employee = /** @class */ (function () {
    function Employee() {
        this.id = 0;
        this.firstName = "";
        this.lastName = "";
        this.email = "";
        this.salary = 0;
        this.hireDate = new Date();
        this.department = new Department;
    }
    return Employee;
}());
export { Employee };
