export class RequestUtils {
  static getNumber(str: string): number {
    return Number.parseInt(str);
  }

  static getDate(date: string): string{
    return new Date(date).toISOString().slice(0, 10);
  }

}
