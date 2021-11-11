package com.aimprosoft.aimlearning.models;


import com.aimprosoft.aimlearning.validations.employee.IsUniqueEmail;
import lombok.*;
import net.sf.oval.constraint.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@With
@AllArgsConstructor(onConstructor = @__(@Autowired))
@NoArgsConstructor
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "idemployee")
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
    @Min(value = 1, message = "salary can not be smaller than 1")
    private BigDecimal salary;

    @NotEmpty(message = "Hire date can not be empty")
    @NotNull(message = "Hire date can not be null")
    @Type(type = "date")
    private Date hireDate;

    @NotEmpty(message = "Department can not be empty")
    @NotNull(message = "Department can not be null")
    @JoinColumn(name = "department_iddepartment")
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    private Department department;

}
