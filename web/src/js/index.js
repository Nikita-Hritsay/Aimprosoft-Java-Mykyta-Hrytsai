import { DisplayHeader } from "../components/header/DisplayHeader";
import { Router } from "../router/Router";
import { appDiv } from "../utils/Constants";
require('jquery-validation');
var Start = /** @class */ (function () {
    function Start() {
    }
    Start.start = function () {
        var router = new Router();
        window.addEventListener("load", function () {
            var app = $(appDiv);
            new DisplayHeader().createHeader(app);
            app.append($("<div >", { id: "main" }));
            router.getUrl(location.hash);
        });
        window.addEventListener('hashchange', function () {
            router.getUrl(location.hash);
        });
    };
    return Start;
}());
Start.start();
