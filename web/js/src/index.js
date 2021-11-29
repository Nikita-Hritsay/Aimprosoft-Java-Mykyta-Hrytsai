import {Router} from "./router/Router";

function main(){
    new Router().getUrl("department").render();
}

main()