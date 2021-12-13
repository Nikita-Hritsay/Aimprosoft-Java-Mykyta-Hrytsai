
export class Formatter {

    public static getDate(date: string): string{
        return new Date(date).toISOString().slice(0, 10);
    }

    public static parseUrl(url: string): any{
        return  Number(url.split("/")[1]);
    }

    public static getUrl(url: string): string{
        const res =  url.split("?")[0].split("/");
        return res[2] == undefined ? res[0] : res[0] + "/" + res[2];
    }

    public static getIdDepartment(url: string): number{
        const res =  Number(url.split("/")[3])
        return  isNaN(res) ? null : res;
    }

}