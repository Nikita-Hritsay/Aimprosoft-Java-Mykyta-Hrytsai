import { EmployeeForm } from "../../components/employee/EmployeeForm";
import { DepartmentService } from "../../service/DepartmentService";
import { EmployeeService } from "../../service/EmployeeService";
var DisplayEmployeeForm = /** @class */ (function () {
    function DisplayEmployeeForm() {
        this.employeeService = new EmployeeService();
        this.departmentService = new DepartmentService();
        this.employeeFormComponent = new EmployeeForm();
    }
    DisplayEmployeeForm.prototype.render = function (param, department) {
        var _this = this;
        this.employeeService.getById(param).done(function (employee) {
            _this.departmentService.getDepartments().done(function (departments) {
                _this.employeeFormComponent.render(employee, departments, department);
            });
        });
    };
    return DisplayEmployeeForm;
}());
export { DisplayEmployeeForm };
