import { Constants } from "../../utils/Constants";

export class ErrorPage {

    render(){
        const main = $(Constants.main);
        main.empty();
        main.append($("<div />", {class: "errorPage"}).append($("<h3 />", {text: "There is no such page"})))
    }

}