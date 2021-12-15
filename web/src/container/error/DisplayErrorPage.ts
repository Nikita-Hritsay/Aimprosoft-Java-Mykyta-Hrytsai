import { ErrorPage } from "../../components/error/errorPage";

export class DisplayErrorPage{

    private errorPage = new ErrorPage();

    render(){
        this.errorPage.render();
    }

}