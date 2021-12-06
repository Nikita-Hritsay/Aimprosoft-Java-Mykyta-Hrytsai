import { Department } from "../../models/Department";
import { ModelValidator } from "../ModelValidator";

export class DepartmentValidation implements ModelValidator{

    public validate(form: string): any{
        return $(form).validate({
            rules: {
                name:{
                    required: true,
                    minlength: 5,
                    maxlength: 70,
                    remote: {
                        url: `/department/exists`,
                        type: "GET",
                        contentType: 'application/json',
                        dataType: 'json',
                        dataFilter: (data: any) => {
                            console.log("id " + $('#id').val())
                            if (!!data) {
                                const department: Department = JSON.parse(data);
                                return department.id == $('#id').val();
                            }
                            return true;
                        }
                    }   
                },
                departmentAddress: {
                    required: true,
                    minlength: 5,
                    maxlength: 70
                }
            },
            messages: {
                name: {
                    required: "please enter name",
                    minlength: "name should be longer than 5",
                    remote: "department with such name alredy exists"
                }
            }
        });
    }

}