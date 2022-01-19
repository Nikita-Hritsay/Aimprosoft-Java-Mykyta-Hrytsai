import { EmployeeService } from "../../service/EmployeeService";
import { mainDiv } from "../../utils/Constants";
import { Formatter } from "../../utils/Formatter";
import "../main.css";
var EmployeeList = /** @class */ (function () {
    function EmployeeList() {
        this.employeeService = new EmployeeService();
    }
    EmployeeList.prototype.render = function (data) {
        if (data.length > 0) {
            var main = $(mainDiv);
            main.empty();
            var table = $("<table/>");
            var headerTable = $("<tr/>");
            headerTable.append($("<th/>", { text: "First name" }));
            headerTable.append($("<th/>", { text: "Last name" }));
            headerTable.append($("<th/>", { text: "Email" }));
            headerTable.append($("<th/>", { text: "Salary" }));
            headerTable.append($("<th/>", { text: "Hire Date" }));
            headerTable.append($("<th/>", { text: "angular name" }));
            headerTable.append($("<th/>", { text: "Update" }));
            headerTable.append($("<th/>", { text: "Delete" }));
            table.append(headerTable);
            this.renderTable(table, data);
            main.append(table);
        }
        else {
            var main = $(mainDiv);
            main.empty();
            main.append($("<div />", { class: "emptyClass" }).append($("<h3 />", { text: "There is no employees in this angular" })));
        }
    };
    EmployeeList.prototype.renderTable = function (table, data) {
        var _this = this;
        data.forEach(function (employee) {
            var tr = $("<tr/>");
            tr.append($("<td/>", { text: employee.firstName }));
            tr.append($("<td/>", { text: employee.lastName }));
            tr.append($("<td/>", { text: employee.email }));
            tr.append($("<td/>", { text: employee.salary }));
            tr.append($("<td/>", { text: Formatter.getDate(employee.hireDate) }));
            tr.append($("<td/>", { text: employee.department.name }));
            var updateButton = $("<button />", { text: "update", href: "#employee/" + employee.id }).addClass("update_button").on("click", function () {
                location.hash = "#employee/".concat(employee.id);
            });
            tr.append($("<td/>").append(updateButton));
            var deleteButton = $("<button />", { text: "delete" }).addClass("delete_button").addClass("submit_delete");
            tr.append($("<td/>").append(deleteButton.on("click", function () {
                _this.employeeService.deleteEmployee(employee.id).done(function () {
                    _this.employeeService.getEmployees().done(function (data) {
                        _this.render(data);
                    });
                });
            })));
            table.append(tr);
        });
    };
    return EmployeeList;
}());
export { EmployeeList };
