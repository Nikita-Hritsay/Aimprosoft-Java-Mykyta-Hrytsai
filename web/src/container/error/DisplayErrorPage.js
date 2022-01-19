import { ErrorPage } from "../../components/error/errorPage";
var DisplayErrorPage = /** @class */ (function () {
    function DisplayErrorPage() {
        this.errorPage = new ErrorPage();
    }
    DisplayErrorPage.prototype.render = function () {
        this.errorPage.render();
    };
    return DisplayErrorPage;
}());
export { DisplayErrorPage };
