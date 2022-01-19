import { EmployeeList } from "../../components/employee/EmployeeList";
import { EmployeeService } from "../../service/EmployeeService";
var DisplayAllEmployees = /** @class */ (function () {
    function DisplayAllEmployees() {
        this.employeeService = new EmployeeService();
        this.employeeComponent = new EmployeeList();
    }
    DisplayAllEmployees.prototype.render = function () {
        var _this = this;
        this.employeeService.getEmployees().then(function (data) {
            _this.employeeComponent.render(data);
        });
    };
    return DisplayAllEmployees;
}());
export { DisplayAllEmployees };
