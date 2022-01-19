import { DepartmentForm } from "../../components/department/DepartmentForm";
import { DepartmentService } from "../../service/DepartmentService";
var DisplayDepartmentForm = /** @class */ (function () {
    function DisplayDepartmentForm() {
        this.departmentService = new DepartmentService();
        this.departmentForm = new DepartmentForm();
    }
    DisplayDepartmentForm.prototype.render = function (param) {
        var _this = this;
        this.departmentService.getDepartmentById(param).done(function (data) {
            _this.departmentForm.render(data);
        }).fail(function () {
            _this.departmentForm.render(null);
        });
    };
    return DisplayDepartmentForm;
}());
export { DisplayDepartmentForm };
