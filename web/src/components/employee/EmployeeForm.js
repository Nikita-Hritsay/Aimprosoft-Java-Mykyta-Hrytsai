import "../main.css";
import { Employee } from "../../models/Employee";
import { EmployeeService } from "../../service/EmployeeService";
import { Formatter } from "../../utils/Formatter";
import { mainDiv } from "../../utils/Constants";
import { DepartmentService } from "../../service/DepartmentService";
import { EmployeeValidation } from "../../validation/employee/EmployeeValidation";
var EmployeeForm = /** @class */ (function () {
    function EmployeeForm() {
        this.employeeService = new EmployeeService();
        this.departmentService = new DepartmentService();
        this.employeeValidation = new EmployeeValidation();
    }
    EmployeeForm.prototype.render = function (employee, department, param) {
        var _this = this;
        var main = $(mainDiv);
        main.empty();
        var formDiv = $("<div />").addClass("createOrUpdateForm");
        var formForm = $("<form />", { id: "createOrUpdateEmployeeForm" }).addClass("createOrUpdateEmployeeForm");
        formForm.append("<div />").addClass("createOrUpdateForm");
        formForm.append($("<input />", { name: "id", type: "hidden", value: employee.id, id: "id", class: "id" }));
        formForm.append($("<p />", { text: "Enter your first name" }));
        formForm.append($("<input />", { value: employee.firstName,
            name: "firstName",
            type: "text",
            class: "input_param firstName",
            id: "firstName" }));
        formForm.append($("<p />", { text: "Enter your last name" }));
        formForm.append($("<input />", { value: employee.lastName,
            name: "lastName",
            type: "text",
            class: "input_param lastName",
            id: "lastName" }));
        formForm.append($("<p />", { text: "Enter your email" }));
        formForm.append($("<input />", { value: employee.email,
            name: "email",
            type: "text",
            class: "input_param email",
            id: "email" }));
        formForm.append($("<p />", { text: "Enter your salary" }));
        formForm.append($("<input />", { value: employee.salary,
            name: "salary",
            type: "number",
            class: "input_param salary",
            id: "salary", }));
        formForm.append($("<p />", { text: "Enter your hire date" }));
        formForm.append($("<input />", { value: Formatter.getDate(employee.hireDate),
            name: "hireDate",
            type: "date",
            class: "input_param hireDate",
            id: "hireDate" }));
        formForm.append($("<p />", { text: "Enter your department name" }));
        formForm.append($("<input />", { value: employee.department == null ? param == null ? "" : param : employee.department.id,
            list: "idDepartments",
            name: "idDepartment",
            class: "input_param idDepartment",
            id: "idDepartment",
            size: "15px" }));
        var datalist = $("<datalist />", { id: "idDepartments" });
        department.forEach(function (department) {
            datalist.append($("<option />", { text: department.name, value: department.id, }));
        });
        formForm.append(datalist);
        formForm.append($("<input />", { type: "submit",
            class: "submit_createOrUpdate idDepartment",
            value: "submit" }));
        formDiv.append(formForm);
        main.append(formDiv);
        var errorList = this.employeeValidation.validate("#createOrUpdateEmployeeForm");
        formForm.on("submit", function (event) {
            event.preventDefault();
            if (errorList.errorList.length < 1) {
                var employee_1 = new Employee();
                var arr = formForm.serializeArray();
                console.log("arr " + arr);
                employee_1.id = Number($("input[name=id]").val()) == 0 ? null : Number($("input[name=id]").val());
                employee_1.firstName = String($("input[name=firstName]").val());
                employee_1.lastName = String($("input[name=lastName]").val());
                employee_1.email = String($("input[name=email]").val());
                employee_1.salary = Number($("input[name=salary]").val());
                employee_1.hireDate = new Date(String($("input[name=hireDate]").val()));
                employee_1.department.id = Number($("input[name=idDepartment]").val());
                console.log("employee in render " + employee_1);
                _this.employeeService.saveOrUpdateEmployee(employee_1).done(function () {
                    location.hash = "#department/" + employee_1.department.id + "/employee";
                });
            }
        });
    };
    return EmployeeForm;
}());
export { EmployeeForm };
