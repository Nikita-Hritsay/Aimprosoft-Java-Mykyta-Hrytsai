import { Header } from "../../components/header/Header";
import {Container} from "../Container";

export class DisplayHeader implements Container{
    private header = new Header();

    render(){
        this.header.render();
    }
}