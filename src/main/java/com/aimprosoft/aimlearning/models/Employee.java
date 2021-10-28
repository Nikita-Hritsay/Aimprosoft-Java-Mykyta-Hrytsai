package com.aimprosoft.aimlearning.models;


import com.aimprosoft.aimlearning.validations.employee.IsUniqueEmail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import net.sf.oval.constraint.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@With
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    private Integer id;

    @NotEmpty(message = "Firstname can not be empty")
    @NotNull(message = "Firstname can not be null")
    @Length(max = 30, message = "Firstname can not be greater than 30")
    private String firstName;

    @NotEmpty(message = "Lastname can not be empty")
    @NotNull(message = "Lastname can not be null")
    @Length(max = 30, message = "Lastname can not be greater than 30")
    private String lastName;

    @NotEmpty(message = "email can not be empty")
    @NotNull(message = "email can not be null")
    @Length(max = 75, message = "Firstname can not be greater than 30")
    @CheckWith(value = IsUniqueEmail.class, message = "employee with such email exists")
    @Email(message = "wrong email format")
    private String email;

    @NotEmpty(message = "Salary can not be empty")
    @NotNull(message = "Salary can not be null")
    @Min(value = 0.1, message = "salary can not be smaller than 0.1")
    private BigDecimal salary;

    @NotEmpty(message = "Hire date can not be empty")
    @NotNull(message = "Hire date can not be null")
    private Date hireDate;

    @NotEmpty(message = "Id department can not be empty")
    @NotNull(message = "Id department can not be null")
    @Min(value = 1, message = "Id department can not be null")
    @Column(name = "department_idDepartment")
    private Integer idDepartment;

}
