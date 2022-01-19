var DepartmentService = /** @class */ (function () {
    function DepartmentService() {
    }
    DepartmentService.prototype.getDepartments = function () {
        return $.ajax({
            type: "GET",
            url: "/department",
            dataType: "json"
        });
    };
    DepartmentService.prototype.deleteDepartment = function (id) {
        return $.ajax({
            url: '/department?' + $.param({ "idDepartment": id }),
            type: 'DELETE',
        });
    };
    DepartmentService.prototype.getDepartmentById = function (id) {
        return $.ajax({
            type: "GET",
            url: "/department/" + id,
            dataType: "json"
        });
    };
    DepartmentService.prototype.saveOrUpdateDepartment = function (department) {
        return $.ajax({
            contentType: 'application/json',
            data: JSON.stringify(department),
            dataType: 'json',
            success: function () {
                location.hash = "#departments";
            },
            error: function (error) {
                location.hash = "#departments";
            },
            processData: false,
            type: 'POST',
            url: '/department'
        });
    };
    return DepartmentService;
}());
export { DepartmentService };
