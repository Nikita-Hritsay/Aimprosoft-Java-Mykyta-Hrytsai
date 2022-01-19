var EmployeeService = /** @class */ (function () {
    function EmployeeService() {
    }
    EmployeeService.prototype.getEmployees = function () {
        return $.ajax({
            type: "GET",
            url: "/employee",
            dataType: "json"
        });
    };
    EmployeeService.prototype.deleteEmployee = function (id) {
        return $.ajax({
            url: '/employee?' + $.param({ "id": id }),
            type: 'DELETE',
        });
    };
    EmployeeService.prototype.getById = function (id) {
        return $.ajax({
            type: "GET",
            url: "/employee/" + id,
            dataType: "json"
        });
    };
    EmployeeService.prototype.getByDepartment = function (id) {
        return $.ajax({
            type: "GET",
            url: "/employee/department/" + id,
            dataType: "json"
        });
    };
    EmployeeService.prototype.saveOrUpdateEmployee = function (employee) {
        return $.ajax({
            contentType: 'application/json',
            data: JSON.stringify(employee),
            dataType: 'json',
            success: function () {
                location.hash = "#departments";
            },
            error: function (err) {
                location.hash = "#department/" + employee.department.id + "/employee";
            },
            processData: false,
            type: 'POST',
            url: '/employee'
        });
    };
    return EmployeeService;
}());
export { EmployeeService };
