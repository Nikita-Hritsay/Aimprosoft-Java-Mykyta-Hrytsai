import { mainDiv } from "../../utils/Constants";
var ErrorPage = /** @class */ (function () {
    function ErrorPage() {
    }
    ErrorPage.prototype.render = function () {
        var main = $(mainDiv);
        main.empty();
        main.append($("<div />", { class: "errorPage" }).append($("<h3 />", { text: "There is no such page" })));
    };
    return ErrorPage;
}());
export { ErrorPage };
