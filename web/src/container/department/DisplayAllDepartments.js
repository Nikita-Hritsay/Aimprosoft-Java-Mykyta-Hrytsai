import { DepartmentList } from "../../components/department/DepartmentList";
import { DepartmentService } from "../../service/DepartmentService";
var DisplayAllDepartments = /** @class */ (function () {
    function DisplayAllDepartments() {
        this.departmentService = new DepartmentService();
        this.departmentComponent = new DepartmentList();
    }
    DisplayAllDepartments.prototype.render = function () {
        var _this = this;
        this.departmentService.getDepartments().then(function (data) {
            _this.departmentComponent.render(data);
        }).fail(function () {
            location.hash = "#errorPage";
        });
    };
    return DisplayAllDepartments;
}());
export { DisplayAllDepartments };
