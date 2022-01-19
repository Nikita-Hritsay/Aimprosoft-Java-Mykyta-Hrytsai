import { EmployeeByDepartment } from "../../components/employee/EmployeeByDepartment";
import { EmployeeService } from "../../service/EmployeeService";
var DisplayEmployeesByDepartment = /** @class */ (function () {
    function DisplayEmployeesByDepartment() {
        this.employeeService = new EmployeeService();
        this.employeeComponent = new EmployeeByDepartment();
    }
    DisplayEmployeesByDepartment.prototype.render = function (param) {
        var _this = this;
        this.employeeService.getByDepartment(param).done(function (data) {
            _this.employeeComponent.render(data, param);
        }).fail(function () {
            _this.employeeComponent.render(null, null);
        });
    };
    return DisplayEmployeesByDepartment;
}());
export { DisplayEmployeesByDepartment };
