import "../main.css";
import { Department } from "../../models/Department";
import { DepartmentService } from "../../service/DepartmentService";
import { mainDiv } from "../../utils/Constants";
import { DepartmentValidation } from "../../validation/department/DepartmentValidation";
var DepartmentForm = /** @class */ (function () {
    function DepartmentForm() {
        this.departmentValidation = new DepartmentValidation();
        this.departmentService = new DepartmentService();
    }
    DepartmentForm.prototype.render = function (department) {
        var _this = this;
        var main = $(mainDiv).empty();
        var formDiv = $("<div />").addClass("createOrUpdateForm");
        var formForm = $("<form />", { id: "createOrUpdateDepartmentForm" }).addClass("createOrUpdateDepartment");
        formDiv.append(this.renderInputForm(formForm, department));
        main.append(formDiv);
        var errorList = this.departmentValidation.validate("#createOrUpdateDepartmentForm");
        formForm.on("submit", function (event) {
            event.preventDefault();
            if (errorList.errorList.length < 1) {
                var arr = formForm.serializeArray();
                var departmentAdd = new Department();
                departmentAdd.id = department.id;
                departmentAdd.name = String($("input[name=name]").val());
                departmentAdd.address = String($("input[name=address]").val());
                _this.departmentService.saveOrUpdateDepartment(departmentAdd).done(function () {
                    location.hash = "#departments";
                });
            }
        });
    };
    DepartmentForm.prototype.renderInputForm = function (formForm, data) {
        formForm.append("<div />").addClass("createOrUpdateForm");
        formForm.append($("<input />", { type: "hidden", name: "id", value: data.id, id: "id", class: "id" }));
        console.log(data);
        formForm.append($("<p />", { text: "Enter your name" }));
        formForm.append($("<input />", {
            value: data == null ? null : data.name,
            name: "name",
            type: "text",
            class: "input_param name",
            id: "name"
        }));
        formForm.append($("<p />", { text: "Enter your address" }));
        formForm.append($("<input />", { value: data == null ? null : data.address,
            name: "address",
            type: "text",
            class: "input_param address",
            id: "address" }));
        formForm.append($("<input />", { type: "submit", class: "submit_createOrUpdate", value: "submit" }));
        return formForm;
    };
    return DepartmentForm;
}());
export { DepartmentForm };
