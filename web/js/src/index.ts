import {Router} from "./router/Router";

class Start{
    public static start(){
        window.addEventListener("load", ()=>{
            new Router().getUrl("department").render("start");
        })
    }
}

Start.start()