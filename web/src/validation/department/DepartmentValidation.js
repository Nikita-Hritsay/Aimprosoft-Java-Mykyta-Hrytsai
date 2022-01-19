var DepartmentValidation = /** @class */ (function () {
    function DepartmentValidation() {
    }
    DepartmentValidation.prototype.validate = function (form) {
        return $(form).validate({
            rules: {
                name: {
                    required: true,
                    minlength: 2,
                    maxlength: 70,
                    remote: {
                        url: "/department/exists",
                        type: "GET",
                        contentType: 'application/json',
                        dataType: 'json',
                        dataFilter: function (data) {
                            if (!!data) {
                                var department = JSON.parse(data);
                                return department.id == $('#id').val();
                            }
                            return true;
                        }
                    }
                },
                address: {
                    required: true,
                    minlength: 2,
                    maxlength: 70
                }
            },
            messages: {
                name: {
                    required: "Enter name",
                    minlength: "Name should be longer than 2",
                    maxlength: "Name should be smaller than 70",
                    remote: "angular with such name alredy exists"
                },
                address: {
                    required: "Enter address",
                    minlength: "Name should be longer than 2",
                    maxlength: "Name should be smaller than 70"
                }
            }
        });
    };
    return DepartmentValidation;
}());
export { DepartmentValidation };
