package com.aimprosoft.aimlearning.models;


import com.aimprosoft.aimlearning.validations.employee.IsUniqueEmail;
import lombok.With;
import net.sf.oval.constraint.*;

import javax.persistence.*;
import java.util.Date;

@With
public class Employee {

    private int id;

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
    private double salary;

    @NotEmpty(message = "Hire date can not be empty")
    @NotNull(message = "Hire date can not be null")
    private Date hireDate;

    @NotEmpty(message = "Id department can not be empty")
    @NotNull(message = "Id department can not be null")
    @Min(value = 1, message = "Id department can not be smaller than 1")
    @Column(name = "department_idDepartment")
    private int idDepartment;

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", salary=" + salary +
                ", hireDate='" + hireDate + '\'' +
                ", idDepartment=" + idDepartment +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public double getSalary() {
        return salary;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public int getIdDepartment() {
        return idDepartment;
    }

    public Employee() {
    }

    public Employee(String firstName, String lastName, String email, double salary, Date hireDate, int idDepartment) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.salary = salary;
        this.hireDate = hireDate;
        this.idDepartment = idDepartment;
    }

    public Employee(int id, String firstName, String lastName, String email, double salary, Date hireDate, int idDepartment) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.salary = salary;
        this.hireDate = hireDate;
        this.idDepartment = idDepartment;
    }

}
