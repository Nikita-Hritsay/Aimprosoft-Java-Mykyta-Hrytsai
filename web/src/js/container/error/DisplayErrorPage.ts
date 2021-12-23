import { ErrorPage } from "../../components/error/errorPage";
import {Container} from "../Container";

export class DisplayErrorPage implements Container{

    private errorPage = new ErrorPage();

    render(){
        this.errorPage.render();
    }

}