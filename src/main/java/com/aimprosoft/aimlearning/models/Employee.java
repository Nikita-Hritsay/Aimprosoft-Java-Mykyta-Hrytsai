package com.aimprosoft.aimlearning.models;


import com.aimprosoft.aimlearning.validations.employee.IsUniqueEmail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import net.sf.oval.constraint.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@With
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
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
    @ManyToOne(fetch = FetchType.EAGER)
    private Department department;

}
