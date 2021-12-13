import { ErrorPage } from "../../components/error/errorPage";

export class DisplayErrorPage{

    errorPage = new ErrorPage();

    render(){
        this.errorPage.render();
    }

}