var DisplayHeader = /** @class */ (function () {
    function DisplayHeader() {
    }
    DisplayHeader.prototype.createHeader = function (app) {
        var headers = $("<div />").addClass("header_refs");
        headers.append($("<a />", { text: "Home page", class: "header_ref", href: "#departments" }));
        headers.append($("<a />", { text: "All Employees", class: "header_ref", href: "#employees" }));
        headers.append($("<a />", { text: "Add angular", class: "header_ref", href: "#department/0" }));
        headers.append($("<a />", { text: "Add Employee", class: "header_ref", href: "#employee/0" }));
        app.append(headers);
    };
    return DisplayHeader;
}());
export { DisplayHeader };
