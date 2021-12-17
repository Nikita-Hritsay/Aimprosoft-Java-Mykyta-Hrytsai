
export class Formatter {

    public static getDate(date: string): string{
        return new Date(date).toISOString().slice(0, 10);
    }

    public static getParams(url: string): any{
        const result = url.replace(/[#\/a-zA-z]+/g, '_')
            .replace(/^_|_$/, '');
        return result.split('_');
    }

    public static get(url: string): any{
        return url.replace(/\/\d+/g, "/{id}");
    }

}