import { DisplayHeader } from "../container/header/DisplayHeader";
import {Router} from "../router/Router";
import {appDiv} from "../utils/Constants";
require('jquery-validation')

class Start{

    public static start(){
        const router = new Router();
        window.addEventListener("load", ()=>{
            const app = $(appDiv);
            new DisplayHeader().render();
            app.append($("<div >", {id: "main"}))
            router.getUrl(location.hash)
        })
        window.addEventListener('hashchange', ()=>{
            router.getUrl(location.hash);
        });
    }
}

Start.start()