export class Formatter {

    public static getDate(date: Date): string {
        return new Date(date).toISOString().slice(0, 10);
    }

    public static getParams(url: string): Map<string, number> {
        const result = url.replace(/[#\/a-zA-z]+/g, '_')
            .replace(/^_|_$/, '').split("_");
        const resultMap = new Map<string, number>();
        if (!!result[0]) {
            resultMap.set("departmentId", Number(result[0]));
        }
        if (!!result[1]) {
            resultMap.set("employeeId", Number(result[1]));
        }
        return resultMap;
    }

    public static getRoute(url: string): string {
        return url.replace(/\/\d+/g, "/{id}");
    }

}