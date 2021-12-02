
export class Formatter {

    public static getDate(date: string): string{
        return new Date(date).toISOString().slice(0, 10);
    }

    public static parseUrl(url: string): any{
        return  Number(url.split("=")[1]);
    }

}