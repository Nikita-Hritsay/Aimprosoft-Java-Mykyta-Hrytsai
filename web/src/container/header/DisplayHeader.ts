import { Header } from "../../components/header/Header";

export class DisplayHeader {
    header = new Header();

    render(){
        this.header.render();
    }
}