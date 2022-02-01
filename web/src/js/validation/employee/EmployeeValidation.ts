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
                idDepartment: {
                    required: true
                } 
            },
            messages: {
                firstName: {
                    required: "Enter name",
                    minlength: "Name should be longer than 3",
                    maxlength: "Name should be smaller than 20"
                },
                lastName:{
                    required: "Enter last name",
                    minlength: "Last Name should be longer than 3",
                    maxlength: "Last Name should be smaller than 20"
                },
                email: {
                    required: "Enter email",
                    minlength: "Email should be longer than 5",
                    maxlength: "Email should be smaller than 100",
                    remote: "Employee with such email already exists"
                },
                salary: {
                    required: "Enter salary",
                    min: "Salary should be bigger than 1",
                    max: "Salary should be smaller than 100000"
                },
                hireDate: {
                    required: "Enter hire date",
                },
                idDepartment: {
                    required: "Enter departmentId",
                }
            }
        });
    }

}