import { ModelValidator } from "../ModelValidator";

export class EmployeeValidation implements ModelValidator{

    public validate(form: string): any{
        console.log($(form).toArray());
        console.log('validation  ' + $(form).validate());
        return $(form).validate({
            rules: {
                firstName: {
                    required: true
                },
                lastName: {
                    required: true
                },
                email: {
                    required: true
                },
                salary: {
                    required: true
                },
                hireDate: {
                    required: true
                },
                departmentId: {
                    required: true
                } 
            }
        });
    }

}