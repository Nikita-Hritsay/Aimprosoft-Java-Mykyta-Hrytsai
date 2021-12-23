import { DisplayHeader } from "./container/header/DisplayHeader";
import {Router} from "./router/Router";
import {appDiv} from "./utils/Constants";
require('jquery-validation')

class Start{



    public static start(){
        const router = new Router();
        window.addEventListener("load", ()=>{
            const app = $(appDiv);
            const displayHeader = new DisplayHeader();
            displayHeader.render();
            app.append($("<div >", {id: "main"}))
            router.renderPage(location.hash)
        })
        window.addEventListener('hashchange', ()=>{
            router.renderPage(location.hash);
        });
    }
}

Start.start()