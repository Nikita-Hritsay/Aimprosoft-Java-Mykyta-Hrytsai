var Formatter = /** @class */ (function () {
    function Formatter() {
    }
    Formatter.getDate = function (date) {
        return new Date(date).toISOString().slice(0, 10);
    };
    Formatter.parseUrl = function (url) {
        return Number(url.split("/")[1]);
    };
    Formatter.getUrl = function (url) {
        var res = url.split("?")[0].split("/");
        return res[2] == undefined ? res[0] : res[0] + "/" + res[2];
    };
    Formatter.getIdDepartment = function (url) {
        var res = Number(url.split("/")[3]);
        return isNaN(res) ? null : res;
    };
    return Formatter;
}());
export { Formatter };
