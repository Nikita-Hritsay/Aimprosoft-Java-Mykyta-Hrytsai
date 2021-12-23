import {mainDiv} from "../../utils/Constants";

export class DataNotFoundPage{

    render(){
        const main = $(mainDiv).empty();
        main.append($("<div />", {class: "errorPage"}).append($("<h3 />", {text: "There is no such data"})))
    }

}