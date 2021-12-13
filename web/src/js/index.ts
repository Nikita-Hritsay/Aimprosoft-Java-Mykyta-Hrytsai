import {Router} from "../router/Router";
import {Constants} from "../utils/Constants";
require('jquery-validation')

class Start{

    public static start(){
        const router = new Router();
        window.addEventListener("load", ()=>{
            const app = $(Constants.app);
            this.createHeader(app);
            app.append($("<div >", {id: "main"}))
            router.getUrl(location.hash)
        })
        window.addEventListener('hashchange', ()=>{
            router.getUrl(location.hash);
        });
    }

    private static createHeader(app: any): void{
        const headers = $("<div />").addClass("header_refs");
        headers.append($("<a />", {text: "Home page", class: "header_ref", href: "#departments"}))
        headers.append($("<a />", {text: "All Employees", class: "header_ref", href: "#employees"}))
        headers.append($("<a />", {text: "Add Department", class: "header_ref", href: "#department/0"}))
        headers.append($("<a />", {text: "Add Employee", class: "header_ref", href: "#employee/0"}))
        app.append(headers);
    }
}

Start.start()