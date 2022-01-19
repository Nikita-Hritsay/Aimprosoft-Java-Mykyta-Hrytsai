import { DepartmentService } from "../../service/DepartmentService";
import { mainDiv } from "../../utils/Constants";
import "../main.css";
var DepartmentList = /** @class */ (function () {
    function DepartmentList() {
        this.departmentService = new DepartmentService();
    }
    DepartmentList.prototype.render = function (departments) {
        var listDiv = $(mainDiv).empty();
        var table = $("<table/>");
        var headerTable = $("<tr/>");
        headerTable.append($("<th/>", { text: "Adress" }));
        headerTable.append($("<th/>", { text: "Name" }));
        headerTable.append($("<th/>", { text: "List" }));
        headerTable.append($("<th/>", { text: "Update" }));
        headerTable.append($("<th/>", { text: "Delete" }));
        table.append(headerTable);
        this.renderTable(table, departments);
        listDiv.append(table);
    };
    DepartmentList.prototype.renderTable = function (table, data) {
        var _this = this;
        data.forEach(function (department) {
            var tr = $("<tr/>");
            tr.append($("<td/>", { text: department.name }));
            tr.append($("<td/>", { text: department.address }));
            var listButton = $("<button />", { text: "Employees", href: "#department/".concat(department.id, "/employee") }).addClass("update_button").on("click", function () {
                location.hash = "#department/".concat(department.id, "/employee");
            });
            tr.append($("<td/>").append(listButton));
            var updateButton = $("<button />", { text: "update", href: "#department/".concat(department.id) }).addClass("update_button").addClass("submit_delete").on("click", function () {
                location.hash = "#department/".concat(department.id);
            });
            tr.append($("<td/>").append(updateButton));
            var deleteButton = $("<button />", { text: "delete" }).addClass("delete_button").addClass("submit_delete");
            tr.append($("<td/>").append(deleteButton.on("click", function () {
                _this.departmentService.deleteDepartment(department.id).done(function () {
                    _this.departmentService.getDepartments().done(function (data) {
                        _this.render(data);
                    });
                });
            })));
            table.append(tr);
        });
    };
    return DepartmentList;
}());
export { DepartmentList };
