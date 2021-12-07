import { Employee } from "../../models/Employee";
import { ModelValidator } from "../ModelValidator";

export class EmployeeValidation implements ModelValidator{

    public validate(form: string): any{
        return $(form).validate({
            rules: {
                firstName: {
                    required: true,
                    minlength: 3,
                    maxlength: 20
                },
                lastName: {
                    required: true,
                    minlength: 3,
                    maxlength: 20
                },
                email: {
                    required: true,
                    minlength: 5,
                    maxlength: 100,
                    remote: {
                        url: `/employee/exists`,
                        type: "GET",
                        contentType: 'application/json',
                        dataType: 'json',
                        dataFilter: (data: any) => {
                            if (!!data) {
                                const employee: Employee = JSON.parse(data);
                                return employee.id == $('#id').val();
                            }
                            return true;
                        }
                    }   
                },
                salary: {
                    required: true,
                    min: 1,
                    max: 100000
                },
                hireDate: {
                    required: true
                },
                departmentId: {
                    required: true
                } 
            },
            messages: {
                firstName: {
                    required: "Enter your name please",
                    minlength: "Name should be longer than 3",
                    maxlength: "Name should be smaller than 20"
                },
                lastName:{
                    required: "Enter your last name please",
                    minlength: "Last Name should be longer than 3",
                    maxlength: "Last Name should be smaller than 20"
                },
                email: {
                    required: "Enter your email please",
                    minlength: "Email should be longer than 5",
                    maxlength: "Email should be smaller than 100",
                    remote: "Employee with such email already exists"
                },
                salary: {
                    required: "Enter your salary please",
                    min: "Salary should be bigger than 1",
                    max: "Salary should be smaller than 100000"
                },
                hireDate: {
                    required: "Enter your hire date please",
                },
                departmentId: {
                    required: "Enter your departmentId please",
                }
            }
        });
    }

}