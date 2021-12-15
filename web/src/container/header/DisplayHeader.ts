import { Header } from "../../components/header/Header";

export class DisplayHeader {
    private header = new Header();

    render(){
        this.header.render();
    }
}